/*
 * @#CompleteComponentRegistrationNotificationEvent.java - 2015
 * Copyright bitDubai.com., All rights reserved.
 * You may not modify, use, reproduce or distribute this software.
 * BITDUBAI/CONFIDENTIAL
 */
package com.bitdubai.fermat_p2p_api.layer.all_definition.communication.events;


import com.bitdubai.fermat_api.layer.all_definition.components.interfaces.PlatformComponentProfile;
import com.bitdubai.fermat_p2p_api.layer.all_definition.communication.enums.EventType;

/**
 * The Class <code>com.bitdubai.fermat_pip_api.layer.pip_platform_service.event_manager.events.CompleteComponentRegistrationNotificationEvent</code> is
 * the  representation of the event for the <code>com.bitdubai.fermat_pip_api.layer.pip_platform_service.event_manager.enums.EventType.COMPONENT_REGISTRATION_COMPLETE_NOTIFICATION</code>.
 * <p/>
 *
 * Created by Roberto Requena - (rrequena) on 14/09/15.
 *
 * @version 1.0
 */
public class CompleteComponentRegistrationNotificationEvent extends AbstractP2PFermatEvent {


    /**
     * Represent the platformComponentProfileRegistered
     */
    private PlatformComponentProfile platformComponentProfileRegistered;

    /**
     * Constructor with parameters
     *
     * @param eventType
     */
    public CompleteComponentRegistrationNotificationEvent(EventType eventType) {
        super(eventType);
    }

    /**
     * Get the PlatformComponentProfileRegistered
     * @return PlatformComponentProfile
     */
    public PlatformComponentProfile getPlatformComponentProfileRegistered() {
        return platformComponentProfileRegistered;
    }

    /**
     * Set the PlatformComponentProfileRegistered
     * @param platformComponentProfileRegistered
     */
    public void setPlatformComponentProfileRegistered(PlatformComponentProfile platformComponentProfileRegistered) {
        this.platformComponentProfileRegistered = platformComponentProfileRegistered;
    }

}
