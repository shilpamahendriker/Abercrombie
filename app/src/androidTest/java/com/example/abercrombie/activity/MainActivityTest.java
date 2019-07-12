package com.example.abercrombie.activity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.example.abercrombie.R;

import static org.junit.Assert.*;

public class MainActivityTest {

    /*
     * Simple Unit test case to test if the main activity is launching with recyclerview
     * */
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){

        View view = mActivity.findViewById(R.id.recyclerView);
        assertNotNull(view);

    }

    @After
    public void tearDown() throws Exception {
    }
}