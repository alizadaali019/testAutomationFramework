import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InvalidPriceRangeTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // ChromeDriver konfiqurasiyası
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void searchAbartWithInvalidPriceRange() {
        // Turbo.az saytına keçid
        driver.get("https://turbo.az");

        // Marka olaraq Abart seçimi
        WebElement markaDropDown = driver.findElement(By.xpath("//*[@id=\"new_q\"]/div/div[2]/div[1]/div/div[1]"));
        markaDropDown.click();

        WebElement toyotaOption = driver.findElement(By.xpath("//*[@id=\"new_q\"]/div/div[2]/div[1]/div/div[2]/div/div[2]/div[1]"));
        toyotaOption.click();

        // Qiymət aralığı daxil etmək (min 9000, max 6000)
        WebElement priceFromShow = driver.findElement(By.xpath("//*[@id=\"new_q\"]/div/div[3]/div[1]/div/div[1]/div/label"));
        priceFromShow.click();
        WebElement priceFrom = driver.findElement(By.xpath("//*[@id=\"new_q\"]/div/div[3]/div[1]/div/div[1]/div/input"));
        priceFrom.sendKeys("9000"); // Minimum qiymət: 9,000 AZN

        WebElement priceToShow = driver.findElement(By.xpath("//*[@id=\"new_q\"]/div/div[3]/div[1]/div/div[2]/div/label"));
        priceToShow.click();
        WebElement priceTo = driver.findElement(By.xpath("//*[@id=\"new_q\"]/div/div[3]/div[1]/div/div[2]/div/input"));
        priceTo.sendKeys("6000"); // Maksimum qiymət: 6,000 AZN

        WebElement showAnnouncement = driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[3]/form[1]/div[1]/div[5]/div[2]/button[1]"));
        showAnnouncement.click();

        WebElement noResultText = driver.findElement(By.xpath("/html[1]/body[1]/div[5]/div[3]/div[2]/div[1]/div[1]/div[1]/p[1]"));

        String actualText = noResultText.getText();

        String expectedText = "Təəssüf ki, axtarışınız əsasında heç nə tapılmadı.";


        Assert.assertEquals(actualText.trim(), expectedText, "Mətn düzgün deyil.");

    }

    @AfterClass
    public void tearDown() {
        // Testdən sonra brauzeri bağlamaq
//        if (driver != null) {
//            driver.quit();
//        }
    }
}
