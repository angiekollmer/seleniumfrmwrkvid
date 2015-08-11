package WordpressTests.PostsTests;

import WordpressFramework.Pages.ListPostsPage;
import WordpressFramework.Pages.NewPostPage;
import org.junit.Assert;
import org.junit.Test;
import WordpressFramework.Pages.ListPostsPage.PostType;
import WordpressTests.Utilities.WordPressTests;
import WordpressFramework.Workflows.PostCreator;

/**
 * Created by loaner on 8/6/15.
 */
public class AllPostsTests extends WordPressTests {

    @Test
    public void Added_Posts_Show_Up() throws InterruptedException {

        // Go to posts, get post count, store
        ListPostsPage.goTo(PostType.Posts);
        ListPostsPage.storeCount();

        // add a new post
        PostCreator.CreatePost();

        // Go to posts, get new post count
        ListPostsPage.goTo(PostType.Posts);
        Assert.assertTrue("count did not increment", (ListPostsPage.PreviousPostCount() + 1) == ListPostsPage.CurrentPostCount());

        // Check for added post
        Assert.assertTrue("New post title is not shown", ListPostsPage.DoesPostExistWithTitle(PostCreator.getPreviousTitle()));

        // Trash post, Clean up
        ListPostsPage.TrashPost(PostCreator.getPreviousTitle());
        Assert.assertTrue("Could not trash post, count did not go down", ListPostsPage.PreviousPostCount() == ListPostsPage.CurrentPostCount());

        // Check for added post
        Assert.assertTrue("Could not trash post, title is still shown", !ListPostsPage.DoesPostExistWithTitle(PostCreator.getPreviousTitle()));

    }

    @Test
    public void Can_Search_Tests() throws InterruptedException {

        //Create a new post
        PostCreator.CreatePost();

        // Go to list posts - commented this because now the searchForPost() checks and goes if necessary
     //   ListPostsPage.goTo(PostType.Posts);

        // Search for post
        ListPostsPage.searchForPost(PostCreator.getPreviousTitle());

        // Check that post shows up in results
        Assert.assertTrue(ListPostsPage.DoesPostExistWithTitle(PostCreator.getPreviousTitle()));
    }
}
