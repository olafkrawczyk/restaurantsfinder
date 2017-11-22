package com.okrawczy.restaurantsfinder.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Olaf on 2017-11-22.
 */


public class RestaurantTableServiceUnitTests {

    private static DateFormat format;

    @Before
    public void setFormat() {
        format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    }

    @Test
    public void testDateBetweenInRange(){
        Date start = null;
        Date end = null;
        Date target = null;
        try {
            start = format.parse("2017-10-19 07:00");
            end = format.parse("2017-10-19 20:00");
            target = format.parse("2017-10-19 10:00");
        } catch (ParseException e) {
            Assert.fail("Parse error");
            e.printStackTrace();
        }
        Assert.assertTrue(RestaurantTableService.isBetweenInclusive(start, end, target));
    }

    @Test
    public void testDateBetweenAtRangeStart(){
        Date start = null;
        Date end = null;
        Date target = null;
        try {
            start = format.parse("2017-10-19 07:00");
            end = format.parse("2017-10-19 20:00");
            target = format.parse("2017-10-19 07:00");
        } catch (ParseException e) {
            Assert.fail("Parse error");
            e.printStackTrace();
        }
        Assert.assertTrue(RestaurantTableService.isBetweenInclusive(start, end, target));
    }

    @Test
    public void testDateBetweenAtRangeEnd(){
        Date start = null;
        Date end = null;
        Date target = null;
        try {
            start = format.parse("2017-10-19 07:00");
            end = format.parse("2017-10-19 20:00");
            target = format.parse("2017-10-19 20:00");
        } catch (ParseException e) {
            Assert.fail("Parse error");
            e.printStackTrace();
        }
        Assert.assertTrue(RestaurantTableService.isBetweenInclusive(start, end, target));
    }

    @Test
    public void testDateBetweenOutOfRangeLower(){
        Date start = null;
        Date end = null;
        Date target = null;
        try {
            start = format.parse("2017-10-19 07:00");
            end = format.parse("2017-10-19 20:00");
            target = format.parse("2017-10-19 06:00");
        } catch (ParseException e) {
            Assert.fail("Parse error");
            e.printStackTrace();
        }
        Assert.assertFalse(RestaurantTableService.isBetweenInclusive(start, end, target));
    }

    @Test
    public void testDateBetweenOutOfRangeHigher(){
        Date start = null;
        Date end = null;
        Date target = null;
        try {
            start = format.parse("2017-10-19 07:00");
            end = format.parse("2017-10-19 20:00");
            target = format.parse("2017-10-19 21:00");
        } catch (ParseException e) {
            Assert.fail("Parse error");
            e.printStackTrace();
        }
        Assert.assertFalse(RestaurantTableService.isBetweenInclusive(start, end, target));
    }

}
