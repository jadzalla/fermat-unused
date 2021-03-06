package com.bitdubai.fermat_pip_api.layer.pip_platform_service.platform_info.interfaces.exceptions;

import com.bitdubai.fermat_api.FermatException;

/**
 * Created by rodrigo on 8/5/15.
 */
public class CantLoadPlatformInformationException extends FermatException {
    public static final String DEFAULT_MESSAGE = "There was an error trying to load platform information values.";
    public CantLoadPlatformInformationException(String message, Exception cause, String context, String possibleReason) {
        super(message, cause, context, possibleReason);
    }
}
