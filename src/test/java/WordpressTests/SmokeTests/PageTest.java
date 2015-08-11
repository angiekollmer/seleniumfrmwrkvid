package WordpressTests.SmokeTests;

import WordpressFramework.Pages.ListPostsPage;
import WordpressFramework.Pages.ListPostsPage.PostType;
import WordpressFramework.Pages.NewPostPage;
import WordpressTests.Utilities.WordPressTests;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by loaner on 7/28/15.
 */
public class PageTest extends WordPressTests {

    @Test
    public void can_Edit_Post(){

        ListPostsPage.goTo(PostType.Page);
        ListPostsPage.selectPost("Sample Page");

        Assert.assertTrue("Page was not in edit mode", NewPostPage.isInEditMode());
        Assert.assertTrue("Title is not correct", NewPostPage.getTitle().equals("Sample Page"));
    }
}

