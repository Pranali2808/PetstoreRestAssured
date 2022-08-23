package com.example.petstore;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import static io.restassured.RestAssured.given;

public class StoreRestAssuredTest {
    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
    }

    @Test
    public void aPlaceOrderForPet() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"petId\": 9,\n" +
                        "  \"quantity\": 10,\n" +
                        "  \"shipDate\": \"2022-08-23T08:49:12.774Z\",\n" +
                        "  \"status\": \"placed\",\n" +
                        "  \"complete\": true\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/store/order");
        response.prettyPrint();
        Assertions.assertEquals(200,response.statusCode());
    }

    @Test
    public void bPurchaseOrderByID() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .get("https://petstore.swagger.io/v2/store/order/2");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void cReturnPetInventoryByStatus() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .get("https://petstore.swagger.io/v2/store/inventory");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }

    @Test
    public void deletePurchaseOrderById() {
        Response response = given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .when()
                .delete("https://petstore.swagger.io/v2/store/order/2");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }
}
