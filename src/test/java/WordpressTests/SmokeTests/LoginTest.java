package WordpressTests.SmokeTests;

import WordpressTests.Utilities.WordPressTests;
import org.junit.Assert;
import org.junit.Test;

import static WordpressFramework.Pages.DashboardPage.isAt;

/**
 * Created by loaner on 7/28/15.
 */
public class LoginTest extends WordPressTests {

    @Test
    public void admin_User_Can_Login(){

        Assert.assertTrue("Failed to login", isAt());
    }
}

