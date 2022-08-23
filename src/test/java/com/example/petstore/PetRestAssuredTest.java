package com.example.petstore;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.given;

public class PetRestAssuredTest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test(priority =1)
    public void AddANewPetToTheStore() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 3,\n" +
                        "    \"name\": \"Rabbit\"\n" +
                        "  },\n" +
                        "  \"name\": \"Piku\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 5,\n" +
                        "      \"name\": \"turtle\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/pet");
        response.prettyPrint();
        Assertions.assertEquals(200,response.statusCode());
    }
    @Test(priority =2)
    public void FindPetById() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .get("https://petstore.swagger.io/v2/pet/3");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }
    @Test(priority =4)
    public void updatedAnExistingPet() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 3,\n" +
                        "    \"name\": \"Dog\"\n" +
                        "  },\n" +
                        "  \"name\": \"doggie\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 7,\n" +
                        "      \"name\": \"monty\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"pending\"\n" +
                        "}")
                .when()
                .put("https://petstore.swagger.io/v2/pet");
        response.prettyPrint();
        Assertions.assertEquals(200,response.statusCode());
    }
    @Test(priority =6)
    public void UploadsAnImage() {
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("https://petstore.swagger.io/v2/pet/8/uploadImage");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }
    @Test(priority =5)
    public void UpdatePetWithFormData() {
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("https://petstore.swagger.io/v2/pet/2");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }
    @Test(priority =3)
    public void FindPetsByStatus() {
        Response response = given()
                .accept(ContentType.JSON)
                .when()
                .get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test(priority =7)
    public void DeletesAPet() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .delete("https://petstore.swagger.io/v2/pet/5");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }


}
