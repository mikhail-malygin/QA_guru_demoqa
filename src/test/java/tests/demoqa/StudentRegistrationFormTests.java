package tests.demoqa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static tests.demoqa.TestData.*;

public class StudentRegistrationFormTests extends TestBase {

    @Test
    @DisplayName("Filling a registration form test")
    void fillingRegistrationFormTest() {

        step("Open a page", () -> {
            studentRegistrationFormPage.openPage();
        });

        step("Fill a registration form", () -> {
            studentRegistrationFormPage.setFirstName(FIRST_NAME)
                    .setLastName(LAST_NAME)
                    .setUserEmail(USER_EMAIL)
                    .setGender(GENDER)
                    .setPhoneNumber(PHONE_NUMBER)
                    .setDateOfBirth(DAY_OF_BIRTH, MONTH_OF_BIRTH, YEAR_OF_BIRTH)
                    .setSubject(SUBJECT)
                    .setHobby(HOBBY)
                    .uploadPicture(PATH_PICTURE)
                    .setCurrentAddress(CURRENT_ADDRESS)
                    .setState(STATE)
                    .setCity(CITY)
                    .submit();
        });

        step("Check a registration form", () -> {
            studentRegistrationFormPage.checkResult("Student Name", FIRST_NAME + " " + LAST_NAME)
                    .checkResult("Student Email", USER_EMAIL)
                    .checkResult("Gender", GENDER)
                    .checkResult("Mobile", PHONE_NUMBER)
                    .checkResult("Date of Birth", (DAY_OF_BIRTH + " " + MONTH_OF_BIRTH + "," + YEAR_OF_BIRTH))
                    .checkResult("Subjects", SUBJECT)
                    .checkResult("Hobbies", HOBBY)
                    .checkResult("Picture", NAME_PICTURE)
                    .checkResult("Address", CURRENT_ADDRESS)
                    .checkResult("State and City", STATE + " " + CITY);
        });
    }
}
