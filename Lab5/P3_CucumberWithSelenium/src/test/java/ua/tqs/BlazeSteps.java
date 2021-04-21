package ua.tqs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BlazeSteps {
    private WebDriver driver;



    @Given("I am on {string}")
    public void iNavigateTo(String arg0) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(arg0);
    }

    @And("I pick {string} for departure city")
    public void iPickForDepartureCity(String arg0) {
        driver.findElement(By.name("fromPort")).click();
        driver.findElement(By.xpath("//option[@value=\'"+arg0+"\']")).click();
    }

    @And("I pick {string} for destination city")
    public void iPickForDestinationCity(String arg0) {
        driver.findElement(By.name("toPort")).click();
        driver.findElement(By.xpath("//option[@value=\'"+arg0+"\']")).click();
    }

    @And("I press {string} button")
    public void iPressButton(String arg0) {
        driver.findElement(By.xpath("//input[@value=\'"+arg0+"\']")).click();
    }

    @Then("{string} appear as h3 title")
    public void appearAsH3Title(String arg0) {
        driver.findElement(By.xpath("//h3[contains(.,\'"+arg0+":\')]")).click();
    }


    @Then("{string} appear as h2 title")
    public void appearAsH2Title(String arg0) {
        driver.findElement(By.xpath("//h2[contains(.,\'"+arg0+"\')]")).click();
    }

    @And("I fill {string} field with {string}")
    public void fillFieldWith(String arg0, String arg1) {
        if (arg0.equals("name")) {
            driver.findElement(By.id("inputName")).click();
            driver.findElement(By.id("inputName")).sendKeys(arg1);
        } else if (arg0.equals("address")) {
            driver.findElement(By.id("address")).click();
            driver.findElement(By.id("address")).sendKeys(arg1);
        } else if (arg0.equals("city")) {
            driver.findElement(By.id("city")).click();
            driver.findElement(By.id("city")).sendKeys(arg1);
        } else  if (arg0.equals("state")) {
            driver.findElement(By.id("state")).click();
            driver.findElement(By.id("state")).sendKeys(arg1);
        } else if (arg0.equals("zip code")) {
            driver.findElement(By.id("zipCode")).click();
            driver.findElement(By.id("zipCode")).sendKeys(arg1);
        }
    }

    @Then("{string} appear as h1 title")
    public void appearAsH1Title(String arg0) {
        driver.findElement(By.xpath("//h1[contains(.,\'"+arg0 +"\')]"));
    }

}