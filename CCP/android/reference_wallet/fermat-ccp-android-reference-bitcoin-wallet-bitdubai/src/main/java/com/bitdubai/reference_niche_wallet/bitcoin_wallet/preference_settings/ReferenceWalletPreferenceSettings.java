package com.bitdubai.reference_niche_wallet.bitcoin_wallet.preference_settings;

import com.bitdubai.fermat_api.layer.dmp_basic_wallet.common.enums.BalanceType;
import com.bitdubai.fermat_wpd_api.layer.wpd_middleware.wallet_settings.PreferenceWalletSettings;

/**
 * Created by Matias Furszyfer on 2015.08.24..
 */
public class ReferenceWalletPreferenceSettings extends PreferenceWalletSettings{

    private int transactionToShow;

    BalanceType balanceType;

    public ReferenceWalletPreferenceSettings(){
        transactionToShow=10;
    }

    public BalanceType getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(BalanceType balanceType) {
        this.balanceType = balanceType;
    }

    public int getTransactionsToShow() {
        return transactionToShow;
    }
}
