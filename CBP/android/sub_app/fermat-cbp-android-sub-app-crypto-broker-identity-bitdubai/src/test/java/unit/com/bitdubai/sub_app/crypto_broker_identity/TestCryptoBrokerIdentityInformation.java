package unit.com.bitdubai.sub_app.crypto_broker_identity;

import com.bitdubai.fermat_cbp_api.layer.cbp_sub_app_module.crypto_broker_identity.interfaces.CryptoBrokerIdentityInformation;

/**
 * Created by nelson on 14/10/15.
 */
class TestCryptoBrokerIdentityInformation implements CryptoBrokerIdentityInformation {
    private String name;
    private byte[] img;

    public TestCryptoBrokerIdentityInformation(String name, byte[] img) {
        this.name = name;
        this.img = img;
    }

    @Override
    public String getPublicKey() {
        return "348u59rfjq3fj02quf029ujf0924u5nf09824";
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public byte[] getProfileImage() {
        return img;
    }
}