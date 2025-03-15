package tests;

import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase{

    @BeforeMethod(alwaysRun = true)
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("margo@gmail.com").withPassword("Mmar123456$"));
        }
        app.getHelperContact().provideContacts();//if list of contacts <3 ==> add 3 contacts


    }

    @Test(groups = {"smoke"})
    public void removeFirstContact(){
        //Assert size contactList less by one
        Assert.assertEquals(app.getHelperContact().removeOneContact(), 1);

    }

    @Test
    public void removeAllContacts(){
        app.getHelperContact().removeAllContacts();
        Assert.assertTrue(app.getHelperContact().isNoContactsHereDisplayed());
        //"No contacts here

    }
}
