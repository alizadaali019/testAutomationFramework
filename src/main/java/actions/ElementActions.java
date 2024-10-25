package actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class ElementActions {

    public WebDriver driver;
    public ElementActions(WebDriver driver){
        this.driver= driver;
    }

    public void click(By locator){
        System.out.println("Started to click element: '" + locator + "'");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));
        try{
            WebElement element = driver.findElement(locator);
            element.click();
            System.out.println("Cliced to element: '" + locator + "'");
        }catch (org.openqa.selenium.StaleElementReferenceException e){
            WebElement element = driver.findElement(locator);
            element.click();
            System.out.println("Cliced to element: '" + locator + "'");
        }catch (org.openqa.selenium.NoSuchElementException e){
            System.out.println("Element is not available for locator: '" + locator + "'");
        }catch (org.openqa.selenium.ElementClickInterceptedException e){
            System.out.println("Element click intercepted for locator: '" + locator + "'");
        }catch (Exception e){
            System.out.println("An error occurred: '" + e.getMessage() + "'");
        }
    }

    public void sendKeys(By locator, String text){
        System.out.println("Started to send: '" + locator + "'" + "Started to send: '" + text + "'" );
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        try{
            WebElement element = driver.findElement(locator);
            element.sendKeys(text);
            System.out.println(text + " send successfully to element " + locator);
        }catch (org.openqa.selenium.NoSuchElementException e){
            System.out.println("Element is not available in the page for locator: " + locator );
        }catch (Exception e){
            System.out.println("An error occurred: '" + e.getMessage() + "'");
        }
    }

    public String getText(By locator){
        System.out.println("Started to find text of element: " + locator );
        WebElement element = driver.findElement(locator);
        String textOfElement = "";
        try{
            textOfElement = element.getText();
            if(!textOfElement.isEmpty()){
                System.out.println("Text of" + locator + "is" + textOfElement);
            }else{
                System.out.println("Element is not contains any text!");
            }
        }catch (Exception e){
            System.out.println("Unable to get text of element: " + locator + "error message is " + e.getMessage());
        }
        return textOfElement;
    }

    public void isDisplayed(By locator){
        try{
            WebElement element = driver.findElement(locator);
            if(element.isDisplayed()){
                System.out.println("Element is displayed for given locator: " + locator);
            }else{
                System.out.println("Element is not displayed for given locator: " + locator);
            }
        }catch (Exception e){
            System.out.println("Unable to get info about element display: " + locator + " error message is " + e.getMessage());
        }
    }

    public void assertText(By locator, String expectedText){
        WebElement element = driver.findElement(locator);

        try{
            String actualText = element.getText();
            if(actualText.equals(expectedText)){
                System.out.println( expectedText + " and " + actualText + "is equal");
            }else{
                System.out.println("Expected" + expectedText + "but is " + actualText);
            }
        }catch (org.openqa.selenium.NoSuchElementException e){
            System.out.println("Unable to assert text, the reason is" + e.getMessage());
        }
    }

    public void selectDropdown(By locator, int index){
        try{
            WebElement selectElement = driver.findElement(locator);
            Select objectSelect = new Select(selectElement);
            objectSelect.selectByIndex(index);
            System.out.println("Element selected successfully");
        }catch (Exception e){
            System.out.println("Selecting from dropdown is failed, posible cause is " + e.getMessage());
        }
    }

    public void selectDropdown(By locator, String visibleText){
        try{
            WebElement selectElement = driver.findElement(locator);
            Select objectSelect = new Select(selectElement);
            objectSelect.selectByVisibleText(visibleText);
            System.out.println("Element selected successfully");
        }catch (Exception e){
            System.out.println("Selecting from dropdown is failed, posible cause is " + e.getMessage());
        }
    }

    public void checkCheckbox(By locator){
        WebElement element = driver.findElement(locator);
        try{
            if(!element.isSelected()){
                element.click();
                System.out.println(locator + " checked successfully!");
            }else{
                System.out.println(locator + " is already checked!");
            }
        }catch (Exception e){
            System.out.println("Checking checkbox is failed, error is " + e.getMessage());
        }
    }

    public void uncheckCheckbox(By locator){
        WebElement element = driver.findElement(locator);
        try{
            if(element.isSelected()){
                element.click();
                System.out.println(locator + " unchecked successfully!");
            }else{
                System.out.println(locator + " is already unchecked!");
            }
        }catch (Exception e){
            System.out.println("Unchecking checkbox is failed, error is " + e.getMessage());
        }
    }

    public void hover(By locator){
        Actions action = new Actions(driver);
        try{
            WebElement element = driver.findElement(locator);
            action.scrollToElement(element);
            action.moveToElement(element).build().perform();
            System.out.println("Successfully hovered to the " + locator);
        }catch (Exception e){
            System.out.println("Failed to hover element, error is " + e.getMessage());
        }
    }

    public void switchToFrame(By locator){
        System.out.println("Trying to switch frame located in " + locator);
        try{
            driver.switchTo().frame(driver.findElement(locator));
            System.out.println("Successfully switched to frame");
        }catch (Exception e){
            System.out.println("Switching to frame is failed! Reason is " + e.getMessage());
        }
    }


    public void switchToFrame(int index){
        System.out.println("Trying to switch frame located in " + index);
        try{
            driver.switchTo().frame(index);
            System.out.println("Successfully switched to frame");
        }catch (Exception e){
            System.out.println("Switching to frame is failed! Reason is " + e.getMessage());
        }
    }


}
