package app.com.mobileassignment.views;


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

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class GeneralTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void init() {
        Intents.init();
    }


    /**
     * Method Name : visibilityOfTitle
     * Returns : Mobile Assignment display in the title
     * Method Description : Use this method to verify 'Mobile Assignment’ is displayed in the title
     */
    @Test
    public void visibilityOfTitle() {
        onView(withId(R.id.action_bar)).check(matches(isDisplayed()));
        onView(withText("Mobile Assignment")).check(matches(isDisplayed()));
    }

    /**
     * Method Name : visibilityOfSearchTextBox
     * Returns: Search box is available
     * Method Description : Use this method to verify whether the search box is displayed in the home screen
     */
    @Test
    public void visibilityOfSearchTextBox() {
        onView(withId(R.id.search)).check(matches(isDisplayed()));
        onView(withId(R.id.search)).check(matches(withHint("Search")));
    }

    /**
     * Method Name : visibilityOfCityList
     * Returns: city list is available
     * Method Description : Use this method to verify that the city list is displayed in the home screen
     */
    @Test
    public void visibilityOfCityList() {
        onView(withId(R.id.citiesList)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
    }

}
