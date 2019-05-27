package ru.android2019.cihometask.failureHandler;

import androidx.test.espresso.AmbiguousViewMatcherException;
import androidx.test.espresso.EspressoException;
import androidx.test.espresso.NoMatchingViewException;

/**
 * Created by asmolianin on 13.10.17.
 */

public class NoHierarchyException extends RuntimeException implements EspressoException {
    public NoHierarchyException(NoMatchingViewException e) {
        super(new NoMatchingViewException.Builder().from(e).includeViewHierarchy(false).build());
    }

    public NoHierarchyException(AmbiguousViewMatcherException e) {
        super(new AmbiguousViewMatcherException.Builder().from(e).includeViewHierarchy(false).build());
    }
}
