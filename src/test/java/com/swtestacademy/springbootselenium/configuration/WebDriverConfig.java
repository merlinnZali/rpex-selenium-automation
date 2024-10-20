package com.swtestacademy.springbootselenium.configuration;

import com.swtestacademy.springbootselenium.annotations.LazyConfiguration;
import com.swtestacademy.springbootselenium.annotations.WebdriverScopeBean;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("!grid")
@LazyConfiguration
public class WebDriverConfig {
    @WebdriverScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    @Primary
    public WebDriver firefoxDriver() {
        //WebDriverManager.firefoxdriver().setup();
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        Proxy proxy = new Proxy();
        proxy.setAutodetect(false);
        proxy.setNoProxy("no_proxy-var");

        /*proxy.setProxyType(org.openqa.selenium.Proxy.ProxyType.DIRECT);
        proxy.setHttpProxy("<HOST:PORT>");
        proxy.setSslProxy("<HOST:PORT>");
        proxy.setFtpProxy("<HOST:PORT>");*/

        //firefoxOptions.setProxy(proxy);

        firefoxOptions.setCapability("proxy", proxy);
        FirefoxDriver firefoxDriver = new FirefoxDriver(firefoxOptions);
        setBasicWebDriverProperties(firefoxDriver);
        return firefoxDriver;
    }

    @WebdriverScopeBean
    @ConditionalOnProperty(name = "browser", havingValue = "edge")
    @Primary
    public WebDriver edgeDriver() {
        EdgeOptions edgeOptions = new EdgeOptions();
        EdgeDriver edgeDriver = new EdgeDriver(edgeOptions);
        setBasicWebDriverProperties(edgeDriver);
        return edgeDriver;
    }

    @WebdriverScopeBean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(name = "browser", havingValue = "chrome")
    @Primary
    public WebDriver chromeDriver() {
        //DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        ChromeOptions chromeOptions = new ChromeOptions();
        /*
        chromeOptions.addArguments("--no-sandbox"); // Bypass OS security model, MUST BE THE VERY FIRST OPTION
        chromeOptions.addArguments("--headless");
        chromeOptions.setExperimentalOption("useAutomationExtension", false);
        chromeOptions.addArguments("start-maximized"); // open Browser in maximized mode
        chromeOptions.addArguments("disable-infobars"); // disabling infobars
        chromeOptions.addArguments("--disable-extensions"); // disabling extensions
        chromeOptions.addArguments("--disable-gpu"); // applicable to windows os only
        chromeOptions.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
        chromeOptions.merge(capabilities);
        */
        ChromeDriver chromeDriver = new ChromeDriver(chromeOptions);
        setBasicWebDriverProperties(chromeDriver);
        return chromeDriver;
    }

    private static void setBasicWebDriverProperties(WebDriver webDriver) {
        webDriver.manage().window().maximize();
    }
}
