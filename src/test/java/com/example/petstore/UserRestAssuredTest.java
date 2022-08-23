package com.example.petstore;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public class UserRestAssuredTest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test(priority =1)
    public void CreateUser() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 0,\n" +
                                "  \"username\": \"Pranali\",\n" +
                                "  \"firstName\": \"Pranali\",\n" +
                                "  \"lastName\": \"Rane\",\n" +
                                "  \"email\": \"ranepranali1997@gmail.com\",\n" +
                                "  \"password\": \"Pranali@123\",\n" +
                                "  \"phone\": \"9130679235\",\n" +
                                "  \"userStatus\": 0\n" +
                                "}")
                .when()
                .post("https://petstore.swagger.io/v2/user");
        response.prettyPrint();
        Assertions.assertEquals(200,response.statusCode());
    }
    @Test(priority = 2)
    public void UserLogin() {
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("https://petstore.swagger.io/v2/user/login?username=Pranali&password=Pranali%40123s");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }
    @Test(priority = 3)
    public void GetUserByUserName() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .get("https://petstore.swagger.io/v2/user/Kanchan");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }
    @Test(priority = 4)
    public void UpdatedUser() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"username\": \"Akshay\",\n" +
                        "  \"firstName\": \"Akshay\",\n" +
                        "  \"lastName\": \"Sayre\",\n" +
                        "  \"email\": \"akshay23@gmail.com\",\n" +
                        "  \"password\": \"Akshay@123\",\n" +
                        "  \"phone\": \"9922567854\",\n" +
                        "  \"userStatus\": 0\n" +
                        "}")
                .when()
                .put("https://petstore.swagger.io/v2/user/Pranali");
        response.prettyPrint();
        Assertions.assertEquals(200,response.statusCode());
    }
    @Test(priority = 5)
    public void CreateWithList() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("[\n" +
                        "  {\n" +
                        "    \"id\": 7,\n" +
                        "    \"username\": \"Karan\",\n" +
                        "    \"firstName\": \"Karan\",\n" +
                        "    \"lastName\": \"Patil\",\n" +
                        "    \"email\": \"karanpatil@gmail.com\",\n" +
                        "    \"password\": \"Karan@123\",\n" +
                        "    \"phone\": \"8876543456\",\n" +
                        "    \"userStatus\": 0\n" +
                        "  }\n" +
                        "]")
                .when()
                .post("https://petstore.swagger.io/v2/user/createWithList");
        response.prettyPrint();
        Assertions.assertEquals(200,response.statusCode());
    }
    @Test(priority = 6)
    public void CreateWithArray() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("[\n" +
                        "  {\n" +
                        "    \"id\": 0,\n" +
                        "    \"username\": \"Kanchan\",\n" +
                        "    \"firstName\": \"Kanchan\",\n" +
                        "    \"lastName\": \"Patil\",\n" +
                        "    \"email\": \"kanchanpatil@gmail.com\",\n" +
                        "    \"password\": \"Kanchan@123\",\n" +
                        "    \"phone\": \"9876543256\",\n" +
                        "    \"userStatus\": 0\n" +
                        "  }\n" +
                        "]")
                .when()
                .post("https://petstore.swagger.io/v2/user/createWithArray");
        response.prettyPrint();
        Assertions.assertEquals(200,response.statusCode());
    }
    @Test(priority = 8)
    public void LogOutCurrentUser() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .get("https://petstore.swagger.io/v2/user/logout");
        response.prettyPrint();
        Assertions.assertEquals(200,response.statusCode());
    }
    @Test(priority = 7)
    public void DeleteUser() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .delete("https://petstore.swagger.io/v2/user/Pranali");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }
}
