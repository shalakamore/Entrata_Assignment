package SeleniumTests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class EntrataTests
{
    WebDriver Driver;
    @Before
    public void setUp()
    {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        Driver = new ChromeDriver(options);
    }
    public void OpenEntrataURL(){
        Driver.get("https://www.entrata.com/b");
        Driver.manage().window().maximize();
        Driver.findElement(By.xpath("//a[@id='cookie-accept']")).click();
    }
    // This test Validates the page tile
    @Test
    public void testPageTitle(){
        //Launch Entrata URL
         OpenEntrataURL();
        //Get actual Title
        String actualTitle = Driver.getTitle();
        String expectedTitle = "Property Management Software | Entrata";
        //Validate page title
        Assert.assertEquals(expectedTitle,actualTitle);
    }
    //This test validates the error message when password is not given
    @Test
    public void testInvalidCredentials() throws InterruptedException {

        OpenEntrataURL();
        Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //Click on sign in button
        Driver.findElement(By.linkText("Sign In")).click();
        Driver.findElement(By.xpath("//a[@href='https://sso.entrata.com/entrata/login']")).click();
        // Enter username and submit form
        Driver.findElement(By.name("company_user[username]")).sendKeys("Shalakha");
        Driver.findElement(By.xpath("//button[@type='submit']")).click();
        //Error validation
        String ActualLoginError = Driver.findElement(By.id("entrata-login-error")).getText();
        String ExpectedLoginError="Please enter username and password";
        //Validate login error
        Assert.assertEquals(ExpectedLoginError,ActualLoginError);
    }
    // This test validates email field format for watch demo form
    @Test
    public void testEmailFieldFormat(){
        //Launch URL
        OpenEntrataURL();
        //Click Watch Demo
        Driver.findElement(By.xpath("//a[@href='https://go.entrata.com/watch-demo.html']")).click();
        //Fillup the form
        Driver.findElement(By.id("FirstName")).sendKeys("shalakha");
        Driver.findElement(By.id("LastName")).sendKeys("More");
        // Enter email in incorrect format
        Driver.findElement(By.id("Email")).sendKeys("shalakhamore");
        Driver.findElement(By.id("Company")).sendKeys("Entrata");
        Driver.findElement(By.id("Phone")).sendKeys("7620883136");
        Driver.findElement(By.id("Title")).sendKeys("Analyst");
        //
        WebDriverWait wait = new WebDriverWait(Driver, 10);
        WebElement element = Driver.findElement(By.xpath("//button[@type='submit']"));
        // Scroll to submit button
        JavascriptExecutor js = (JavascriptExecutor) Driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        // Wait until submit button is clickable
        element=wait.until(ExpectedConditions.visibilityOf(element));

        element = wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
        // Email id field error validation
        String EmailError = Driver.findElement(By.id("ValidMsgEmail")).getText().replaceAll("[\\n\\r]", " ");
        Assert.assertEquals("Must be valid email. example@yourdomain.com",EmailError);
    }
    //This test validates Entrata logo on watch demo page
    @Test
    public void testValidateLogoOnWatchDemoPage(){
        OpenEntrataURL();
        //Click Watch Demo
        Driver.findElement(By.xpath("//a[@href='https://go.entrata.com/watch-demo.html']")).click();
        //Find logo
        WebElement logo =Driver.findElement(By.xpath("//img[@class='img-logo'][1]"));
        //Validate Logo
        Assert.assertTrue(logo.isDisplayed());
        //Validate logo text
        String logoText = logo.getAttribute("alt");
        Assert.assertEquals("Entrata",logoText);
    }

    @After
    public void CloseBrowser()
    {
        Driver.quit();
    }

}
