package WordpressTests.Utilities;

import WordpressFramework.Selenium.Driver;
import WordpressFramework.Pages.LoginPage;
import WordpressFramework.Workflows.PostCreator;
import org.junit.After;
import org.junit.Before;

/**
 * Created by loaner on 8/6/15.
 */
public class WordPressTests {

    @Before
    public void init(){

        Driver.initialize();

        PostCreator.initialize();

        LoginPage.goTo();
        LoginPage.loginAs("admin").withPassword("password").login();
    }

    @After
    public void cleanUp(){

        PostCreator.cleanUp();

        Driver.close();
    }


}
