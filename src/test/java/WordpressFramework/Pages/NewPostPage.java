package WordpressFramework.Pages;

import WordpressFramework.Selenium.Driver;
import WordpressFramework.Navigation.LeftNavigation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by loaner on 8/3/15.
 */
public class NewPostPage {

    public static void goTo() {

        LeftNavigation.Posts.AddNew.Select();
    }

    public static CreatePostCommand createPost(String title) {
        return new CreatePostCommand(title);
    }

    public static void goToNewPost() {

        WebElement message = Driver.getInstance().findElement(By.id("message"));
        List<WebElement> linkList = message.findElements(By.tagName("a"));
        WebElement newPostLink = linkList.get(0);
        newPostLink.click();
    }

    public static boolean isInEditMode() {
        List<WebElement> h2 = Driver.getInstance().findElements(By.tagName("h2"));
        if (h2.size() > 0 ){
            return h2.get(0).getText().startsWith("Edit Page");
        }
        return false;
    }

    public static String getTitle() {
        WebElement titleBox =  Driver.getInstance().findElement(By.id("title"));
        if (titleBox != null){
            return titleBox.getAttribute("value");
        }
        return "";
    }


    public static class CreatePostCommand {
        private String title;
        private String body;

        public CreatePostCommand(String title) {
            this.title = title;
        }

        public CreatePostCommand withBody(String body) {
            this.body = body;
            return this;
        }

        public void publish() throws InterruptedException {
            WebElement titleBox = Driver.getInstance().findElement(By.name("post_title"));
            titleBox.sendKeys(title);

            Driver.getInstance().switchTo().frame("content_ifr");
            WebElement bodyBox = Driver.getInstance().switchTo().activeElement();
            bodyBox.sendKeys(body);

            Driver.getInstance().switchTo().defaultContent();

            Driver.wait(1);

            WebElement publish = Driver.getInstance().findElement(By.id("publish"));
            publish.click();
        }
    }

}
