/*
 * @(#DeveloperIdentityDao.java 07/16/2015
 * Copyright 2015 bitDubai, Inc. All rights reserved.
 * BITDUBAI/CONFIDENTIAL
 * */

package com.bitdubai.fermat_pip_plugin.layer.identity.developer.developer.bitdubai.version_1.structure;


// Packages and classes to import of jdk 1.7

import java.util.*;

// Packages and classes to import of fermat api
import com.bitdubai.fermat_api.DealsWithPluginIdentity;
import com.bitdubai.fermat_api.FermatException;
import com.bitdubai.fermat_api.layer.all_definition.crypto.asymmetric.ECCKeyPair;
import com.bitdubai.fermat_api.layer.all_definition.enums.DeviceDirectory;
import com.bitdubai.fermat_api.layer.osa_android.database_system.Database;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseFilterType;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTable;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTableRecord;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DealsWithPluginDatabaseSystem;
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
import com.bitdubai.fermat_api.layer.osa_android.logger_system.LogManager;
import com.bitdubai.fermat_api.layer.pip_Identity.developer.interfaces.DeveloperIdentity;
import com.bitdubai.fermat_pip_api.layer.pip_user.device_user.interfaces.DeviceUser;
import com.bitdubai.fermat_api.layer.pip_Identity.developer.exceptions.CantGetUserDeveloperIdentitiesException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantLoadTableToMemoryException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantInsertRecordException;
import com.bitdubai.fermat_api.layer.pip_Identity.developer.exceptions.CantCreateNewDeveloperException;
import com.bitdubai.fermat_pip_plugin.layer.identity.developer.developer.bitdubai.version_1.database.DeveloperIdentityDatabaseConstants;
import com.bitdubai.fermat_pip_plugin.layer.identity.developer.developer.bitdubai.version_1.database.DeveloperIdentityDatabaseFactory;
import com.bitdubai.fermat_pip_plugin.layer.identity.developer.developer.bitdubai.version_1.DeveloperIdentityPluginRoot;
import com.bitdubai.fermat_pip_plugin.layer.identity.developer.developer.bitdubai.version_1.exceptions.CantInitializeDeveloperIdentityDatabaseException;
import com.bitdubai.fermat_pip_plugin.layer.identity.developer.developer.bitdubai.version_1.exceptions.*;
// Packages and classes to import of apache commons.
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * The Class <code>com.bitdubai.fermat_pip_plugin.layer.identity.developer.developer.bitdubai.version_1.structure.DeveloperIdentityDao</code>
 * all methods implementation to access the data base<p/>
 * <p/>
 * <p/>
 * Created by Leon Acosta - (laion.cj91@gmail.com) on 14/07/15.
 * Updated by Raul Pena   - (raul.pena@gmail.com)  on 16/07/15.
 *
 * @version 1.0
 * @since Java JDK 1.7
 */
public class DeveloperIdentityDao implements DealsWithPluginDatabaseSystem, DealsWithPluginIdentity {


    // Private instance fields declarations.
    // DealsWithPluginDatabaseSystem Interface member variables.
    private PluginDatabaseSystem pluginDatabaseSystem = null;

    /**
     * DealsWithPlatformFileSystem Interface member variables.
     */
    PluginFileSystem pluginFileSystem;

    //  Database factory
    private DeveloperIdentityDatabaseFactory databaseFactory = null;

    // Database object.
    private Database dataBase = null;

    private UUID pluginId = null;

    // DealsWithlogManager interface member variable.
    private LogManager logManager = null;

    // Private class fields declarations.

    // Blank target.
    private static final String _DEFAUL_STRING = "";


    final String DEVELOPER_IDENTITY_PRIVATE_KEYS_FILE_NAME = "developerIdentityPrivateKeys";

    // Public constructor declarations.

    /**
     * <p>Constructor without parameters.
     */
    public DeveloperIdentityDao() {

        // Call to super class.
        super();
    }

    /**
     * <p>Constructor with parameters.
     *
     * @param pluginDatabaseSystem
     * @param databaseFactory
     * @param pluginId
     */
    public DeveloperIdentityDao(PluginFileSystem pluginFileSystem, PluginDatabaseSystem pluginDatabaseSystem, DeveloperIdentityDatabaseFactory databaseFactory, UUID pluginId, LogManager logManager) {

        // Call to super class.
        super();

        // Set internal values.
        this.pluginDatabaseSystem = pluginDatabaseSystem;
        this.databaseFactory = databaseFactory;
        this.pluginId = pluginId;
        this.logManager = logManager;
        this.pluginFileSystem = pluginFileSystem;
    }


