/*
 * @#CommunicationNetworkServiceConnectionManager.java - 2015
 * Copyright bitDubai.com., All rights reserved.
 * You may not modify, use, reproduce or distribute this software.
 * BITDUBAI/CONFIDENTIAL
 */
package com.bitdubai.fermat_dap_plugin.layer.network.service.asset.transmission.developer.bitdubai.version_1.communications;

import com.bitdubai.fermat_api.layer.all_definition.components.enums.PlatformComponentType;
import com.bitdubai.fermat_api.layer.all_definition.components.interfaces.PlatformComponentProfile;
import com.bitdubai.fermat_api.layer.all_definition.network_service.enums.NetworkServiceType;
import com.bitdubai.fermat_dap_plugin.layer.network.service.asset.transmission.developer.bitdubai.version_1.AssetTransmissionPluginRoot;
import com.bitdubai.fermat_p2p_api.layer.p2p_communication.commons.client.CommunicationsClientConnection;


/**
 * The Class <code>com.bitdubai.fermat_dap_plugin.layer.network.service.asset.transmission.developer.bitdubai.version_1.communication.CommunicationRegistrationProcessNetworkServiceAgent</code>
 * <p/>
 * Created by Roberto Requena - (rart3001@gmail.com) on 04/10/15.
 *
 * @version 1.0
 * @since Java JDK 1.7
 */
public class CommunicationRegistrationProcessNetworkServiceAgent extends Thread {

    /*
     * Represent the sleep time for the read or send (5000 milliseconds)
     */
    private static final long SLEEP_TIME = 5000;

    /**
     * Represent the assetTransmissionPluginRoot
     */
    private AssetTransmissionPluginRoot assetTransmissionPluginRoot;

    /**
     * Represent the communicationsClientConnection
     */
    private CommunicationsClientConnection communicationsClientConnection;

    /**
     * Represent the active
     */
    private boolean active;

    /**
     * Constructor with parameters
     * @param assetTransmissionPluginRoot
     * @param communicationsClientConnection
     */
    public CommunicationRegistrationProcessNetworkServiceAgent(AssetTransmissionPluginRoot assetTransmissionPluginRoot, CommunicationsClientConnection communicationsClientConnection) {
        this.assetTransmissionPluginRoot = assetTransmissionPluginRoot;
        this.communicationsClientConnection = communicationsClientConnection;
        this.active = Boolean.FALSE;
    }

    /**
     * (non-javadoc)
     * @see Thread#run()
     */
    @Override
    public void run() {

        while (active){

            if (communicationsClientConnection.isRegister() && !assetTransmissionPluginRoot.isRegister()){

                /*
                 * Construct my profile and register me
                 */
                PlatformComponentProfile platformComponentProfile =  communicationsClientConnection.constructPlatformComponentProfileFactory(assetTransmissionPluginRoot.getIdentityPublicKey(), "AssetTransmissionNetworkService", "Asset Transmission Network Service ("+assetTransmissionPluginRoot.getId()+")", NetworkServiceType.ASSET_TRANSMISSION, PlatformComponentType.NETWORK_SERVICE, null);
                communicationsClientConnection.registerComponentForCommunication(platformComponentProfile);
                assetTransmissionPluginRoot.setPlatformComponentProfile(platformComponentProfile);
                assetTransmissionPluginRoot.initializeNetworkServiceConnectionManager();
                active = Boolean.FALSE;

            }else if (!assetTransmissionPluginRoot.isRegister()){
                try {

                    sleep(CommunicationRegistrationProcessNetworkServiceAgent.SLEEP_TIME);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else if (!assetTransmissionPluginRoot.isRegister()){
                active = Boolean.FALSE;
            }

        }
    }

    /**
     * (non-javadoc)
     * @see Thread#start()
     */
    @Override
    public synchronized void start() {
        this.active = Boolean.TRUE;
        super.start();
    }

    /**
     * Get the IsRunning
     * @return boolean
     */
    public boolean getActive() {
        return active;
    }
}