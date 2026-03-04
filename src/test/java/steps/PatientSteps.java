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
}