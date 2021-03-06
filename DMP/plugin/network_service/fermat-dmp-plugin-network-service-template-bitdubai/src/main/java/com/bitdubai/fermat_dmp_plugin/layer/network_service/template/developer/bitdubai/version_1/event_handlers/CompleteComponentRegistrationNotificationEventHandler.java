/*
 * @#CompleteComponentRegistrationNotificationEventHandler.java - 2015
 * Copyright bitDubai.com., All rights reserved.
 * You may not modify, use, reproduce or distribute this software.
 * BITDUBAI/CONFIDENTIAL
 */
package com.bitdubai.fermat_dmp_plugin.layer.network_service.template.developer.bitdubai.version_1.event_handlers;

import com.bitdubai.fermat_api.FermatException;
import com.bitdubai.fermat_api.Service;
import com.bitdubai.fermat_api.layer.all_definition.components.enums.PlatformComponentType;
import com.bitdubai.fermat_api.layer.all_definition.enums.ServiceStatus;
import com.bitdubai.fermat_api.layer.all_definition.events.interfaces.FermatEvent;
import com.bitdubai.fermat_api.layer.all_definition.events.interfaces.FermatEventHandler;
import com.bitdubai.fermat_api.layer.all_definition.network_service.enums.NetworkServiceType;
import com.bitdubai.fermat_dmp_plugin.layer.network_service.template.developer.bitdubai.version_1.TemplateNetworkServicePluginRoot;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.events.CompleteComponentRegistrationNotificationEvent;

/**
 * The Class <code>com.bitdubai.fermat_dmp_plugin.layer.network_service.template.developer.bitdubai.version_1.event_handlers.CompleteComponentRegistrationNotificationEventHandler</code>
 * implements the handle to the event <code>com.bitdubai.fermat_api.layer.platform_service.event_manager.events.CompleteComponentRegistrationNotificationEvent</code><p/>
 *
 * Created by Roberto Requena - (rart3001@gmail.com) on 14/09/15.
 *
 * @version 1.0
 * @since Java JDK 1.7
 */
public class CompleteComponentRegistrationNotificationEventHandler implements FermatEventHandler {

    /*
    * Represent the templateNetworkServicePluginRoot
    */
    private TemplateNetworkServicePluginRoot templateNetworkServicePluginRoot;

    /**
     * Constructor with parameter
     *
     * @param templateNetworkServicePluginRoot
     */
    public CompleteComponentRegistrationNotificationEventHandler(TemplateNetworkServicePluginRoot templateNetworkServicePluginRoot) {
        this.templateNetworkServicePluginRoot = templateNetworkServicePluginRoot;
    }

    /**
     * (non-Javadoc)
     *
     * @see FermatEventHandler#handleEvent(FermatEvent)
     *
     * @param platformEvent
     * @throws Exception
     */
    @Override
    public void handleEvent(FermatEvent platformEvent) throws FermatException {

        System.out.println("CompleteComponentRegistrationNotificationEventHandler - handleEvent platformEvent ="+platformEvent );

        if (((Service) this.templateNetworkServicePluginRoot).getStatus() == ServiceStatus.STARTED) {


            CompleteComponentRegistrationNotificationEvent completeComponentRegistrationNotificationEvent = (CompleteComponentRegistrationNotificationEvent) platformEvent;

            if (completeComponentRegistrationNotificationEvent.getPlatformComponentProfileRegistered().getPlatformComponentType() == PlatformComponentType.NETWORK_SERVICE_COMPONENT &&
                    completeComponentRegistrationNotificationEvent.getPlatformComponentProfileRegistered().getNetworkServiceType() == NetworkServiceType.NETWORK_SERVICE_TEMPLATE_TYPE) {

                /*
                 *  TemplateManager make the job
                 */
                 this.templateNetworkServicePluginRoot.handleCompleteComponentRegistrationNotificationEvent(completeComponentRegistrationNotificationEvent.getPlatformComponentProfileRegistered());

            }
        }
    }
}
