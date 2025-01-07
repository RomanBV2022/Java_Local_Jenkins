package school.redrover;

import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.model.HomePage;
import school.redrover.model.SearchResultQueryPage;
import school.redrover.runner.BaseTest;

import java.util.List;

public class SearchHeaderTest  extends BaseTest {

    @Test
    public void testSearchCharacter() {
        List<String> itemList = new  HomePage(getDriver())
                .goSearchBox("A", new SearchResultQueryPage(getDriver()))
                .getSearchResultQueryText();
        Assert.assertEquals(itemList.size(), 3);
    }
}
