package com.bitdubai.fermat_cbp_api.all_definition.enums;

import com.bitdubai.fermat_api.layer.all_definition.exceptions.InvalidParameterException;

/**
 * Created by natalia on 16/09/15.
 */
public enum WalletType {
    NICHE("NICHE"),
    REFERENCE("REFER"), ;

    private String code;

    WalletType(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    public static WalletType getByCode(String code) throws InvalidParameterException {
        switch (code){
            case "NICHE": return WalletType.NICHE;
            case "REFER": return WalletType.REFERENCE;
            //Modified by Manuel Perez on 03/08/2015
            default: throw new InvalidParameterException(InvalidParameterException.DEFAULT_MESSAGE, null, "Code Received: " + code, "This Code Is Not Valid for the WalletType enum");
        }
    }
}
