import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LoginTest {
    WebDriver webDriver;
    LoginPage loginPage;

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new FirefoxDriver();
        webDriver.get("https://www.linkedin.com");
        loginPage= new LoginPage(webDriver);
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

    @DataProvider
    public Object[][] ValidDataProvider() {
        return new Object[][]{
                {"innatestauto@gmail.com", "DgL-ce3-9mm-TKE"},
                {"Innatestauto@gmail.com", "DgL-ce3-9mm-TKE"},
                {" innatestauto@gmail.com ", "DgL-ce3-9mm-TKE"}
        };
    }

    @DataProvider
    public Object[][] NegativeLoginPageDataProvider() {
            return new Object[][]{
                    {"innatestauto@gmail.com", ""},
                    {"", "DgL-ce3-9mm-TKE"},
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
                {"innatest@gmail.com", "DgL-ce3-9mm-TKE", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", ""},
                {"carl@ua.fm", "DgL-ce3-9mm-TKE", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", ""},
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










