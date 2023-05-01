
import io.appium.java_client.MobileBy;
import org.example.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Login extends BaseClass {

    public Login() {
    }

    public void signIn() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebDriverWait wait1=new WebDriverWait(driver2,10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElementByAccessibilityId("LOGIN")));
        wait1.until(ExpectedConditions.visibilityOf(driver2.findElement(MobileBy.AccessibilityId("LOGIN"))));
        driver.findElementByAccessibilityId("LOGIN").click();
        driver2.findElementByAccessibilityId("LOGIN").click();
    }

    public void cred() {
        driver.findElement(By.xpath("//android.widget.EditText[@hint=\"Mobile Number / Email\"]")).click();
        driver.findElement(By.xpath("//android.widget.EditText[@hint=\"Mobile Number / Email\"]")).sendKeys(prop.getProperty("username"));
        driver.findElement(By.xpath("//android.widget.EditText[@hint=\"Password\"]")).click();
        driver.findElement(By.xpath("//android.widget.EditText[@hint=\"Password\"]")).sendKeys(prop.getProperty("password"));

        driver.findElementByAccessibilityId("LOGIN").click();
    }

    public void cred2() {
        driver2.findElement(By.xpath("//android.widget.EditText[@hint=\"Mobile Number / Email\"]")).click();
        driver2.findElement(By.xpath("//android.widget.EditText[@hint=\"Mobile Number / Email\"]")).sendKeys(prop.getProperty("username2"));
        driver2.findElement(By.xpath("//android.widget.EditText[@hint=\"Password\"]")).click();
        driver2.findElement(By.xpath("//android.widget.EditText[@hint=\"Password\"]")).sendKeys(prop.getProperty("password2"));

        driver2.findElementByAccessibilityId("LOGIN").click();
    }


    public String balance() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//android.widget.ImageView[ends-with(@content-desc,'Khalti Balance')]"))));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String Balance = driver.findElement(By.xpath("//android.widget.ImageView[ends-with(@content-desc,'Khalti Balance')]")).getAttribute("content-desc");
        System.out.println("Pre-Sender Balance: "+Balance.replace("/[\n]/g","").replace("Khalti Balance",""));
        return Balance.replace("/[\n]/g","").replace("Khalti Balance","");
    }

    public String balance2() {
        WebDriverWait wait = new WebDriverWait(driver2, 10);
        wait.until(ExpectedConditions.visibilityOf(driver2.findElement(By.xpath("//android.widget.ImageView[ends-with(@content-desc,'Khalti Balance')]"))));

        driver2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String Balance = driver2.findElement(By.xpath("//android.widget.ImageView[ends-with(@content-desc,'Khalti Balance')]")).getAttribute("content-desc");
        System.out.println("Pre-Receiver Balance: "+Balance.replace("/[\n]/g","").replace("Khalti Balance",""));
        return Balance.replace("/[\n]/g","").replace("Khalti Balance","");
    }
}