    // Private instance methods declarations.
    /*
     *
     *  <p>Method that check if alias exists.
      *
      *  @param alias
      *  @return Boolean that indicate if the alias exists or not.
     * */
    private boolean aliasExists(String alias) throws CantCreateNewDeveloperException {


        // Setup method.
        DatabaseTable table; // Developer table.


        // Check the arguments.
        if (isEmpty(alias)) {

            // Cancel the process.
            logManager.log(DeveloperIdentityPluginRoot.getLogLevelByClass(this.getClass().getName()), "Alias is empty, arguments are null or empty.", _DEFAUL_STRING, _DEFAUL_STRING);
            return Boolean.FALSE;
        }

        if (this.dataBase == null) {

            // Cancel the process.
            throw new CantCreateNewDeveloperException("Cant check if alias exists or not, Database is closed o null.", "Plugin Identity", "Cant check if alias exists or not, Database is closed o null.");
        }


        // Get developers identities list.
        try {

            logManager.log(DeveloperIdentityPluginRoot.getLogLevelByClass(this.getClass().getName()), "Checking if alias " + alias + " exists.", _DEFAUL_STRING, _DEFAUL_STRING);


            // 1) Get the table.
            logManager.log(DeveloperIdentityPluginRoot.getLogLevelByClass(this.getClass().getName()), "Getting " + DeveloperIdentityDatabaseConstants.DEVELOPER_TABLE_NAME + " table and record.", _DEFAUL_STRING, _DEFAUL_STRING);
            table = this.dataBase.getTable(DeveloperIdentityDatabaseConstants.DEVELOPER_TABLE_NAME);

            if (table == null) {

                // Table not found.
                logManager.log(DeveloperIdentityPluginRoot.getLogLevelByClass(this.getClass().getName()), "Cant check if alias exists, table not " + DeveloperIdentityDatabaseConstants.DEVELOPER_TABLE_NAME + " found.", _DEFAUL_STRING, _DEFAUL_STRING);
                throw new CantGetUserDeveloperIdentitiesException("Cant check if alias exists, table not \" + DeveloperIdentityDatabaseConstants.DEVELOPER_TABLE_NAME + \" found.", "Plugin Identity", "Cant check if alias exists, table not \" + DeveloperIdentityDatabaseConstants.DEVELOPER_TABLE_NAME + \" found.");
            }


            // 2) Find the developers.
            logManager.log(DeveloperIdentityPluginRoot.getLogLevelByClass(this.getClass().getName()), "Applying filter to " + DeveloperIdentityDatabaseConstants.DEVELOPER_TABLE_NAME + " table by developer alias key [" + alias.trim() + "].", _DEFAUL_STRING, _DEFAUL_STRING);
            table.setStringFilter(DeveloperIdentityDatabaseConstants.DEVELOPER_DEVELOPER_ALIAS_COLUMN_NAME, alias, DatabaseFilterType.EQUAL);
            table.loadToMemory();


            // 3) Get developers.
            logManager.log(DeveloperIdentityPluginRoot.getLogLevelByClass(this.getClass().getName()), "Developer alias found (" + table.getRecords().size() + ") by alias [" + alias + "].", _DEFAUL_STRING, _DEFAUL_STRING);
            return table.getRecords().size() > 0;


        } catch (CantLoadTableToMemoryException em) {

            // Failure unknown.
            throw new CantCreateNewDeveloperException(em.getMessage(), em, "Plugin Identity", "Cant load " + DeveloperIdentityDatabaseConstants.DEVELOPER_TABLE_NAME + " table in memory.");

        } catch (Exception e) {

            // Failure unknown.
            throw new CantCreateNewDeveloperException(e.getMessage(), e, "Plugin Identity", "Cant check if alias exists, unknown failure.");
        }
    }


    // Public instance methods declarations extends of com.bitdubai.fermat_api.layer.osa_android.database_system.DealsWithPluginDatabaseSystem

