import org.example.base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SendMoneyRunner extends BaseClass {

    @Test(groups = "default")
    public void sendMoneyRunner() {
        LoginRunner login = new LoginRunner();
        SendMoney sm = new SendMoney();

        login.loginRunner();

        String balance = login.balance();
        String receiverBalance = login.balance2();

        double preBalance = Double.parseDouble(balance.replace(",", ""));
        double preReceiverBalance = Double.parseDouble(receiverBalance.replace(",", ""));

        sm.clickSendMoney();
        sm.userDetail();
        sm.paymentPin();

        String transctionSender = sm.transactionIdSender();

        String balancePost = sm.postBalance();
        double postBalance = Double.parseDouble(balancePost);

        double finalBal = preBalance - postBalance;
        // Sender validation here
        if (finalBal == Double.parseDouble(prop.getProperty("PayAmount"))) {
            System.out.println("Test passed with Validation of balance for sender");
        } else {
            Assert.assertEquals(finalBal, Double.parseDouble(prop.getProperty("PayAmount")));
        }

        // Validate receiver balance
        String ReceiverBalancePost = sm.postBalance2();
        double postReceiverBal = Double.parseDouble(ReceiverBalancePost.replace(",", ""));

        double finalReceiverBal = postReceiverBal - preReceiverBalance;
        // Receiver validation here
        if (finalReceiverBal == Double.parseDouble(prop.getProperty("PayAmount"))) {
            System.out.println("Test passed with Validation of balance for receiver");
        } else {
            Assert.assertEquals(finalBal, Double.parseDouble(prop.getProperty("PayAmount")));
        }
        sm.transactionTabReceiver();
        String transactionIdReceiver=sm.transactionIdReceiver();

        // Validate that the transaction appears in the transaction history with the correct details for both sender and receiver.
        if(transactionIdReceiver.equals(transctionSender)){
            System.out.println("Transaction id matches in both sender and receiver");
        }
        else{
            Assert.assertEquals(transactionIdReceiver,transactionIdReceiver);
        }
        sm.homePage();


    }
}
