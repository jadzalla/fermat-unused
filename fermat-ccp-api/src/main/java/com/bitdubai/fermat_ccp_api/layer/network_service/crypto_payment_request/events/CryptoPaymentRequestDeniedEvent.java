package com.bitdubai.fermat_ccp_api.layer.network_service.crypto_payment_request.events;

import com.bitdubai.fermat_api.layer.all_definition.enums.interfaces.FermatEventEnum;
import com.bitdubai.fermat_ccp_api.all_definition.events.AbstractCCPEvent;

import java.util.UUID;

/**
 * The event <code>com.bitdubai.fermat_ccp_api.layer.network_service.crypto_payment_request.events.CryptoPaymentRequestDeniedEvent</code>
 * is raised when one of our requests is denied by incompatibility.
 *
 * Created by by Leon Acosta (laion.cj91@gmail.com) on 01/10/2015.
 */
public class CryptoPaymentRequestDeniedEvent extends AbstractCCPEvent {

    private UUID requestId;

    public CryptoPaymentRequestDeniedEvent(FermatEventEnum eventType) {
        super(eventType);
    }

    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestId(final UUID requestId) {
        this.requestId = requestId;
    }

}
