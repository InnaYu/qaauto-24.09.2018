package test;


import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.HomePage;
import page.LoginSubmitPage;



public class LoginTest extends BaseTest {


    @DataProvider
    public Object[][] ValidDataProvider() {
        return new Object[][]{
                {"innatestauto@gmail.com", "La=La-Lend"},
                {"Innatestauto@gmail.com", "La=La-Lend"},
                {" innatestauto@gmail.com ", "La=La-Lend"}
        };
    }

    @DataProvider
    public Object[][] NegativeLoginPageDataProvider() {
            return new Object[][]{
                    {"innatestauto@gmail.com", ""},
                    {"", "La=La-Lend"},
                    {"", ""}
        };
    }

        /**
         * Preconditions:
         * - Open FF browser.
         * <p>
         * Scenario:
         * - Navigate to https://www.linkedin.com;
         * - Verify that login page is loaded;
         * - Enter userEmail into userEmail field;
         * - Enter userPassword into userPassword field;
         * - Click on signIn button;
         * - Verify that Home Page is loaded.
         * <p>
         * PostCondition:
         * - Close FF browser.
         */
    @Test(dataProvider = "ValidDataProvider")
    public void successfullLoginTest(String userEmail, String userPassword) {

        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login page is not loaded.");

        HomePage homePage =loginPage.login(userEmail, userPassword);
        Assert.assertTrue(homePage.isPageLoaded(),
                "Home page is not displayed on Login Page");
    }



    @Test(dataProvider = "NegativeLoginPageDataProvider")
    public void negativeEmptyLoginFieldsTest (String userEmail, String userPassword) {

        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login page is not loaded.");

        loginPage.login(userEmail, userPassword);

        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login page is not loaded.");

    }

    @DataProvider
    public Object[][] validationMessagesCombination() {
        return new Object[][]{
                {"innatestauto@gmail.com", "wrong", "", ""},
                {"1ksd3fkv", "1ncm4nv", "Укажите действительный адрес эл. почты.", ""},
                {"innatest@gmail.com", "La=La-Lend", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", ""},
                {"carl@ua.fm", "La=La-Lend", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", ""},
                {"innatestauto@gmail.com", "mv1ncm4nv", "", "Это неверный пароль. Повторите попытку или измените пароль."},
                {"innatestauto@gmail.com", "mvm", "", "Пароль должен содержать не менее 6 символов."},

        };
    }

    @Test(dataProvider = "validationMessagesCombination")
    public void validationMessageOnInvalidEmailPasswordTest (String userEmail,
                                                        String userPassword,
                                                        String emailValidationMessage,
                                                        String passwordValidationMessage)  {


        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        LoginSubmitPage loginSubmitPage=loginPage.login(userEmail, userPassword);
        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "LoginSubmit page is not loaded");

        Assert.assertEquals(loginSubmitPage.getAlertMessageText(),
                "При заполнении формы были допущены ошибки. Проверьте и исправьте отмеченные поля.",
                "Alert message text is wrong");

       Assert.assertEquals(loginSubmitPage.getEmailValidationMessage(), emailValidationMessage,
                "Email validation message is wrong");

       Assert.assertEquals(loginSubmitPage.getPasswordValidationMessage(), passwordValidationMessage,
                "Password validation message is wrong");

    }



}










