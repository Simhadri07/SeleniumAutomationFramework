package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utilities.ApiConfig;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class ApiSmokeTest {
    private static final String POSTS_PATH = "/posts";
    private static final String USERS_PATH = "/users";

    private io.restassured.specification.RequestSpecification request() {
        return RestAssured
                .given()
                .baseUri(ApiConfig.getBaseUrl())
                .accept(ContentType.JSON);
    }

    @Test
    public void shouldGetPostById() {
        request()
                .when()
                .get(POSTS_PATH + "/1")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1))
                .body("userId", equalTo(1));
    }

    @Test
    public void shouldGetPostsCollection() {
        request()
                .when()
                .get(POSTS_PATH)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("", hasSize(100))
                .body("id", everyItem(greaterThan(0)));
    }

    @Test
    public void shouldFilterPostsByUserId() {
        request()
                .queryParam("userId", 1)
                .when()
                .get(POSTS_PATH)
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("userId", everyItem(equalTo(1)));
    }

    @Test
    public void shouldGetUserDetails() {
        request()
                .when()
                .get(USERS_PATH + "/1")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1))
                .body("username", equalTo("Bret"))
                .body("email", equalTo("Sincere@april.biz"));
    }

    @Test
    public void shouldCreatePost() {
        String requestBody = "{\"title\":\"automation test\",\"body\":\"created by API test\",\"userId\":1}";

        Response response = request()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(POSTS_PATH);

        response.then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("title", equalTo("automation test"))
                .body("userId", equalTo(1));
    }

    @Test
    public void shouldUpdatePost() {
        String requestBody = "{\"id\":1,\"title\":\"updated title\",\"body\":\"updated body\",\"userId\":1}";

        request()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(POSTS_PATH + "/1")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1))
                .body("title", equalTo("updated title"))
                .body("body", equalTo("updated body"));
    }

    @Test
    public void shouldPartiallyUpdatePost() {
        request()
                .contentType(ContentType.JSON)
                .body("{\"title\":\"patched title\"}")
                .when()
                .patch(POSTS_PATH + "/1")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1))
                .body("title", equalTo("patched title"));
    }

    @Test
    public void shouldDeletePost() {
        request()
                .when()
                .delete(POSTS_PATH + "/1")
                .then()
                .statusCode(200);
    }

    @Test
    public void shouldReturnNotFoundForUnknownPost() {
        request()
                .when()
                .get(POSTS_PATH + "/9999")
                .then()
                .statusCode(404);
    }
}
