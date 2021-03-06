package unit.com.bitdubai.fermat_dmp_plugin.layer.basic_wallet.bitcoin_wallet.developer.bitdubai.version_1.structure.BitcoinWalletBasicWallet;

import com.bitdubai.fermat_dmp_plugin.layer.basic_wallet.bitcoin_wallet.developer.bitdubai.version_1.structure.BitcoinWalletBasicWallet;

import org.junit.Test;

import java.util.UUID;

import static org.fest.assertions.api.Assertions.*;

/**
 * Created by jorgegonzalez on 2015.07.10..
 */
public class ContructionTest {

    private BitcoinWalletBasicWallet testBasicWallet;
    private UUID testBasicWalletId;

    @Test
    public void Construction_ValidUUID_ObjectCreated(){
        testBasicWalletId = UUID.randomUUID();
        testBasicWallet = new BitcoinWalletBasicWallet(testBasicWalletId);
        assertThat(testBasicWallet).isNotNull();
    }
}
