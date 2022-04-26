package com.cucumber.junit.steps;

import desktop.pages.AccountPage;
import desktop.pages.HomePage;
import desktop.pages.SearchResultsPage;
import driver.SingletonDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebDriver;

public class StepDefs {

    WebDriver driver = SingletonDriver.getInstance();
    HomePage homePageObject;
    SearchResultsPage searchResultsPageObject;
    AccountPage accountPageObject;

    @Given("^the user opens bookdepository site$")
    public void openSite() {
        homePageObject = new HomePage(driver);
    }

    @When("^searches for a search term \"([^\"]*)\"$")
    public void searchForTerm(String searchTerm) {
        homePageObject.enterSearchTerm(searchTerm);
        searchResultsPageObject = homePageObject.searchButtonClick();
    }

    @Then("^the search results are displayed$")
    public void pageWithSearchResultsIsPresent() {
        Assertions.assertThat(searchResultsPageObject.isSearchResultsPresent())
                .overridingErrorMessage("Search results are not displayed")
                .isTrue();
    }

    @When("^clicks on the Sign in button on navigation bar$")
    public void clickSigninButton() {
        accountPageObject = homePageObject.navBarClick();
        accountPageObject.isLoginTitleDisplayed();
    }

    @When("^the user fills in the login and password$")
    public void fillInCredentials() {
        accountPageObject.fillInSignInFields("olsteshen@example.com", "Temp12345");
    }

    @Then("^the user is logged in$")
    public void advancedSearchPageIsDisplayed() {
        accountPageObject.checkURL();
    }
}
