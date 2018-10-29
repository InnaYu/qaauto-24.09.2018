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

    @BeforeMethod
    public void beforeMethod() {
        webDriver = new FirefoxDriver();
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

    @DataProvider
    public Object[][] NegativeLoginSubmitDataProvider() {
        return new Object[][]{
                {"8ksd3fkv", "8ncm4nv", "Укажите действительный адрес эл. почты.", ""},
                {"innatest@gmail.com", "DgL-ce3-9mm-TKE", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", ""},
                {"carl@ua.fm", "DgL-ce3-9mm-TKE", "Этот адрес эл. почты не зарегистрирован в LinkedIn. Повторите попытку.", ""},
                {"innatestauto@gmail.com", "mv1ncm4nv", "", "Это неверный пароль. Повторите попытку или измените пароль."},
                {"innatestauto@gmail.com", "mvm", "", "Пароль должен содержать не менее 6 символов."},

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

        webDriver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login page is not loaded.");

        HomePage homePage =loginPage.login(userEmail, userPassword);
        Assert.assertTrue(homePage.isPageLoaded(),
                "Home page is not displayed on Login Page");
    }



    @Test(dataProvider = "NegativeLoginPageDataProvider")
    public void negativeEmptyLoginFieldsTest (String userEmail, String userPassword) {

        webDriver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login page is not loaded.");

        loginPage.login(userEmail, userPassword);

        Assert.assertTrue(loginPage.isPageLoaded(),
                "Login page is not loaded.");

    }


    @Test(dataProvider = "NegativeLoginSubmitDataProvider")
    public void negativeWrongLoginTest (String userEmail, String userPassword, String emailValidationMessage, String passwordValidationMessage)  {
        webDriver.get("https://www.linkedin.com");
        LoginPage loginPage = new LoginPage(webDriver);
        Assert.assertTrue(loginPage.isPageLoaded(), "Login page is not loaded.");

        LoginSubmitPage loginSubmitPage=loginPage.login(userEmail, userPassword);

        Assert.assertTrue(loginSubmitPage.isPageLoaded(), "LoginSubmit page is not loaded");

        Assert.assertEquals(loginSubmitPage.errorMessageForEmail(), emailValidationMessage, "Actual error message is not the same that Expected");

        Assert.assertEquals(loginSubmitPage.errorMessageForPassword(), passwordValidationMessage, "Actual error message is not the same that Expected");

    }
}










