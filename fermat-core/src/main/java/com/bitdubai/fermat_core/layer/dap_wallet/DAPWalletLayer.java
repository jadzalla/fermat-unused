package com.bitdubai.fermat_core.layer.dap_wallet;

import com.bitdubai.fermat_api.Plugin;
import com.bitdubai.fermat_api.layer.CantStartLayerException;
import com.bitdubai.fermat_api.layer.PlatformLayer;
import com.bitdubai.fermat_core.layer.dap_wallet.asset_issuer.AssetWalletIssuerSubsytem;
import com.bitdubai.fermat_dap_api.layer.dap_wallet.CantStartSubsystemException;
import com.bitdubai.fermat_dap_api.layer.dap_wallet.DAPAssetWalletSubsystem;

/**
 * Created by franklin on 27/09/15.
 */
public class DAPWalletLayer implements PlatformLayer {
    private Plugin assetIssuer;

    @Override
    public void start() throws CantStartLayerException {
        assetIssuer = getPlugin(new AssetWalletIssuerSubsytem());
    }

    private Plugin getPlugin(DAPAssetWalletSubsystem dapAssetWalletSubsystem) throws CantStartLayerException{
        try{
            dapAssetWalletSubsystem.start();
            return dapAssetWalletSubsystem.getPlugin();
        }catch(CantStartSubsystemException e){
            throw new CantStartLayerException();

        }
    }

    public Plugin getPluginAssetWalletIssuer(){
        return  assetIssuer;
    }
}
