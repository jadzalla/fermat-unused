package com.bitdubai.fermat_dap_plugin.layer.digital_asset_transaction.asset_issuing.developer.bitdubai.version_1.structure;

import com.bitdubai.fermat_api.layer.all_definition.util.XMLParser;
import com.bitdubai.fermat_api.layer.osa_android.file_system.FileLifeSpan;
import com.bitdubai.fermat_api.layer.osa_android.file_system.FilePrivacy;
import com.bitdubai.fermat_api.layer.osa_android.file_system.PluginFileSystem;
import com.bitdubai.fermat_api.layer.osa_android.file_system.PluginTextFile;
import com.bitdubai.fermat_api.layer.osa_android.file_system.exceptions.CantCreateFileException;
import com.bitdubai.fermat_api.layer.osa_android.file_system.exceptions.CantPersistFileException;
import com.bitdubai.fermat_api.layer.osa_android.file_system.exceptions.FileNotFoundException;
import com.bitdubai.fermat_dap_api.layer.all_definition.digital_asset.DigitalAssetMetadata;
import com.bitdubai.fermat_dap_api.layer.all_definition.enums.AssetBalanceType;
import com.bitdubai.fermat_dap_api.layer.all_definition.exceptions.CantSetObjectException;
import com.bitdubai.fermat_dap_api.layer.dap_transaction.CantCreateDigitalAssetFileException;
import com.bitdubai.fermat_dap_api.layer.dap_transaction.DigitalAssetVault;
import com.bitdubai.fermat_dap_api.layer.dap_transaction.asset_issuing.exceptions.CantDeliverDigitalAssetToAssetWalletException;
import com.bitdubai.fermat_dap_api.layer.dap_wallet.asset_issuer_wallet.interfaces.AssetIssuerWalletManager;
import com.bitdubai.fermat_dap_api.layer.dap_transaction.CantDeleteDigitalAssetFromLocalStorageException;
import com.bitdubai.fermat_dap_api.layer.dap_transaction.CantGetDigitalAssetFromLocalStorageException;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.error_manager.ErrorManager;

import java.util.UUID;

/**
 * Created by Manuel Perez (darkpriestrelative@gmail.com) on 01/10/15.
 * This class must be started with the AssetIssuing Plugin
 */
public class DigitalAssetMetadataVault implements DigitalAssetVault {

    PluginFileSystem pluginFileSystem;
    UUID pluginId;
    //private final String LOCAL_STORAGE_PATH="digital-asset-metadata/";
    //private final FileLifeSpan FILE_LIFE_SPAN=FileLifeSpan.PERMANENT;
    AssetIssuerWalletManager assetIssuerWalletManager;
    ErrorManager errorManager;
    //For testing I'm gonna use this type of privacy, change to PRIVATE in production release
    //private final FilePrivacy FILE_PRIVACY=FilePrivacy.PUBLIC;

    public DigitalAssetMetadataVault(UUID pluginId, PluginFileSystem pluginFileSystem, ErrorManager errorManager) throws CantSetObjectException {
        setPluginFileSystem(pluginFileSystem);
        setPluginId(pluginId);
        setErrorManager(errorManager);
    }

    @Override
    public void setPluginId(UUID pluginId) throws CantSetObjectException{
        if(pluginId==null){
            throw new CantSetObjectException("pluginId is null");
        }
        this.pluginId=pluginId;
    }

    @Override
    public void setPluginFileSystem(PluginFileSystem pluginFileSystem) throws CantSetObjectException{
        if(pluginFileSystem==null){
            throw new CantSetObjectException("pluginFileSystem is null");
        }
        this.pluginFileSystem=pluginFileSystem;
    }

    public void setErrorManager(ErrorManager errorManager) throws CantSetObjectException{
        if(errorManager==null){
            throw new CantSetObjectException("ErrorManager is null");
        }
        this.errorManager=errorManager;
    }

    @Override
    public void persistDigitalAssetMetadataInLocalStorage(DigitalAssetMetadata digitalAssetMetadata)throws CantCreateDigitalAssetFileException {
        String digitalAssetMetadataFileName="no-file-name-assigned";
        try{
            String genesisTransaction=digitalAssetMetadata.getGenesisTransaction();
            String digitalAssetMetadataInnerXML=digitalAssetMetadata.toString();
            digitalAssetMetadataFileName=genesisTransaction+".xml";
            PluginTextFile digitalAssetMetadataFile=this.pluginFileSystem.createTextFile(this.pluginId, this.LOCAL_STORAGE_PATH, digitalAssetMetadataFileName, FILE_PRIVACY, FILE_LIFE_SPAN);
            digitalAssetMetadataFile.setContent(digitalAssetMetadataInnerXML);
            digitalAssetMetadataFile.persistToMedia();
        } catch(CantCreateFileException exception){
            throw new CantCreateDigitalAssetFileException(exception,"Persisting Digital Asset in local storage","Can't create '"+this.LOCAL_STORAGE_PATH+digitalAssetMetadataFileName+"' file");
        } catch (CantPersistFileException exception) {
            throw new CantCreateDigitalAssetFileException(exception,"Persisting Digital Asset in local storage","Can't persist '"+this.LOCAL_STORAGE_PATH+digitalAssetMetadataFileName+"' file");
        } catch (Exception exception){
            throw new CantCreateDigitalAssetFileException(exception,"Persisting Digital Asset in local storage","Unexpected exception creating '"+this.LOCAL_STORAGE_PATH+digitalAssetMetadataFileName+"' file");
        }

    }

