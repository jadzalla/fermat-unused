package com.bitdubai.android_core.app.common.version_1.Sessions;

import com.bitdubai.fermat_android_api.layer.definition.wallet.interfaces.WalletSession;

import com.bitdubai.fermat_wpd_api.layer.wpd_middleware.wallet_settings.interfaces.WalletSettings;
import com.bitdubai.fermat_api.layer.dmp_module.wallet_manager.InstalledWallet;
import com.bitdubai.fermat_wpd_api.layer.wpd_network_service.wallet_resources.interfaces.WalletResourcesProviderManager;
import com.bitdubai.fermat_api.layer.dmp_wallet_module.crypto_wallet.interfaces.CryptoWalletManager;
import com.bitdubai.fermat_cbp_api.layer.cbp_wallet_module.crypto_broker.interfaces.CryptoBrokerWalletModuleManager;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.error_manager.ErrorManager;
import com.bitdubai.reference_niche_wallet.bitcoin_wallet.session.ReferenceWalletSession;
import com.bitdubai.reference_wallet.crypto_broker_wallet.session.CryptoBrokerWalletSession;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Matias Furszyfer on 2015.07.20..
 */

public class WalletSessionManager implements com.bitdubai.fermat_android_api.layer.definition.wallet.interfaces.WalletSessionManager{

    private Map<String,WalletSession> lstWalletSession;

    public WalletSessionManager(){
        lstWalletSession= new HashMap<String,WalletSession>();
    }

    @Override
    public Map<String,WalletSession> listOpenWallets() {
        return lstWalletSession;
    }


    @Override
    public WalletSession openWalletSession(InstalledWallet installedWallet,CryptoWalletManager cryptoWalletManager,WalletSettings walletSettings,WalletResourcesProviderManager walletResourcesProviderManager,ErrorManager errorManager,CryptoBrokerWalletModuleManager cryptoBrokerWalletModuleManager) {
        WalletSession walletSession = null;
        if(installedWallet!=null){
            switch (installedWallet.getWalletCategory()){
                case REFERENCE_WALLET:
                    switch (installedWallet.getWalletPublicKey()){
                        case "reference_wallet":
                            walletSession= new ReferenceWalletSession(installedWallet,cryptoWalletManager,walletSettings,walletResourcesProviderManager,errorManager);
                            lstWalletSession.put(installedWallet.getWalletPublicKey(),walletSession);
                            return walletSession;
                        case "test_wallet":
                            walletSession= new com.bitdubai.fermat_dmp_android_clone_reference_nich_wallet.session.ReferenceWalletSession(installedWallet,cryptoWalletManager,walletSettings,walletResourcesProviderManager,errorManager);
                            //lstWalletSession.put(installedWallet.getWalletPublicKey(),walletSession);
                            return walletSession;
                        case "crypto_broker_wallet":
                            walletSession = new CryptoBrokerWalletSession(installedWallet,errorManager,cryptoBrokerWalletModuleManager);
                            return walletSession;
                    }

                case NICHE_WALLET:
                    break;
                case BRANDED_NICHE_WALLET:
                    break;
                case BRANDED_REFERENCE_WALLET:
                    break;
                default:
                    walletSession= new com.bitdubai.fermat_dmp_android_clone_reference_nich_wallet.session.ReferenceWalletSession(installedWallet,cryptoWalletManager,walletSettings,walletResourcesProviderManager,errorManager);
                    break;
            }
        }else{
            walletSession= new ReferenceWalletSession(installedWallet,cryptoWalletManager,walletSettings,walletResourcesProviderManager,errorManager);
        }

        walletSession= new com.bitdubai.fermat_dmp_android_clone_reference_nich_wallet.session.ReferenceWalletSession(installedWallet,cryptoWalletManager,walletSettings,walletResourcesProviderManager,errorManager);

        return walletSession;
    }



    @Override
    public boolean closeWalletSession(String publicKey) {
        try {
            lstWalletSession.remove(publicKey);
        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean isWalletOpen(String publicKey) {
        return lstWalletSession.containsKey(publicKey);
    }

    @Override
    public WalletSession getWalletSession(String publicKey) {
        return lstWalletSession.get(publicKey);
    }

}
