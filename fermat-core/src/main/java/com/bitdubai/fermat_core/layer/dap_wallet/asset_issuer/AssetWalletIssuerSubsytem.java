package com.bitdubai.fermat_core.layer.dap_wallet.asset_issuer;

import com.bitdubai.fermat_api.FermatException;
import com.bitdubai.fermat_api.Plugin;
import com.bitdubai.fermat_dap_api.layer.dap_wallet.CantStartSubsystemException;
import com.bitdubai.fermat_dap_api.layer.dap_wallet.DAPAssetWalletSubsystem;
import com.bitdubai.fermat_dap_plugin.layer.wallet.asset.issuer.developer.bitdubai.DeveloperBitDubai;

/**
 * Created by franklin on 27/09/15.
 */
public class AssetWalletIssuerSubsytem implements DAPAssetWalletSubsystem {
    Plugin plugin;
    @Override
    public void start() throws CantStartSubsystemException {
        try{
            DeveloperBitDubai developerBitDubai = new DeveloperBitDubai();
            plugin = developerBitDubai.getPlugin();
        }catch(Exception exception){
            throw new CantStartSubsystemException(FermatException.wrapException(exception),"AssetWalletIssuerSubsystem","Unexpected Exception");
        }
    }

    @Override
    public Plugin getPlugin() {
        return plugin;
    }
}
