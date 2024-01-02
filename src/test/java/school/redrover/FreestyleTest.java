package school.redrover;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.HomePage;
import school.redrover.model.base.BasePage;
import school.redrover.model.jobs.configs.FreestyleProjectConfigurePage;
import school.redrover.runner.BaseTest;

public class FreestyleTest extends BaseTest {
    private static final String PROJECT_NAME = "ProjectName";

    @Test
    public void testCreate() {
        HomePage homePage = new HomePage(getDriver())
                .clickNewItem()
                .typeItemName(PROJECT_NAME)
                .selectFreestyleProject()
                .clickOk(new FreestyleProjectConfigurePage(getDriver()))
                .goHomePage();
        Assert.assertEquals(homePage.getJobList().contains(PROJECT_NAME), true);

    }


}
