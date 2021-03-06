package com.bitdubai.fermat_dmp_plugin.layer.actor.intra_user.developer.bitdubai.version_1.event_handlers;

import com.bitdubai.fermat_api.FermatException;
import com.bitdubai.fermat_api.Service;
import com.bitdubai.fermat_api.layer.all_definition.enums.ServiceStatus;
import com.bitdubai.fermat_api.layer.all_definition.events.interfaces.FermatEventHandler;
import com.bitdubai.fermat_api.layer.all_definition.events.interfaces.FermatEventMonitor;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.event_manager.enums.EventType;
import com.bitdubai.fermat_api.layer.all_definition.events.interfaces.FermatEvent;
import com.bitdubai.fermat_api.layer.dmp_actor.intra_user.exceptions.CantAcceptIntraUserException;
import com.bitdubai.fermat_api.layer.dmp_actor.intra_user.interfaces.ActorIntraUserManager;
import com.bitdubai.fermat_api.layer.dmp_network_service.intra_user.interfaces.IntraUserManager;
import com.bitdubai.fermat_api.layer.dmp_transaction.TransactionServiceNotStartedException;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.event_manager.interfaces.EventManager;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.event_manager.events.IntraUserActorConnectionAcceptedEvent;

/**
 * Created by natalia on 14/08/15.
 */
public class IntraUserConnectionAcceptedEventHandlers implements FermatEventHandler {
    /**
     * listener  INTRA_USER_CONNECTION_ACCEPTED event
     * Change Actor status to ACCEPTED
     */
    ActorIntraUserManager actorIntraUserManager;
    EventManager eventManager;
    FermatEventMonitor fermatEventMonitor;

    IntraUserManager intraUserNetworkServiceManager;

    public void setActorIntraUserManager(ActorIntraUserManager actorIntraUserManager){
        this.actorIntraUserManager = actorIntraUserManager;

    }

    public void setEventManager(EventManager eventManager){
        this.eventManager = eventManager;

    }

    public void setEventManager(FermatEventMonitor fermatEventMonitor){
        this.fermatEventMonitor = fermatEventMonitor;

    }

    public void setIntraUserManager( IntraUserManager intraUserNetworkServiceManager){
        this.intraUserNetworkServiceManager = intraUserNetworkServiceManager;

    }

    @Override
    public void handleEvent(FermatEvent fermatEvent) throws FermatException {
        if (((Service) this.actorIntraUserManager).getStatus() == ServiceStatus.STARTED){

            try
            {
                IntraUserActorConnectionAcceptedEvent intraUserActorConnectionAcceptedEvent = (IntraUserActorConnectionAcceptedEvent) fermatEvent;
                /**
                 * Change Intra User Actor Status To Connected
                 */
                this.actorIntraUserManager.acceptIntraUser(intraUserActorConnectionAcceptedEvent.getIntraUserLoggedInPublicKey(),
                        intraUserActorConnectionAcceptedEvent.getIntraUserToAddPublicKey());
                /**
                 * Confirm connexion on Network services
                 */
                intraUserNetworkServiceManager.confirmNotification(intraUserActorConnectionAcceptedEvent.getIntraUserLoggedInPublicKey(), intraUserActorConnectionAcceptedEvent.getIntraUserToAddPublicKey());
                /**
                 * fire event "INTRA_USER_CONNECTION_ACCEPTED_NOTIFICATION"
                 */
                FermatEvent event =  eventManager.getNewEvent(EventType.INTRA_USER_CONNECTION_ACCEPTED_NOTIFICATION);
                eventManager.raiseEvent(event);

            }
            catch(CantAcceptIntraUserException e)
            {
                this.fermatEventMonitor.handleEventException(e, fermatEvent);
            }

            catch(Exception e)
            {
                this.fermatEventMonitor.handleEventException(e, fermatEvent);
            }

        }
        else
        {
            throw new TransactionServiceNotStartedException();
        }



    }
}
