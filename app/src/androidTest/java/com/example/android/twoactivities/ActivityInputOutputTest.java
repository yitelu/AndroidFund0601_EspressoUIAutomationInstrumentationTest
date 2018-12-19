/*
 * Copyright (C) 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.twoactivities;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;

/**
 * The Two Activities app provides a text entry field and a Send button (the
 * button_main id). Clicking Send launches the Second activity with the entered
 * text shown in the text_header view of the Second activity.
 *
 * The first Espresso test performs a click on the main activity's button, and
 * checks to see if the text_header matches what is displayed. If it passes, it
 * means that the second activity was started. The test then clicks the second
 * activity's button, and checks to see if the text_header_reply matches what is
 * displayed. If it passes, it means that the main activity was started from the
 * second activity.
 *
 * The second test locates the view containing the editText_main view, enters
 * the text "This is a test." and clicks the main button. It then compares the
 * text_message with the assertion "This is a test." If it passes, it means that
 * the entered text was successfully passed to the text_message field.
 */

@RunWith(AndroidJUnit4.class)
public class ActivityInputOutputTest {

    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.android.twoactivities",
                appContext.getPackageName());
    }

    @Test
    public void activityLaunch() {
        //click send button in the 1st activity and check if the 2nd activity's text would appear.
        onView(withId(R.id.button_main)).perform(click());
        onView(withId(R.id.text_header)).check(matches(isDisplayed()));

        //(vice versa) click send button in the 2 activity and check if the 1st activity's text would appear.
        onView(withId(R.id.button_second)).perform(click());
        onView(withId(R.id.text_header_reply)).check(matches(isDisplayed()));
    }

    @Test
    public void textInputOutput() {

        //send test in the edit text field in the 1st activity
        onView(withId(R.id.editText_main)).perform(typeText("1st activity main ET"));
        //click the send button in the 1st activity
        onView(withId(R.id.button_main)).perform(click());

        //validate the 2nd activity's text view field to check if the text display is correct
        onView(withId(R.id.text_message)).check(matches(withText("1st activity main ET")));
    }
}
