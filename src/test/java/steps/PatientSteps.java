package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.DashboardPage;
import pages.PatientPage;
import utils.BaseUI;
import utils.Driver;

import java.util.List;
import java.util.Map;

public class PatientSteps extends BaseUI {

    WebDriver driver = Driver.getDriver();
    PatientPage patientPage = new PatientPage();
    DashboardPage dashboardPage = new DashboardPage();

    @Given("user navigates to Patients page")
    public void user_navigates_to_patients_page() throws InterruptedException {
        Thread.sleep(1000);
        driver.navigate().to(PatientPage.URL);
    }

    @When("user selects {string} in All Genders dropdown")
    public void user_selects_in_all_genders_dropdown(String gender) {
        patientPage.selectGender(gender);
    }

    @Then("verify {string} patients are displayed")
    public void verify_patients_are_displayed(String gender) {
        for (WebElement actualGender : patientPage.patientsGenderList) {
            Assertions.assertTrue(actualGender.getText().equalsIgnoreCase(gender));
        }
    }

    @When("user selects gender verify patients are displayed correct gender")
    public void user_selects_gender_verify_patients_are_displayed_correct_gender(DataTable dataTable) {
        List<String> genders = dataTable.asList(); //Female, Male. Other

        for (String gender : genders) {
            patientPage.selectGender(gender);

            for (WebElement actualGender : patientPage.patientsGenderList) {
                Assertions.assertTrue(actualGender.getText().equalsIgnoreCase(gender));
            }
        }

    }

    @When("user clicks on New patient button")
    public void user_clicks_on_new_patient_button() {
        //add code which clicks on New patient

    }
    @When("user fills the form with following data")
    public void user_fills_the_form_with_following_data(DataTable dataTable) {
        Map<String, String> patientInfo = dataTable.asMap(String.class, String.class);
        /*
        firstName, Donald
        lastName, Trump

        firstName, Barack
        lastName, Obama
         */
    }
    @When("user clicks on Create Patient button")
    public void user_clicks_on_create_patient_button() {

    }
    @Then("verify new patient was created")
    public void verify_new_patient_was_created() {

    }

}