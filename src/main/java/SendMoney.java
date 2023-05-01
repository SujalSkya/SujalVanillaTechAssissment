import io.appium.java_client.android.AndroidKeyCode;
import org.example.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class SendMoney extends BaseClass {

    public SendMoney() {
    }

    public void clickSendMoney() {

        driver.findElement(By.xpath("//android.view.View[@index='5']")).click();
        driver.findElementByAccessibilityId("Send to Khalti User\n" + "Transfer money to Khalti Friends").click();
    }

    public void userDetail() {
        driver.findElement(By.xpath("//android.widget.EditText[@hint=\"Khalti Mobile Number\"]")).click();
        driver.findElement(By.xpath("//android.widget.EditText[@hint=\"Khalti Mobile Number\"]")).sendKeys(prop.getProperty("username2"));

        driver.findElement(By.xpath("//android.widget.EditText[@hint=\"Amount\"]")).click();
        driver.findElement(By.xpath("//android.widget.EditText[@hint=\"Amount\"]")).sendKeys(prop.getProperty("PayAmount"));

        driver.findElement(By.xpath("//android.widget.EditText[@hint=\"Remarks\"]")).click();
        driver.findElement(By.xpath("//android.widget.EditText[@hint=\"Remarks\"]")).sendKeys("Send money");

        driver.hideKeyboard();

        driver.findElementByAccessibilityId("PAY RS. " + prop.getProperty("PayAmount")).click();
        driver.findElementByAccessibilityId("OK").click();
    }

    public void paymentPin() {

        driver.findElement(By.xpath("//android.widget.Button[@index='4']")).click();

        driver.findElement(By.xpath("//android.view.View[@index='2']//android.view.View[@index='0']")).click();
        driver.pressKeyCode(AndroidKeyCode.KEYCODE_0);

        driver.findElement(By.xpath("//android.view.View[@index='2']//android.view.View[@index='1']")).click();

        driver.pressKeyCode(AndroidKeyCode.KEYCODE_0);

        driver.findElement(By.xpath("//android.view.View[@index='2']//android.view.View[@index='2']")).click();
        driver.pressKeyCode(AndroidKeyCode.KEYCODE_0);

        driver.findElement(By.xpath("//android.view.View[@index='2']//android.view.View[@index='3']")).click();
        driver.pressKeyCode(AndroidKeyCode.KEYCODE_0);

        driver.findElementByAccessibilityId("CONFIRM").click();

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElementByAccessibilityId("Success")));

        driver.findElementByAccessibilityId("OK").click();
    }

    public String transactionIdReceiver(){
        String transferDetail = driver2.findElement(By.xpath("//android.view.View[starts-with(@content-desc,'Transfer')]")).getAttribute("content-desc");
        String []transaction=transferDetail.split("\n");
        String transactionId="";
        for(int i=0;i<transaction.length;i++){
            if(transaction[i].equals("Transaction ID:")){
                transactionId+=transaction[i+1];
            }
        }
        System.out.println("Transaction id is : " + transactionId);
        return transactionId;
    }

    public String transactionIdSender(){
        String transferDetail = driver.findElement(By.xpath("//android.view.View[starts-with(@content-desc,'Transfer')]")).getAttribute("content-desc");
        String []transaction=transferDetail.split("\n");
        String transactionId="";
        for(int i=0;i<transaction.length;i++){
            if(transaction[i].equals("Transaction ID:")){
                transactionId+=transaction[i+1];
            }
        }
        System.out.println("Transaction id is : " + transactionId);
        return transactionId;
    }

    public void transactionTabReceiver() {
        driver2.findElementByAccessibilityId("Transactions \n" + "Tab 4 of 5").click();
//        paymentDetail();
        driver2.findElement(By.xpath("//android.view.View[starts-with(@content-desc,'Fund Received')][1]")).click();
    }

    public String postBalance() {
        String blnce = driver.findElement(By.xpath("//android.view.View[starts-with(@content-desc,'Amount')]")).getAttribute("content-desc");
        String[] split = blnce.split("\n");
        String postBalance = "";
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("Balance")) {
                postBalance += split[i + 1].replace("Rs. ", "");
            }
        }
        System.out.println("Post Sender Balance: " + postBalance);
        return postBalance;
    }

    public String postBalance2() {
        driver2.findElementByAccessibilityId("OK").click();
        receiverHomePage();

        WebDriverWait wait = new WebDriverWait(driver2, 10);
        wait.until(ExpectedConditions.visibilityOf(driver2.findElement(By.xpath("//android.widget.ImageView[ends-with(@content-desc,'Khalti Balance')]"))));

        driver2.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String Balance = driver2.findElement(By.xpath("//android.widget.ImageView[ends-with(@content-desc,'Khalti Balance')]")).getAttribute("content-desc");
        System.out.println("Pre-Receiver Balance: " + Balance.replace("/[\n]/g", "").replace("Khalti Balance", ""));
        return Balance.replace("/[\n]/g", "").replace("Khalti Balance", "");
    }

    public void homePage() {
        driver.findElementByAccessibilityId("Home\n" + "Tab 1 of 5").click();
    }

    public void receiverHomePage() {
        driver2.findElementByAccessibilityId("Home\n" + "Tab 1 of 5").click();
    }

    public String checkBalance() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//android.widget.ImageView[ends-with(@content-desc,'Khalti Balance')]"))));

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        String Balance = driver.findElement(By.xpath("//android.widget.ImageView[ends-with(@content-desc,'Khalti Balance')]")).getAttribute("content-desc");
        System.out.println(Balance.replace("/[\n]/g", "").replace("Khalti Balance", ""));
        return Balance.replace("/[\n]/g", "").replace("Khalti Balance", "");
    }
}
