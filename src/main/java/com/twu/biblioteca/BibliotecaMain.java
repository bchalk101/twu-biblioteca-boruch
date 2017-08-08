package com.twu.biblioteca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by boruch on 20/7/17.
 */
public class BibliotecaMain {

    public String[] args;

    private List<Book> bookList = new ArrayList<Book>();
    private List<Movie> movieList = new ArrayList<Movie>();
    private List<UserAccount> allUsers = new ArrayList<UserAccount>();

    private static final int LIST_BOOKS = 1;
    private static final int LIST_MOVIES = 2;
    private static final int BORROW_ITEM = 3;
    private static final int RETURN_BOOK = 4;
    private static final int LOGIN_USER = 5;
    private static final int VIEW_USER_INFO = 6;
    private static final int QUIT = 7;

    public BibliotecaMain(String []args) {
        this.args = args;
    }

    public void run(){
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        LibrarySystem lbSystem = new LibrarySystem(this.bookList, this.movieList, this.allUsers);
        lbSystem.init();

        printMenu();

        boolean run = true;

        while(run)
        {
            int menuSelection = 0;
            try {
                menuSelection = Integer.valueOf(br.readLine().toString());
            } catch (IOException e) {
                System.out.println("Error reading menu option");
            }
            switch (menuSelection){
                case(LIST_BOOKS):
                    lbSystem.printListOfBooks();
                    break;
                case(LIST_MOVIES):
                    lbSystem.printAllMovies();
                    break;
                case (BORROW_ITEM):
                    borrowItem(br, lbSystem);
                    break;
                case(RETURN_BOOK):
                    returnBook(br, lbSystem);
                    break;
                case(LOGIN_USER):
                    userLogin(br, lbSystem);
                    break;
                case(VIEW_USER_INFO):
                    if (lbSystem.getLoggedInUser() != null)
                    {
                        lbSystem.getLoggedInUser().displayUserInfo();;
                    } else {
                        System.out.println("Please log in to see user info");
                    }
                    break;
                case(QUIT):
                    quitSelected();
                    return;
                default:
                    incorrentSelectionMessage();

            }

        }
    }

    private void returnBook(BufferedReader br, LibrarySystem lbSystem) {
        System.out.println("Enter name of book to return:");
        String bkName = null;
        try {
            bkName = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        lbSystem.returnBook(bkName);
    }

    private void borrowItem(BufferedReader br, LibrarySystem lbSystem) {
        System.out.println("Enter name of item to checkout:");
        String itemName = null;
        try {
            itemName = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        lbSystem.checkOutItem(itemName);

    }

    private void userLogin(BufferedReader br, LibrarySystem lbSystem) {
        if (lbSystem.getLoggedInUser() == null) {
            String libraryNumber = null;
            System.out.println("Enter username: ");
            try {
                libraryNumber = br.readLine().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Enter password");
            String password = null;
            try {
                password = br.readLine().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (lbSystem.loginUser(libraryNumber, password)) {
                printUserMenu();
            } else {
                System.out.println("Login Failed");
            }
        } else {
            lbSystem.logoutUser();
        }
    }


    public static boolean quitSelected()
    {
        System.out.println("Closing program");
        return true;
    }

    public static String printWelcome() {
        String message = "Welcome";
        System.out.println(message);
        return message;
    }

    public static String printMenu() {
        String menu = "1.List Books 2.List Movies 3.Checkout Item 4.Return Book 5.Login 6.Display User Info 7.Quit";
        System.out.println(menu);
        System.out.println("Enter number to select menu option");
        return menu;
    }
    public static String printUserMenu() {
        String menu = "1.List Books 2.List Movies 3.Checkout Item 4.Return Book 5.Logout 6.Display User Info 7.Quit";
        System.out.println(menu);
        System.out.println("Enter number to select menu option");
        return menu;
    }

    public static String incorrentSelectionMessage() {
        String incorrectMessage = "Select a valid option!";
        System.out.println(incorrectMessage);
        return incorrectMessage;
    }
}
