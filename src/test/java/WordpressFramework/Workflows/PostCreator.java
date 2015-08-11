package WordpressFramework.Workflows;

import WordpressFramework.Pages.ListPostsPage;
import WordpressFramework.Pages.NewPostPage;

import java.util.Random;

/**
 * Created by loaner on 8/10/15.
 */
public class PostCreator {
    private static String previousTitle;
    private static String previousBody;

    public static void CreatePost() throws InterruptedException {
        previousTitle = createTitle();
        previousBody = createBody();

        NewPostPage.goTo();
        NewPostPage.createPost(previousTitle).withBody(previousBody).publish();

    }

    private static String createTitle() {
        return createRandomString() + ", title";
    }

    private static String createBody() {
        return createRandomString() + ", body";
    }

    public static String getPreviousTitle() {
        return previousTitle;
    }

    public static String getPreviousBody() {
        return previousBody;
    }

    private static String createRandomString() {
        StringBuilder builder = new StringBuilder();

        Random random = new Random();
        int cycles = random.nextInt(5);

        for (int i = 0; i < cycles; i++){
            builder.append(words[random.nextInt(words.length)]);
            builder.append(" ");
            builder.append(articles[random.nextInt(articles.length)]);
            builder.append(" ");
            builder.append(words[random.nextInt(words.length)]);
            builder.append(" ");
        }

        return builder.toString();
    }

    private static String[] words = {"hat", "sun", "car", "doll", "sleep", "cozy"};
    private static String[] articles = {"the", "an", "and", "a", "of", "to", "it"};

    public static void initialize() {
        previousTitle = null;
        previousBody = null;
    }


    public static void cleanUp() {
        if (createdAPost()){
            trashPost();
        }
    }

    private static void trashPost() {
        ListPostsPage.TrashPost(previousTitle);
    }

    private static boolean createdAPost() {
        return !(previousTitle == null);
    }
}