    /**
     * DealsWithPluginDatabaseSystem Interface implementation.
     */
    @Override
    public void setPluginDatabaseSystem(PluginDatabaseSystem pluginDatabaseSystem) {

        // Set internal values.
        this.pluginDatabaseSystem = pluginDatabaseSystem;
    }


    /**
     * DealsWithPluginIdentity Interface implementation.
     */
    @Override
    public void setPluginId(UUID pluginId) {

        // Set value.
        this.pluginId = pluginId;
    }


    // Public instance methods declarations.

    /**
     * <p>Method tha set the Database factory.
     */
    public void setDeveloperIdentityDatabaseFactory(DeveloperIdentityDatabaseFactory databaseFactory) {

        // Set the value.
        this.databaseFactory = databaseFactory;
    }

    /**
     * This method open or creates the database i'll be working with.
     *
     * @param ownerId plugin id
     * @throws CantInitializeDeveloperIdentityDatabaseException
     */
    public void initializeDatabase(UUID ownerId) throws CantInitializeDeveloperIdentityDatabaseException {


        // Check the arguments.
        if (ownerId == null) {

            // Cancel the process.
            throw new CantInitializeDeveloperIdentityDatabaseException("Cant create database, arguments are null or empty.", "Plugin Identity", "Cant create database, arguments are null or empty.");
        }


        // Create the database.
        try {
            logManager.log(DeveloperIdentityPluginRoot.getLogLevelByClass(this.getClass().getName()), "Initializing identity database system...", _DEFAUL_STRING, _DEFAUL_STRING);
            this.databaseFactory.setPluginDatabaseSystem(this.pluginDatabaseSystem);

            //Check the database exist
            this.dataBase = pluginDatabaseSystem.openDatabase(pluginId, DeveloperIdentityDatabaseConstants.DEVELOPER_DB_NAME);

        } catch (DatabaseNotFoundException databaseNotFoundException) {
            try {
                this.dataBase = this.databaseFactory.createDatabase(ownerId, DeveloperIdentityDatabaseConstants.DEVELOPER_DB_NAME);

            } catch (CantCreateDatabaseException e) {
                throw new CantInitializeDeveloperIdentityDatabaseException(e.getMessage(), e, "Plugin Identity", "Cant create database.");

            }
        } catch (CantOpenDatabaseException cantOpenDatabaseException) {
            throw new CantInitializeDeveloperIdentityDatabaseException(cantOpenDatabaseException.getMessage(), cantOpenDatabaseException, "Plugin Identity", "Cant create database.");

        } catch (Exception e) {

            // Failure unknown.
            throw new CantInitializeDeveloperIdentityDatabaseException(e.getMessage(), e, "Plugin Identity", "Cant create database, unknown failure.");
        }
    }

