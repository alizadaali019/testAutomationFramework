import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MercedesCreditSearchTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        // ChromeDriver konfiqurasiyası
    //    System.setProperty("webdriver.chrome.driver", "path/to/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void searchMercedesWithCredit() throws InterruptedException {
        // Turbo.az saytına keçid
        driver.get("https://turbo.az");

        // Marka olaraq Mercedes seçimi
        WebElement markaDropDown = driver.findElement(By.xpath("//div[text()='Marka']"));
        markaDropDown.click();
        Thread.sleep(3000);
        WebElement mercedesOption = driver.findElement(By.xpath("//span[text()='Mercedes']"));
        mercedesOption.click();


        // Kredit seçimini aktivləşdirmək
        WebElement creditCheckbox = driver.findElement(By.xpath("//label[text()='Kredit']"));
        if (!creditCheckbox.isSelected()) {
            creditCheckbox.click();
        }

        // Axtarış düyməsinə basmaq
        Thread.sleep(3000);
        WebElement searchBTN = driver.findElement(By.xpath("//button[contains(@class, 'main-search__btn')]"));
        searchBTN.click();

        // Nəticələri almaq və çap etmək (Mercedes avtomobilləri kredit ilə)
        List<WebElement> mercedesResults = driver.findElements(By.cssSelector(".products-i__link"));
        System.out.println("Mercedes avtomobilləri kredit seçimi ilə axtarış nəticələri:");
        for (WebElement result : mercedesResults) {
            System.out.println(result.getAttribute("title"));
        }
    }

    @AfterClass
    public void tearDown() {
        // Testdən sonra brauzeri bağlamaq
        if (driver != null) {
            driver.quit();
        }
    }
}
