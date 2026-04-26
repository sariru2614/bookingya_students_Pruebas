package com.project.bookingya.unit;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        ReservationServiceTest.class,
        ReservationServiceTest_Failed.class
})
public class SuiteReservationUnitTestCase {
}