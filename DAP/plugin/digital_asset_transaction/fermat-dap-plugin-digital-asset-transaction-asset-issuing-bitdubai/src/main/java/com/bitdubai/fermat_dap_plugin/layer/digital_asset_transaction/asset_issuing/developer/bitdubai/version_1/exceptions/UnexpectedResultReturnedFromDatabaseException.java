package com.bitdubai.fermat_dap_plugin.layer.digital_asset_transaction.asset_issuing.developer.bitdubai.version_1.exceptions;

import com.bitdubai.fermat_api.layer.DAPException;

/**
 * Created by Manuel Perez (darkpriestrelative@gmail.com) on 10/09/15.
 */
public class UnexpectedResultReturnedFromDatabaseException extends DAPException {

    public static final String DEFAULT_MESSAGE = "The Database returns an unexpected result.";

    public UnexpectedResultReturnedFromDatabaseException(Exception cause, String context, String possibleReason) {
        super(DEFAULT_MESSAGE , cause, context, possibleReason);
    }

    public UnexpectedResultReturnedFromDatabaseException(String message, String possibleReason) {
        super(DEFAULT_MESSAGE, null, message, possibleReason);
    }

}
