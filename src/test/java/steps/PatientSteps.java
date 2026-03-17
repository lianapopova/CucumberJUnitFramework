package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.DashboardPage;
import pages.PatientsPage;
import utils.BaseUI;
import utils.Driver;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class PatientSteps extends BaseUI {

    WebDriver driver = Driver.getDriver();
    PatientsPage patientsPage = new PatientsPage();
    DashboardPage dashboardPage = new DashboardPage();

    @Given("user navigates to Patients page")
    public void user_navigates_to_patients_page() throws InterruptedException {
        Thread.sleep(1000);
        driver.navigate().to(PatientsPage.URL);
    }

    @When("user selects {string} in All Genders dropdown")
    public void user_selects_in_all_genders_dropdown(String gender) {
        patientsPage.selectGender(gender);
    }

    @Then("verify {string} patients are displayed")
    public void verify_patients_are_displayed(String gender) {
        for (WebElement actualGender : patientsPage.patientsGenderList) {
            Assertions.assertTrue(actualGender.getText().equalsIgnoreCase(gender));
        }
    }

    @When("user selects gender verify patients are displayed correct gender")
    public void user_selects_gender_verify_patients_are_displayed_correct_gender(DataTable dataTable) {
        List<String> genders = dataTable.asList(); //Female, Male. Other

        for (String gender : genders) {
            patientsPage.selectGender(gender);

            for (WebElement actualGender : patientsPage.patientsGenderList) {
                Assertions.assertTrue(actualGender.getText().equalsIgnoreCase(gender));
            }
        }
    }

    @When("user clicks on New Patient button")
    public void user_clicks_on_new_patient_button() {
        waitAndClick(patientsPage.newPatientButton);
    }

    @When("user fills the form with following data")
    public void user_fills_the_form_with_following_data(DataTable dataTable) {
        Map<String, String> userData = dataTable.asMap(String.class, String.class);
        patientsPage.lastNameInput.sendKeys(userData.get("lastName"));
        patientsPage.firstNameInput.sendKeys(userData.get("firstName"));
        patientsPage.dobInput.sendKeys(userData.get("dob"));
        patientsPage.phoneInput.sendKeys(userData.get("phone"));
        patientsPage.selectGenderNewPatient(userData.get("gender"));
    }

    @When("user clicks on Create Patient button")
    public void user_clicks_on_create_patient_button() {
        patientsPage.createPatientButton.click();
    }

    @Then("verify new patient was created")
    public void verify_new_patient_was_created() {

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));

        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//div[contains(text(),'Patient created successfully')]")
                )
        );

        Assertions.assertTrue(successMessage.isDisplayed());
    }

    @Then("search patient {string}")
    public void search_patient(String firstName) {
        patientsPage.searchInput.sendKeys(firstName);
    }

    @And("delete patient {string}")
    public void delete_patient(String firstName) {
        patientsPage.waitAndClick(patientsPage.deleteButton);
        patientsPage.waitAndClick(patientsPage.confirmDeleteButton);
    }
}