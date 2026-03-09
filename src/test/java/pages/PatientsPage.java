package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.BaseUI;
import utils.Driver;

import java.util.List;

public class PatientsPage extends BaseUI {

    public PatientsPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    public static final String URL = "https://codewise-clinic-portal.lovable.app/patients";

    @FindBy(xpath = "//a[text()='Patients']")
    public WebElement patientTab;

    @FindBy(xpath = "//button[@data-testid='filter-gender']")
    public WebElement genderDropdown;

    @FindBy(xpath = "//span[text()='Female']")
    public WebElement femaleOption;

    @FindBy(xpath = "//span[text()='Male']")
    public WebElement maleOption;

    @FindBy(xpath = "//span[text()='Other']")
    public WebElement othersOption;

    @FindBy(xpath = "//tbody[@class='[&_tr:last-child]:border-0']//tr/td[3]")
    public List<WebElement> patientsGenderList;

    public void selectGender(String gender) {
        waitAndClick(genderDropdown);
        waitAndClick(Driver.getDriver().findElement(By.xpath("//span[text()='" + gender + "']")));
    }

    @FindBy (xpath = "//button[contains(text(),'New Patient')]")
    public WebElement newPatientButton;

    @FindBy(xpath = "//input[@id='first_name']")
    public WebElement firstNameInput;

    @FindBy(xpath = "//input[@id='last_name']")
    public WebElement lastNameInput;

    @FindBy(xpath = "//input[@id='dob']")
    public WebElement dobInput;

    @FindBy(xpath = "//label[text()='Gender *']/following-sibling::button[@role='combobox']")
    public WebElement genderDropdownInput;

    @FindBy(xpath = "//input[@id='phone']")
    public WebElement phoneInput;

    @FindBy(xpath = "//button[text()='Create Patient']")
    public WebElement createPatientButton;

    @FindBy(xpath = "//div[contains(@class,'toast')]")
    public WebElement successfullyCreatedPatientText;

    public void selectGenderNewPatient(String gender){
        waitAndClick(genderDropdownInput);
        waitAndClick(Driver.getDriver().findElement(
                By.xpath("//span[text()='" + gender.substring(0,1).toUpperCase() + gender.substring(1) + "']")
        ));
    }

    @FindBy(css = "input[placeholder*='Search']")
    public WebElement searchInput;

    @FindBy(css = "[data-testid*='delete-patient']")
    public WebElement deleteButton;

    @FindBy(xpath = "//button[text()='Delete Patient']")
    public WebElement confirmDeleteButton;
}