    @Override
    public DigitalAssetMetadata getDigitalAssetMetadataFromLocalStorage(String genesisTransaction) throws CantGetDigitalAssetFromLocalStorageException {
        String digitalAssetMetadataFileName = "no-file-name-assigned";
        try {
            DigitalAssetMetadata digitalAssetMetadataObtainedFromFileStorage = new DigitalAssetMetadata();
            digitalAssetMetadataFileName = genesisTransaction + ".xml";
            PluginTextFile digitalAssetMetadataFile = this.pluginFileSystem.getTextFile(this.pluginId, this.LOCAL_STORAGE_PATH, digitalAssetMetadataFileName, FILE_PRIVACY, FILE_LIFE_SPAN);
            String digitalAssetMetadataXMLString = digitalAssetMetadataFile.getContent();
            digitalAssetMetadataObtainedFromFileStorage = (DigitalAssetMetadata) XMLParser.parseXML(digitalAssetMetadataXMLString, digitalAssetMetadataObtainedFromFileStorage);
            return digitalAssetMetadataObtainedFromFileStorage;
        } catch (FileNotFoundException exception) {
            throw new CantGetDigitalAssetFromLocalStorageException(exception, "Getting Digital Asset file from local storage", "Cannot find " + this.LOCAL_STORAGE_PATH + digitalAssetMetadataFileName + "' file");
        } catch (CantCreateFileException exception) {
            throw new CantGetDigitalAssetFromLocalStorageException(exception, "Getting Digital Asset file from local storage", "Cannot create " + this.LOCAL_STORAGE_PATH + digitalAssetMetadataFileName + "' file");
        } catch (Exception exception){
            throw new CantGetDigitalAssetFromLocalStorageException(exception, "Getting Digital Asset file from local storage","Unexpected exception getting '"+this.LOCAL_STORAGE_PATH+digitalAssetMetadataFileName+"' file");
        }

    }

    @Override
    public void deleteDigitalAssetMetadataFromLocalStorage(String genesisTransaction) throws CantDeleteDigitalAssetFromLocalStorageException {
        String digitalAssetMetadataFileName = "no-file-name-assigned";
        try{
            digitalAssetMetadataFileName = genesisTransaction + ".xml";
            PluginTextFile digitalAssetMetadataFile = this.pluginFileSystem.getTextFile(this.pluginId, this.LOCAL_STORAGE_PATH, digitalAssetMetadataFileName, FILE_PRIVACY, FILE_LIFE_SPAN);
            digitalAssetMetadataFile.delete();
        } catch (FileNotFoundException exception) {
            throw new CantDeleteDigitalAssetFromLocalStorageException(exception, "Deleting Digital Asset file from local storage", "Cannot find " + this.LOCAL_STORAGE_PATH + digitalAssetMetadataFileName + "' file");
        } catch (CantCreateFileException exception) {
            throw new CantDeleteDigitalAssetFromLocalStorageException(exception, "Deleting Digital Asset file from local storage", "Cannot create " + this.LOCAL_STORAGE_PATH + digitalAssetMetadataFileName + "' file");
        } catch (Exception exception){
            throw new CantDeleteDigitalAssetFromLocalStorageException(exception, "Deleting Digital Asset file from local storage","Unexpected exception getting '"+this.LOCAL_STORAGE_PATH+digitalAssetMetadataFileName+"' file");
        }
    }

    public void deliverDigitalAssetMetadataToAssetWallet(String genesisTransaction, AssetBalanceType assetBalanceType)throws CantDeliverDigitalAssetToAssetWalletException{
        try{
            DigitalAssetMetadata digitalAssetMetadataToDeliver=getDigitalAssetMetadataFromLocalStorage(genesisTransaction);
            //TODO:deliver
            switch (assetBalanceType.getCode()){
                case "BOOK":
                    //TODO: deliver to book credit
                    break;
                case "AVAI":
                    //TODO: deliver to available credit
                    break;
                default:
                    throw new CantDeliverDigitalAssetToAssetWalletException("Incorrect AssetBalanceType");
            }
        } catch (CantGetDigitalAssetFromLocalStorageException exception) {
            throw new CantDeliverDigitalAssetToAssetWalletException(exception,"Delivering DigitalAssetMetadata to Asset Wallet", "Cannot get the DigitalAssetMetadata from storage");
        }
    }

    public void setAssetIssuerWalletManager(AssetIssuerWalletManager assetIssuerWalletManager) throws CantSetObjectException {
        if(assetIssuerWalletManager==null){
            throw new CantSetObjectException("assetIssuerWalletManager is null");
        }
        this.assetIssuerWalletManager=assetIssuerWalletManager;
    }

}
