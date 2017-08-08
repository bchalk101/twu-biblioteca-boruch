package com.twu.biblioteca;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        BibliotecaMainTest.class,
        LibrarySystemTest.class
})
public class AllTests {

}