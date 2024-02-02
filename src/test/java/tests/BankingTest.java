package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pages.BankingPage;
import utility.Utility;

public class BankingTest extends Utility {

    public static WebDriver driverBanking;
    public int[] amt ={50000,10000};
    public String[] BankManagerModules={"Add Customer","Open Account","Customers"};
    BankingPage page=new BankingPage();

    @BeforeTest
    @Parameters({"browser", "baseUrl"})
    public void launchBankingApp(String browser, String baseUrl) throws Exception {
        driverBanking=setup(browser, baseUrl);


    }

//    @AfterTest
//    public void closeBrowser(){
//        driverBanking.quit();
//    }

    @Test
    public void bankingTests() throws Exception {
        page.navigateToModuleFromBankManagerLogin(BankManagerModules[0]);
        page.verifyAddCustomerFieldsWithUniqueValues();
        String accHolderName=page.openAccForCustomer();
        page.successfulUserLoginFromHome(accHolderName);
        page.depositMoney(String.valueOf(amt[0]));
//        page.withdrawMoney(String.valueOf(amt[1]));
        page.withdrawAndVerify(String.valueOf(amt[1]));
        page.withdrawalWithMoreThanAvailBalance();
        page.navigateToTransactionFromLoggedInPage();
        page.verifyTransactionAmount(String.valueOf(amt[0]),"Credit");
        page.verifyTransactionAmount(String.valueOf(amt[1]),"Debit");
        page.deleteLastUserFromBankManager();

    }
}
