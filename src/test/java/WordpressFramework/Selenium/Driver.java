package WordpressFramework.Selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * Created by loaner on 7/28/15.
 */
public class Driver {


    private static WebDriver instance;

    public static String getBaseAddress() {
        return baseAddress;
    }

    public static String baseAddress = "http://localhost:8888/";

    public static WebDriver getInstance() {
        return instance;
    }


    public static void initialize(){
        instance = new FirefoxDriver();
        turnOnWait();
    }

    public static void noWait(Function function, WebElement row, List<WebElement> list){
        turnOffWait();
        list = (List<WebElement>) function.apply(row);
        turnOnWait();
    }

    private static void turnOnWait() {
        instance.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private static void turnOffWait() {
        instance.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    public static void wait(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);
    }
    public static void close() {
        instance.close();
    }
}
