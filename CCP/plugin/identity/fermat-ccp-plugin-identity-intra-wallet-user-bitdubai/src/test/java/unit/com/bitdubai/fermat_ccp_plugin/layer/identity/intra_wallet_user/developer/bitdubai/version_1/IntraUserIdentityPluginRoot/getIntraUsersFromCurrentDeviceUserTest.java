package unit.com.bitdubai.fermat_ccp_plugin.layer.identity.intra_wallet_user.developer.bitdubai.version_1.IntraUserIdentityPluginRoot;

import com.bitdubai.fermat_api.layer.all_definition.developer.DeveloperDatabaseTable;
import com.bitdubai.fermat_ccp_api.layer.identity.intra_wallet_user.exceptions.CantListIntraWalletUsersException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.Database;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTable;
import com.bitdubai.fermat_api.layer.osa_android.database_system.DatabaseTableRecord;
import com.bitdubai.fermat_api.layer.osa_android.database_system.PluginDatabaseSystem;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.CantOpenDatabaseException;
import com.bitdubai.fermat_api.layer.osa_android.database_system.exceptions.DatabaseNotFoundException;
import com.bitdubai.fermat_api.layer.osa_android.file_system.PluginFileSystem;
import com.bitdubai.fermat_ccp_plugin.layer.identity.intra_wallet_user.developer.bitdubai.version_1.IntraWalletUserIdentityPluginRoot;
import com.bitdubai.fermat_ccp_plugin.layer.identity.intra_wallet_user.developer.bitdubai.version_1.database.IntraWalletUserIdentityDatabaseConstants;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.error_manager.ErrorManager;
import com.bitdubai.fermat_pip_api.layer.pip_user.device_user.interfaces.DeviceUser;
import com.bitdubai.fermat_pip_api.layer.pip_user.device_user.interfaces.DeviceUserManager;
import com.googlecode.catchexception.CatchException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.UUID;

import static com.googlecode.catchexception.CatchException.catchException;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Created by angel on 20/8/15.
 */

@RunWith(MockitoJUnitRunner.class)
public class getIntraUsersFromCurrentDeviceUserTest {

    @Mock
    private Database mockDatabase;

    @Mock
    private PluginDatabaseSystem mockPluginDatabaseSystem;

    @Mock
    private PluginFileSystem mockPluginFileSystem;

    @Mock
    private DeveloperDatabaseTable developerDatabaseTable;

    @Mock
    private DeviceUserManager deviceUserManager;

    @Mock
    private ErrorManager errorManager;

    @Mock
    private DatabaseTable mockTable;

    @Mock
    private List<DatabaseTableRecord> mockRecords;

    @Mock
    private DatabaseTableRecord mockRecord;
    @Mock
    private DeviceUser mockDeviceUser;

    private IntraWalletUserIdentityPluginRoot pluginRoot;


    @Before
    public void SetUp() throws Exception {
        UUID testOwnerId = UUID.randomUUID();

        pluginRoot = new IntraWalletUserIdentityPluginRoot();

        when(mockPluginDatabaseSystem.openDatabase(any(UUID.class), anyString())).thenReturn(mockDatabase);
        when(mockDatabase.getTable(IntraWalletUserIdentityDatabaseConstants.INTRA_WALLET_USER_TABLE_NAME)).thenReturn(mockTable);

        pluginRoot.setPluginDatabaseSystem(mockPluginDatabaseSystem);
        pluginRoot.setPluginFileSystem(mockPluginFileSystem);
        pluginRoot.setId(testOwnerId);
        pluginRoot.setErrorManager(errorManager);
        pluginRoot.setDeviceUserManager(deviceUserManager);
        when(mockTable.getRecords()).thenReturn(mockRecords);
        when(mockTable.getEmptyRecord()).thenReturn(mockRecord);
        when(deviceUserManager.getLoggedInDeviceUser()).thenReturn(mockDeviceUser);
        when(mockTable.getRecords()).thenReturn(mockRecords);
        when(mockPluginDatabaseSystem.openDatabase(any(UUID.class), anyString())).thenReturn(mockDatabase);

        pluginRoot.start();
         }

    @Test
    public void getIntraUsersFromCurrentDeviceUserException() throws CantOpenDatabaseException, DatabaseNotFoundException, CantListIntraWalletUsersException {
        when(mockDatabase.getTable(IntraWalletUserIdentityDatabaseConstants.INTRA_WALLET_USER_TABLE_NAME)).thenReturn(null);

          catchException(pluginRoot).getAllIntraWalletUsersFromCurrentDeviceUser();
        assertThat(CatchException.<Exception>caughtException()).isNotNull();

    }
@Ignore
    @Test
    public void getIntraUsersFromCurrentDeviceUser_GetOk() throws CantOpenDatabaseException, DatabaseNotFoundException, CantListIntraWalletUsersException {

//TODO Ejecucion con OK error en for each sobre los records hay que ver como se mockea eso
        catchException(pluginRoot).getAllIntraWalletUsersFromCurrentDeviceUser();
        assertThat(CatchException.<Exception>caughtException()).isNull();

    }
}