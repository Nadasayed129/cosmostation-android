package wannabit.io.cosmostaion.fragment.txs.authz.grantee

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import cosmos.authz.v1beta1.Authz.GrantAuthorization
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.txs.authz.AuthzRevokeActivity
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.databinding.FragmentAuthzGranterBinding
import wannabit.io.cosmostaion.model.NetworkResult
import wannabit.io.cosmostaion.model.viewModel.authz.AuthzViewModel
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.makeToast
import java.io.Serializable
import java.util.*
import kotlin.collections.ArrayList

class AuthzGranterFragment : BaseFragment() {

    private var _binding: FragmentAuthzGranterBinding? = null
    private val binding get() = _binding!!

    private val authzViewModel: AuthzViewModel by activityViewModels()
    private lateinit var authzGranterAdapter: AuthzGranterAdapter

    private var selectedItems = mutableListOf<GrantAuthorization>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthzGranterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        baseActivity.onShowWaitDialog()
        binding.emptyRoot.setCardBackgroundColor(ContextCompat.getColor(requireActivity(), baseActivity.mChainConfig.chainBgColor()))
        initRecyclerView()

        authzViewModel.granterData(baseActivity.mChainConfig, baseActivity.mAccount.address)
        loadDataObserve()
        onStartRevoke()
    }

    private fun initRecyclerView() {
        authzGranterAdapter = AuthzGranterAdapter(selectedItems)
        binding.recycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = authzGranterAdapter
        }
    }

    private fun loadDataObserve() {
        authzViewModel.authzGranterData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    baseActivity.onHideWaitDialog()
                    val data = mutableListOf<GrantAuthorization>()

                    if (response.data.size > 0) {
                        binding.recycler.visibility = View.VISIBLE
                        binding.emptyRoot.visibility = View.GONE
                    } else {
                        binding.recycler.visibility = View.GONE
                        binding.emptyRoot.visibility = View.VISIBLE
                    }

                    val now = Calendar.getInstance().timeInMillis
                    response.data.forEach { grant ->
                        if (grant.expiration.seconds * 1000 >= now) {
                            data.add(grant)
                        }
                    }
                    authzGranterAdapter.submitList(data)
                }

                is NetworkResult.Error -> {
                    baseActivity.onHideWaitDialog()
                    requireContext().makeToast(response.errorMessage)
                }
            }
        }
    }

    private fun onStartRevoke() {
        binding.btnRevoke.setOnClickListener {
            baseActivity.apply {
                if (!mAccount.hasPrivateKey && !mAccount.isLedger) {
                    onInsertKeyDialog()
                    return@setOnClickListener
                }

                if (!WDp.isTxFeePayable(requireContext(), baseDao, mChainConfig)) {
                    makeToast(R.string.error_not_enough_fee)
                    return@setOnClickListener
                }

                if (selectedItems.size <= 0) {
                    requireContext().makeToast(R.string.str_authz_no_revoke_msg)
                    return@setOnClickListener
                } else {
                    Intent(requireContext(), AuthzRevokeActivity::class.java).apply {
                        putExtra("selectedItems", ArrayList(selectedItems))
                        startActivity(this)
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}