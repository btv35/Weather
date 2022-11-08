import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Btv35Test {
    /* TC_1_1 - Тест кейс:
     * 1. Открыть страницу https://openweathermap.org/
     * 2. Набрать в строке поиска город Paris
     * 3.Нажать пункт меню Search
     * 4.Из выпадающего списка выбрать Paris,FR
     * 5.Подтвердить, что заголовок изменился на Paris,FR
     */

    @Test
    public void testH2TagText_WhenSearchingCityCountry() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\ChromeDriver\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName = "Paris";
        String expectedResult = "Paris, FR";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchCityField = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//input[@placeholder = 'Search city']"));
        searchCityField.click();
        searchCityField.sendKeys(cityName);
        Thread.sleep(5000);

        WebElement searchButton = driver.findElement(By.xpath("//button[@type = 'submit']"));
        searchButton.click();
        Thread.sleep(1000);

        WebElement parisFRInDropDownMenu = driver.findElement(
                By.xpath("//ul[@class = 'search-dropdown-menu']/li/span[text() = 'Paris, FR ']"));
        parisFRInDropDownMenu.click();

        WebElement h2CityCountryHeader = driver.findElement(
                By.xpath("//div[@id = 'weather-widget']//h2"));

        Thread.sleep(5000);
        String actualResult = h2CityCountryHeader.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();

    }
    /*    TC_11_01
     * 1.  Открыть базовую ссылку https://openweathermap.org/
     * 2.  Нажать на пункт меню Guide
     * 3.  Подтвердить, что вы перешли на страницу со ссылкой https://openweathermap.org/guide и
           что title этой страницы OpenWeatherMap API guide - OpenWeatherMap
     */
    @Test
    public void testGuideMenuWithTitle_OpenWeatherMapAPIGuide() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String guideUrl = "https://openweathermap.org/guide";
        String expectedResult = "OpenWeatherMap API guide - OpenWeatherMap";

        driver.get(url);
        Thread.sleep(5000);

        WebElement menuGuide = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']//a [@href = '/guide']"));
        menuGuide.click();
        Thread.sleep(5000);

        driver.getCurrentUrl();

        Assert.assertEquals(guideUrl, driver.getCurrentUrl());

        String actualResult = driver.getTitle();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }
    /*    TC_11_02
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на единицы измерения Imperial: °F, mph
     * 3.  Подтвердить, что температура для города показана в Фарингейтах
     */
    @Test
    public void testSwitchingTemperatureToFahrenheit() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "F";

        driver.get(url);
        Thread.sleep(5000);

        WebElement switchToFahrenheit = driver.findElement(
                By.xpath("//div[@id ='weather-widget']//div[text() = 'Imperial: °F, mph']"));
        switchToFahrenheit.click();
        Thread.sleep(5000);

        WebElement temperatureInFahrenheit = driver.findElement(
                By.xpath("//div[@id='weather-widget']//span[contains (text(),'F')]"));

        String temperatureInFahrenheitText = temperatureInFahrenheit.getText();
        String actualResult = temperatureInFahrenheitText.substring(temperatureInFahrenheitText.length() - 1);

        Assert.assertEquals(actualResult,expectedResult);

        Assert.assertTrue(temperatureInFahrenheit.getText().contains(expectedResult));

        driver.quit();
    }
    /* TC_11_03
     * 1.  Открыть базовую ссылку
     * 2. Подтвердить, что внизу страницы есть панель с текстом “We use cookies which are essential for
          the site to work. We also use non-essential cookies to help us improve our services.
          Any data collected is anonymised. You can allow all cookies or manage them individually.”
     * 3.Подтвердить, что на панели внизу страницы есть 2 кнопки “Allow all” и “Manage cookies”
     */
    @Test
    public void testTwoButtonsOnTheCookiesMessage() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cookies = "We use cookies which are essential for the site to work. We also use "
                + "non-essential cookies to help us improve our services. Any data collected is "
                + "anonymised. You can allow all cookies or manage them individually.";
        String expectedResultAllowAll = "Allow all";
        String expectedResultManageCookies = "Manage cookies";

        driver.get(url);
        Thread.sleep(5000);

        WebElement cookiesPanel = driver.findElement(
                By.xpath("//div[@id = 'stick-footer-panel']" +
                        "//p[@class='stick-footer-panel__description']"));
        Assert.assertEquals(cookies, cookiesPanel.getText());

        WebElement buttonAllowAll = driver.findElement(
                By.xpath("//div[@id='stick-footer-panel']//button[@class='stick-footer-panel__link']"));
        String actualResultAllowAllButton = buttonAllowAll.getText();

        WebElement buttonManageCookies = driver.findElement(
                By.xpath("//div[@id='stick-footer-panel']//a[text()=' Manage cookies '] "));
        String actualResultButtonManageCookies = buttonManageCookies.getText();

        Assert.assertEquals(actualResultAllowAllButton, expectedResultAllowAll);
        Assert.assertEquals(actualResultButtonManageCookies, expectedResultManageCookies);

        driver.quit();
    }

    /* TC_11_04
     * 1.  Открыть базовую ссылку
     * 2.  Подтвердить, что в меню Support есть 3 подменю с названиями “FAQ”, “How to start” и
          “Ask a question”
     */
    @Test
    public void testSubmitFAQHowToStartAskAQuestion_InTheSupportMenu() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResultFaq = "FAQ";
        String expectedResultHowToStart = "How to start";
        String expectedResultAskAQuestion = "Ask a question";

        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(5000);

        WebElement menuSupport = driver.findElement(
                By.xpath("//div[@id='support-dropdown']"));
        Thread.sleep(1000);
        menuSupport.click();
        //подтверждаем 3 сабменю.
        Assert.assertEquals(driver.findElements(
                By.xpath("//div[@id='desktop-menu']//ul[@id='support-dropdown-menu']/li")).size(),3);

        WebElement subMenuFAQ = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//ul[@id='support-dropdown-menu']/li"));
        String actualResultFAQ = subMenuFAQ.getText();

        WebElement submenuHowToStart = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//ul[@id='support-dropdown-menu']"
                        + "//a[text()='How to start']"));
        String actualResultHowToStart = submenuHowToStart.getText();

        WebElement submenuAskAQuestion = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//ul[@id='support-dropdown-menu']"
                        + "//a[text()='Ask a question']"));
        String actualResultAskAQuestion = submenuAskAQuestion.getText();

        Assert.assertEquals(actualResultFAQ, expectedResultFaq);
        Assert.assertEquals(actualResultHowToStart, expectedResultHowToStart);
        Assert.assertEquals(actualResultAskAQuestion, expectedResultAskAQuestion);

        driver.quit();
    }

    /* TC_11_05
     * 1. Открыть базовую ссылку
     * 2. Нажать пункт меню Support → Ask a question
     * 3. Заполнить поля Email, Subject, Message
     * 4. Не подтвердив CAPTCHA, нажать кнопку Submit
     * 5. Подтвердить, что пользователю будет показана ошибка “reCAPTCHA verification failed, please try again.”
     */
    @Test
    public void testSubmitInputEmailSubjectMessage_WithoutCaptcha() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String enterEmail = "kiparis@gmail.com";
        String subject = "Other";
        String message = "Hello Tinky Winky";
        String expectedResult = "reCAPTCHA verification failed, please try again.";

        driver.get(url);
        driver.manage().window().maximize();

        String originalWindow = driver.getWindowHandle();

        assert driver.getWindowHandles().size() == 1;

        WebElement menuSupport = driver.findElement(
                By.xpath("//div[@id='support-dropdown']"));
        Thread.sleep(5000);
        menuSupport.click();

        WebElement submenuAskAQuestion = driver.findElement(
                By.xpath("//ul[@id='support-dropdown-menu']//a[text()='Ask a question']"));
        submenuAskAQuestion.click();
        Thread.sleep(4000);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Thread.sleep(3000);

        WebElement panelEmail = driver.findElement(
                By.xpath("//input[@class='form-control string email required']"));
        panelEmail.click();
        panelEmail.sendKeys(enterEmail);
        Thread.sleep(3000);

        WebElement chooseSubject = driver.findElement(
                By.xpath("//select[@class='form-control select required']"));
        chooseSubject.click();
        chooseSubject.sendKeys(subject);
        Thread.sleep(3000);

        WebElement enterMessage = driver.findElement(
                By.xpath("//textarea[@class='form-control text required']"));
        enterMessage.click();
        enterMessage.sendKeys(message);
        Thread.sleep(3000);

        WebElement pressSubmit = driver.findElement(
                By.xpath("//input[@data-disable-with='Create Question form']"));
        pressSubmit.click();
        Thread.sleep(3000);

        WebElement captchaTextError = driver.findElement(
                By.xpath("//div[@class='help-block']"));
        String actualResult = captchaTextError.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }
    /*    TC_11_06
    * 1.  Открыть базовую ссылку
    * 2.  Нажать пункт меню Support → Ask a question
    * 3.  Оставить значение по умолчанию в checkbox Are you an OpenWeather user?
    * 4.  Оставить пустым поле Email
    * 5.  Заполнить поля  Subject, Message
    * 6.  Подтвердить CAPTCHA
    * 7.  Нажать кнопку Submit
    * 8.  Подтвердить, что в поле Email пользователю будет показана ошибка "can't be blank"
     */
    @Test
    public void testErrorEmail() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String subject = "Other";
        String message = "Hi Lilu we are waiting for you";

        String expectedResult = "can't be blank";

        driver.get(url);

        Thread.sleep(5000);
        driver.manage().window().maximize();

        WebElement clickOnSupport = driver.findElement(By.xpath("//div[@id='support-dropdown']"));
        clickOnSupport.click();

        String originalWindow = driver.getWindowHandle();
        Thread.sleep(4000);
        WebElement selectSubmenu_AskAQuestion = driver.findElement(By.xpath(
                "//ul[@id='support-dropdown-menu']//" +
                        "a[@href='https://home.openweathermap.org/questions']"));
        selectSubmenu_AskAQuestion.click();

        Thread.sleep(4500);

        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        Thread.sleep(3000);

        WebElement enterSubject = driver.findElement(By.xpath(
                "//select[@class='form-control select required']"));

        enterSubject.click();

        enterSubject.sendKeys(subject);

        Thread.sleep(4000);

        WebElement enterMessage = driver.findElement(By.xpath(
                "//textarea[@class='form-control text required']"));
        enterMessage.click();
        enterMessage.sendKeys(message);

        Thread.sleep(5000);

        String window2 = driver.getWindowHandle();

        driver.switchTo().frame(driver.findElement(By.cssSelector("iframe[title='reCAPTCHA']")));

        WebElement enterCaptcha = driver.findElement(By.xpath(
                "//span[@class='recaptcha-checkbox goog-inline-block recaptcha-checkbox-unchecked "
                        + "rc-anchor-checkbox']"));
        enterCaptcha.click();

        Thread.sleep(10000);

        driver.switchTo().window(window2);

        WebElement pressSubmit = driver.findElement(By.xpath(
                "//input[@data-disable-with='Create Question form']"));
        pressSubmit.click();

        WebElement confirmErrorEmail = driver.findElement(By.xpath("//span[@class='help-block']"));

        String actualResult = confirmErrorEmail.getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }
    /* TC_11_07
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на единицы измерения Imperial: °F, mph
     * 3.  Нажать на единицы измерения Metric: °C, m/s
     * 4.  Подтвердить, что в результате этих действий, единицы измерения температуры изменились с F на С
     */
    @Test
    public void testChangingFromCToF() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String expectedResult = "C";

        driver.get(url);
        Thread.sleep(10000);

        WebElement switchToFahrenheit = driver.findElement(
                By.xpath("//div[@id ='weather-widget']//div[text() = 'Imperial: °F, mph']"));
        switchToFahrenheit.click();
        Thread.sleep(10000);

        WebElement switchToCelsius = driver.findElement(
                By.xpath("//div[text()='Metric: °C, m/s']"));
        switchToCelsius.click();
        Thread.sleep(5000);

        WebElement celsius = driver.findElement(
                By.xpath("//div[@class='current-temp']//span[contains(text(),'C')]"));

        String celsiusText = celsius.getText();
        String actualResult = celsiusText.substring(celsiusText.length() - 1);

        Assert.assertEquals(actualResult,expectedResult);

        driver.quit();
    }
   /* TC_11_08
    * 1. Открыть базовую ссылку
    * 2. Нажать на лого компании
    * 3. Дождаться, когда произойдет перезагрузка сайта, и подтвердить, что текущая ссылка не изменилась
    */
    @Test
    public void testSubmitOfCurrentLink_WhenClickingOnLogo () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";

        driver.get(url);
        Thread.sleep(5000);

        WebElement elementLogo = driver.findElement(
                By.xpath("//nav[@id='nav-website']//a"));
        elementLogo.click();
        Thread.sleep(5000);

        String actualResult = driver.getCurrentUrl();

        Assert.assertEquals(actualResult,url);

        driver.quit();
    }
    /* TTC_11_09
     * 1.  Открыть базовую ссылку
     * 2.  В строке поиска в навигационной панели набрать “Rome”
     * 3.  Нажать клавишу Enter
     * 4. Подтвердить, что вы перешли на страницу в ссылке которой содержатся слова “find” и “Rome”
     * 5. Подтвердить, что в строке поиска на новой странице вписано слово “Rome”
     */
    @Test
    public void testSearchingForWeatherInOtherCity () throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\ChromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        String cityName= "Rome";

        driver.get(url);
        Thread.sleep(5000);

        WebElement searchField = driver.findElement(
                By.xpath("//div[@id='desktop-menu']//input[@placeholder]"));
        searchField.click();
        searchField.sendKeys(cityName);
        Thread.sleep(4000);

        searchField.sendKeys(Keys.ENTER);

        String newUrl = driver.getCurrentUrl();
        if (newUrl.contains("find") && newUrl.contains("Rome")) {
            WebElement searchFieldNewUrl = driver.findElement(
                    By.xpath("//input[@id='search_str']"));
            String obj = searchFieldNewUrl.getAttribute("value");
            Assert.assertEquals(obj,cityName);
            Thread.sleep(4000);
            driver.quit();
        }
    }
    /* TC_11_10
     * 1.  Открыть базовую ссылку
     * 2.  Нажать на пункт меню API
     * 3.  Подтвердить, что на открывшейся странице пользователь видит 30 оранжевых кнопок
     */
    @Test
    public void testDeskTopMenuClickAPIFind30Buttons1() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:/Drivers_for_Selenium/chromedriver_win32 (1)/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        String url = "https://openweathermap.org/";
        driver.get(url);
        driver.manage().window().maximize();
        int expectedResult = 30;
        Thread.sleep(5000);

        WebElement element = driver.findElement(
                By.xpath("//div[@id = 'desktop-menu']//a[text()= 'API']"));
        element.click();

        int countButton = driver.findElements(
                By.xpath("//a[contains(@class, 'btn_block orange round') " +
                        "or contains(@class, 'ow-btn round btn-orange') ]")).size();

        int actualResult = countButton;

        Assert.assertEquals(actualResult, expectedResult);
        driver.quit();
    }


}