    /**
     * Method that create a new developer in the database.
     *
     * @param alias            alias of developer
     * @param developerKeyPair new private and public key for the developer
     * @param deviceUser       logged in device user
     * @return DeveloperIdentity
     * @throws CantCreateNewDeveloperException
     */
    public DeveloperIdentity createNewDeveloper(String alias, ECCKeyPair developerKeyPair, DeviceUser deviceUser) throws CantCreateNewDeveloperException {


        // Check the arguments.
        if (developerKeyPair == null || isEmpty(alias) || deviceUser == null) {

            // Cancel the process.
            throw new CantCreateNewDeveloperException("Cant create new developer, arguments are null or empty.", "Plugin Identity", "Cant create database, arguments are null or empty.");
        }


        if (this.dataBase == null) {

            // Cancel the process.
            throw new CantCreateNewDeveloperException("Cant create new developer, Database is closed o null.", "Plugin Identity", "Cant create new developer, Database is closed o null.");
        }


        // Create the new developer.
        try {

            logManager.log(DeveloperIdentityPluginRoot.getLogLevelByClass(this.getClass().getName()), "Initializing developer record creation.", _DEFAUL_STRING, _DEFAUL_STRING);

            if (aliasExists(alias)) {

                throw new CantCreateNewDeveloperException("Cant create new developer, alias exists.", "Plugin Identity", "Cant create new developer, alias exists.");
            }

            logManager.log(DeveloperIdentityPluginRoot.getLogLevelByClass(this.getClass().getName()), "Getting " + DeveloperIdentityDatabaseConstants.DEVELOPER_TABLE_NAME + " table and record.", _DEFAUL_STRING, _DEFAUL_STRING);
            DatabaseTable table = this.dataBase.getTable(DeveloperIdentityDatabaseConstants.DEVELOPER_TABLE_NAME);
            DatabaseTableRecord record = table.getEmptyRecord();

            String publicKey = developerKeyPair.getPublicKey(); //;
            logManager.log(DeveloperIdentityPluginRoot.getLogLevelByClass(this.getClass().getName()), "Getting " + DeveloperIdentityDatabaseConstants.DEVELOPER_TABLE_NAME + " table and record.", _DEFAUL_STRING, _DEFAUL_STRING);
            record.setStringValue(DeveloperIdentityDatabaseConstants.DEVELOPER_DEVELOPER_PUBLIC_KEY_COLUMN_NAME, publicKey);
            record.setStringValue(DeveloperIdentityDatabaseConstants.DEVELOPER_DEVICE_USER_PUBLIC_KEY_COLUMN_NAME, deviceUser.getPublicKey());
            record.setStringValue(DeveloperIdentityDatabaseConstants.DEVELOPER_DEVELOPER_ALIAS_COLUMN_NAME, alias);//deviceUser.getAlias()

            //  logManager.log(DeveloperIdentityPluginRoot.getLogLevelByClass(this.getClass().getName()), "Inserting [Alias=" + deviceUser.getAlias() + ", PK=" + developerKeyPair.getPublicKey() + "] in " + DeveloperIdentityDatabaseConstants.DEVELOPER_TABLE_NAME + " table and record.", _DEFAUL_STRING, _DEFAUL_STRING);
            table.insertRecord(record);
            // Persist private key on a file
            persistNewUserPrivateKeysFile(publicKey, developerKeyPair.getPrivateKey());

        } catch (CantInsertRecordException e) {

            // Cant insert record.
            throw new CantCreateNewDeveloperException(e.getMessage(), e, "Plugin Identity", "Cant create new developer, insert database problems.");

        } catch (Exception e) {

            // Failure unknown.
            throw new CantCreateNewDeveloperException(e.getMessage(), e, "Plugin Identity", "Cant create new developer, unknown failure.");
        }


        // Return the new developer.
        return new DeveloperIdentityRecord(alias, developerKeyPair.getPublicKey(), developerKeyPair.getPrivateKey());
    }


