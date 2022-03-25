import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
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
        dropbox();
        checkcart1();
        leastexpensive();

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

    //method to work on most expensive product
    public static void dropbox()throws InterruptedException
    {
        WebElement db = getele("/html/body/div/div/div/div[1]/div[2]/div[2]/span/select");
        Select select = new Select(db);
        select.selectByValue("hilo");
        WebElement addtocart = getele("//*[@id=\"add-to-cart-sauce-labs-fleece-jacket\"]");

        //checking if add to cart is enabled
       if(addtocart.isEnabled())
           System.out.println("Enabled");
       else
           System.out.println("Not enabled");


       //clicking on add to cart
       addtocart.click();

       WebElement remove  =getele("//*[@id=\"remove-sauce-labs-fleece-jacket\"]");

       //checking if remove is enabled
        if(remove.isEnabled())
            System.out.println("Enabled");
        else
            System.out.println("Not enabled");
        //clicking on remove
        remove.click();
        
        WebElement addtocar = getele("//*[@id=\"add-to-cart-sauce-labs-fleece-jacket\"]");
        //clicking on add to cart
        addtocar.click();
    }


    //method to get element
    public static WebElement getele(String xp)
    {
        WebElement e = driver.findElement(By.xpath(xp));
        return e;
    }

    //method that performs actions on cart for the first time
    public static void checkcart1()
    {
        getele("//*[@id=\"shopping_cart_container\"]/a").click();
        getele("//*[@id=\"continue-shopping\"]").click();

    }

    public static void leastexpensive()
    {
        WebElement db = getele("/html/body/div/div/div/div[1]/div[2]/div[2]/span/select");
        Select select = new Select(db);
        select.selectByValue("lohi");
        getele("//*[@id=\"add-to-cart-sauce-labs-onesie\"]").click();

        int cartcount = Integer.parseInt(getele("//*[@id=\"shopping_cart_container\"]/a/span").getText());
        System.out.println(cartcount);
    }


    @AfterTest
    public static void quitdriver()
    {
        driver.quit();
    }
}
