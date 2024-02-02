package utility;

//import org.openqa.selenium.WebDriver;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Utility {
    public WebDriver driver;
    public static FileInputStream fi;
    public static XSSFWorkbook wb;
    public static XSSFSheet ws;
    public static XSSFRow row;
    public WebDriver setup(String browser, String baseUrl) throws Exception {
        if (browser.equalsIgnoreCase("firefox")) {
        driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
        driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("Edge")) {
        driver = new EdgeDriver();
        } else {
        throw new Exception("Incorrect Browser");
        }
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(baseUrl);
        iWait(20);
        return driver;
    }

    public void iWait(int sec)
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
    }


    public static String getCellDataExcel(String excelPath, int sheetAt, int row, int col) throws IOException {

        FileInputStream inputStream = new FileInputStream(excelPath);
        XSSFWorkbook workBook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workBook.getSheetAt(sheetAt); // sheetAt -0 means first sheet of the Excel
        XSSFCell cell = sheet.getRow(row).getCell(col);// to get on the cell from which we have to extract data

        String data;
        try {
            DataFormatter formatter = new DataFormatter();//typecasting the cell data to string using DataFormatter
            data = formatter.formatCellValue(cell);
        } catch (Exception e) {
            data = "";
        }

        workBook.close();
        inputStream.close();
        return data;
    }

    public static int getRowCount(String excelPath, String xlSheet) throws IOException {
        fi = new FileInputStream(excelPath);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlSheet);
        int rowCount = ws.getLastRowNum();
        wb.close();
        fi.close();
        return rowCount;
    }
    public static int getCellCount(String excelPath, String xlSheet, int rowNum) throws IOException{
        fi = new FileInputStream(excelPath);
        wb = new XSSFWorkbook(fi);
        ws = wb.getSheet(xlSheet);
        row = ws.getRow(rowNum);
        int columnCount = row.getLastCellNum();
        wb.close();
        fi.close();
        return columnCount;
    }


}
