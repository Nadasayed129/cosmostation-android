package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Juno extends ChainConfig{

    public BaseChain baseChain() { return BaseChain.JUNO_MAIN; }
    public int chainImg() { return R.drawable.chain_juno; }
    public int chainInfoImg() { return R.drawable.infoicon_juno; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_juno; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_juno; }
    public int chainColor() { return R.color.color_juno; }
    public int chainBgColor() { return R.color.colorTransBgJuno; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_juno; }
    public String chainName() { return "juno"; }
    public String chainKoreanName() { return "주노"; }
    public String chainIdPrefix() { return "juno-"; }

    public int mainDenomImg() { return R.drawable.token_juno; }
    public String mainDenom() { return "ujuno"; }
    public String addressPrefix() { return "juno"; }

    public boolean erc20CoinSupport() { return true; }
    public boolean dexSupport() { return false; }
    public boolean wasmSupport() { return true; }
    public boolean wcSupport() { return false; }
    public boolean authzSupport() { return true; }
    public boolean kadoMoneySupport() { return true; }


    public String grpcUrl() { return "grpc-juno.cosmostation.io"; }

    public String explorerUrl() { return EXPLORER_BASE_URL + "juno/"; }
    public String homeInfoLink() { return  "https://junochain.com"; }
    public String blogInfoLink() { return  "https://medium.com/@JunoNetwork"; }
    public String coingeckoLink() { return  COINGECKO_URL + "juno-network"; }
}
