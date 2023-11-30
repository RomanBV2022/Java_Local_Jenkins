package school.redrover.model;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import school.redrover.model.base.BasePage;

public class NodeUpdateOfflineReasonPage extends BasePage {

    @FindBy(xpath = "//textarea[@name = 'offlineMessage']")
    WebElement offlineReasonInputField;

    @FindBy(name = "Submit")
    WebElement submitButton;

    public NodeUpdateOfflineReasonPage(WebDriver driver) {
        super(driver);
    }

    public NodeDetailsPage setNewNodeOfflineReason(String message) {
        offlineReasonInputField.clear();
        offlineReasonInputField.sendKeys(message);
        submitButton.click();

        return new NodeDetailsPage(getDriver());
    }
}