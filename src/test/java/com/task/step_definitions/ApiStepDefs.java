package com.task.step_definitions;


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


        String url = ConfigurationReader.get("url")+"/posts";

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


}
