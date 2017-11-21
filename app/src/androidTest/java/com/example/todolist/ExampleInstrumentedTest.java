package com.example.todolist;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)

public class ExampleInstrumentedTest {

//  Tell the tests which page to start on when testing
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

//  Do before each test
    @Before
    public void init(){
        activityActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
    }

//  Do after each test
    @After
    public void tearDown() {}

//  Tests Context is correct
    @Test
    public void testUseAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.todolist", appContext.getPackageName());
    }

//  Tests presence of 'add new task' button on main activity
    @Test
    public void testCheckText() {
        onView(withId(R.id.button_new_task)).check(matches(isDisplayed()));
    }

//  Test that the button brings up 'add new entry' alert dialog with hints in the two fields
    @Test
    public void testClickAddNewButtonBringsUpAddNewAlertDialog() {
        onView(withId(R.id.button_new_task)).perform(click());

        onView(withHint("Title")).check(matches(withText("")));
        onView(withHint("Description")).check(matches(withText("")));

    }


}
