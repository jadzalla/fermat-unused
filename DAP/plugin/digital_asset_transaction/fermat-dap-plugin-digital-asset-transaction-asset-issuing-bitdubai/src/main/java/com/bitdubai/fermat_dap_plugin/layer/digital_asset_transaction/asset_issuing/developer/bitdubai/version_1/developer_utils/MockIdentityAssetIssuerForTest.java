package com.bitdubai.fermat_dap_plugin.layer.digital_asset_transaction.asset_issuing.developer.bitdubai.version_1.developer_utils;

import com.bitdubai.fermat_dap_api.layer.dap_identity.asset_issuer.exceptions.CantSingMessageException;
import com.bitdubai.fermat_dap_api.layer.dap_identity.asset_issuer.interfaces.IdentityAssetIssuer;

/**
 * Created by Manuel Perez (darkpriestrelative@gmail.com) on 25/09/15.
 */
public class MockIdentityAssetIssuerForTest implements IdentityAssetIssuer {
    @Override
    public String getAlias() {
        return "Franklin Marcano";
    }

    @Override
    public String getPublicKey() {
        return "ASDS-10087982";
    }

    @Override
    public String createMessageSignature(String mensage) throws CantSingMessageException {
        return "signature";
    }
}
