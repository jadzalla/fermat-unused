package com.bitdubai.fermat_dmp_plugin.layer.middleware.wallet_skin.developer.bitdubai;

import com.bitdubai.fermat_api.Plugin;
import com.bitdubai.fermat_api.PluginDeveloper;
import com.bitdubai.fermat_api.layer.all_definition.enums.CryptoCurrency;
import com.bitdubai.fermat_api.layer.all_definition.enums.TimeFrequency;
import com.bitdubai.fermat_api.layer.all_definition.license.PluginLicensor;
import com.bitdubai.fermat_dmp_plugin.layer.middleware.wallet_skin.developer.bitdubai.version_1.WalletSkinMiddlewarePluginRoot;

/**
 * I will choose from the different versions of my implementations which one to start. Now there is only one, so
 * it is easy to choose.
 *
 * Created by Leon Acosta - (laion.cj91@gmail.com) on 29/07/15.
 *
 * @version 1.0
 * @since Java JDK 1.7
 */
public class DeveloperBitDubai implements PluginDeveloper, PluginLicensor {

    Plugin plugin;

    @Override
    public Plugin getPlugin() {
        return plugin;
    }

    public DeveloperBitDubai() {
        plugin = new WalletSkinMiddlewarePluginRoot();
    }

    @Override
    public int getAmountToPay() {
        return 100;
    }

    @Override
    public CryptoCurrency getCryptoCurrency() {
        return CryptoCurrency.BITCOIN;
    }

    @Override
    public String getAddress() {
        return "13gpMizSNvQCbJzAPyGCUnfUGqFD8ryzcv";
    }

    @Override
    public TimeFrequency getTimePeriod() {
        return TimeFrequency.MONTHLY;
    }

}
