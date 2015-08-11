package WordpressFramework.Pages;

import WordpressFramework.Selenium.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


/**
 * Created by loaner on 8/3/15.
 */
public class PostPage {
    public static String title() {
        WebElement postTitle = Driver.getInstance().findElement(By.className("entry-title"));
        String title = postTitle.getText();
        if (title != null)
            return title;
        return "";
    }
}
