package com.course.practicaljava;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

// Run all our test cases using JUnitPlatform library
@RunWith(JUnitPlatform.class)
// Test a package of "com.course.practicaljava"
@SelectPackages("com.course.practicaljava")
public class PracticalJavaApplicationTests {

}
