package com.bitdubai.fermat_cbp_api.all_definition.enums;

import com.bitdubai.fermat_cbp_api.all_definition.exceptions.InvalidParameterException;

/**
 * Created by Yordin Alayn on 23.09.15.
 */
 
public enum BusinessTransactionStatus {
    NEGOTIATION("NEG"),
    PAUSED("PAU"),
    PENDING_PAYMENT("PEN"),
    COMPLETED("COM"),
    CANCELLED ("CAN");

    private String code;

    BusinessTransactionStatus(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public static BusinessTransactionStatus getByCode(String code) throws InvalidParameterException {
        switch (code) {
            case "NEG": return BusinessTransactionStatus.NEGOTIATION;
            case "PAU": return BusinessTransactionStatus.PAUSED;
            case "PEN": return BusinessTransactionStatus.PENDING_PAYMENT;
            case "COM": return BusinessTransactionStatus.COMPLETED;
            case "CAN": return BusinessTransactionStatus.CANCELLED;
            default: throw new InvalidParameterException(InvalidParameterException.DEFAULT_MESSAGE, null, "Code Received: " + code, "This Code Is Not Valid for the ContactState enum");
        }
    }
}
