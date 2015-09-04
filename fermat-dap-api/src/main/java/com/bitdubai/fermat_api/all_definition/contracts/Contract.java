package com.bitdubai.fermat_api.all_definition.contracts;

import com.bitdubai.fermat_api.all_definition.contracts.exceptions.CantDefineContractPropertyException;

import java.util.List;


/**
 * Created by rodrigo on 9/4/15.
 */
public interface Contract {
    List<ContractProperty> getContractProperties();
    void setContractProperty (ContractProperty contractProperty) throws CantDefineContractPropertyException;
}
