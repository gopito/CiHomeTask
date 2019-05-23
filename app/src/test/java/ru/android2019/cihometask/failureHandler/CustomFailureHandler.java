package ru.android2019.cihometask.failureHandler;

import android.content.Context;
import android.view.View;

import androidx.test.espresso.AmbiguousViewMatcherException;
import androidx.test.espresso.FailureHandler;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.base.DefaultFailureHandler;

import org.hamcrest.Matcher;

/**
 * Created by asmolianin on 13.10.17.
 */

public class CustomFailureHandler implements FailureHandler {
    private final FailureHandler delegate;

    public CustomFailureHandler(Context targetContext) {
        delegate = new DefaultFailureHandler(targetContext);
    }

    @Override
    public void handle(Throwable error, Matcher<View> viewMatcher) {
        try {
            delegate.handle(error, viewMatcher);
        } catch (NoMatchingViewException e) {
            throw new NoHierarchyException(e);
        } catch (AmbiguousViewMatcherException e) {
            throw new NoHierarchyException(e);
        }
    }
}
