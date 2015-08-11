package WordpressTests.SmokeTests;

import WordpressFramework.Pages.NewPostPage;
import WordpressFramework.Pages.PostPage;
import WordpressTests.Utilities.WordPressTests;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by loaner on 7/28/15.
 */
public class CreatePostTest extends WordPressTests {

    @Test
    public void Can_Create_Basic_Post() throws InterruptedException {

        NewPostPage.goTo();
        NewPostPage.createPost("This is the test post title").withBody("Hi, This is a test post").publish();

        NewPostPage.goToNewPost();

        Assert.assertTrue("The title is not the same", PostPage.title().equals("This is the test post title"));
    }

}

