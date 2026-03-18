package apiPractice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class IntroToHamcrest {

    /*
    1. hit the GET list of prescriptions
    2. verify status code is 200
    3. verify the number of prescriptions = 25
     */

    @Test
    public void verifyListOfPrescriptions() {
        ValidatableResponse response = RestAssured.given()
                .baseUri("https://jvjdarxnrsqkkhkeotkc.supabase.co/functions/v1")
                .header("Authorization", "Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjgwZGVjODU2LTQ2ZDktNGJhYy1hNDc4LTY4YzQ0ZTc2NWU4YSIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p2amRhcnhucnNxa2toa2VvdGtjLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiI4MzlkYmJiMC0wMDkzLTQ3MWYtYjE4NC1hMDc5MWYzMmIwNTQiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzczODYwMTE4LCJpYXQiOjE3NzM4NTY1MTgsImVtYWlsIjoiZHIucG9wb3ZhQG1lZGlmbG93LmNvbSIsInBob25lIjoiIiwiYXBwX21ldGFkYXRhIjp7InByb3ZpZGVyIjoiZW1haWwiLCJwcm92aWRlcnMiOlsiZW1haWwiXX0sInVzZXJfbWV0YWRhdGEiOnsiZW1haWxfdmVyaWZpZWQiOnRydWV9LCJyb2xlIjoiYXV0aGVudGljYXRlZCIsImFhbCI6ImFhbDEiLCJhbXIiOlt7Im1ldGhvZCI6InBhc3N3b3JkIiwidGltZXN0YW1wIjoxNzczODU2NTE4fV0sInNlc3Npb25faWQiOiIwY2I5ZjY5Ny0yMTVhLTQwNjEtYmI1OC02MDUyOTVkZjA2YjkiLCJpc19hbm9ueW1vdXMiOmZhbHNlfQ.4HWn7AZBnUNe9p4lQPrwAnOuLoFEgPKXjEFOHPqzjLsIW7gZjnePYmg1paHcsmR7GlBmxKDIHnWn1Thm47o5MA")
                .when().get("/api-prescriptions")
                .then().statusCode(200);

        Assertions.assertEquals(200, response.statusCode());

        JSONObject responseBody = new JSONObject(response.body().asString()); // extracted entire response body and put inside JSONObject

        JSONArray arrayOfPrescriptions = responseBody.getJSONArray("data");

        Assertions.assertEquals(25, arrayOfPrescriptions.length());
    }


     /*
    1. hit the GET list of prescriptions
            query param
    2. verify status code is 200
    3. verify the number of prescriptions = 25
     */

    @Test
    public void verifyListOfPrescriptionsWithQueryParam() {
        Response response = RestAssured.given()
                .baseUri("https://jvjdarxnrsqkkhkeotkc.supabase.co/functions/v1")
                .header("Authorization", "Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjgwZGVjODU2LTQ2ZDktNGJhYy1hNDc4LTY4YzQ0ZTc2NWU4YSIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p2amRhcnhucnNxa2toa2VvdGtjLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiI4MzlkYmJiMC0wMDkzLTQ3MWYtYjE4NC1hMDc5MWYzMmIwNTQiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzczODYwMTE4LCJpYXQiOjE3NzM4NTY1MTgsImVtYWlsIjoiZHIucG9wb3ZhQG1lZGlmbG93LmNvbSIsInBob25lIjoiIiwiYXBwX21ldGFkYXRhIjp7InByb3ZpZGVyIjoiZW1haWwiLCJwcm92aWRlcnMiOlsiZW1haWwiXX0sInVzZXJfbWV0YWRhdGEiOnsiZW1haWxfdmVyaWZpZWQiOnRydWV9LCJyb2xlIjoiYXV0aGVudGljYXRlZCIsImFhbCI6ImFhbDEiLCJhbXIiOlt7Im1ldGhvZCI6InBhc3N3b3JkIiwidGltZXN0YW1wIjoxNzczODU2NTE4fV0sInNlc3Npb25faWQiOiIwY2I5ZjY5Ny0yMTVhLTQwNjEtYmI1OC02MDUyOTVkZjA2YjkiLCJpc19hbm9ueW1vdXMiOmZhbHNlfQ.4HWn7AZBnUNe9p4lQPrwAnOuLoFEgPKXjEFOHPqzjLsIW7gZjnePYmg1paHcsmR7GlBmxKDIHnWn1Thm47o5MA")
                .queryParam("status", "Active")
                .queryParam("search", "Metoprolol")
                        .get("/api-prescriptions");

        Assertions.assertEquals(200, response.statusCode());

        JSONObject responseBody = new JSONObject(response.body().asString()); // extracted entire response body and put inside JSONObject
        JSONArray arrayOfPrescriptions = responseBody.getJSONArray("data");

        Assertions.assertEquals(25, arrayOfPrescriptions.length());

        //verify all prescriptions are Active and medication name = Metoprolol

        for (int i = 0; i < arrayOfPrescriptions.length(); i++) {

            JSONObject prescription = arrayOfPrescriptions.getJSONObject(i);

            assertThat(prescription.getString("status"), equalTo("Active"));
            assertThat(prescription.getString("medication_name"), equalTo("Metoprolol"));
        }
    }
}
