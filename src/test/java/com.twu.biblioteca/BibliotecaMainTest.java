package com.twu.biblioteca;

import com.twu.biblioteca.BibliotecaMain;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by boruch on 20/7/17.
 */
public class BibliotecaMainTest {

    private String[] args = {""};
    private BibliotecaMain app = new BibliotecaMain(args);

    @Test
    public void welcomeMessageDispalyed(){

        assertEquals(app.printWelcome(), "Welcome");
    }

    @Test
    public void displayMenu()
    {
        assertEquals(app.printMenu(), "1.List Books 2.List Movies 3.Checkout Item 4.Return Book 5.Login 6.Display User Info 7.Quit");
    }

    @Test
    public void messageForIncorrectMenuSelection()
    {
        assertEquals(app.incorrentSelectionMessage(), "Select a valid option!");
    }

    @Test
    public void selectingQuit()
    {
        assertEquals(app.quitSelected(), true);
    }

}
