package WordpressFramework.Pages;

import WordpressFramework.Selenium.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by loaner on 7/28/15.
 */
public class LoginPage {


    public static void goTo() {

        Driver.getInstance().navigate().to(Driver.getBaseAddress() + "wp-login.php");

        // Wait for the javascript that changes focus to the username field to complete running before returning
        WebDriverWait wait = new WebDriverWait(Driver.getInstance(), 10);
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver webDriver) {
                return webDriver.switchTo().activeElement().getAttribute("id").equals("user_login");
            }
        });

        //this should work in java8 (instead of the code above, but not working here
        //wait.until(d -> d.switchTo().activeElement().getAttribute("id").equals("user_login"));
        //Driver.getInstance().switchTo().activeElement().getAttribute("id").equals("user_login")


        // I'm not seeing the focus problem that Jon is adding the waits to avoid, so
        // I'm not sure whether the expected condition wait described below would fix the problem or not.
        //WebDriverWait wait = new WebDriverWait(Driver.getInstance(), 10);
        //wait.until(ExpectedConditions.elementToBeClickable(By.id("user_login")));
    }

    public static LoginCommand loginAs(String userName) {
        return new LoginCommand(userName);
    }

    public static class LoginCommand {
        private String userName;
        private String password;

        public LoginCommand(String userName) {
            this.userName = userName;
        }

        public LoginCommand withPassword(String password) {
            this.password = password;
            return this;
        }

        public void login() {
            WebElement userInput = Driver.getInstance().findElement(By.id("user_login"));
            userInput.sendKeys(userName);

            WebElement passInput = Driver.getInstance().findElement(By.id("user_pass"));
            passInput.sendKeys(password);

            WebElement loginButton = Driver.getInstance().findElement(By.id("wp-submit"));
            loginButton.click();
        }
    }
}
