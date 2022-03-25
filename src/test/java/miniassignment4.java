import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class miniassignment4
{
    static WebDriver driver;

    public static void main(String [] args)throws InterruptedException,FileNotFoundException
    {
        System.setProperty("webdriver.chrome.driver","C:\\Selenium\\chromedriver_win32 (1)\\chromedriver.exe");
        driver = new ChromeDriver();
        openurl();
        login();
    }

    //opening url
    @BeforeTest
    public static void openurl()throws InterruptedException
    {
        driver.get("https://www.saucedemo.com");
        driver.manage().window().maximize();
    }


    //logging in
    public static void login() throws FileNotFoundException {
        WebElement usn = getele("//div[@id='login_credentials']");
        String username = getlogindetails(0);
        String password = getlogindetails(1);
        getele("//input[@id='user-name']").sendKeys(username);
        getele("//input[@id='password']").sendKeys(password);
        getele("//input[@id='login-button']").click();
    }

    //getting username details from csv file
    public static String getlogindetails(int i)throws FileNotFoundException
    {
        Scanner ob = new Scanner(new File("C:\\Users\\aterahman\\HU_SELENIUM_MAVEN\\Login.csv"));
        String st = "";
        String detail="";
        while(ob.hasNext())
        {
            st = ob.next();
            String values[] = st.split(",");
            detail = values[i];
        }
        return detail;
    }

    //method to find most expensive product
    public static void dropbox()
    {
        WebElement db = getele("//select[@class='product_sort_container']");
        Select s = new Select(db);
        s.selectByIndex(4);

    }


    //method to get element
    public static WebElement getele(String xp)
    {
        WebElement e = driver.findElement(By.xpath(xp));
        return e;
    }


}
