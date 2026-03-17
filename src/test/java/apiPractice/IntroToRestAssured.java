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
                .header("Authorization", "Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjgwZGVjODU2LTQ2ZDktNGJhYy1hNDc4LTY4YzQ0ZTc2NWU4YSIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p2amRhcnhucnNxa2toa2VvdGtjLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiI4MzlkYmJiMC0wMDkzLTQ3MWYtYjE4NC1hMDc5MWYzMmIwNTQiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzczNzkxNzUxLCJpYXQiOjE3NzM3ODgxNTEsImVtYWlsIjoiZHIucG9wb3ZhQG1lZGlmbG93LmNvbSIsInBob25lIjoiIiwiYXBwX21ldGFkYXRhIjp7InByb3ZpZGVyIjoiZW1haWwiLCJwcm92aWRlcnMiOlsiZW1haWwiXX0sInVzZXJfbWV0YWRhdGEiOnsiZW1haWxfdmVyaWZpZWQiOnRydWV9LCJyb2xlIjoiYXV0aGVudGljYXRlZCIsImFhbCI6ImFhbDEiLCJhbXIiOlt7Im1ldGhvZCI6InBhc3N3b3JkIiwidGltZXN0YW1wIjoxNzczNzg4MTUxfV0sInNlc3Npb25faWQiOiI5MjA1MDg2MC05NjRhLTQxMmMtYjY3Yi0wZWVjMjk2NTY1ODciLCJpc19hbm9ueW1vdXMiOmZhbHNlfQ.3Ot595DqYeMwPVaJ6EIwKSKJR2Voj8sVaGgfXv4-qItALFwZz2-YqqEP3VRzBU4TbwP8giy0v6XIw9x2II9S9A");

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
                        .header("Authorization", "Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjgwZGVjODU2LTQ2ZDktNGJhYy1hNDc4LTY4YzQ0ZTc2NWU4YSIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p2amRhcnhucnNxa2toa2VvdGtjLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiI4MzlkYmJiMC0wMDkzLTQ3MWYtYjE4NC1hMDc5MWYzMmIwNTQiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzczNzkxNzUxLCJpYXQiOjE3NzM3ODgxNTEsImVtYWlsIjoiZHIucG9wb3ZhQG1lZGlmbG93LmNvbSIsInBob25lIjoiIiwiYXBwX21ldGFkYXRhIjp7InByb3ZpZGVyIjoiZW1haWwiLCJwcm92aWRlcnMiOlsiZW1haWwiXX0sInVzZXJfbWV0YWRhdGEiOnsiZW1haWxfdmVyaWZpZWQiOnRydWV9LCJyb2xlIjoiYXV0aGVudGljYXRlZCIsImFhbCI6ImFhbDEiLCJhbXIiOlt7Im1ldGhvZCI6InBhc3N3b3JkIiwidGltZXN0YW1wIjoxNzczNzg4MTUxfV0sInNlc3Npb25faWQiOiI5MjA1MDg2MC05NjRhLTQxMmMtYjY3Yi0wZWVjMjk2NTY1ODciLCJpc19hbm9ueW1vdXMiOmZhbHNlfQ.3Ot595DqYeMwPVaJ6EIwKSKJR2Voj8sVaGgfXv4-qItALFwZz2-YqqEP3VRzBU4TbwP8giy0v6XIw9x2II9S9A")
                        .body(requestBody.toString());

        Response response = request.put("/api-patients/PT-000162");

        System.out.println("STATUS CODE: " + response.statusCode());
        System.out.println("RESPONSE BODY: " + response.asPrettyString());
    }



}