    /**
     * Method that list the developers related to the parametrized device user.
     *
     * @param deviceUser device user
     * @throws CantGetUserDeveloperIdentitiesException
     */
    public List<DeveloperIdentity> getDevelopersFromCurrentDeviceUser(DeviceUser deviceUser) throws CantGetUserDeveloperIdentitiesException {


        // Setup method.
        List<DeveloperIdentity> list = new ArrayList<DeveloperIdentity>(); // Developer list.
        DatabaseTable table; // Developer table.


        // Check the arguments.
        if (deviceUser == null) {

            // Cancel the process.
            throw new CantGetUserDeveloperIdentitiesException("Cant get developers from current device, arguments are null or empty.", "Plugin Identity", "Cant get developers from current device, arguments are null or empty.");
        }

        if (this.dataBase == null) {

            // Cancel the process.
            throw new CantGetUserDeveloperIdentitiesException("Cant get developers from current device, Database is closed o null.", "Plugin Identity", "Cant get developers from current device, Database is closed o null.");
        }


        // Get developers identities list.
        try {

            logManager.log(DeveloperIdentityPluginRoot.getLogLevelByClass(this.getClass().getName()), "Getting developer list.", _DEFAUL_STRING, _DEFAUL_STRING);

            // 1) Get the table.
            // logManager.log (DeveloperIdentityPluginRoot.getLogLevelByClass(this.getClass().getName()), "Getting " + DeveloperIdentityDatabaseConstants.DEVELOPER_TABLE_NAME + " table and record.", _DEFAUL_STRING, _DEFAUL_STRING);
            table = this.dataBase.getTable(DeveloperIdentityDatabaseConstants.DEVELOPER_TABLE_NAME);

            if (table == null) {

                // Table not found.
                throw new CantGetUserDeveloperIdentitiesException("Cant get developer identity list, table not \" + DeveloperIdentityDatabaseConstants.DEVELOPER_TABLE_NAME + \" found.", "Plugin Identity", "Cant get developer identity list, table not \" + DeveloperIdentityDatabaseConstants.DEVELOPER_TABLE_NAME + \" found.");
            }


            // 2) Find the developers.
            logManager.log(DeveloperIdentityPluginRoot.getLogLevelByClass(this.getClass().getName()), "Applying filter to " + DeveloperIdentityDatabaseConstants.DEVELOPER_TABLE_NAME + " table by developer public key [" + deviceUser.getPublicKey() + "].", _DEFAUL_STRING, _DEFAUL_STRING);
            table.setStringFilter(DeveloperIdentityDatabaseConstants.DEVELOPER_DEVELOPER_PUBLIC_KEY_COLUMN_NAME, deviceUser.getPublicKey(), DatabaseFilterType.EQUAL);
            table.loadToMemory();


            // 3) Get developers.
            logManager.log(DeveloperIdentityPluginRoot.getLogLevelByClass(this.getClass().getName()), "Developer identity found (" + table.getRecords().size() + ") by public key [" + deviceUser.getPublicKey() + "].", _DEFAUL_STRING, _DEFAUL_STRING);
            for (DatabaseTableRecord record : table.getRecords()) {

                // Add records to list.
                list.add(new DeveloperIdentityRecord(record.getStringValue(DeveloperIdentityDatabaseConstants.DEVELOPER_DEVELOPER_ALIAS_COLUMN_NAME),
                        record.getStringValue(DeveloperIdentityDatabaseConstants.DEVELOPER_DEVELOPER_PUBLIC_KEY_COLUMN_NAME), getDeveloperIdentiyPrivateKey(record.getStringValue(DeveloperIdentityDatabaseConstants.DEVELOPER_DEVELOPER_PUBLIC_KEY_COLUMN_NAME))));
            }


        } catch (CantLoadTableToMemoryException em) {

            // Failure unknown.
            throw new CantGetUserDeveloperIdentitiesException(em.getMessage(), em, "Plugin Identity", "Cant load " + DeveloperIdentityDatabaseConstants.DEVELOPER_TABLE_NAME + " table in memory.");

        } catch (Exception e) {

            // Failure unknown.
            throw new CantGetUserDeveloperIdentitiesException(e.getMessage(), FermatException.wrapException(e), "Plugin Identity", "Cant get developer identity list, unknown failure.");
        }


        // Return the list values.
        return list;
    }

    private void persistNewUserPrivateKeysFile(String publicKey, String privateKey) throws CantPersistPrivateKeyException {
        try {
            PluginTextFile file = this.pluginFileSystem.createTextFile(pluginId,
                    DeviceDirectory.LOCAL_USERS.getName(),
                    DEVELOPER_IDENTITY_PRIVATE_KEYS_FILE_NAME + "_" + publicKey,
                    FilePrivacy.PRIVATE,
                    FileLifeSpan.PERMANENT
            );

            file.setContent(privateKey);

            file.persistToMedia();
        } catch (CantPersistFileException e) {
            throw new CantPersistPrivateKeyException("CAN'T PERSIST PRIVATE KEY ", e, "Error persist file.", null);

        } catch (CantCreateFileException e) {
            throw new CantPersistPrivateKeyException("CAN'T PERSIST PRIVATE KEY ", e, "Error creating file.", null);
        }
    }


    public String getDeveloperIdentiyPrivateKey(String publicKey) throws CantGetDeveloperIdentityPrivateKeyException {
        String privateKey = "";
        try {
            PluginTextFile file = this.pluginFileSystem.getTextFile(pluginId,
                    DeviceDirectory.LOCAL_USERS.getName(),
                    DEVELOPER_IDENTITY_PRIVATE_KEYS_FILE_NAME + "_" + publicKey,
                    FilePrivacy.PRIVATE,
                    FileLifeSpan.PERMANENT
            );


            file.loadFromMedia();

            privateKey = file.getContent();

        } catch (CantLoadFileException e) {
            throw new CantGetDeveloperIdentityPrivateKeyException("CAN'T GET PRIVATE KEY ", e, "Error loaded file.", null);

        } catch (FileNotFoundException | CantCreateFileException e) {
            throw new CantGetDeveloperIdentityPrivateKeyException("CAN'T GET PRIVATE KEY ", e, "Error getting developer identity private keys file.", null);
        }

        return privateKey;
    }


}
