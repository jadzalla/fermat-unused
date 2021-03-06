package com.bitdubai.fermat_bnk_plugin.layer.bank_money_transaction.make_offline_bank_transfer.developer.bitdubai.version_1.database;

import com.bitdubai.fermat_api.DealsWithPluginIdentity;
import com.bitdubai.fermat_api.layer.all_definition.developer.DeveloperDatabase;
import com.bitdubai.fermat_api.layer.all_definition.developer.DeveloperDatabaseTable;
import com.bitdubai.fermat_api.layer.all_definition.developer.DeveloperDatabaseTableRecord;
import com.bitdubai.fermat_api.layer.all_definition.developer.DeveloperObjectFactory;
import com.bitdubai.fermat_api.layer.osa_android.database_system.Database;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseRecord;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTable;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTableRecord;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DealsWithPluginDatabaseSystem;
import com.bitdubai.fermat_api.layer.osa_android.database_system.PluginDatabaseSystem;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantCreateDatabaseException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantLoadTableToMemoryException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.DatabaseNotFoundException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantOpenDatabaseException;
import com.bitdubai.fermat_bnk_plugin.layer.bank_money_transaction.make_offline_bank_transfer.developer.bitdubai.version_1.exceptions.CantInitializeMakeOfflineBankTransferBankMoneyTransactionDatabaseException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The Class <code>com.bitdubai.fermat_bnk_plugin.layer.bank_money_transaction.make_offline_bank_transfer.developer.bitdubai.version_1.database.MakeOfflineBankTransferBankMoneyTransactionDeveloperDatabaseFactory</code> have
 * contains the methods that the Developer Database Tools uses to show the information.
 * <p/>
 *
 * Created by Yordin Alayn - (y.alayn@gmail.com) on 01/10/15.
 *
 * @version 1.0
 * @since Java JDK 1.7
 */

public class MakeOfflineBankTransferBankMoneyTransactionDeveloperDatabaseFactory implements DealsWithPluginDatabaseSystem, DealsWithPluginIdentity {

    /**
     * DealsWithPluginDatabaseSystem Interface member variables.
     */
    PluginDatabaseSystem pluginDatabaseSystem;

    /**
     * DealsWithPluginIdentity Interface member variables.
     */
    UUID pluginId;


    Database database;

    /**
     * Constructor
     *
     * @param pluginDatabaseSystem
     * @param pluginId
     */
    public MakeOfflineBankTransferBankMoneyTransactionDeveloperDatabaseFactory(PluginDatabaseSystem pluginDatabaseSystem, UUID pluginId) {
        this.pluginDatabaseSystem = pluginDatabaseSystem;
        this.pluginId = pluginId;
    }

    /**
     * This method open or creates the database i'll be working with
     *
     * @throws CantInitializeMakeOfflineBankTransferBankMoneyTransactionDatabaseException
     */
    public void initializeDatabase() throws CantInitializeMakeOfflineBankTransferBankMoneyTransactionDatabaseException {
        try {

             /*
              * Open new database connection
              */
            database = this.pluginDatabaseSystem.openDatabase(pluginId, pluginId.toString());

        } catch (CantOpenDatabaseException cantOpenDatabaseException) {

             /*
              * The database exists but cannot be open. I can not handle this situation.
              */
            throw new CantInitializeMakeOfflineBankTransferBankMoneyTransactionDatabaseException(cantOpenDatabaseException.getMessage());

        } catch (DatabaseNotFoundException e) {

             /*
              * The database no exist may be the first time the plugin is running on this device,
              * We need to create the new database
              */
            MakeOfflineBankTransferBankMoneyTransactionDatabaseFactory makeOfflineBankTransferBankMoneyTransactionDatabaseFactory = new MakeOfflineBankTransferBankMoneyTransactionDatabaseFactory(pluginDatabaseSystem);

            try {
                  /*
                   * We create the new database
                   */
                database = makeOfflineBankTransferBankMoneyTransactionDatabaseFactory.createDatabase(pluginId, pluginId.toString());
            } catch (CantCreateDatabaseException cantCreateDatabaseException) {
                  /*
                   * The database cannot be created. I can not handle this situation.
                   */
                throw new CantInitializeMakeOfflineBankTransferBankMoneyTransactionDatabaseException(cantCreateDatabaseException.getMessage());
            }
        }
    }


    public List<DeveloperDatabase> getDatabaseList(DeveloperObjectFactory developerObjectFactory) {
        /**
         * I only have one database on my plugin. I will return its name.
         */
        List<DeveloperDatabase> databases = new ArrayList<DeveloperDatabase>();
        databases.add(developerObjectFactory.getNewDeveloperDatabase("Make Offline Bank Transfer", this.pluginId.toString()));
        return databases;
    }


