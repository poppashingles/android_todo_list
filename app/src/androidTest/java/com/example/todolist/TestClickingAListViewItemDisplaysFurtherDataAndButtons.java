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
import org.junit.Before;
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
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestClickingAListViewItemDisplaysFurtherDataAndButtons {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void init(){
        mActivityTestRule.getActivity().getSupportFragmentManager().beginTransaction();
        TaskRepo taskRepo = new TaskRepo(mActivityTestRule.getActivity());
        taskRepo.deleteAll();
    }

    @Test
    public void testClickingAListViewItemDisplaysFurtherDataAndButtons() {
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button_new_task), withText("Add a new task"),
                        isDisplayed()));
        appCompatButton.perform(click());


//       Matcher<View> child2 = childAtPosition(withId(R.id.custom), 0);
//
//       Matcher<View> child1 = childAtPosition(child2, 0);



//        Matcher<View> frameLayout = withId(R.id.custom);
//
//        Matcher<View> dialogLayout = childAtPosition(frameLayout, 0);
//
//        Matcher<View> editTextMatcher = childAtPosition(dialogLayout, 0);
//
//        ViewInteraction editText = onView(editTextMatcher);
//


                 ViewInteraction editText = onView(withHint("Title"));


//        allOf(frameLayout, childAtPosition(
//                frameLayout, 0
//        )

//                allOf(childAtPosition(
//                        childAtPosition(
//                                withId(R.id.custom),
//                                0),
//                        0),
//                        isDisplayed()));
        editText.perform(replaceText("Laundry"), closeSoftKeyboard());

        ViewInteraction editText2 = onView(withHint("Description"));

//        ViewInteraction editText2 = onView(
//                allOf(childAtPosition(
//                        childAtPosition(
//                                withId(R.id.custom),
//                                0),
//                        1),
//                        isDisplayed()));
        editText2.perform(replaceText("Do stuff"), closeSoftKeyboard());


        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button3)));
        appCompatButton2.perform(scrollTo(), click());


        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.title_textview),
                        isDisplayed()));
        appCompatTextView.perform(click());


        ViewInteraction textView = onView(
                allOf(withId(R.id.alertTitle),
                        isDisplayed()));

        //only use withText either:
            //1. Multiple same id
            //2. Dont know id
            // if multiple with text with same, then use childAtPosition

        textView.check(matches(withText("Laundry")));


        ViewInteraction textView2 = onView(
                allOf(withId(android.R.id.message),
                        isDisplayed()));
        textView2.check(matches(withText("Do stuff")));


        ViewInteraction button = onView(
                allOf(withId(android.R.id.button3),
                        isDisplayed()));
        button.check(matches(isDisplayed()));


        ViewInteraction button2 = onView(
                allOf(withId(android.R.id.button2),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));

    }

    private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher, final int position) {

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
