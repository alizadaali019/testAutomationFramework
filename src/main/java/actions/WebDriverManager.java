package actions;

import drivers.DriverFactory;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class WebDriverManager {
    public WebDriver driver;

    public WebDriver setupDriver(String browserName){
        driver = DriverFactory.createDriver(browserName);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return  driver;
    }


    public  void tearDown(){
        if(driver != null){
            driver.quit();
            System.out.println("Driver successfuly tear down!");
        }
    }
}
