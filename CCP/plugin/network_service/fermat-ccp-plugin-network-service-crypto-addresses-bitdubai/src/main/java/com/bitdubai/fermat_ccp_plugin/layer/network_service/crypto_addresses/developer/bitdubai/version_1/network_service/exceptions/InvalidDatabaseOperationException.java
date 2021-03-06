package com.bitdubai.fermat_ccp_plugin.layer.network_service.crypto_addresses.developer.bitdubai.version_1.network_service.exceptions;

import com.bitdubai.fermat_api.FermatException;

/**
 * Created by rodrigo on 7/23/15.
 */
public class InvalidDatabaseOperationException extends FermatException {
    static public final String DEFAULT_MESSAGE = "Invalid database operation detected. Valid values are INSERT, UPDATE, DELETE and SELECT";
    public InvalidDatabaseOperationException(String message, Exception cause, String context, String possibleReason) {
        super(message, cause, context, possibleReason);
    }
}
