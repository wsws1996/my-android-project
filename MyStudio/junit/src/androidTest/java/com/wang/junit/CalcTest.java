package com.wang.junit;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

/**
 * Created by wang on 17-2-8.
 */

@RunWith(AndroidJUnit4.class)
public class CalcTest {
    @Test
    public void addTest() {
        Calc calc = new Calc();
        int result = calc.add(8, 2);
        assertEquals(10, result);
    }
}
