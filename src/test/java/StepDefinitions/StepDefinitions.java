package StepDefinitions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import cucumber.api.java.After;
import cucumber.api.java.en.*;
import lib.GeneralActions;

import org.openqa.selenium.JavascriptExecutor;


public class StepDefinitions {
	WebDriver driver = null; 
	Logger log = Logger.getLogger("StepDefinitions");
	GeneralActions genAct;
	private static final String searchBoxXpath = "//input[@id='search_query_top')]" ;
	private static final String productListXpath = "//ul[contains(@class, 'product_list')]";
	private static final String productNameXpath = "//a[contains(@class, 'product-name')]";
	private static final String productItemNameXpath = "//a[contains(@title, 'Faded Short Sleeve T-shirts')]";
	private static final String addToCartXpath = "//a[contains(@title, 'Add to cart')]";
	private static final String productCartTitleXpath = "//span[@id='layer_cart_product_title']";
	private static final String cartMessageHeaderXpath = "//div[contains(@class, 'layer_cart_product']/h2";
	private static final String moreLinkXpath = "//span[contains(text(), 'More')]";
	private static final String productDetailsXpath = "//h1[contains(@itemprop, 'name')]";
	private static final String productPriceXpath ="//span[@id, 'our_price_display']";
	private static final String productQuantityXpath = "//input[@id, 'quantity_wanted']";
	private static final String productPlusIconXpath = "//i[@class, 'icon-plus')]";
	private static final String productMinusIconXpath = "//i[@class, 'icon-minus')]";
	private static final String productSizeXpath = "//select[@id, 'group_1')]";
	private static final String productDetailsAddToCartXpath = "//p[@id='add_to_cart']//button//span[contains(tex(), 'Add to cart')]";
	private static final String loginXpath = "//a[@class='login']";
	private static final String loginFormXpath = "//form[@id='login_form']";
	private static final String emailFieldXpath = "//input[@id='email']";
	private static final String passwordFieldXpath = "//input[@id='passwd']";
	private static final String submitButtonXpath = "//button[@id='SubmitLogin']";
	private static final String errorMessgaeXpath ="//div[contains(@class, 'alert-danger')]";
	
    @Given("^User opens the website$")
    public void user_opens_the_website() throws Throwable {
    	log.info("This is test");
    	try {
    		System.setProperty("webdriver.chrome.driver", "D:\\Project\\Automation\\chromedriver.exe");
    		driver = new ChromeDriver(); 
    		driver.navigate().to("http://automationpractice.com/index.php");
    		driver.manage().window().maximize();
    	} catch (Exception e) {
    		log.error(e.getMessage());
    	}
    }

