
import actions.ApiTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class ApiTestAutomation {

    private ApiTest apiTest;

    @BeforeClass
    public void setup() {
        apiTest = new ApiTest();
    }

    @Test
    public void testGetRequest() {
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        Response response = apiTest.getApiResponse(url);
        ApiTest.validateResponse(response, 200);
    }

    @Test
    public void testPostRequest() {
        String url = "https://jsonplaceholder.typicode.com/posts";

        // JSON obyektini hazırlayın
        String requestBody = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(url);

        ApiTest.validateResponse(response, 201);
    }


    @Test
    public void testPostCommentRequest() {
        String url = "https://jsonplaceholder.typicode.com/comments";

        // Şərh üçün JSON obyektini hazırlayın
        String requestBody = "{\"postId\": 1, \"name\": \"Test User\", \"email\": \"testuser@example.com\", \"body\": \"This is a test comment.\"}";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post(url);

        // Uğurlu status kodunu yoxlayın
        ApiTest.validateResponse(response, 201);

        // Geri qaytarılan `comment` ID-ni və şərh məlumatlarını yoxlayın
        String returnedName = response.jsonPath().getString("name");
        String returnedEmail = response.jsonPath().getString("email");

        Assert.assertEquals(returnedName, "Test User", "Name in response does not match!");
        Assert.assertEquals(returnedEmail, "testuser@example.com", "Email in response does not match!");

        System.out.println("Response body: " + response.getBody().asString());
    }

    @Test
    public void testPutRequest() {
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        // JSON obyektini String formatında hazırlayın
        String requestBody = "{\"id\": 1, \"title\": \"foo updated\", \"body\": \"bar updated\", \"userId\": 1}";

        Response response = RestAssured.given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .put(url);

        ApiTest.validateResponse(response, 200);
    }

    @Test
    public void testDeleteRequest() {
        String url = "https://jsonplaceholder.typicode.com/posts/1";

        Response response = apiTest.deleteApiRequest(url);
        ApiTest.validateResponse(response, 200);
    }
}
