package com.bitdubai.fermat_dmp_plugin.layer.world.crypto_index.developer.bitdubai.version_1.providers;

import com.bitdubai.fermat_dmp_plugin.layer.world.crypto_index.developer.bitdubai.version_1.interfaces.InterfaceUrlAPI;

/**
 * Created by francisco on 31/08/15.
 */
public class BterServiceAPI implements InterfaceUrlAPI {

   private String UrlAPI;

    @Override
    public String getUrlAPI(String c, String f) {
        String pair = c + "_" + f;
        UrlAPI="http://data.bter.com/api/1/ticker/"+ pair;
        return UrlAPI;
    }
}
