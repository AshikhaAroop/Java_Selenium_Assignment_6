package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.DemoqaPage;
import utility.Utility;

public class DemoqaTest extends Utility {

    public static WebDriver driverDemoqa;
    public String[] endPoints ={"browser-windows","nestedframes","date-picker","menu"};
    DemoqaPage page=new DemoqaPage();

    @Test
    @Parameters({"browser", "baseUrl2"})
    public void verifyTextDisplayOnEachClick(String browser, String baseUrl) throws Exception {
        String newBaseUrl= baseUrl+endPoints[0];
        driverDemoqa=setup(browser, newBaseUrl);
        page.newTabText();
        page.newWindowText();
        page.newWindowMessageText();

    }

    @Test
    @Parameters({"browser", "baseUrl2"})
    public void verifyFramesText(String browser, String baseUrl) throws Exception {
        String newBaseUrl= baseUrl+endPoints[1];
        driverDemoqa=setup(browser, newBaseUrl);
        page.frames();

    }

    @Test
    @Parameters({"browser", "baseUrl2"})
    public void verifyDatePicker(String browser, String baseUrl) throws Exception {
        String newBaseUrl= baseUrl+endPoints[2];
        driverDemoqa=setup(browser, newBaseUrl);
        page.datePicker();

    }

    @Test
    @Parameters({"browser", "baseUrl2"})
    public void verifyMenuItemSelection(String browser, String baseUrl) throws Exception {
        String newBaseUrl= baseUrl+endPoints[3];
        driverDemoqa=setup(browser, newBaseUrl);
        page.menuItem();

    }
}
