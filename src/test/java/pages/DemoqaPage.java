package pages;

import locatorsAndConstants.LocatorsAndConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utility.Utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import static tests.DemoqaTest.driverDemoqa;
public class DemoqaPage {

    LocatorsAndConstants loc=new LocatorsAndConstants();

    public void newTabText(){
        driverDemoqa.findElement(loc.newTabBtn).click();
        Set<String> windows=driverDemoqa.getWindowHandles();
        driverDemoqa.switchTo().window(driverDemoqa.getWindowHandle());
        Iterator<String>it=windows.iterator();
        String parentId=it.next();
        String childId=it.next();
        driverDemoqa.switchTo().window(childId);
        Assert.assertTrue(driverDemoqa.findElement(loc.newTabWindowText).isDisplayed());
        driverDemoqa.close();
        driverDemoqa.switchTo().window(parentId);
    }

    public void newWindowText(){
        String winHandleBefore = driverDemoqa.getWindowHandle();

        driverDemoqa.findElement(loc.newWindowBtn).click();

        // Switch to new window opened
        for(String winHandle : driverDemoqa.getWindowHandles()){
            driverDemoqa.switchTo().window(winHandle);
        }
        Assert.assertTrue(driverDemoqa.findElement(loc.newTabWindowText).isDisplayed());
        driverDemoqa.close();
        driverDemoqa.switchTo().window(winHandleBefore);
    }

    public void newWindowMessageText(){
        driverDemoqa.findElement(loc.newnewWindwMsgBtn);

        for(String winHandle : driverDemoqa.getWindowHandles()){
            driverDemoqa.switchTo().window(winHandle);
        }
        String windowBodyTxt=driverDemoqa.findElement(By.tagName("body")).getText();
        Assert.assertEquals(windowBodyTxt,"Knowledge increases by sharing but not by saving. Please share this website with your friends and in your organization.");
        driverDemoqa.quit();
    }

    public void frames(){
        driverDemoqa.switchTo().frame("frame1");//frame1 is id
        String parentBody = driverDemoqa.findElement(By.cssSelector("body")).getText();
        Assert.assertEquals(parentBody,"Parent frame");

        driverDemoqa.switchTo().frame(0);
        String childBody = driverDemoqa.findElement(By.cssSelector("body")).getText();

        Assert.assertEquals(childBody,"Child Iframe");
        driverDemoqa.quit();
    }

    public void datePicker() throws InterruptedException {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_YEAR, -2);
        String newDate = dateFormat.format(cal.getTime());
        System.out.println(newDate);
        String dateValue= newDate.substring(3,5);
        System.out.println(dateValue);
        if(dateValue.charAt(0)=='0'){
            dateValue= String.valueOf(dateValue.charAt(1));
        }
        String twoDaysBeforeCurrent=loc.date.replace("select_date",dateValue);
        driverDemoqa.findElement(loc.datePickerFld).click();
        driverDemoqa.findElement(By.xpath(twoDaysBeforeCurrent)).click();
        driverDemoqa.quit();

    }

    public void menuItem() throws InterruptedException {

        WebElement mainMenu= driverDemoqa.findElement(By.linkText("Main Item 2"));
        mainMenu.click();
        Actions actions = new Actions(driverDemoqa);

        JavascriptExecutor js = (JavascriptExecutor) driverDemoqa;
        js.executeScript("window.scrollBy(0,200)", "");
        Thread.sleep(2000);

        actions.moveToElement(mainMenu);

        Thread.sleep(3000);
        WebElement subMenu = driverDemoqa.findElement(By.linkText("SUB SUB LIST »"));

        actions.moveToElement(subMenu);

        WebElement subsubMenu = driverDemoqa.findElement(By.linkText("Sub Sub Item 2"));

        actions.moveToElement(subsubMenu);
//build()- used to compile all the actions into a single step
        actions.click().build().perform();
    }
}
