package WordpressFramework.Navigation;

import WordpressFramework.Selenium.Driver;
import org.openqa.selenium.By;

/**
 * Created by loaner on 8/6/15.
 */
public class LeftNavigation {

    public static class Posts {

        public static class AddNew {
            public static void Select() { MenuSelector.Select("menu-posts", "Add New"); }
        }

        public static class AllPosts {
            public static void Select() { MenuSelector.Select("menu-posts", "All Posts"); }
        }
    }

    public static class Pages {

        public static class AllPages {

            public static void Select() { MenuSelector.Select("menu-pages", "All Pages"); }

        }
    }

    private static class MenuSelector {
        public static void Select(String topLevelMenuID, String subLevelMenuLinkText) {

            Driver.getInstance().findElement(By.id(topLevelMenuID)).click();
            Driver.getInstance().findElement(By.linkText(subLevelMenuLinkText)).click();

        }
    }
}
