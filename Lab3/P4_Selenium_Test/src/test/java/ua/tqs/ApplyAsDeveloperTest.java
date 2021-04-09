import ua.tqs.webpages.DeveloperApplyPage;
import ua.tqs.webpages.HomePage;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.extension.ExtendWith;
import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class ApplyAsDeveloperTest {
  
  private WebDriver driver;

  public ApplyAsDeveloperTest(FirefoxDriver driver) {
    this.driver = driver;
  }

  @BeforeEach
  public void setup(){

    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.manage().deleteAllCookies();
    driver.manage().window().maximize();
  }

  @AfterEach
  public void close(){

  }

  @Test
  public void applyAsDeveloper() {

    //Create object of HomePage Class
    HomePage home = new HomePage(driver);
    assertTrue(home.isPageOpened());
    home.clickOnDeveloperApplyButton();

    //Create object of DeveloperApplyPage
    DeveloperApplyPage applyPage =new DeveloperApplyPage(driver);

    //Check if page is opened
    assertTrue(applyPage.isPageOpened());

    //Fill up data
    applyPage.setDeveloper_email("dejan@toptal.com");
    applyPage.setDeveloper_full_name("Dejan Zivanovic Automated Test");
    applyPage.setDeveloper_password("password123");
    applyPage.setDeveloper_password_confirmation("password123");

    //Click on join
    //applyPage.clickOnJoin(); 
  }

  
}