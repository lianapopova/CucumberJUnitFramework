package steps.api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import utils.ConfigurationReader;

public class ApiSteps {

    RequestSpecification request;
    Response response;

    @Given("base url")
    public void base_url() {
        request = RestAssured.given().baseUri(ConfigurationReader.getProperty("apiBaseUrl"));
    }

    @Given("user has valid authorization")
    public void user_has_valid_authorization() {
        request = request.header("Authorization", "Bearer eyJhbGciOiJFUzI1NiIsImtpZCI6IjgwZGVjODU2LTQ2ZDktNGJhYy1hNDc4LTY4YzQ0ZTc2NWU4YSIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJodHRwczovL2p2amRhcnhucnNxa2toa2VvdGtjLnN1cGFiYXNlLmNvL2F1dGgvdjEiLCJzdWIiOiI4MzlkYmJiMC0wMDkzLTQ3MWYtYjE4NC1hMDc5MWYzMmIwNTQiLCJhdWQiOiJhdXRoZW50aWNhdGVkIiwiZXhwIjoxNzczODUyMTk1LCJpYXQiOjE3NzM4NDg1OTUsImVtYWlsIjoiZHIucG9wb3ZhQG1lZGlmbG93LmNvbSIsInBob25lIjoiIiwiYXBwX21ldGFkYXRhIjp7InByb3ZpZGVyIjoiZW1haWwiLCJwcm92aWRlcnMiOlsiZW1haWwiXX0sInVzZXJfbWV0YWRhdGEiOnsiZW1haWxfdmVyaWZpZWQiOnRydWV9LCJyb2xlIjoiYXV0aGVudGljYXRlZCIsImFhbCI6ImFhbDEiLCJhbXIiOlt7Im1ldGhvZCI6InBhc3N3b3JkIiwidGltZXN0YW1wIjoxNzczODQ4NTk1fV0sInNlc3Npb25faWQiOiI5MjQyNmMzYy1mOTNjLTQ2MTMtYmU4NS1lZjYyZTBlNGEzYjYiLCJpc19hbm9ueW1vdXMiOmZhbHNlfQ.NEyg4FzFNv3BRQVmy_VyVcAogGy8X3cnVi_uZW-FTbFkTRqaIoPtL6funv_emdi_saz4qaH7g6I58ykU8z6bRQ");
    }

    @Given("user has invalid authorization")
    public void user_has_invalid_authorization() {
        request = request.header("Authorization", "Bearer invalidTokenJhbGciOiJFUz");
    }

    @When("user hits GET {string}")
    public void user_hits_get(String endpoint) {
        response = request.get(endpoint);
    }

    @Then("verify status code is {int}")
    public void verify_status_code_is(Integer expectedStatusCode) {
        Integer actualStatusCode = response.statusCode();
        Assertions.assertEquals(expectedStatusCode, actualStatusCode);
    }

    @Given("user provided query param {string} with value {string}")
    public void user_provided_query_param_with_value(String key, String value) {
        request = request.queryParam(key, value);
    }

    @Then("verify the number of patients is <= {int}")
    public void verify_the_number_of_patients_is(Integer maxNumberOfPatients) {
        String body = response.getBody().asString();
        JSONObject jsonObject = new JSONObject(body);

        JSONArray arrayOfPatients = jsonObject.getJSONArray("data");
        System.out.println("Number of patients: " + arrayOfPatients.length());

        for (int i = 0; i < arrayOfPatients.length(); i++) {
            JSONObject patientObj = arrayOfPatients.getJSONObject(i);
            System.out.println(patientObj.getString("first_name"));
            System.out.println(patientObj.getString("email"));
        }
    }
}
