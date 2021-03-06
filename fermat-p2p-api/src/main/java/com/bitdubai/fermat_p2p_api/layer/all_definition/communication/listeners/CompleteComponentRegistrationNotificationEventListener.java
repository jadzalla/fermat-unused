/*
 * @#CompleteComponentRegistrationNotificationEventListener.java - 2015
 * Copyright bitDubai.com., All rights reserved.
 * You may not modify, use, reproduce or distribute this software.
 * BITDUBAI/CONFIDENTIAL
 */
package com.bitdubai.fermat_p2p_api.layer.all_definition.communication.listeners;


import com.bitdubai.fermat_api.layer.all_definition.events.interfaces.FermatEventMonitor;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.enums.EventType;

/**
 * The Class <code>com.bitdubai.fermat_pip_api.layer.pip_platform_service.event_manager.listeners.CompleteComponentRegistrationNotificationEventListener</code> is
 * the event listener for the <code>com.bitdubai.fermat_pip_api.layer.pip_platform_service.event_manager.enums.EventType.COMPONENT_REGISTRATION_COMPLETE_NOTIFICATION</code>.
 * <p/>
 *
 * Created by Roberto Requena - (rrequena) on 14/09/15.
 *
 * @version 1.0
 */
public class CompleteComponentRegistrationNotificationEventListener extends BasicFermatEventListener {

    /**
     * Constructor with parameters
     *
     * @param eventType
     * @param eventMonitor
     */
    public CompleteComponentRegistrationNotificationEventListener(EventType eventType, FermatEventMonitor eventMonitor) {
        super(eventType, eventMonitor);
    }
}
