package com.bitdubai.fermat_dap_plugin.layer.wallet.asset.user.developer.bitdubai.version_1.structure;

import com.bitdubai.fermat_api.FermatException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.Database;
import com.bitdubai.fermat_api.layer.osa_android.database_system.PluginDatabaseSystem;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantCreateDatabaseException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantOpenDatabaseException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.DatabaseNotFoundException;
import com.bitdubai.fermat_api.layer.osa_android.file_system.FileLifeSpan;
import com.bitdubai.fermat_api.layer.osa_android.file_system.FilePrivacy;
import com.bitdubai.fermat_api.layer.osa_android.file_system.PluginFileSystem;
import com.bitdubai.fermat_api.layer.osa_android.file_system.PluginTextFile;
import com.bitdubai.fermat_api.layer.osa_android.file_system.exceptions.CantCreateFileException;
import com.bitdubai.fermat_api.layer.osa_android.file_system.exceptions.CantLoadFileException;
import com.bitdubai.fermat_api.layer.osa_android.file_system.exceptions.CantPersistFileException;
import com.bitdubai.fermat_api.layer.osa_android.file_system.exceptions.FileNotFoundException;
import com.bitdubai.fermat_dap_api.layer.dap_wallet.asset_user_wallet.interfaces.AssetUserWallet;
import com.bitdubai.fermat_dap_api.layer.dap_wallet.asset_user_wallet.interfaces.AssetUserWalletManager;
import com.bitdubai.fermat_dap_api.layer.dap_wallet.asset_user_wallet.interfaces.AssetUserWalletTransaction;
import com.bitdubai.fermat_dap_api.layer.dap_wallet.asset_user_wallet.interfaces.AssetUserWalletTransactionSummary;
import com.bitdubai.fermat_dap_api.layer.dap_wallet.common.enums.BalanceType;
import com.bitdubai.fermat_dap_api.layer.dap_wallet.common.enums.TransactionType;
import com.bitdubai.fermat_dap_api.layer.dap_wallet.common.exceptions.CantCreateWalletException;
import com.bitdubai.fermat_dap_api.layer.dap_wallet.common.exceptions.CantFindTransactionException;
import com.bitdubai.fermat_dap_api.layer.dap_wallet.common.exceptions.CantGetActorTransactionSummaryException;
import com.bitdubai.fermat_dap_api.layer.dap_wallet.common.exceptions.CantGetTransactionsException;
import com.bitdubai.fermat_dap_api.layer.dap_wallet.common.exceptions.CantStoreMemoException;
import com.bitdubai.fermat_dap_plugin.layer.wallet.asset.user.developer.bitdubai.version_1.structure.database.AssetUserWalletDao;
import com.bitdubai.fermat_dap_plugin.layer.wallet.asset.user.developer.bitdubai.version_1.structure.database.AssetUserWalletDatabaseFactory;
import com.bitdubai.fermat_dap_plugin.layer.wallet.asset.user.developer.bitdubai.version_1.structure.exceptions.CantInitializeAssetUserWalletException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.ErrorManager;

/**
 * Created by franklin on 05/10/15.
 */
public class AssetUserWalletImpl implements AssetUserWallet {
    private static final String ASSET_USER_WALLET_FILE_NAME = "walletsIds";

    /**
     * AssetIssuerWallet member variables.
     */
    private Database database;

    private Map<String, UUID> walletAssetIssuer = new HashMap<>();

    //TODO: Implementar clase DAO y los metodos de la interfaz manager y otros metodos.
    private AssetUserWalletDao assetIssuerWalletDao;
    private ErrorManager errorManager;

    private PluginDatabaseSystem pluginDatabaseSystem;

    private PluginFileSystem pluginFileSystem;

    private UUID pluginId;

    public AssetUserWalletImpl(ErrorManager errorManager, PluginDatabaseSystem pluginDatabaseSystem, PluginFileSystem pluginFileSystem, UUID pluginId) {
        this.errorManager = errorManager;
        this.pluginDatabaseSystem = pluginDatabaseSystem;
        this.pluginFileSystem = pluginFileSystem;
        this.pluginId = pluginId;
    }

    public void initialize(UUID walletId) throws CantInitializeAssetUserWalletException {
        if (walletId == null)
            throw new CantInitializeAssetUserWalletException("InternalId is null", null, "Parameter walletId is null", "loadWallet didn't find the asociated id");

        try {
            database = this.pluginDatabaseSystem.openDatabase(this.pluginId, walletId.toString());
        } catch (CantOpenDatabaseException cantOpenDatabaseException) {
            throw new CantInitializeAssetUserWalletException("I can't open database", cantOpenDatabaseException, "WalletId: " + walletId.toString(), "");
        } catch (DatabaseNotFoundException databaseNotFoundException) {
            throw new CantInitializeAssetUserWalletException("Database does not exists", databaseNotFoundException, "WalletId: " + walletId.toString(), "");
        } catch (Exception exception) {
            throw new CantInitializeAssetUserWalletException(CantInitializeAssetUserWalletException.DEFAULT_MESSAGE, FermatException.wrapException(exception), null, null);
        }
    }

