import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AbartWrongSearch {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void searchToyotaByPrice() {
        driver.get("https://turbo.az");

        WebElement markaDropDown = driver.findElement(By.xpath("//*[@id=\"new_q\"]/div/div[2]/div[1]/div/div[1]"));
        markaDropDown.click();

        WebElement toyotaOption = driver.findElement(By.xpath("//*[@id=\"new_q\"]/div/div[2]/div[1]/div/div[2]/div/div[2]/div[1]"));
        toyotaOption.click();

        WebElement yearFromOpen = driver.findElement(By.xpath("//*[@id=\"new_q\"]/div/div[3]/div[4]/div/div[1]/div/div[1]"));
        yearFromOpen.click();

        WebElement yearFrom = driver.findElement((By.xpath("/html[1]/body[1]/div[5]/div[3]/form[1]/div[1]/div[3]/div[4]/div[1]/div[1]/div[1]/div[2]/div[1]/div[6]/div[1]/span[1]")));
        yearFrom.click();


        WebElement yearToOpen = driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[3]/form[1]/div[1]/div[3]/div[4]/div[1]/div[2]/div[1]/div[1]/div[1]"));
        yearToOpen.click();

        WebElement yearTo = driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[3]/form[1]/div[1]/div[3]/div[4]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/div[1]/span[1]"));
        yearTo.click();

        WebElement showAnnouncement = driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[3]/form[1]/div[1]/div[5]/div[2]/button[1]"));
        showAnnouncement.click();

        WebElement noResultText = driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[3]/div[2]/div[1]/div[1]/div[1]/p[1]"));

        String actualText = noResultText.getText();

        String expectedText = "Təəssüf ki, axtarışınız əsasında heç nə tapılmadı.";


        Assert.assertEquals(actualText.trim(), expectedText, "Mətn düzgün deyil.");

    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
