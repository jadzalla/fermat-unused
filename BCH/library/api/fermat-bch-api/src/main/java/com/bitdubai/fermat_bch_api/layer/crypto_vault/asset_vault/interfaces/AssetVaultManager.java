package com.bitdubai.fermat_bch_api.layer.crypto_vault.asset_vault.interfaces;

import com.bitdubai.fermat_api.layer.all_definition.money.CryptoAddress;
import com.bitdubai.fermat_api.layer.all_definition.enums.BlockchainNetworkType;
import com.bitdubai.fermat_api.layer.all_definition.transaction_transference_protocol.crypto_transactions.CryptoTransaction;
import com.bitdubai.fermat_bch_api.layer.crypto_vault.asset_vault.interfaces.exceptions.CantGetGenesisTransactionException;
import com.bitdubai.fermat_bch_api.layer.crypto_vault.asset_vault.interfaces.exceptions.GetNewCryptoAddressException;

/**
 * Created by rodrigo on 9/20/15.
 */
public interface AssetVaultManager {

    /**
     * Will generate a CryptoAddress in the current network originated at the vault.
     * @return
     */
    CryptoAddress getNewAssetVaultCryptoAddress(BlockchainNetworkType blockchainNetworkType) throws GetNewCryptoAddressException;

    CryptoTransaction getGenesisTransaction(String transactionId) throws CantGetGenesisTransactionException;

    long getAvailableBalanceForTransaction (String genesisTransaction);

}
