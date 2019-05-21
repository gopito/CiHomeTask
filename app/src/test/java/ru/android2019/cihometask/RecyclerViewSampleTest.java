/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ru.android2019.cihometask;

import android.content.Context;
import android.view.View;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.FailureHandler;
import androidx.test.espresso.base.DefaultFailureHandler;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.android.testing.espresso.RecyclerViewSample.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


/**
 * Test class showcasing some {@link RecyclerViewActions} from Espresso.
 */
@RunWith(AndroidJUnit4.class)
@Config(manifest = Config.NONE)
public class RecyclerViewSampleTest {

    private ActivityScenario<MainActivity> scenario;
    private String CLICKED_TEXT = "This is element #0 Clicked";
    private String NOT_CLICKED_TEXT = "This is element #0";
    private String MIDDLE_ELEMENT_TEXT = "This is the middle!";
    @Before
    public void launchActivity() {
        scenario = ActivityScenario.launch(MainActivity.class);
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.moveToState(Lifecycle.State.RESUMED);
    }

    @Test
    public void toggleTextTest() {
        //Небольшой костыль, чтобы читаемая ошибка была
        Espresso.setFailureHandler(new ClickFailureHandler());
        RecyclerViewMatcher recyclerViewMatcher = new RecyclerViewMatcher(R.id.recyclerView);
        Matcher<View> buttonMatcher = recyclerViewMatcher.atPositionOnView(0, R.id.button);
        Matcher<View> textMatcher = recyclerViewMatcher.atPositionOnView(0, R.id.textView);

        onView(buttonMatcher).perform(click());
        onView(textMatcher).check(matches(withText(CLICKED_TEXT)));

        onView(buttonMatcher).perform(click());
        onView(textMatcher).check(matches(withText(NOT_CLICKED_TEXT)));

        onView(buttonMatcher).perform(click());
        onView(textMatcher).check(matches(withText(CLICKED_TEXT)));


    }

    @Test
    public void itemInMiddleOfList_hasSpecialText() {
        //Небольшой костыль, чтобы читаемая ошибка была
        Espresso.setFailureHandler(new MiddleFailureHandler());
        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.scrollToHolder(isInTheMiddle()));

        // Надо юзать check(matches(isDisplayed()) но у  Robolectirc'а траблы с этим.
        onView(withText(MIDDLE_ELEMENT_TEXT)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    /**
     * Matches the {@link CustomAdapter.ViewHolder}s in the middle of the list.
     */
    private static Matcher<CustomAdapter.ViewHolder> isInTheMiddle() {
        return new TypeSafeMatcher<CustomAdapter.ViewHolder>() {
            @Override
            protected boolean matchesSafely(CustomAdapter.ViewHolder customHolder) {
                return customHolder.getIsInTheMiddle();
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("item in the middle");
            }
        };
    }

    private class ClickFailureHandler implements FailureHandler {
        private final FailureHandler delegate;

        public ClickFailureHandler() {
            delegate = new DefaultFailureHandler(ApplicationProvider.getApplicationContext());
        }

        @Override
        public void handle(final Throwable error, final Matcher<View> viewMatcher) {
            delegate.handle(new Exception("Надо поправить android.view.View.OnClickListener.onClick в " +
                    "CustomAdapter.\n\r" +
                    String.format("Получили текст \"%s\" хотя ожидали \"%s\"", NOT_CLICKED_TEXT, CLICKED_TEXT)), viewMatcher);
        }
    }


    private class MiddleFailureHandler implements FailureHandler {
        private final FailureHandler delegate;
        private final Context applicationContext;

        public MiddleFailureHandler() {
            applicationContext = ApplicationProvider.getApplicationContext();
            delegate = new DefaultFailureHandler(applicationContext);
        }

        @Override
        public void handle(final Throwable error, final Matcher<View> viewMatcher) {

            delegate.handle(new Exception(String.format("Искали \"%s\" a нашли \"%s\""
                    ,MIDDLE_ELEMENT_TEXT, applicationContext.getString(R.string.middle))), viewMatcher);
        }
    }


}