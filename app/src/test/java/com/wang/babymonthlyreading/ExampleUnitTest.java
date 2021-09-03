package com.wang.babymonthlyreading;

import org.junit.Test;

import static org.junit.Assert.*;

import android.icu.number.NumberFormatter;

import java.util.Locale;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        float a = 1.51993f;

        System.out.println(String.format(Locale.CHINA,"￥ %.2f", 120.315));
    }
}