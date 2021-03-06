package com.bitdubai.fermat_core.layer.bch_crypto_network.bitcoin;


import com.bitdubai.fermat_api.Plugin;
import com.bitdubai.fermat_bch_api.layer.crypto_network.CantStartSubsystemException;
import com.bitdubai.fermat_bch_api.layer.crypto_network.CryptoNetworkSubsystem;
import com.bitdubai.fermat_bch_plugin.layer.crypto_network.bitcoin.developer.bitdubai.DeveloperBitDubai;

/**
 * Created by rodrigo on 9/30/15.
 */
public class BitcoinSubsystem implements CryptoNetworkSubsystem {

    Plugin plugin;

    @Override
    public Plugin getPlugin() {
        return plugin;
    }





    @Override
    public void start() throws CantStartSubsystemException {

        /**
         * I will choose from the different Developers available which implementation to use. Right now there is only
         * one, so it is not difficult to choose.
         */

        try {
            DeveloperBitDubai developerBitDubai = new DeveloperBitDubai();
            plugin = developerBitDubai.getPlugin();
        }
        catch (Exception e)
        {
            System.err.println("Exception: " + e.getMessage());
            throw new CantStartSubsystemException();
        }
    }
}
