package org.example.app.task.service;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class TaskServiceTest extends Assertions {

    @Test
    public void testFindTaskList() {
        given().when().get("/task/list/1").then().statusCode(200)
                .body(is("{\"id\":1,\"version\":0,\"title\":\"Shopping List\"}"));
    }

    @Test
    public void testFindTaskItem() {
        given().when().get("/task/item/11").then().statusCode(200)
                .body(is("{\"id\":11,\"version\":0,\"completed\":false,\"starred\":true,\"title\":\"Milk\"}"));
    }


    @Test
    public void testInvalidTaskList() {
        given().when().get("/task/list/0").then().statusCode(404);
    }

    @Test
    public void testInvalidFindTaskItem() {
        given().when().get("/task/item/10").then().statusCode(404);
    }

    @Test
    public void testDeleteTaskList(){
        given().when().delete("/task/list/1").then().statusCode(204);
        given().when().get("/task/list/1").then().statusCode(404);
    }

    @Test
    public void testDeleteTaskItem(){
        given().when().delete("/task/item/11").then().statusCode(204);
        given().when().get("/task/item/11").then().statusCode(404);
    }
}
