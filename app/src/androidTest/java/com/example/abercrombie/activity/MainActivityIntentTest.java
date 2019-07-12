package com.example.abercrombie.activity;

import android.support.test.rule.ActivityTestRule;

import com.example.abercrombie.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class MainActivityIntentTest {

    /*
     * Simple Unit test case to test if button on main activity opens the webview activity with the intent and passes the data
     * */
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mActivity = null;

    @Before
    public void setUp() throws Exception {
        mActivity = mainActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchOfWebviewActivityOnButtonClick(){
        //assertNotNull(mActivity.findViewById(R.id.);

    }

    @After
    public void tearDown() throws Exception {
    }
}