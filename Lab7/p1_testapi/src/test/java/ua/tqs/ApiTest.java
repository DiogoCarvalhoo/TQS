package ua.tqs;

import static org.junit.Assert.assertTrue;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class ApiTest 
{


    @Test
    public void test1() {
        get("https://jsonplaceholder.typicode.com/todos").then().statusCode(200);
    }

    @Test
    public void test2() {
        String title = "et porro tempora";
        get("https://jsonplaceholder.typicode.com/todos/4").then().statusCode(200).assertThat()
            .body("title", equalTo(title)); 
    }

    @Test
    public void test3() {
        String title = "et porro tempora";
        get("https://jsonplaceholder.typicode.com/todos").then().statusCode(200).assertThat()
            .body("id", hasItems(198, 199));
    }
}
