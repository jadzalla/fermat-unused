package com.bitdubai.fermat_pip_api.layer.pip_platform_service.event_manager.listeners;

import com.bitdubai.fermat_api.layer.all_definition.events.interfaces.FermatEvent;
import com.bitdubai.fermat_api.layer.all_definition.events.interfaces.FermatEventHandler;
import com.bitdubai.fermat_api.layer.all_definition.events.interfaces.FermatEventListener;
import com.bitdubai.fermat_api.layer.all_definition.events.interfaces.FermatEventMonitor;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.event_manager.enums.EventType;

/**
 * Created by natalia on 17/08/15.
 */
public class IntraUserActorConnectionCancelledEventListener implements FermatEventListener {
    FermatEventMonitor fermatEventMonitor;
    private EventType eventType;
    private FermatEventHandler fermatEventHandler;

    public IntraUserActorConnectionCancelledEventListener(EventType eventType, FermatEventMonitor fermatEventMonitor){
        this.eventType = eventType;
        this.fermatEventMonitor = fermatEventMonitor;
    }

    @Override
    public EventType getEventType() {
        return eventType;
    }

    @Override
    public void setEventHandler(FermatEventHandler fermatEventHandler) {
        this.fermatEventHandler = fermatEventHandler;
    }

    @Override
    public FermatEventHandler getEventHandler() {
        return this.fermatEventHandler;
    }

    @Override
    public void raiseEvent(FermatEvent fermatEvent) {

        try
        {
            this.fermatEventHandler.handleEvent(fermatEvent);
        }
        catch (Exception exception)
        {
            fermatEventMonitor.handleEventException(exception, fermatEvent);
        }

    }
}
