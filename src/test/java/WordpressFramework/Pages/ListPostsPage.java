package WordpressFramework.Pages;

import WordpressFramework.Selenium.Driver;
import WordpressFramework.Navigation.LeftNavigation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.function.Function;

/**
 * Created by loaner on 8/3/15.
 */
public class ListPostsPage {

    private static int lastCount;

    public static void goTo(PostType type){

        switch (type){

            case Page:
                LeftNavigation.Pages.AllPages.Select();
                break;
            case Posts:
                LeftNavigation.Posts.AllPosts.Select();
        }
    }

    public static void selectPost(String title) {
        List<WebElement> linkList = Driver.getInstance().findElements(By.linkText(title));
        if (linkList.size() > 0){
            linkList.get(0).click();
        }
    }

    public static int PreviousPostCount() {
        return lastCount;
    }

    public static void storeCount() {
        lastCount = getPostCount();
    }

    private static int getPostCount() {
        String countText = Driver.getInstance().findElement(By.className("displaying-num")).getText();
     //   System.out.println(countText);
        int count = Integer.parseInt(countText.split(" ")[0]);
     //   System.out.println(count);
        return count;
    }

    public static int CurrentPostCount() {
        return getPostCount();
    }

    public static void TrashPost(final String title) {
        List<WebElement> rowList = Driver.getInstance().findElements(By.tagName("tr"));

        for (WebElement row : rowList) {

            List<WebElement> linkList = null;

            linkList = row.findElements(By.linkText(title));


        // I tried this for turning off the wait, but never got it to work


            Function<WebElement, List<WebElement>> getTitle = new Function<WebElement, List<WebElement>>() {
                public List<WebElement> apply (WebElement row){
                    List<WebElement> list = row.findElements(By.linkText(title));
                    return list;
                }
            };

            Driver.noWait(getTitle, row, linkList);


            if (linkList.size() > 0){
                Actions action = new Actions(Driver.getInstance());
                action.moveToElement(linkList.get(0));
                action.perform();
                row.findElement(By.className("submitdelete")).click();
                return;
            }
        }
    }

    public static boolean DoesPostExistWithTitle(String title) {
        List<WebElement> list = Driver.getInstance().findElements(By.linkText(title));
        return list.size() > 0;
    }

    public static void searchForPost(String searchString) {

        if (!isAt()){
            goTo(PostType.Posts);
        }
        WebElement searchBox = Driver.getInstance().findElement(By.id("post-search-input"));
        searchBox.sendKeys(searchString);

        WebElement searchButton = Driver.getInstance().findElement(By.id("search-submit"));
        searchButton.click();
    }

    public static boolean isAt() {
        String title = Driver.getInstance().getTitle();
        return title.startsWith("Posts");
    }

    public enum PostType {
        Page,
        Posts
    }

}
