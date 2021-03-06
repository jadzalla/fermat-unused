package com.bitdubai.fermat_ccp_api.layer.network_service.crypto_addresses.events;

import com.bitdubai.fermat_api.layer.all_definition.enums.Actors;
import com.bitdubai.fermat_api.layer.all_definition.enums.interfaces.FermatEventEnum;
import com.bitdubai.fermat_ccp_api.all_definition.events.AbstractCCPEvent;

import java.util.UUID;

/**
 * The event <code>com.bitdubai.fermat_ccp_api.layer.network_service.crypto_addresses.events.CryptoAddressRequestedEvent</code>
 *
 * Created by  by Leon Acosta (laion.cj91@gmail.com) on 22/09/2015.
 */
public class CryptoAddressRequestedEvent extends AbstractCCPEvent {

    private Actors actorType;

    private UUID requestId;

    public CryptoAddressRequestedEvent(FermatEventEnum eventType) {
        super(eventType);
    }

    public UUID getRequestId() {
        return requestId;
    }

    public Actors getActorType() {
        return actorType;
    }

    public void setActorType(final Actors actorType) {
        this.actorType = actorType;
    }

    public void setRequestId(final UUID requestId) {
        this.requestId = requestId;
    }
}
