package apiPractice;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class IntroToRestAssured {

    /*
    TEST POST - LOGIN - REQUEST
    1. provide base URL, endpoint
    2. provide request body: email and password
    3. provide headers: content type and accept type
    4. hit POST button
    5. verify status code is 200
    6. verify  response body contains token
    7. print response body

    TWO JAVA CLASSES IN REST ASSURED
    - RequestSpecification request - this object can be used to build your request
    - Response response - this object can be used to store your response
     */

    public static void main(String[] args) {

        //building request body
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "dr.popova@mediflow.com");
        requestBody.put("password", "Test@1234");

        RequestSpecification request =
                RestAssured.given().baseUri("https://jvjdarxnrsqkkhkeotkc.supabase.co/functions/v1")
                        .contentType(ContentType.JSON)
                        .body(requestBody.toString());

        Response response = request.post("/api-auth/login");

        System.out.println("STATUS CODE: " + response.statusCode());
        System.out.println("RESPONSE BODY: " + response.asPrettyString());
    }

    @Test
    public void testGetListOfPatients() {
        RequestSpecification request = RestAssured.given()
                .baseUri("https://jvjdarxnrsqkkhkeotkc.supabase.co/functions/v1")
                .header("Authorization", "Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjgwZGVjODU2LTQ2ZDktNGJhYy1hNDc4LTY4YzQ0ZTc2NWU4YSIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p2amRhcnhucnNxa2toa2VvdGtjLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiI4MzlkYmJiMC0wMDkzLTQ3MWYtYjE4NC1hMDc5MWYzMmIwNTQiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzczODAzMTE0LCJpYXQiOjE3NzM3OTk1MTQsImVtYWlsIjoiZHIucG9wb3ZhQG1lZGlmbG93LmNvbSIsInBob25lIjoiIiwiYXBwX21ldGFkYXRhIjp7InByb3ZpZGVyIjoiZW1haWwiLCJwcm92aWRlcnMiOlsiZW1haWwiXX0sInVzZXJfbWV0YWRhdGEiOnsiZW1haWxfdmVyaWZpZWQiOnRydWV9LCJyb2xlIjoiYXV0aGVudGljYXRlZCIsImFhbCI6ImFhbDEiLCJhbXIiOlt7Im1ldGhvZCI6InBhc3N3b3JkIiwidGltZXN0YW1wIjoxNzczNzk5NTE0fV0sInNlc3Npb25faWQiOiJhZDc2YmExMi05ZjIxLTRhY2EtYTZhMC1lMjgxM2YzNTA1N2UiLCJpc19hbm9ueW1vdXMiOmZhbHNlfQ.nKe4CGfrl-WtZr2e_64HER_6XZYLlrd092mnfZs_-5fv4koEsPH5R2oblgyH-r65uM1UmeDjCeLYqLk9OUdhkg");

        Response response = request.get("/api-patients");

        System.out.println("STATUS CODE: " + response.statusCode());
        System.out.println("RESPONSE BODY: " + response.asPrettyString());
    }

    @Test
    public void testUpdatePatient() {

        JSONObject requestBody = new JSONObject();
        requestBody.put("patient_id", "PT-000162");
        requestBody.put("last_name", "Smith");

        RequestSpecification request =
                RestAssured.given().baseUri("https://jvjdarxnrsqkkhkeotkc.supabase.co/functions/v1")
                        .contentType(ContentType.JSON)
                        .header("Authorization", "Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjgwZGVjODU2LTQ2ZDktNGJhYy1hNDc4LTY4YzQ0ZTc2NWU4YSIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p2amRhcnhucnNxa2toa2VvdGtjLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiI4MzlkYmJiMC0wMDkzLTQ3MWYtYjE4NC1hMDc5MWYzMmIwNTQiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzczODAzMTE0LCJpYXQiOjE3NzM3OTk1MTQsImVtYWlsIjoiZHIucG9wb3ZhQG1lZGlmbG93LmNvbSIsInBob25lIjoiIiwiYXBwX21ldGFkYXRhIjp7InByb3ZpZGVyIjoiZW1haWwiLCJwcm92aWRlcnMiOlsiZW1haWwiXX0sInVzZXJfbWV0YWRhdGEiOnsiZW1haWxfdmVyaWZpZWQiOnRydWV9LCJyb2xlIjoiYXV0aGVudGljYXRlZCIsImFhbCI6ImFhbDEiLCJhbXIiOlt7Im1ldGhvZCI6InBhc3N3b3JkIiwidGltZXN0YW1wIjoxNzczNzk5NTE0fV0sInNlc3Npb25faWQiOiJhZDc2YmExMi05ZjIxLTRhY2EtYTZhMC1lMjgxM2YzNTA1N2UiLCJpc19hbm9ueW1vdXMiOmZhbHNlfQ.nKe4CGfrl-WtZr2e_64HER_6XZYLlrd092mnfZs_-5fv4koEsPH5R2oblgyH-r65uM1UmeDjCeLYqLk9OUdhkg")
                        .body(requestBody.toString());

        Response response = request.put("/api-patients/PT-000162");

        System.out.println("STATUS CODE: " + response.statusCode());
        System.out.println("RESPONSE BODY: " + response.asPrettyString());
    }

    @Test
    public void testGetAppointments() {
        RequestSpecification request = RestAssured.given()
                .baseUri("https://jvjdarxnrsqkkhkeotkc.supabase.co/functions/v1")
                .header("Authorization", "Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjgwZGVjODU2LTQ2ZDktNGJhYy1hNDc4LTY4YzQ0ZTc2NWU4YSIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p2amRhcnhucnNxa2toa2VvdGtjLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiI4MzlkYmJiMC0wMDkzLTQ3MWYtYjE4NC1hMDc5MWYzMmIwNTQiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzczODAzMTE0LCJpYXQiOjE3NzM3OTk1MTQsImVtYWlsIjoiZHIucG9wb3ZhQG1lZGlmbG93LmNvbSIsInBob25lIjoiIiwiYXBwX21ldGFkYXRhIjp7InByb3ZpZGVyIjoiZW1haWwiLCJwcm92aWRlcnMiOlsiZW1haWwiXX0sInVzZXJfbWV0YWRhdGEiOnsiZW1haWxfdmVyaWZpZWQiOnRydWV9LCJyb2xlIjoiYXV0aGVudGljYXRlZCIsImFhbCI6ImFhbDEiLCJhbXIiOlt7Im1ldGhvZCI6InBhc3N3b3JkIiwidGltZXN0YW1wIjoxNzczNzk5NTE0fV0sInNlc3Npb25faWQiOiJhZDc2YmExMi05ZjIxLTRhY2EtYTZhMC1lMjgxM2YzNTA1N2UiLCJpc19hbm9ueW1vdXMiOmZhbHNlfQ.nKe4CGfrl-WtZr2e_64HER_6XZYLlrd092mnfZs_-5fv4koEsPH5R2oblgyH-r65uM1UmeDjCeLYqLk9OUdhkg")
                .queryParam("page", 2)
                .queryParam("pageSize", 3)
                .queryParam("status", "Cancelled");

        Response response = request.get("/api-appointments");

        System.out.println("STATUS CODE: " + response.statusCode());
        System.out.println("RESPONSE BODY: " + response.asPrettyString());
    }



}
