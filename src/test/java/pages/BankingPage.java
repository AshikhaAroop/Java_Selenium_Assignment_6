package pages;
import locatorsAndConstants.LocatorsAndConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utility.Utility;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static tests.BankingTest.driverBanking;

public class BankingPage extends Utility {

    public String addCustomerExcelPath = "src/test/resources/AddCustomerFields.xlsx";
    public String openAccSuccessAlertMsg = "Account created successfully with account Number :";
    public String addCustomerAlertMsg = "Customer added successfully with customer id :";
    public String depositSuccessExpctdMsg = "Deposit Successful";
    public String withdrawSuccessExpctdMsg = "Transaction successful";
//    public String withMoreThanAvailBalance="";
    public List<String> customersList = null;
    LocatorsAndConstants loc = new LocatorsAndConstants();

    public void navigateToModuleFromBankManagerLogin(String moduleName) throws Exception {
        if (!(moduleName.equals("Add Customer"))) {
            throw new Exception("Not valid module");

        }
        String moduleLocator = (loc.dynamicBankMngrLoginModulesL).replace("DynamicModuleName", moduleName);
        driverBanking.findElement(loc.bankManagerLoginBtnL).click();
        driverBanking.findElement(By.xpath(moduleLocator)).click();

    }

    public void verifyAddCustomerFieldsWithUniqueValues() throws IOException {

        int rownum = getRowCount(addCustomerExcelPath, "Sheet1");
//        int colnum = getCellCount(excelPath, "Sheet1", 1);

        for (int i = 1; i <= rownum; i++) {
            customersList = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                customersList.add(j, getCellDataExcel(addCustomerExcelPath, 0, i, j));
            }
            driverBanking.findElement(loc.firstNameInpL).sendKeys(customersList.get(0));
            driverBanking.findElement(loc.lastNameInpL).sendKeys(customersList.get(1));
            driverBanking.findElement(loc.postCodeInpL).sendKeys(customersList.get(2));
            driverBanking.findElement(loc.AddCustSubmitBtn).click();
            String alertMsg = driverBanking.switchTo().alert().getText();
            Assert.assertTrue(alertMsg.contains(addCustomerAlertMsg));
            driverBanking.switchTo().alert().accept();
        }
    }

    public String openAccForCustomer() {
        Select dropdown;
        String moduleLocator = (loc.dynamicBankMngrLoginModulesL).replace("DynamicModuleName", "Open Account");
        driverBanking.findElement(By.xpath(moduleLocator)).click();
        driverBanking.findElement(loc.userNameDrpdwn).click();
        String validCustname = customersList.get(0) + " " + customersList.get(1);
        WebElement userNameDrpdwn = driverBanking.findElement(loc.userNameDrpdwn);
        dropdown = new Select(userNameDrpdwn);
        dropdown.selectByVisibleText(validCustname);
        WebElement currencyDrpdwn = driverBanking.findElement(loc.currencyDrpdwn);
        dropdown = new Select(currencyDrpdwn);
        dropdown.selectByIndex(1);
        driverBanking.findElement(loc.openAccProceedBtn).click();
        String alertMsg = driverBanking.switchTo().alert().getText();
        Assert.assertTrue(alertMsg.contains(openAccSuccessAlertMsg));
        driverBanking.switchTo().alert().accept();

        return validCustname;
    }

    public void successfulUserLoginFromHome(String custName) {
        driverBanking.findElement(loc.homeBtn).click();
        driverBanking.findElement(loc.customerLoginBtn).click();
        WebElement userNameDrpdwn = driverBanking.findElement(loc.userNameDrpdwn);
        Select dropdown = new Select(userNameDrpdwn);
        dropdown.selectByVisibleText(custName);
        driverBanking.findElement(loc.loginBtn).click();
    }

    public void depositMoney(String amountToDeposit) {
        String balanceActualBeforeDeposit = driverBanking.findElement(loc.balanceAmountTxt).getText();
        driverBanking.findElement(loc.depositModuleBtn).click();
        driverBanking.findElement(loc.amtDepositAndWithdrwaInp).sendKeys(amountToDeposit);
        driverBanking.findElement(loc.depositSubmitBtn).click();
//        WebDriverWait w=new WebDriverWait(driverBanking, Duration.ofSeconds(10));
//        w.until(ExpectedConditions.visibilityOfElementLocated(loc.depositSuccessMsg));

        String depositActualMsg = driverBanking.findElement(loc.depositSuccessMsg).getText();
        Assert.assertEquals(depositActualMsg, depositSuccessExpctdMsg);
        String balanceActualAfterDeposit = driverBanking.findElement(loc.balanceAmountTxt).getText();
        int amt = Integer.parseInt(balanceActualBeforeDeposit) + Integer.parseInt(amountToDeposit);
        Assert.assertEquals(balanceActualAfterDeposit, Integer.toString(amt));

    }

    public void withdrawalWithMoreThanAvailBalance() throws InterruptedException {
        String balanceBeforeWithdraw = driverBanking.findElement(loc.balanceAmountTxt).getText();
        int amt = Integer.parseInt(balanceBeforeWithdraw) + 1;
        String balaceBeforeWithdrawal=withdrawMoney(String.valueOf(amt));
        Assert.assertTrue(driverBanking.findElement(By.xpath(loc.withdrawHugeAmtMsg)).isDisplayed());
    }
    public String withdrawMoney(String amountToWithdraw) throws InterruptedException {

        driverBanking.findElement(loc.withdrawalModuleBtn).click();
        String balanceActualBeforeWithdrawal = driverBanking.findElement(loc.balanceAmountTxt).getText();
        WebDriverWait w=new WebDriverWait(driverBanking, Duration.ofSeconds(20));
//        driverBanking.findElement(loc.amtDepositAndWithdrwaInp).click();
        Thread.sleep(500);
        driverBanking.findElement(loc.amtDepositAndWithdrwaInp).sendKeys(amountToWithdraw);
        driverBanking.findElement(loc.withdrawSubmitBtn).click();
//        w.until(ExpectedConditions.visibilityOfElementLocated(loc.withdrawSuccessMsg));
        return balanceActualBeforeWithdrawal;
        }

    public void withdrawAndVerify(String amountToWithdraw) throws InterruptedException {
        String balanceActualBeforeWithdrawal= withdrawMoney(amountToWithdraw);
        verifyMsgOnWithdrawal(withdrawSuccessExpctdMsg);
        verifyAmountAfterWithdrawal(balanceActualBeforeWithdrawal,amountToWithdraw);
    }
    public void verifyMsgOnWithdrawal(String message){
        String withdrawActualMsg = driverBanking.findElement(By.xpath(loc.withdrawSuccessMsg)).getText();
        Assert.assertEquals(withdrawActualMsg, message);
    }

    public void verifyAmountAfterWithdrawal(String balanceActualBeforeWithdrawal, String amountToWithdraw){
        String balanceActualAfterWithdrawal = driverBanking.findElement(loc.balanceAmountTxt).getText();
        int amt = Integer.parseInt(balanceActualBeforeWithdrawal) - Integer.parseInt(amountToWithdraw);
        Assert.assertEquals(balanceActualAfterWithdrawal, Integer.toString(amt));

    }

    public void navigateToTransactionFromLoggedInPage() throws InterruptedException {
        Thread.sleep(10000);
        driverBanking.findElement(loc.transactionModuleBtn).click();
    }

    public void verifyTransactionAmount(String amount, String trnsType) throws InterruptedException {
        Thread.sleep(3000);
        String transactionType=(loc.transactionType).replace("transactionType",trnsType);
        String id= driverBanking.findElement(By.xpath(transactionType)).getAttribute("id");
        String replaceIdInLocator= (loc.transactionTableElement).replace("dynamicId",id);
        String newStr= (replaceIdInLocator).replace("dynamicAmount", amount);
        String actualAmt=driverBanking.findElement(By.xpath(newStr)).getText();
        Assert.assertEquals(actualAmt,amount);
    }

    public void verifyTransactionForDateRange(String amount, String trnsType) throws InterruptedException {

        driverBanking.findElement(loc.startDate).click();

    }

    public void deleteLastUserFromBankManager() throws Exception {
        driverBanking.findElement(loc.homeBtn).click();
        driverBanking.findElement(loc.bankManagerLoginBtnL).click();
        String moduleLocator = (loc.dynamicBankMngrLoginModulesL).replace("DynamicModuleName", "Customers");
        driverBanking.findElement(By.xpath(moduleLocator)).click();
        List<WebElement> element= driverBanking.findElements(By.xpath(loc.transactionEntriesList));
        int length=element.size();
        String rplcLocator=(loc.lastTransactionDelete).replace("length",Integer.toString(length));
        String accNoElement=(loc.transactionAccNo).replace("length",Integer.toString(length));
        String accNo= driverBanking.findElement(By.xpath(accNoElement)).getText();
        driverBanking.findElement(By.xpath(rplcLocator)).click();
        List<WebElement> newEntries= driverBanking.findElements(loc.listOfAccNos);
        for(WebElement entry : newEntries){
            if(Objects.equals(entry.getText(), accNo))
                throw new Exception("Customer not deleted");
        }
    }
}
