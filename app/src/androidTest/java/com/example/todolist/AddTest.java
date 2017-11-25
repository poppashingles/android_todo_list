package com.example.todolist;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AddTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void addTest() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button_new_task), withText("Add a new task"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction editText = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.custom),
                                0),
                        0),
                        isDisplayed()));
        editText.perform(replaceText("Stuff"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.custom),
                                0),
                        1),
                        isDisplayed()));
        editText2.perform(replaceText("a"), closeSoftKeyboard());

        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button3), withText("Add new task"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.buttonPanel),
                                        0),
                                0)));
        appCompatButton2.perform(scrollTo(), click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.title_textview), withText("Stuff"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list),
                                        0),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Stuff")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
