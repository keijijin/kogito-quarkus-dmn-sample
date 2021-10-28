package com.sample;

import com.sample.utils.JsonToString;

import org.junit.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class FlightFraudTest {
    String dirs = "src/test/resources/com/sample/";
    
    @Test
    public void Score0Test() {
        Double resultScore = GetScore("FlightFraudScore00Test.json");
        assertThat(resultScore, equalTo(0.0));
    }

    @Test
    public void Score02Test() {
        Double resultScore = GetScore("FlightFraudScore02Test.json");
        assertThat(resultScore, equalTo(0.2));
    }

    @Test
    public void Score05Test() {
        Double resultScore = GetScore("FlightFraudScore05Test.json");
        assertThat(resultScore, equalTo(0.5));
    }

    @Test
    public void Score07Test() {
        Double resultScore = GetScore("FlightFraudScore07Test.json");
        assertThat(resultScore, equalTo(0.7));
    }

    @Test
    public void Score10Test() {
        Double resultScore = GetScore("FlightFraudScore10Test.json");
        assertThat(resultScore, equalTo(1.0));
    }

    private Double GetScore(String jsonFile) {
        String json = JsonToString.run(dirs +jsonFile);
        Response result =
            given()
                .body(json)
                .contentType(ContentType.JSON)
            .when()
                .post("/FlightFraud")
            .then()
                .statusCode(200)
                .extract()
                .response();

        Double score = result.jsonPath().getDouble("'Determine Fraud Action'.'score'");
        assertThat(score, not(nullValue()));
        return score;
    }
}