    public List<DeveloperDatabaseTable> getDatabaseTableList(DeveloperObjectFactory developerObjectFactory) {
        List<DeveloperDatabaseTable> tables = new ArrayList<DeveloperDatabaseTable>();

        /**
         * Table Make Offline Bank Transfer columns.
         */
        List<String> makeOfflineBankTransferColumns = new ArrayList<String>();

        makeOfflineBankTransferColumns.add(MakeOfflineBankTransferBankMoneyTransactionDatabaseConstants.MAKE_OFFLINE_BANK_TRANSFER_BANK_TRANSACTION_ID_COLUMN_NAME);
        makeOfflineBankTransferColumns.add(MakeOfflineBankTransferBankMoneyTransactionDatabaseConstants.MAKE_OFFLINE_BANK_TRANSFER_PUBLIC_KEY_BROKER_COLUMN_NAME);
        makeOfflineBankTransferColumns.add(MakeOfflineBankTransferBankMoneyTransactionDatabaseConstants.MAKE_OFFLINE_BANK_TRANSFER_PUBLIC_KEY_CUSTOMER_COLUMN_NAME);
        makeOfflineBankTransferColumns.add(MakeOfflineBankTransferBankMoneyTransactionDatabaseConstants.MAKE_OFFLINE_BANK_TRANSFER_STATUS_COLUMN_NAME);
        makeOfflineBankTransferColumns.add(MakeOfflineBankTransferBankMoneyTransactionDatabaseConstants.MAKE_OFFLINE_BANK_TRANSFER_TRANSACTION_TYPE_COLUMN_NAME);
        makeOfflineBankTransferColumns.add(MakeOfflineBankTransferBankMoneyTransactionDatabaseConstants.MAKE_OFFLINE_BANK_TRANSFER_AMOUNT_COLUMN_NAME);
        makeOfflineBankTransferColumns.add(MakeOfflineBankTransferBankMoneyTransactionDatabaseConstants.MAKE_OFFLINE_BANK_TRANSFER_BANK_CURRENCY_TYPE_COLUMN_NAME);
        makeOfflineBankTransferColumns.add(MakeOfflineBankTransferBankMoneyTransactionDatabaseConstants.MAKE_OFFLINE_BANK_TRANSFER_BANK_OPERATION_TYPE_COLUMN_NAME);
        makeOfflineBankTransferColumns.add(MakeOfflineBankTransferBankMoneyTransactionDatabaseConstants.MAKE_OFFLINE_BANK_TRANSFER_BANK_DOCUMENT_REFERENCE_COLUMN_NAME);
        makeOfflineBankTransferColumns.add(MakeOfflineBankTransferBankMoneyTransactionDatabaseConstants.MAKE_OFFLINE_BANK_TRANSFER_BANK_TO_NAME_COLUMN_NAME);
        makeOfflineBankTransferColumns.add(MakeOfflineBankTransferBankMoneyTransactionDatabaseConstants.MAKE_OFFLINE_BANK_TRANSFER_BANK_TO_ACCOUNT_NUMBER_COLUMN_NAME);
        makeOfflineBankTransferColumns.add(MakeOfflineBankTransferBankMoneyTransactionDatabaseConstants.MAKE_OFFLINE_BANK_TRANSFER_BANK_TO_ACCOUNTTYPE_COLUMN_NAME);
        makeOfflineBankTransferColumns.add(MakeOfflineBankTransferBankMoneyTransactionDatabaseConstants.MAKE_OFFLINE_BANK_TRANSFER_BANK_FROM_NAME_COLUMN_NAME);
        makeOfflineBankTransferColumns.add(MakeOfflineBankTransferBankMoneyTransactionDatabaseConstants.MAKE_OFFLINE_BANK_TRANSFER_BANK_FROM_ACCOUNT_NUMBER_COLUMN_NAME);
        makeOfflineBankTransferColumns.add(MakeOfflineBankTransferBankMoneyTransactionDatabaseConstants.MAKE_OFFLINE_BANK_TRANSFER_BANK_FROM_ACCOUNT_TYPE_COLUMN_NAME);
        makeOfflineBankTransferColumns.add(MakeOfflineBankTransferBankMoneyTransactionDatabaseConstants.MAKE_OFFLINE_BANK_TRANSFER_TIMESTAMP_COLUMN_NAME);
        /**
         * Table Make Offline Bank Transfer addition.
         */
        DeveloperDatabaseTable makeOfflineBankTransferTable = developerObjectFactory.getNewDeveloperDatabaseTable(MakeOfflineBankTransferBankMoneyTransactionDatabaseConstants.MAKE_OFFLINE_BANK_TRANSFER_TABLE_NAME, makeOfflineBankTransferColumns);
        tables.add(makeOfflineBankTransferTable);



        return tables;
    }


    public List<DeveloperDatabaseTableRecord> getDatabaseTableContent(DeveloperObjectFactory developerObjectFactory, DeveloperDatabaseTable developerDatabaseTable) {
        /**
         * Will get the records for the given table
         */
        List<DeveloperDatabaseTableRecord> returnedRecords = new ArrayList<DeveloperDatabaseTableRecord>();


        /**
         * I load the passed table name from the SQLite database.
         */
        DatabaseTable selectedTable = database.getTable(developerDatabaseTable.getName());
        try {
            selectedTable.loadToMemory();
        } catch (CantLoadTableToMemoryException cantLoadTableToMemory) {
            /**
             * if there was an error, I will returned an empty list.
             */
            return returnedRecords;
        }

        List<DatabaseTableRecord> records = selectedTable.getRecords();
        List<String> developerRow = new ArrayList<String>();
        for (DatabaseTableRecord row : records) {
            /**
             * for each row in the table list
             */
            for (DatabaseRecord field : row.getValues()) {
                /**
                 * I get each row and save them into a List<String>
                 */
                developerRow.add(field.getValue().toString());
            }
            /**
             * I create the Developer Database record
             */
            returnedRecords.add(developerObjectFactory.getNewDeveloperDatabaseTableRecord(developerRow));
        }


        /**
         * return the list of DeveloperRecords for the passed table.
         */
        return returnedRecords;
    }

    @Override
    public void setPluginDatabaseSystem(PluginDatabaseSystem pluginDatabaseSystem) {
        this.pluginDatabaseSystem = pluginDatabaseSystem;
    }

    @Override
    public void setPluginId(UUID pluginId) {
        this.pluginId = pluginId;
    }
}