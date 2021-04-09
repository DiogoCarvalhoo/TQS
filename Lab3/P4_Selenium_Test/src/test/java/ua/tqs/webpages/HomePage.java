package ua.tqs.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;

    //page URL
    private static String PAGE_URL = "https://www.toptal.com";

    //locators

    @FindBy(tagName = "h1")
    WebElement heading;

    //Apply as Freelancer Button
    @FindBy(how = How.LINK_TEXT, using = "Apply as a Freelancer")
    private WebElement developerApplyButton;

    //Constructor
    public HomePage(WebDriver driver){
        this.driver=driver;
        driver.get(PAGE_URL);
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    public boolean isPageOpened() {
        return heading.getText().toString().contains("Hire the Top");
    }

    public void clickOnDeveloperApplyButton(){

        developerApplyButton.click();

    }
}