    @When("^User enters the product name as ([^\"]*) in search field$")
    public void user_enters_the_product_name_in_search_field(String productName) throws Throwable {
    	Thread.sleep(10);
    	    	
    	WebDriverWait wait = new WebDriverWait(driver, 15);
    	wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(searchBoxXpath)));
    	try {
    		WebElement searchBox = driver.findElement(By.id("search_query_top"));    	
	    	if(searchBox!= null) {
	            searchBox.sendKeys(productName);
	    	} else {
	    		log.error("Searchbox is not displayed");
	    	}
    	} catch (Exception e) {
    		log.error(e.getMessage());
    	}
    }

    @When("^User clicks search icon$")
    public void user_clicks_search_icon() throws Throwable {
    	try {
    		driver.findElement(By.name("submit_search")).click();
	    } catch (Exception e) {
			log.error(e.getMessage());
		}
    }

    @Then("^User can see the product displayed$")
    public void user_can_see_the_product_displayed() throws Throwable {
    	try {
	        Thread.sleep(5);
	    	driver.findElement(By.xpath(productListXpath));
    	} catch (Exception e) {
			log.error(e.getMessage());
		}
    }

    @Then("^User can see valid product name as ([^\"]*)$")
    public void user_can_see_valid_product_name(String productName) throws Throwable {
    	try {
	    	String prodName = driver.findElement(By.xpath(productNameXpath)).getText();
	    	Assert.assertEquals(productName, prodName);
    	} catch (Exception e) {
			log.error(e.getMessage());
		}
    }
    
    @When("^User scroll down in home page$")
    public void user_scroll_down_in_home_page() throws Throwable {
    	try {
	    	JavascriptExecutor js = (JavascriptExecutor) driver;
	    	WebElement searchBox = driver.findElement(By.id("search_query_top"));
	    	js.executeScript("arguments[0].scrollIntoView();", searchBox);
    	} catch (Exception e) {
			log.error(e.getMessage());
		}
    }
    
    @When("^User clicks Add to Cart button for ([^\"]*)$")
    public void user_clicks_Add_to_Cart_button_for() throws Throwable {
    	try {
	    	driver.findElement(By.xpath(productItemNameXpath));
	    	Actions action = new Actions(driver);
	    	WebElement we = driver.findElement(By.xpath(productItemNameXpath));
	    	action.moveToElement(we).click().build().perform();
	    	driver.findElement(By.xpath(addToCartXpath)).click();
    	} catch (Exception e) {
			log.error(e.getMessage());
		}
    }

    @Then("^User can see the pop-up displayed$")    
    public void user_can_see_the_pop_up_displayed() throws Throwable {
    	try {
	    	Thread.sleep(5);
	    	if(driver.findElement(By.id("layer_cart"))!= null) {
	    		log.error("Pop-up box is displayed");
	    	} else {
	    		log.info("Pop-up box is not displayed");
	    	}
    	}
    	catch (Exception e) {
			log.error(e.getMessage());
		}
    }

    @Then("^User can see valid product name as ([^\"]*) displayed$")
    public void user_can_see_valid_product_name_as_displayed(String productName) throws Throwable {
    	try {
	    	Thread.sleep(5);
	    	String displayedProdName = driver.findElement(By.xpath(productCartTitleXpath)).getText();
	    	Assert.assertEquals( displayedProdName, productName);
    	} catch (Exception e) {
			log.error(e.getMessage());
		}
    }
    
    @Then("^User can see the successful message as ([^\"]*)$")
    public void user_can_see_the_successful_message_as(String message) throws Throwable {
    	try {
	    	String displayedMessage = driver.findElement(By.xpath(cartMessageHeaderXpath)).getText();
	    	Assert.assertEquals( displayedMessage, message);
    	} catch (Exception e) {
			log.error(e.getMessage());
		}
    }

    @When("^User mousehover on ([^\"]*) $")
    public void user_mousehover_on(String product) throws Throwable {
    	try {
	    	Actions actions = new Actions(driver);
	    	WebElement productNameLink = driver.findElement(By.xpath("//a[contains(@title, " + product + ")]"));    	
	    	actions.moveToElement(productNameLink).perform();
    	} catch (Exception e) {
			log.error(e.getMessage());
		}
    }

    @Then("^User can see More link$")
    public void user_can_see_More_link() throws Throwable {
    	try {
	    	Thread.sleep(1);
	    	if(genAct.isElementPresent(driver, moreLinkXpath)) {
	    		log.error("More link is displayed");
	    	} else {
	    		log.info("More link is not displayed");
	    	}
    	} catch (Exception e) {
			log.error(e.getMessage());
		}
    }

    @When("^User click on More link$")
    public void user_click_on_More_link() throws Throwable {
    	try {
    		driver.findElement(By.xpath(moreLinkXpath)).click();
    	} catch (Exception e) {
			log.error(e.getMessage());
		}
    }

    @Then("^User should be redirected to product details page$")
    public void user_should_be_redirected_to_product_details_page() throws Throwable {
    	try {
	    	if(genAct.isElementPresent(driver, productDetailsXpath)) {
	    		log.error("Product details page is displayed");
	    	} else {
	    		log.info("Product details page is not displayed");
	    	}
	    } catch (Exception e) {
			log.error(e.getMessage());
		}
    }
    
    @Then("^User can see the product name as ([^\"]*) $")
    public void user_can_see_the_product_name(String productName) throws Throwable {
    	try {
	    	Thread.sleep(5);
	    	String displayedProdName = driver.findElement(By.xpath(productDetailsXpath)).getText();
	    	Assert.assertEquals( displayedProdName, productName);
	    } catch (Exception e) {
			log.error(e.getMessage());
		}
    }

    @Then("^User can see the product price as ([^\"]*)$")
    public void user_can_see_the_product_price_as(String price) throws Throwable {  
    	try {
	    	String displayedPrice = driver.findElement(By.xpath(productPriceXpath)).getText();
	    	Assert.assertEquals( displayedPrice, price);
	    	
    	} catch (Exception e) {
			log.error(e.getMessage());
		}
    }

    @Then("^User can see the product quantity as (\\d+)$")
    public void user_can_see_the_product_quantity_as(int qty) throws Throwable {
    	try {
	    	String displayedQty = driver.findElement(By.xpath(productQuantityXpath)).getAttribute("value");
	    	Assert.assertEquals( displayedQty, qty);
    	} catch (Exception e) {
			log.error(e.getMessage());
		}
    }
    @When("^User clicks the + button$")
    public void user_clicks_the_button1() throws Throwable {
    	try {
	    	if(genAct.isElementPresent(driver, productPlusIconXpath)) {
	    		driver.findElement(By.xpath(productPlusIconXpath)).click();
	    	} else {
	    		log.info("+ button is not displayed");
	    	}
    	} catch (Exception e) {
			log.error(e.getMessage());
		}
    }

    @When("^User clicks the - button$")
    public void user_clicks_the_button() throws Throwable {
    	try {
	    	if(genAct.isElementPresent(driver, productMinusIconXpath)) {
	    		driver.findElement(By.xpath(productMinusIconXpath)).click();
	    	} else {
	    		log.info("- button is not displayed");
	    	}
    	} catch (Exception e) {
			log.error(e.getMessage());
		}
    }

    @Then("^User can see the size as ([^\"]*)$")
    public void user_can_see_the_size_as(String size) throws Throwable {
    	try {
	    	if(genAct.isElementPresent(driver, productSizeXpath)) {
	    		String sizeText = driver.findElement(By.xpath(productSizeXpath)).getText();
	    		Assert.assertEquals(sizeText, size);
	    	} else {
	    		log.info("Size element is not displayed");
	    	}
    	} catch (Exception e) {
			log.error(e.getMessage());
		}
    }
	
	@Then("^User can select the size as ([^\"]*)$")
	public void user_can_select_the_size(String size) throws Throwable {
	   try {
		   Select sizeDropdown = new Select(driver.findElement(By.id("group_1")));
		   sizeDropdown.selectByVisibleText(size);
	   } catch(Exception e) {
		   log.error(e.getMessage()); 
	   }
	}
	
	@Then("^User clicks Add to cart button$")
	public void user_clicks_Add_to_cart_button() throws Throwable {
		try {
			WebElement ele = driver.findElement(By.xpath(productDetailsAddToCartXpath));
			ele.click();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
    @When("^User clicks Login button$")
    public void user_clicks_login_button() throws Throwable {
		try {
			WebElement ele = driver.findElement(By.xpath(loginXpath));
			ele.click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			log.info(e.toString());
		}
	}
    
    @Then("^User should be redirected to Login page$")
    public void user_should_redirected_to_login_page() throws Throwable {
		try {
			if(genAct.isElementPresent(driver, loginFormXpath)) {
				log.info("User redirected to Login Page");
	    	} else {
	    		log.info("User not redirected to Login Page");
	    	}
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}
    
    @When("^User enters \"(.*?)\" in Email address field$")
    public void user_enters_email_address(String email) throws Throwable {
		try {
			if(genAct.isElementPresent(driver, emailFieldXpath)) {
				driver.findElement(By.xpath(emailFieldXpath)).sendKeys(email);
	    	} else {
	    		log.error("Email field not found.");
	    	}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
    @When("^User enters \"(.*?)\" in Password field$")
    public void user_enters_password_field(String password) throws Throwable {
		try {
			if(genAct.isElementPresent(driver, passwordFieldXpath)) {
				driver.findElement(By.xpath(passwordFieldXpath)).sendKeys(password);
	    	} else {
	    		log.error("Password field not found.");
	    	}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
    @When("^User clicks the Sign in buttton$")
    public void user_clicks_sign_in_button() throws Throwable {
		try {
			if(genAct.isElementPresent(driver, submitButtonXpath)) {
				driver.findElement(By.xpath(submitButtonXpath)).click();
	    	} else {
	    		log.error("Sign in button not found.");
	    	}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
    @Then("^User should be redirected to login page$")
    public void user_should_be_redirected_to_login_page() throws Throwable {
    	try {
			if(genAct.isElementPresent(driver, loginFormXpath)) {
				log.info("User redirected to Login Page");
	    	} else {
	    		log.error("User not redirected to Login Page");
	    	}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
    
    @Then("^User can see the authentication failure message as \"(.*?)\"$")
    public void user_can_see_the_authentication_failure_message(String message) throws Throwable {
    	try {
			if(genAct.isElementPresent(driver, errorMessgaeXpath)) {
				String authenticationErrorMessage = driver.findElement(By.xpath(errorMessgaeXpath)).getText();
				Assert.assertEquals(authenticationErrorMessage, message);
	    	} else {
	    		log.error("User not redirected to Login Page");
	    	}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

    @After
    public void teardown() {
    	driver.close();
    }
}