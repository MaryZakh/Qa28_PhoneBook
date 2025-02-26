package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {

    @BeforeMethod
    public void preCondition(){
        //if SignOut present --->logout
        if(app.getHelperUser().isLogged())
            app.getHelperUser().logout();
        logger.info("Before method finished logout");
    }


    @Test
    public void loginSuccess() {
        logger.info("Start test with name 'loginSuccess'");
      app.getHelperUser().openLoginRegistrationForm();
      app.getHelperUser().fillLoginRegistrationForm("mara@gmail.com","Mmar123456$");
      logger.info("Test data --> email: 'mara@gmail.com' & password: 'Mmar123456$'");
      app.getHelperUser().sumitLogin();
      //Assert
//        Assert.assertEquals();
//        Assert.assertNotEquals();
//        Assert.assertTrue()
//        Assert.assertFalse();
        Assert.assertTrue(app.getHelperUser().isLogged());
        logger.info("Assert check is element button 'Sign out' present");
    }

    @Test
    public void loginSuccessModel() {
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("mara@gmail.com","Mmar123456$");
        app.getHelperUser().sumitLogin();
        Assert.assertTrue(app.getHelperUser().isLogged());
    }


    @Test
    public void loginWrongEmail(){
        logger.info("Test data --> email: 'maragmail.com' & password: 'Mmar123456$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("maragmail.com","Mmar123456$");
        app.getHelperUser().sumitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error text 'Wrong email or password'");
    }

    @Test
    public void loginWrongPassword(){
        logger.info("Test data --> email: 'mara@gmail.com' & password: 'Mmar123'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("mara@gmail.com","Mmar123");
        app.getHelperUser().sumitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error text 'Wrong email or password'");

    }

    @Test
    public void loginUnregisteredUser(){
        logger.info("Test data --> email: 'maaa@gmail.com' & password: 'Maa123456$'");
        app.getHelperUser().openLoginRegistrationForm();
        app.getHelperUser().fillLoginRegistrationForm("maaa@gmail.com","Maa123456$");
        app.getHelperUser().sumitLogin();
        Assert.assertTrue(app.getHelperUser().isAlertPresent("Wrong email or password"));
        logger.info("Assert check is alert present with error text 'Wrong email or password'");

    }

}
