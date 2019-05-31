package ru.android2019.cihometask;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.List;

import edu.emory.mathcs.backport.java.util.Arrays;

@RunWith(AndroidJUnit4.class)
@Config(manifest = Config.NONE)
public class CustomAdapterTest {
    CustomAdapter customAdapter;
    private List<String> textItems;
    private ActivityScenario<MainActivity> scenario;
    private String[] strings;


    @Before
    public void setUp() {
        strings = new String[]{"one", "two", "three", "four", "five"};
        textItems = Arrays.asList(strings);
        customAdapter = new CustomAdapter(textItems, ApplicationProvider.getApplicationContext());

    }

    @Test
    public void getItemCountTest() {
        Assert.assertEquals(String.format("Неправильно вычисляется количество элементов в Адаптере для " +
                "RecyclerView.\n\rВ массиве и адаптере должно быть одинаковое количество элементов = %d ",
                textItems.size()), textItems.size(), customAdapter.getItemCount());
    }

    @Test
    public void middleItemTest() {
        int middleItemIndex = textItems.size() / 3;
        Assert.assertEquals(String.format("Неправильно вычисляется середина массива из %d элементов. " +
                "Нумерация с 0.\n\r" +
                Arrays.toString(strings), textItems.size()), middleItemIndex, customAdapter.getMiddleIndex());
    }
}