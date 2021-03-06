package unit.com.bitdubai.fermat_dmp_plugin.layer.module.intra_user.developer.bitdubai.version_1.structure.IntraUserModuleSearch;

import com.bitdubai.fermat_api.layer.dmp_actor.intra_user.interfaces.ActorIntraUserManager;
import com.bitdubai.fermat_api.layer.dmp_identity.intra_user.interfaces.IntraUserIdentityManager;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.exceptions.CantGetIntraUserSearchResult;
import com.bitdubai.fermat_api.layer.dmp_module.intra_user.interfaces.IntraUserInformation;
import com.bitdubai.fermat_api.layer.dmp_network_service.intra_user.interfaces.IntraUserManager;
import com.bitdubai.fermat_api.layer.osa_android.file_system.PluginFileSystem;
import com.bitdubai.fermat_dmp_plugin.layer.module.intra_user.developer.bitdubai.version_1.structure.IntraUserModuleSearch;
import com.bitdubai.fermat_pip_api.layer.pip_platform_service.error_manager.ErrorManager;
import com.bitdubai.fermat_pip_api.layer.pip_user.device_user.interfaces.DeviceUserManager;

import junit.framework.TestCase;

import org.fest.assertions.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static com.googlecode.catchexception.CatchException.catchException;
import static com.googlecode.catchexception.CatchException.caughtException;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by natalia on 20/08/15.
 */

@RunWith(MockitoJUnitRunner.class)
public class GetResultTest extends TestCase {

    /**
     * DealsWithErrors interface Mocked
     */
    @Mock
    private ErrorManager mockErrorManager;

    /**
     * UsesFileSystem Interface member variables.
     */
    @Mock
    private PluginFileSystem mockPluginFileSystem;

    /**
     * DealWithDeviceUserManager Interface member variables.
     */
    @Mock
    private DeviceUserManager mockDeviceUserManager;

    /**
     * DealWithIntraUserIdentityManager Interface member variables.
     */
    @Mock
    private IntraUserIdentityManager mockIntraUserIdentityManager;


    /**
     * DealWithActorIntraUserManager Interface member variables.
     */
    @Mock
    private ActorIntraUserManager mockActorIntraUserManager;


    /**
     * DealWithIntraUserNetworkServiceManager Interface member variables.
     */
    @Mock
    private IntraUserManager mockIntraUserNetworkServiceManager;




    private IntraUserModuleSearch testIntraUserModuleSearch;


    private String intraUserAlias = "intraUserTest";

    private List<IntraUserInformation> intraUserInformationList;



    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        setUpMockitoRules();

        testIntraUserModuleSearch = new IntraUserModuleSearch(mockIntraUserNetworkServiceManager, mockIntraUserIdentityManager);


    }

    public void setUpMockitoRules()  throws Exception{

    }


    @Test
    public void createIntraUserTest_CreateOk_throwsCantGetIntraUserSearchResult() throws Exception{

        testIntraUserModuleSearch.setNameToSearch(intraUserAlias);

        intraUserInformationList =testIntraUserModuleSearch.getResult();

        Assertions.assertThat(intraUserInformationList)
                .isNotNull();

    }


    @Test
    public void createIntraUserTest_Exception_throwsCantGetIntraUserSearchResult() throws Exception{


        testIntraUserModuleSearch = new IntraUserModuleSearch(null, mockIntraUserIdentityManager);

        testIntraUserModuleSearch.setNameToSearch(intraUserAlias);

        catchException(testIntraUserModuleSearch).getResult();

        assertThat(caughtException())
                .isNotNull()
                .isInstanceOf(CantGetIntraUserSearchResult.class);


    }
}
