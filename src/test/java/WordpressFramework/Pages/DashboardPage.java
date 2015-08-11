package WordpressFramework.Pages;

import WordpressFramework.Selenium.Driver;

/**
 * Created by loaner on 7/29/15.
 */
public class DashboardPage {
    public static boolean isAt() {
        String title = Driver.getInstance().getTitle();
        return title.startsWith("Dashboard");
    }
}
