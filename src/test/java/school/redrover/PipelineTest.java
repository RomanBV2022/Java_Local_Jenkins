package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

public class PipelineTest extends BaseTest {

    private void createPipeline(String pipelineName) {
        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();
        getDriver().findElement(By.className("jenkins-input")).sendKeys(pipelineName);
        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getDriver().findElement(By.xpath("//button[@id = 'ok-button']")).click();

        getDriver().findElement(By.xpath("//button[@name = 'Submit']")).click();

        getDriver().findElement(By.id("jenkins-name-icon")).click();
    }

    @Test
    public void testCreateWithValidName() {
        final String pipelineName = "PipelineName";

        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();
        getDriver().findElement(By.className("jenkins-input")).sendKeys(pipelineName);
        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        getDriver().findElement(By.xpath("//button[@id = 'ok-button']")).click();

        getDriver().findElement(By.xpath("//button[@name = 'Submit']")).click();
        getDriver().findElement(By.id("jenkins-name-icon")).click();

        getDriver().findElement(By.xpath("//td/a[@href = 'job/" + pipelineName + "/']")).click();

        Assert.assertEquals(getDriver().getTitle(),pipelineName + " [Jenkins]");
    }

    @Test
    public void testCreateWithEmptyName() {
        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();

        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();

        Assert.assertEquals(
                getDriver().findElement(By.xpath("//*[@id=\"itemname-required\"]")).getText(),
                "» This field cannot be empty, please enter a valid name");
        Assert.assertTrue(
                getDriver().findElement(By.cssSelector(".disabled")).isDisplayed());
    }

    @Test
    public void testCreateWithDublicateName() {
        final String pipelineName = "PipelineName";
        createPipeline(pipelineName);

        getDriver().findElement(By.xpath("//a[@href = '/view/all/newJob']")).click();
        getDriver().findElement(By.className("jenkins-input")).sendKeys(pipelineName);

        getDriver().findElement(By.className("org_jenkinsci_plugins_workflow_job_WorkflowJob")).click();
        Assert.assertEquals(
                getDriver().findElement(By.xpath("//*[@id=\"itemname-invalid\"]")).getText(),
                "» A job already exists with the name ‘" + pipelineName + "’");


        getDriver().findElement(By.xpath("//button[@id = 'ok-button']")).click();

        Assert.assertTrue(getDriver().findElement(By.cssSelector("#main-panel")).getText().contains("A job already exists with the name ‘" + pipelineName + "’"));

    }

    @Test
    public void testRenamePipeline() {
        String pipelineNameForCreate = "Hello";
        String pipelineNameForRename = "HaveAGoodDay";

        createPipeline(pipelineNameForCreate);

//        new Actions(getDriver())
//                .moveToElement(getDriver().findElement(By.xpath("//td/a[@href = 'job/" + pipelineNameForCreate + "/']")))
//                .perform();
//        getDriver().findElement(By.xpath("//button[@data-href = 'http://localhost:8080/job/" + pipelineNameForCreate + "/']")).click();

        getDriver().findElement(By.xpath("//*[@id='job_" + pipelineNameForCreate+"']/td[3]/a")).click();
        getDriver().findElement(By.xpath("//a[@href = '/job/" + pipelineNameForCreate + "/confirm-rename']")).click();

        getDriver().findElement(By.cssSelector(".jenkins-input.validated")).clear();
        getDriver().findElement(By.cssSelector(".jenkins-input.validated")).sendKeys(pipelineNameForRename);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        getDriver().findElement(By.xpath("//a[@id= 'jenkins-home-link']")).click();

        Assert.assertTrue(getDriver().findElement(By.xpath("//td/a[@href='job/"+ pipelineNameForRename +"/']")).isDisplayed());
    }

    @Test
    public void testPipelineRename() {
        final String pipelineName = "PipelineName";
        final String newPipelineName = "NewPipelineName";

        createPipeline(pipelineName);

        getDriver().findElement(By.xpath("//span[contains(text(),'" + pipelineName + "')]")).click();
        getDriver().findElement(By.xpath("//a[contains(@href,'rename')]")).click();

        getDriver().findElement(By.name("newName")).sendKeys(Keys.CONTROL+"a");
        getDriver().findElement(By.name("newName")).sendKeys(newPipelineName);
        getDriver().findElement(By.xpath("//button[@name='Submit']")).click();

        String confirmingName = getDriver().findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(confirmingName, "Pipeline " + newPipelineName);
    }





}