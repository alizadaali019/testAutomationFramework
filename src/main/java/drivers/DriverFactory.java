package drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
    public static WebDriver createDriver(String browser){
        WebDriver driver;
        System.out.println("Started to create driver...");
        switch (browser.toLowerCase()){

            case "chrome":
                driver = new ChromeDriver();
                System.out.println("Chrome driver created seccessfully.");
                break;

            case "safari":
                driver = new SafariDriver();
                System.out.println("Safari driver created seccessfully.");
                break;

            case "firefox":
                driver = new FirefoxDriver();
                System.out.println("FireFox driver created seccessfully.");
                break;

            case "edge":
                driver = new EdgeDriver();
                System.out.println("Edge driver created seccessfully.");
                break;

            default:
                throw new IllegalArgumentException("Browser not found: " + browser + ". Use these browsers: Chrome, Safri, FireFox, Edge");
        }

        return driver;
    }
}
