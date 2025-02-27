package wannabit.io.cosmostaion.fragment.txs.liquidstaking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.liquidstaking.StrideLiquidActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class StrideLiquidStep3Fragment extends BaseFragment implements View.OnClickListener{

    private TextView        mFeeAmount;
    private TextView        mFeeAmountSymbol;
    private TextView        mLSInAmount, mLSInAmountSymbol;
    private TextView        mLSOutAmount, mLSOutAmountSymbol;
    private LinearLayout    mLSRecipientLayer;
    private TextView        mLSRecipient;
    private TextView        mMemo;

    private Button          mBeforeBtn, mConfirmBtn;

    public static StrideLiquidStep3Fragment newInstance() {
        return new StrideLiquidStep3Fragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_liquid_step3, container, false);
        mFeeAmount              = rootView.findViewById(R.id.ls_fee_amount);
        mFeeAmountSymbol        = rootView.findViewById(R.id.ls_fee_amount_symbol);
        mLSInAmount             = rootView.findViewById(R.id.ls_in_amount);
        mLSInAmountSymbol       = rootView.findViewById(R.id.ls_in_amount_symbol);
        mLSOutAmount            = rootView.findViewById(R.id.ls_out_amount);
        mLSOutAmountSymbol      = rootView.findViewById(R.id.ls_out_amount_symbol);
        mLSRecipientLayer       = rootView.findViewById(R.id.ls_recipient_address_layer);
        mLSRecipient            = rootView.findViewById(R.id.ls_recipient_address);
        mMemo                   = rootView.findViewById(R.id.memo);
        mBeforeBtn              = rootView.findViewById(R.id.btn_before);
        mConfirmBtn             = rootView.findViewById(R.id.btn_confirm);

        mBeforeBtn.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        WDp.setDpCoin(getSActivity(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mTxFee.amount.get(0), mFeeAmountSymbol, mFeeAmount);

        WDp.setDpCoin(getContext(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mSwapInCoin, mLSInAmountSymbol, mLSInAmount);
        WDp.setDpCoin(getContext(), getBaseDao(), getSActivity().mChainConfig, getSActivity().mSwapOutCoin, mLSOutAmountSymbol, mLSOutAmount);
        if (getSActivity().mTxType == BaseConstant.CONST_PW_TX_STRIDE_LIQUID_STAKING) {
            mLSRecipientLayer.setVisibility(View.GONE);
        } else {
            mLSRecipientLayer.setVisibility(View.VISIBLE);
            mLSRecipient.setText(getSActivity().mToAddress);
        }
        mMemo.setText(getSActivity().mTxMemo);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBeforeBtn)) {
            getSActivity().onBeforeStep();

        } else if (v.equals(mConfirmBtn)) {
            getSActivity().onStartLiquidStaking();
        }
    }

    private StrideLiquidActivity getSActivity() {
        return (StrideLiquidActivity)getBaseActivity();
    }
}
