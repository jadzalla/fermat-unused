package com.bitdubai.fermat_core.layer.pip_platform_service.location_subsystem;

import com.bitdubai.fermat_api.Addon;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.CantStartSubsystemException;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.PlatformServiceSubsystem;
import com.bitdubai.fermat_pip_addon.layer.platform_service.location_subsystem.developer.bitdubai.DeveloperBitDubai;

/**
 * Created by loui on 28/04/15.
 */
public class LocationSubsystemSubsystem implements PlatformServiceSubsystem {

    Addon addon;





    @Override
    public Addon getAddon() {
        return addon;
    }





    @Override
    public void start() throws CantStartSubsystemException {
        /**
         * I will choose from the different Developers available which implementation to use. Right now there is only
         * one, so it is not difficult to choose.
         */

        try {
            DeveloperBitDubai developerBitDubai = new DeveloperBitDubai();
            addon = developerBitDubai.getAddon();
        }
        catch (Exception e)
        {
            System.err.println("Exception: " + e.getMessage());
            throw new CantStartSubsystemException();
        }
    }


}
