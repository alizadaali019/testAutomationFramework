package actions;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiTest {
    public Response getApiResponse(String url){
        System.out.println("GET request sending to url: " + url);
        Response response = RestAssured.given()
                .baseUri(url)
                .when()
                .get();
        System.out.println("GET request successfully send, Status code: " + response.getStatusCode() + "Response body is " + response.getBody().asString());
        return response;
    }

    public Response postApiRequest(String url, Object body){
        System.out.println("POST request sending to url: " + url);
        Response response = RestAssured.given()
                .baseUri(url)
                .header("Content-Type","application/json")
                .body(body)
                .when()
                .post();
        System.out.println("POST request successfully send, Status code: " + response.getStatusCode() + "Response body is " + response.getBody().asString());
        return response;
    }


    public Response putApiRequest (String url, Object body){
        System.out.println("PUT request sending to url: " + url);
        Response response = RestAssured.given()
                .baseUri(url)
                .body(body)
                .when()
                .put();
        System.out.println("PUT request successfully send, Status code: " + response.getStatusCode() + "Response body is " + response.getBody().asString());
        return  response;
    }

    public Response deleteApiRequest(String url){
        System.out.println("DELETE request sending to url: " + url);
        Response response = RestAssured.given()
                .baseUri(url)
                .when()
                .delete();

        System.out.println("DELETE request successfully send status code: " + response.getStatusCode() + "Response body is" + response.getBody().asString());
        return response;
    }

    public static void validateResponse(Response response, int expectedStatusCode){
        int actualStatusCode = response.getStatusCode();

        if(actualStatusCode == expectedStatusCode){
            System.out.println("Success: Status codes match. Expected: " + expectedStatusCode + ", Actual: " + actualStatusCode);
        }else{
            System.out.println("Failure: Status code dose not match. Expected: " + expectedStatusCode + ", Actual: " + actualStatusCode);
        }
    }
}
