package locatorsAndConstants;

import org.openqa.selenium.By;

public class LocatorsAndConstants {

    //locators
    public By bankManagerLoginBtnL= By.xpath("//button[text()='Bank Manager Login']");
    public By customerLoginBtn= By.xpath("//button[text()='Customer Login']");
    public String dynamicBankMngrLoginModulesL= "//button[contains(text(),'DynamicModuleName')]";
    public By firstNameInpL= By.xpath("//input[@placeholder='First Name']");
    public By lastNameInpL= By.xpath("//input[@placeholder='Last Name']");
    public By postCodeInpL= By.xpath("//input[@placeholder=\"Post Code\"]");

    public By AddCustSubmitBtn= By.xpath("//button[contains(text(),'Add Customer') and @type='submit']");
    public By userNameDrpdwn= By.xpath("//select[@name='userSelect']");
    public By currencyDrpdwn= By.xpath("//select[@name='currency']");
    public By openAccProceedBtn= By.xpath("//button[text()='Process']");

    public By homeBtn= By.xpath("//button[text()='Home']");

    public By loginBtn=By.xpath("//*[text()='Login']");
    public By depositModuleBtn= By.xpath("//button[contains(text(),'Deposit')]");
    public By amtDepositAndWithdrwaInp = By.xpath("//*[@placeholder='amount']");
    public By depositSuccessMsg= By.xpath("//*[text()='Deposit Successful']");
    public By balanceAmountTxt=By.xpath("//div[@ng-hide=\"noAccount\"]/strong[2]");
    public By depositSubmitBtn= By.xpath("//button[contains(text(),'Deposit') and @type='submit']");
    public By withdrawalModuleBtn= By.xpath("//button[contains(text(),'Withdrawl')]");
    public By withdrawSubmitBtn= By.xpath("//button[contains(text(),'Withdraw') and @type='submit']");
    public String withdrawSuccessMsg= "//*[text()='Transaction successful']";
    public String withdrawHugeAmtMsg= "//*[text()='Transaction Failed. You can not withdraw amount more than the balance.']";

    public By transactionModuleBtn= By.xpath("//button[contains(text(),'Transactions')]");

    public String transactionTableElement="//tbody/tr[starts-with(@id,'dynamicId')]/td[text()='dynamicAmount']";

    public String transactionType= "//tbody/tr[starts-with(@id,'anchor')]/td[text()='transactionType']";
    public String transactionEntriesList="//tr[starts-with(@ng-repeat,'cust in Customers')]";
    public String transactionAccNo="//tr[starts-with(@ng-repeat,'cust in Customers')][length]/td[4]";
    public String lastTransactionDelete="//tr[starts-with(@ng-repeat,'cust in Customers')][length]//*[text()='Delete']";
    public By listOfAccNos=By.xpath("//tr[starts-with(@ng-repeat,'cust in Customers')]/td[4]");
    public By startDate=By.xpath("//*[@type='datetime-local' and @id='start']");
    public By endDate=By.xpath("//*[@type='datetime-local' and @id='end']");

    //demoqa

    public By newTabBtn= By.xpath("//button[text()='New Tab']");
    public By newWindowBtn= By.xpath("//button[text()='New Window']");
    public By newnewWindwMsgBtn= By.xpath("//button[text()='New Window Message']");

    public By newTabWindowText= By.xpath("//*[text()=\"This is a sample page\"]");

    public String childFrame="//iframe[contains(@srcdoc,\"Child Iframe\")]";
    public By datePickerFld=By.xpath("//input[@id=\"datePickerMonthYearInput\"]");

    public String date="//*[@class=\"react-datepicker__month\"]/div/div[text()='select_date']";
}

