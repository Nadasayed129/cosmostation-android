package wannabit.io.cosmostaion.base.chains;

import static wannabit.io.cosmostaion.base.BaseConstant.COINGECKO_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BASE_URL;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import org.bitcoinj.crypto.ChildNumber;

import java.util.ArrayList;
import java.util.List;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;

public class Persistence extends ChainConfig {

    public BaseChain baseChain() { return BaseChain.PERSIS_MAIN; }
    public int chainImg() { return R.drawable.chain_persistence; }
    public int chainInfoImg() { return R.drawable.infoicon_persistence; }
    public int chainInfoTitle() { return R.string.str_front_guide_title_persistence; }
    public int chainInfoMsg() { return R.string.str_front_guide_msg_persistence; }
    public int chainColor() { return R.color.color_persistence; }
    public int chainBgColor() { return R.color.colorTransBgPersistence; }
    public int chainTabColor() { return R.color.color_tab_myvalidator_persistence; }
    public String chainName() { return "persistence"; }
    public String chainKoreanName() { return "퍼시스턴스"; }
    public String chainIdPrefix() { return "core-"; }

    public int mainDenomImg() { return R.drawable.token_persistence; }
    public String mainDenom() { return "uxprt"; }
    public int sendImgColor() { return chainColor(); }
    public int sendBgColor() { return R.color.colorBlack2; }
    public String addressPrefix() { return "persistence"; }

    public boolean dexSupport() { return true; }
    public boolean wcSupport() { return false; }
    public boolean authzSupport() { return true; }

    public String grpcUrl() { return "grpc-persistence.cosmostation.io"; }

    public String explorerUrl() { return EXPLORER_BASE_URL + "persistence/"; }
    public String homeInfoLink() { return  "https://persistence.one"; }
    public String blogInfoLink() { return  "https://medium.com/persistence-blog"; }
    public String coingeckoLink() { return  COINGECKO_URL + "persistence"; }

    public String defaultPath() { return "m/44'/118'/0'/0/X"; }

    public List<ChildNumber> setParentPath(int customPath) {
        if (customPath == 0) {
            return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(118, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
        } else {
            return ImmutableList.of(new ChildNumber(44, true), new ChildNumber(750, true), ChildNumber.ZERO_HARDENED, ChildNumber.ZERO);
        }
    }

    public ArrayList<String> supportHdPaths() {
        return Lists.newArrayList(defaultPath(), "m/44'/750'/0'/0/X");
    }
}
