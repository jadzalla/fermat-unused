package com.bitdubai.fermat_csh_api.layer.csh_wallet.cash_money.interfaces;

import com.bitdubai.fermat_csh_api.all_definition.enums.BalanceType;
import com.bitdubai.fermat_csh_api.all_definition.enums.CashCurrencyType;
import com.bitdubai.fermat_csh_api.all_definition.enums.CashTransactionStatus;
import com.bitdubai.fermat_csh_api.all_definition.enums.TransactionType;

import java.util.UUID;

/**
 * Created by Yordin Alayn on 30.09.15.
 */
public interface CashMoneyTransactionRecord {

    UUID getCashTransactionId();

    String getPublicKeyCustomer();

    String getPublicKeyBroker();

    CashTransactionStatus getStatus();

    BalanceType getBalanceType();

    TransactionType getTransactionType();

    float getAmount();

    CashCurrencyType getCashCurrencyType();

    String getCashReference();

    long getTimestamp();

    String getMemo();

}