    public UUID create(String walletId) throws CantCreateWalletException {
        try {
            // TODO: Until the Wallet MAnager create the wallets, we will use this internal id
            //       We need to change this in the near future
            UUID internalWalletId = UUID.randomUUID();
            createWalletDatabase(internalWalletId);
            PluginTextFile walletAssetIssuerFile = createAssetUserWalletFile();
            loadAssetUserWalletMap(walletAssetIssuerFile);
            walletAssetIssuer.put(walletId, internalWalletId);
            persistAssetUserWallet(walletAssetIssuerFile);
            return internalWalletId;
        } catch (CantCreateWalletException exception) {
            throw exception;
        } catch (Exception exception) {
            throw new CantCreateWalletException(CantCreateWalletException.DEFAULT_MESSAGE, FermatException.wrapException(exception), null, null);
        }

    }

    private void loadAssetUserWalletMap(final PluginTextFile loadAssetIssuerWalletMap) throws CantCreateWalletException
    {
        try {
            loadAssetIssuerWalletMap.loadFromMedia();
            String[] stringAssetIssuerWallet = loadAssetIssuerWalletMap.getContent().split(";", -1);

            for (String stringWalletId : stringAssetIssuerWallet) {

                if (!stringWalletId.equals("")) {
                    String[] idPair = stringWalletId.split(",", -1);
                    walletAssetIssuer.put(idPair[0], UUID.fromString(idPair[1]));
                }
            }
        } catch (CantLoadFileException exception) {
            throw new CantCreateWalletException("Can't load file content from media", exception, "", "");
        }
    }

    private void persistAssetUserWallet(final PluginTextFile pluginTextFile) throws CantCreateWalletException
    {
        StringBuilder stringBuilder = new StringBuilder(walletAssetIssuer.size() * 72);
        Iterator iterator = walletAssetIssuer.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry) iterator.next();

            stringBuilder
                    .append(pair.getKey().toString())
                    .append(",")
                    .append(pair.getValue().toString())
                    .append(";");

            iterator.remove();
        }

        pluginTextFile.setContent(stringBuilder.toString());

        try {
            pluginTextFile.persistToMedia();
        } catch (CantPersistFileException cantPersistFileException) {
            throw new CantCreateWalletException("Could not persist in file", cantPersistFileException, "stringBuilder: " + stringBuilder.toString(), "");
        }
    }

    private void createWalletDatabase(final UUID internalWalletId) throws CantCreateWalletException
    {
        try {
            AssetUserWalletDatabaseFactory databaseFactory = new AssetUserWalletDatabaseFactory();
            databaseFactory.setPluginDatabaseSystem(pluginDatabaseSystem);
            database = databaseFactory.createDatabase(this.pluginId, internalWalletId);
        } catch (CantCreateDatabaseException cantCreateDatabaseException) {
            throw new CantCreateWalletException("Database could not be created", cantCreateDatabaseException, "internalWalletId: " + internalWalletId.toString(), "");
        }
    }

    private PluginTextFile  createAssetUserWalletFile() throws CantCreateWalletException
    {
        try {
            return pluginFileSystem.getTextFile(pluginId, "", ASSET_USER_WALLET_FILE_NAME, FilePrivacy.PRIVATE, FileLifeSpan.PERMANENT);
        } catch (CantCreateFileException cantCreateFileException) {
            throw new CantCreateWalletException("File could not be created (?)", cantCreateFileException, "File Name: " + ASSET_USER_WALLET_FILE_NAME, "");
        } catch (FileNotFoundException e) {
            throw new CantCreateWalletException("File could not be found", e, "File Name: " + ASSET_USER_WALLET_FILE_NAME, "");
        }
    }

    @Override
    public AssetUserWalletManager getBookBalance(BalanceType balanceType) throws CantGetTransactionsException {
        return null;
    }

    @Override
    public List<AssetUserWalletTransaction> getTransactions(BalanceType balanceType, TransactionType transactionType, int max, int offset, String assetPublicKey) throws CantGetTransactionsException {
        return null;
    }

    @Override
    public List<AssetUserWalletTransaction> getTransactionsByActor(String actorPublicKey, BalanceType balanceType, int max, int offset) throws CantGetTransactionsException {
        return null;
    }

    @Override
    public List<AssetUserWalletTransaction> gettLastActorTransactionsByTransactionType(BalanceType balanceType, TransactionType transactionType, int max, int offset) throws CantGetTransactionsException {
        return null;
    }

    @Override
    public void setTransactionDescription(UUID transactionID, String description) throws CantFindTransactionException, CantStoreMemoException {

    }

    @Override
    public AssetUserWalletTransactionSummary getActorTransactionSummary(String actorPublicKey, BalanceType balanceType) throws CantGetActorTransactionSummaryException {
        return null;
    }
}
