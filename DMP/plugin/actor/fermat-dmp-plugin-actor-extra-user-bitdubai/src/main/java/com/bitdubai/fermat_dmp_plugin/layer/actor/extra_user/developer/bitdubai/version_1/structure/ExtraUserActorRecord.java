package com.bitdubai.fermat_dmp_plugin.layer.actor.extra_user.developer.bitdubai.version_1.structure;

import com.bitdubai.fermat_api.layer.all_definition.crypto.asymmetric.AsymmectricCryptography;
import com.bitdubai.fermat_api.layer.all_definition.enums.Actors;
import com.bitdubai.fermat_api.layer.dmp_actor.Actor;
import com.bitdubai.fermat_api.layer.dmp_actor.extra_user.exceptions.CantSignExtraUserMessageException;

/**
 * The Class <code>com.bitdubai.fermat_dmp_plugin.layer.actor.extra_user.developer.bitdubai.version_1.structure.ExtraUserActorRecord</code>
 * Implements the functionality of the actor Interface with all his methods.
 * <p/>
 * Created by Leon Acosta - (laion.cj91@gmail.com) on 02/09/2015.
 *
 * @version 1.0
 * @since Java JDK 1.7
 */
public class ExtraUserActorRecord implements Actor {


    private String actorPublicKey;

    private String name;

    private byte[] photo;

    private String privateKey;

    public ExtraUserActorRecord(String actorPublicKey, String privateKey, String name, byte[] photo) {

        this.actorPublicKey = actorPublicKey;
        this.name           = name;
        this.photo          = photo.clone();
        this.privateKey     = privateKey;

    }

    public ExtraUserActorRecord(String actorPublicKey, String privateKey, String name) {

        this.actorPublicKey = actorPublicKey;
        this.name           = name;
        this.photo          = new byte[0];
        this.privateKey     = privateKey;

    }

    @Override
    public String getActorPublicKey() {
        return actorPublicKey;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public byte[] getPhoto() {
        return this.photo.clone();
    }

    @Override
    public Actors getType() {
        return Actors.EXTRA_USER;
    }

    @Override
    public String createMessageSignature(String message) throws CantSignExtraUserMessageException {
        try {
            return AsymmectricCryptography.createMessageSignature(message, this.privateKey);
        } catch (Exception e) {
            throw new CantSignExtraUserMessageException("Fatal Error Signed message", e, "", "");
        }
    }
}
