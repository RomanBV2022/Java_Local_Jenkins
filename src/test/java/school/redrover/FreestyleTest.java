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
    @Test(dependsOnMethods = "testCreate")
    public void testCreateWithSameName() {
        String homePage = new HomePage(getDriver())
                .clickNewItem()
                .typeItemName(PROJECT_NAME)
                .getInvalidNameErrorMessage();

        Assert.assertEquals(homePage, "» A job already exists with the name ‘ProjectName’");
    }
    @Test(dependsOnMethods = "testCreate")
    public void isExistFreestyleProject() {
        boolean homePage = new HomePage(getDriver())
                .goHomePage()
                .isProjectExist(PROJECT_NAME);

        Assert.assertEquals(homePage, true);










    }


}
