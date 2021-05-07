package com.task.step_definitions;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import com.task.utilities.ConfigurationReader;
import com.task.utilities.DBUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

import static io.restassured.RestAssured.given;


public class ApiStepDefs {
    Response response;


    @When("I get the current user information from api")
    public void i_get_the_current_user_information_from_api() {
        //send get request to retrieve current user information


        String url = ConfigurationReader.get("url");

        response=     given().accept(ContentType.JSON)
                .and()
                .when()
                .get(url);


    }
    @Then("status code should be {int}")
    public void status_code_should_be(int statusCode) {

        Assert.assertEquals(statusCode,response.statusCode());
        System.out.println(statusCode);

    }
    @Then("I am creating post")
    public void i_am_creating_post() {

        baseURI = ConfigurationReader.get("url");

//        Response response = given().accept(ContentType.JSON)
//                .and().contentType(ContentType.JSON)
//                .body("{\n" +
//                        "    title: 'foo',\n" +
//                        "    body: 'bar',\n" +
//                        "    userId: 1,\n" +
//                        "  }")
//                .when().post("/posts");
        Response response = given().log().all()
                .accept(ContentType.TEXT)
                .and()
                .contentType(ContentType.TEXT)
                .and()
                .body("{\n" +
                        "    title: 'foo',\n" +
                        "    body: 'bar',\n" +
                        "    userId: 1,\n" +
                        "  }")
                .when()
                .post("/posts");

        assertEquals(response.statusCode(),201);
        assertEquals(response.contentType(),"application/json; charset=utf-8");


        JsonPath json=response.jsonPath();
        String title = response.path("Body.title");

//       Assert.assertTrue(response.prettyPrint().contains(" title: 'foo',\n" +
//               "    body: 'bar',\n" +
//               "    userId: 1,"));
        assertEquals(json.getString("Body.title"),"foo");



    }


}
