package tests;

import manager.DataProviderContact;
import models.Contact;
import models.User;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AddNewContactTests extends TestBase {


    @BeforeClass(alwaysRun = true)
    public void preCondition() {
        if (!app.getHelperUser().isLogged()) {
            app.getHelperUser().login(new User().withEmail("margo@gmail.com").withPassword("Mmar123456$"));
        }

    }
//for jenkins

    @Test(dataProvider = "contactSuccess", dataProviderClass = DataProviderContact.class)
    public void addContactSuccessAllFields(Contact contact) {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
//
//        Contact contact = Contact.builder()
//                .name("Tony" + i)
//                .lastName("Stark")
//                .address("NY")
//                .phone("34343434" + i)
//                .email("stark" + i + "@gmail.com")
//                .description("all fields")
//                .build();
        logger.info("Tests run with data: --->" + contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
       // app.getHelperContact().pause(15000);
        app.getHelperContact().getScreen("src/test/screenshots/screen-" + i +".png");
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test(dataProvider = "contactCSV", dataProviderClass = DataProviderContact.class)
    public void addContactSuccessAllFieldsCSV(Contact contact) {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);

        logger.info("Tests run with data: --->" + contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        // app.getHelperContact().pause(15000);
        app.getHelperContact().getScreen("src/test/screenshots/screen-" + i +".png");
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test(groups = {"smoke","regress","retest"})
    public void addContactSuccessReqFields() {
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);

        Contact contact = Contact.builder()
                .name("TonyReq")
                .lastName("Stark")
                .address("NY")
                .phone("34343434" + i)
                .email("stark" + i + "@gmail.com")
                .build();
        logger.info("Tests run with data: --->" + contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isContactAddedByName(contact.getName()));
        Assert.assertTrue(app.getHelperContact().isContactAddedByPhone(contact.getPhone()));
    }

    @Test
    public void addNewContactWrongName() {
        Contact contact = Contact.builder()
                .name("")
                .lastName("Stark")
                .address("NY")
                .phone("343434349862")
                .email("stark@gmail.com")
                .description("Empty name")
                .build();
        logger.info("Tests run with data: --->" + contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
    }

    @Test
    public void addNewContactWrongLastName() {
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("")
                .address("NY")
                .phone("343434349862")
                .email("stark@gmail.com")
                .description("Empty LastName")
                .build();
        logger.info("Tests run with data: --->" + contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
    }

    @Test
    public void addNewContactWrongAddress() {
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Stark")
                .address("")
                .phone("343434349862")
                .email("stark@gmail.com")
                .description("Empty Address")
                .build();
        logger.info("Tests run with data: --->" + contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
    }

    @Test(dataProvider = "ContactWrongPhone",dataProviderClass = DataProviderContact.class)
    public void addNewContactWrongPhone(Contact contact) {
//        Contact contact = Contact.builder()
//                .name("Tony")
//                .lastName("Stark")
//                .address("NY")
//                .phone("")
//                .email("stark@gmail.com")
//                .description("Empty Phone")
//                .build();
        logger.info("Tests run with data: --->" + contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent(" Phone not valid: Phone number must contain only digits! And length min 10, max 15!"));
    }

    @Test
    public void addNewContactWrongEmail() {
        Contact contact = Contact.builder()
                .name("Tony")
                .lastName("Stark")
                .address("NY")
                .phone("343434349862")
                .email("starkgmail.com")
                .description("Wrong email")
                .build();
        logger.info("Tests run with data: --->" + contact.toString());
        app.getHelperContact().openContactForm();
        app.getHelperContact().fillContactForm(contact);
        //app.getHelperContact().pause(15000);
        app.getHelperContact().saveContact();
        Assert.assertTrue(app.getHelperContact().isAddPageStillDisplayed());
        Assert.assertTrue(app.getHelperContact().isAlertPresent("Email not valid: "));

    }


}
