/*
 * @#CompleteClientComponentRegistrationNotificationEvent.java - 2015
 * Copyright bitDubai.com., All rights reserved.
 * You may not modify, use, reproduce or distribute this software.
 * BITDUBAI/CONFIDENTIAL
 */
package com.bitdubai.fermat_p2p_api.layer.all_definition.communication.events;


import com.bitdubai.fermat_api.layer.all_definition.components.interfaces.PlatformComponentProfile;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.enums.EventType;

/**
 * The Class <code>com.bitdubai.fermat_pip_api.layer.pip_platform_service.event_manager.events.CompleteClientComponentRegistrationNotificationEvent</code> is
 * the  representation of the event for the <code>com.bitdubai.fermat_pip_api.layer.pip_platform_service.event_manager.enums.EventType.COMPLETE_CLIENT_COMPONENT_REGISTRATION_NOTIFICATION</code>.
 * <p/>
 *
 * Created by Roberto Requena - (rrequena) on 14/09/15.
 *
 * @version 1.0
 */
public class CompleteClientComponentRegistrationNotificationEvent extends AbstractP2PFermatEvent {

    /**
     * Represent the message
     */
    private String  message;

    /**
     * Constructor with parameters
     *
     * @param eventType
     */
    public CompleteClientComponentRegistrationNotificationEvent(EventType eventType) {
        super(eventType);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
