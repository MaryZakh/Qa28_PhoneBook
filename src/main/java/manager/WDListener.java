package manager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class WDListener implements WebDriverListener {

    Logger logger = LoggerFactory.getLogger(WDListener.class);

    @Override
    public void beforeFindElements(WebElement element, By locator) {
        WebDriverListener.super.beforeFindElements(element, locator);
        logger.info("Before find element--->" + locator);
    }

    @Override
    public void afterFindElements(WebElement element, By locator, List<WebElement> result) {
        WebDriverListener.super.afterFindElements(element, locator, result);
        logger.info("After find element--->" + locator);
    }


    @Override
    public void beforeClick(WebElement element) {
        WebDriverListener.super.beforeClick(element);
        logger.info("Before click element-->" + element.getTagName() + " text = " + element.getText());
    }

    @Override
    public void afterClick(WebElement element) {
        WebDriverListener.super.afterClick(element);
        logger.info("After click element-->" + element.getTagName() + " text = " + element.getText());

    }


    @Override
    public void afterSendKeys(WebElement element, CharSequence... keysToSend) {
        WebDriverListener.super.afterSendKeys(element, keysToSend);
        logger.info("sendKys-->" +element.getTagName() );
    }


}
