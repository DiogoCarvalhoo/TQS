import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

 class SauceExampleTest {

        WebDriver browser = null;

        @BeforeEach
        public void setUp() {
            System.setProperty("webdriver.gecko.driver", "/opt/WebDriver/bin/geckodriver");  
            this.browser = new FirefoxDriver();
        }

        @AfterEach
        public void tearDown() {
            this.browser.close();  
        }

        @Test
         public void site_header_is_on_home_page() {
            this.browser.get("https://www.saucelabs.com"); 
            WebElement href = this.browser.findElement(By.xpath("//a[@href='https://accounts.saucelabs.com/']"));        
            assertTrue((href.isDisplayed()));

        }
}