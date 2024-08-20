package test;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class Tests {
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        DesiredCapabilities caps = new DesiredCapabilities();
        System.setProperty("web-driver.chrome.driver", "Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");
    }

    @Test
    public void successLogin(){
        driver.findElement(By.xpath("//input[@name='user-name']")).sendKeys("standard_user");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("secret_sauce");
        driver.findElement(By.xpath("//input[@name='login-button']")).click();
/*
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
*/
        String textLogo = driver.findElement(By.xpath("//div[@class='app_logo']")).getText();
        System.out.println(textLogo);

       Assert.assertTrue(textLogo.contains("Swag Labs"));
    }

    @Test
    public void failedLogin (){
        driver.findElement(By.xpath("//input[@name='user-name']")).sendKeys("standard_uXer");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("secret_sauXe");
        driver.findElement(By.xpath("//input[@name='login-button']")).click();

        String failedText = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        System.out.println(failedText);

       // Assert.assertTrue(failedText.contains("Epic sadface: Username and password do not match any user in this service"));  /* Una opcion de hacer el Assert*/
        Assert.assertTrue(driver.findElement(By.xpath("//h3[@data-test='error']")).getText().contains("Epic sadface: Username and password do not match any user in this service"));  /* Otra  opcion de hacer el Assert*/
    }

    @AfterMethod
    public void closeTest(){
        driver.close();
    }
}


//this is a commment
