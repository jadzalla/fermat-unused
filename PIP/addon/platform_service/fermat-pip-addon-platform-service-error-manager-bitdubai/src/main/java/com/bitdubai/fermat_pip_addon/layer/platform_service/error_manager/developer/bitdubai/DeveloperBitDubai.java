package com.bitdubai.fermat_pip_addon.layer.platform_service.error_manager.developer.bitdubai;

import com.bitdubai.fermat_api.Addon;
import com.bitdubai.fermat_api.AddonDeveloper;
import com.bitdubai.fermat_pip_addon.layer.platform_service.error_manager.developer.bitdubai.version_1.ErrorManagerPlatformServiceAddonRoot;

/**
 * Created by ciencias on 05.02.15.
 */
public class DeveloperBitDubai implements AddonDeveloper {

    Addon addon;





    @Override
    public Addon getAddon() {
        return addon;
    }





    public DeveloperBitDubai () {

        /**
         * I will choose from the different versions of my implementations which one to start. Now there is only one, so
         * it is easy to choose.
         */

        addon = new ErrorManagerPlatformServiceAddonRoot();

    }
}
