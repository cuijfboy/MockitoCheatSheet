package com.ilab.mockito.example;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by cuijfboy on 15/11/14.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ExampleTest.class,
        StaticExampleTest.class
})
public class TestSuite {
}
