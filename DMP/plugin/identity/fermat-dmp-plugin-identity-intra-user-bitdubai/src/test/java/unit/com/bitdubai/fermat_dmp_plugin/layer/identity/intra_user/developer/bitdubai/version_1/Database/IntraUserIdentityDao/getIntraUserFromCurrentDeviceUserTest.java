package unit.com.bitdubai.fermat_dmp_plugin.layer.identity.intra_user.developer.bitdubai.version_1.Database.IntraUserIdentityDao;

import com.bitdubai.fermat_api.layer.all_definition.crypto.asymmetric.ECCKeyPair;
import com.bitdubai.fermat_api.layer.osa_android.database_system.Database;
import com.bitdubai.fermat_api.layer.osa_android.database_system.PluginDatabaseSystem;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantCreateDatabaseException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantOpenDatabaseException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.DatabaseNotFoundException;
import com.bitdubai.fermat_api.layer.osa_android.file_system.PluginFileSystem;
import com.bitdubai.fermat_api.layer.osa_android.file_system.exceptions.CantCreateFileException;
import com.bitdubai.fermat_api.layer.osa_android.file_system.exceptions.FileNotFoundException;
import com.bitdubai.fermat_api.layer.pip_Identity.developer.exceptions.CantCreateNewDeveloperException;
import com.bitdubai.fermat_dmp_plugin.layer.identity.intra_user.developer.bitdubai.version_1.database.IntraUserIdentityDao;
import com.bitdubai.fermat_dmp_plugin.layer.identity.intra_user.developer.bitdubai.version_1.exceptions.CantGetIntraUserIdentitiesException;
import com.bitdubai.fermat_dmp_plugin.layer.identity.intra_user.developer.bitdubai.version_1.exceptions.CantGetIntraUserIdentityPrivateKeyException;
import com.bitdubai.fermat_dmp_plugin.layer.identity.intra_user.developer.bitdubai.version_1.exceptions.CantGetIntraUserIdentityProfileImageException;
import com.bitdubai.fermat_dmp_plugin.layer.identity.intra_user.developer.bitdubai.version_1.exceptions.CantInitializeIntraUserIdentityDatabaseException;
import com.bitdubai.fermat_pip_api.layer.pip_user.device_user.interfaces.DeviceUser;
import com.googlecode.catchexception.CatchException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static com.googlecode.catchexception.CatchException.catchException;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by angel on 25/8/15.
 */

@RunWith(MockitoJUnitRunner.class)
public class getIntraUserFromCurrentDeviceUserTest {

    private IntraUserIdentityDao identityDao;

    @Mock
    private Database mockDatabase;

    @Mock
    private PluginDatabaseSystem mockPluginDatabaseSystem;

    @Mock
    private DeviceUser mockDeviceUser;

    @Mock
    private PluginFileSystem mockPluginFileSystem;

    private UUID testOwnerId1;

    @Before
    public void SetUp() throws CantCreateDatabaseException, CantInitializeIntraUserIdentityDatabaseException, CantOpenDatabaseException, DatabaseNotFoundException, FileNotFoundException, CantCreateFileException {
        testOwnerId1 = UUID.randomUUID();

        when(mockPluginDatabaseSystem.openDatabase(any(UUID.class), anyString())).thenReturn(mockDatabase);

        identityDao = new IntraUserIdentityDao(mockPluginDatabaseSystem, mockPluginFileSystem, testOwnerId1);
        identityDao.setPluginDatabaseSystem(mockPluginDatabaseSystem);
    }

    @Test
    public void SetPluginTest() throws CantInitializeIntraUserIdentityDatabaseException, CantOpenDatabaseException, DatabaseNotFoundException, CantGetIntraUserIdentityPrivateKeyException, CantGetIntraUserIdentitiesException, CantCreateNewDeveloperException, CantGetIntraUserIdentityProfileImageException, FileNotFoundException, CantCreateFileException {
        identityDao.initializeDatabase();

        ECCKeyPair eccKeyPair = new ECCKeyPair();

        when(mockDeviceUser.getPublicKey()).thenReturn(eccKeyPair.getPublicKey());

        //identityDao.getIntraUserFromCurrentDeviceUser(mockDeviceUser);

        catchException(identityDao).getIntraUserFromCurrentDeviceUser(mockDeviceUser);
        assertThat(CatchException.<Exception>caughtException()).isNotNull();

    }

}