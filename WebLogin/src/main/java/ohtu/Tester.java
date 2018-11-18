package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();

        loginSuccessful(driver);
        loginFailsRightUsernameWrongPassword(driver);
        loginFailsUsernameDoesntExist(driver);
        creatingNewAccount(driver);
        logoutAfterCreatingNewAccount(driver);
        
        driver.close();
    }
    
    private static void loginSuccessful(WebDriver driver) {
        driver.get("http://localhost:4567");
        
        sleep(1);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("akkep");
        element = driver.findElement(By.name("login"));
        
        sleep(1);
        element.submit();

        sleep(1);
    }
    
    private static void loginFailsRightUsernameWrongPassword(WebDriver driver) {
        driver.get("http://localhost:4567");
        sleep(1);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("pekka");
        element = driver.findElement(By.name("password"));
        element.sendKeys("wrong");
        element = driver.findElement(By.name("login"));
        
        sleep(1);
        element.submit();

        sleep(1);
    }
    
    private static void loginFailsUsernameDoesntExist(WebDriver driver) {
        driver.get("http://localhost:4567");
        sleep(1);
        
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("nonexistent");
        element = driver.findElement(By.name("password"));
        element.sendKeys("password");
        element = driver.findElement(By.name("login"));
        
        sleep(1);
        element.submit();

        sleep(1);
    }
    
    private static void creatingNewAccount(WebDriver driver) {
        driver.get("http://localhost:4567");
        sleep(1);
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("newuser");
        element = driver.findElement(By.name("password"));
        element.sendKeys("password");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("password");
        element = driver.findElement(By.name("signup"));
        
        sleep(1);
        element.submit();

        sleep(1);
    }
    
    private static void logoutAfterCreatingNewAccount(WebDriver driver) {
        driver.get("http://localhost:4567");
        sleep(1);
        
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(1);

        element = driver.findElement(By.name("username"));
        element.sendKeys("neweruser");
        element = driver.findElement(By.name("password"));
        element.sendKeys("password");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("password");
        element = driver.findElement(By.name("signup"));
        
        sleep(1);
        element.submit();
        sleep(1);
        
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        sleep(1);

        element = driver.findElement(By.linkText("logout"));
        sleep(1);
        element.click();
        sleep(1);
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
}
