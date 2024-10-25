package data;

import org.testng.annotations.DataProvider;

import java.util.HashMap;
import java.util.Map;
public class TestData {
    public String getTestData(String key){
        Map<String, String> testData = new HashMap<>();
        testData.put("username", "ALilizada");
        testData.put("password", "Salam");
        testData.put("age", "26");

        return testData.getOrDefault(key, "Test data not found for given key: " + key);
    }

    @DataProvider(name = "searchData")
    public Object[][] getDataProvider(){

        return new Object[][] {
        };

    }
}
