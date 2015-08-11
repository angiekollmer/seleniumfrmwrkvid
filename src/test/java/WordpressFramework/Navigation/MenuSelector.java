package WordpressFramework.Navigation;

import WordpressFramework.Selenium.Driver;
import org.openqa.selenium.By;

/**
 * Created by loaner on 8/6/15.
 */
public class MenuSelector {

    public static void Select(String topLevelMenuID, String subLevelMenuLinkText) {

        Driver.getInstance().findElement(By.id(topLevelMenuID)).click();
        Driver.getInstance().findElement(By.linkText(subLevelMenuLinkText)).click();

    }
}
