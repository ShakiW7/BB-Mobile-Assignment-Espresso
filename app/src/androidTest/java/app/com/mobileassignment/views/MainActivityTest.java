package app.com.mobileassignment.views;


import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import app.com.mobileassignment.R;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.startsWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    private static final String valid_CityName = "Amsterdam";
    private static final String invalid_CityName = "Nugegoda";
    private static final String specialCharacter = "ñ";
    private static final String countyCode = "NL";
    private static final String number = "665";
    private static final String comma = ",";

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void init() {
        Intents.init();
    }

    /**
     * Method Name : searchCity
     * Returns: search city display in the city list
     * Method Description : Use this method to search a city and verify whether it is available in the city list
     */
    @Test
    public void searchCity() throws InterruptedException {
        onView(withId(R.id.search)).perform(typeText(valid_CityName), closeSoftKeyboard());
        Thread.sleep(2000);
        onData(anything()).inAdapterView(withId(R.id.citiesList)).atPosition(0)
                .onChildView(withId(R.id.cityName))
                .check(matches(withText(startsWith(valid_CityName))));
    }

    /**
     * Method Name : checkListSize
     * Returns: 4 cities avilable in the city list
     * Method Description : Use this method to search a city and verify the number of items in the list
     */
    @Test
    public void checkListSize() throws InterruptedException {
        onView(withId(R.id.search)).perform(typeText(valid_CityName), closeSoftKeyboard());
        Thread.sleep(2000);
        onView(withId(R.id.citiesList)).check(ViewAssertions.matches(Matchers.withListSize(4)));
    }

    /**
     * Method Name : selectCity
     * Returns: Navigate to the map
     * Method Description : Use this method to search a city and navigate to the map by clicking on city name
     */
    @Test
    public void selectCity() throws InterruptedException {
        onView(withId(R.id.search)).perform(typeText(valid_CityName), closeSoftKeyboard());
        Thread.sleep(2000);
        onData(anything()).inAdapterView(withId(R.id.citiesList)).atPosition(0).perform(click());
        Thread.sleep(2000);
        intended(hasComponent(MapActivity.class.getName()));
    }

    /**
     * Method Name : scrollToAPosition
     * Returns: Navigate to the map
     * Method Description : Use this method to scroll to a position and check whether the “Aachen, DE” city is displayed in the list
     */
    @Test
    public void scrollToAPosition() {
        onData(anything()).atPosition(25).perform(swipeUp());
        onView(withText("Aachen, DE")).check(matches(isDisplayed()));
    }

    /**
     * Method Name : searchEmptyValue
     * Returns: city list is empty
     * Method Description : Use this method to search an empty value and to check if nothing is displayed in the city list
     */
    @Test
    public void searchEmptyValue() throws InterruptedException {
        onView(withId(R.id.search)).perform(typeText(" "), closeSoftKeyboard());
        Thread.sleep(1000);
        onView(withId(R.id.cityName))
                .check(doesNotExist());
    }

    /**
     * Method Name : searchInvalidValue
     * Returns: city list is empty
     * Method Description : Use this method to search an Invalid city and to check if nothing is displayed in the city list
     */
    @Test
    public void searchInvalidValue() throws InterruptedException {
        onView(withId(R.id.search)).perform(typeText(invalid_CityName), closeSoftKeyboard());
        Thread.sleep(4000);
        onView(withId(R.id.cityName)).check(doesNotExist());
    }

    /**
     * Method Name : searchSpecialCharacter
     * Returns: all the cities with "ñ" display in the city list
     * Method Description : Use this method to search "ñ" and check if all the displayed cities are starting with "ñ"
     */
    @Test
    public void searchSpecialCharacter() {
        onView(withId(R.id.search)).perform(replaceText(specialCharacter), closeSoftKeyboard());
        onView(withText(specialCharacter)).check(matches(isCompletelyDisplayed()));
    }

    /**
     * Method Name : searchPunctuation
     * Returns: city list is empty
     * Method Description : Use this method to search comma and verify whether the city list is empty
     */
    @Test
    public void searchPunctuation() throws InterruptedException {
        onView(withId(R.id.search)).perform(replaceText(comma), closeSoftKeyboard());
        Thread.sleep(1000);
        onView(withId(R.id.cityName))
                .check(doesNotExist());
    }

    /**
     * Method Name : searchNumber
     * Returns: all the city start with numbers display
     * Method Description : Use this method to search numbers and verify whether the cities are starting with numbers
     */
    @Test
    public void searchNumber() throws InterruptedException {
        onView(withId(R.id.search)).perform(typeText(number), closeSoftKeyboard());
        Thread.sleep(1000);
        onData(anything()).inAdapterView(withId(R.id.citiesList)).atPosition(0)
                .onChildView(withId(R.id.cityName))
                .check(matches(withText(containsString(number))));
    }

    /**
     * Method Name : filterByRandomText
     * Returns: all the city start with Aar display in the list
     * Method Description : Use this method to search cities that  starts with "Aar" and verify if the displayed cities are starting with "Aar"
     */
    @Test
    public void filterByRandomText() throws InterruptedException {
        onView(withId(R.id.search)).perform(typeText("Aar"), closeSoftKeyboard());
        Thread.sleep(1000);
        for (int i = 0; i < 14; i++) {
            onData(anything())
                    .inAdapterView(withId(R.id.citiesList))
                    .atPosition(i)
                    .onChildView(withId(R.id.cityName))
                    .check(matches(withText(startsWith("Aar"))));
            Thread.sleep(500);
        }
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }
}
