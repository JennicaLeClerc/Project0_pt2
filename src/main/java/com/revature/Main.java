package com.revature;

import com.revature.model.User;
import com.revature.persistence.UserDao;
import com.revature.service.MenuService;
import com.revature.service.UserService;

//import java.util.LinkedList;
import java.util.Scanner;


public class Main {

    User currentUser;
    UserService userService;
    MenuService menuService;
    Scanner scanner;

    /**
     * Is a driver that allows all that call this method to use these.
     */
    public Main(){
        this.menuService = new MenuService();
        this.scanner = new Scanner(System.in);
        this.userService = new UserService(this.scanner, this.menuService);
    }

    /**
     * The main function. Creates a driver from Main(). Calls the mainMenu method.
     */
    public static void main(String[] args) {

        /*Project0Service service = new Project0Service();
        List<BankingTypes> bankingtypeslist = service.getAllBankingTypes();

        System.out.println(bankingtypeslist);*/

        Main driver = new Main();
        driver.mainMenu();
    }

    /**
     * The Main Menu. Gives the user the ability to Create an Account, Log In, or Exit the program.
     *
     * Create a new user:   Has the user enter all that is necessary to create an account and then logs them in.
     * Log In:              Has the User enter their username and Password. Sends that user to the Log In Menu
     *                      If the user is back to this method after logging in then they have asked to Log Out,
     *                      so the user tempuser is set to null so that while loop runs again.
     * Exit:                Lets the User know they are closing the program, then exits the program
     */
    public void mainMenu(){
        Main driver = new Main();
        menuService.mainMenuPrompt();

        while (true) {
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    User newuser = userService.createUser();
                    driver.LogInMenu(newuser);
                    break;
                case "2": {
                    User tempUser = userService.Login();
                    if (tempUser != null) {
                        currentUser = tempUser;
                        driver.LogInMenu(tempUser);
                        tempUser = null;
                    }
                    break;
                }
                case "0":
                    menuService.textClosing();
                    System.exit(0);
                    break;
                default:
                    menuService.incorrectMenuSelection();
                    menuService.mainMenuOptions();
            }
        }
    }

    /**
     * The Log In Menu. Keeps the user information that is logged in. Lets that user go to the Check Balance,
     * Withdraw, Deposit, Transfer, and Log Out Menus.
     * Check Balance:       Allows the user to go to the Check Balances Menu where the user can check the balances
     *                      of all accounts.
     * Withdraw Balance:    Allows the user to go to the Withdraw Balances Menu where the user can withdraw money
     *                      in increments of $20 from their accounts
     * Deposit Balance:     Allows the user to go to the Deposit Balances Menu where the user can deposit money
     *                      from their accounts.
     * Transfer Balance:    Allows the user to go to the Transfer Balances Menu where the user can transfer money
     *                      between their accounts.
     * Sign Out:            Sends the user back to the Main Menu, then sets the tempuser to null escentially logging
     *                      out the user.
     */
    public void LogInMenu( User user ){
        Main driver = new Main();
        menuService.logInMenuPrompt();

        while (true){
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    driver.CheckBalanceMenu(user);
                    break;
                case "2":
                    driver.WithdrawMenu(user);
                    break;
                case "3":
                    driver.DepositMenu(user);
                    break;
                case "4":
                    driver.TransferMenu(user);
                    break;
                case "0":
                    menuService.logoutText();
                    return;
                default:
                    menuService.incorrectMenuSelection();
                    menuService.logInMenuOptions();
            }
        }
    }

    /**
     * The Check Balance Menu. Uses the user information to allow the logged in user to check their balances.
     * All Balances:        Prints out the current balances from all the user's accounts.
     * Checking Balances:   Prints out the current balance from the user's checking account.
     * Savings Balances:    Prints out the current balance from the user's savings account.
     * Back:                Takes the user back to the Log In Menu.
     */
    public void CheckBalanceMenu( User user ){
        menuService.checkBalanceMenuPrompt();
        while(true){
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    userService.ViewBalance(user);
                    break;
                case "2":
                    userService.ViewBalance(user, "Checking");
                    break;
                case "3":
                    userService.ViewBalance(user, "Savings");
                    break;
                case "0":
                    return;
                default:
                    menuService.incorrectMenuSelection();
                    menuService.checkBalanceMenuOptions();
            }
        }
    }

    /**
     * The Withdraw Menu. Uses the user information to allow the logged in user to withdraw money from the chosen
     * account.
     * Checking:    Lets the user withdraw money from their Checking Account.
     * Savings:     Lets the user withdraw money from their Savings Account.
     * Back:        Takes the user back to the Log In Menu.
     */
    public void WithdrawMenu( User user ){
        menuService.withdrawMenuPrompt();
        while(true){
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    userService.Withdraw(user, "Checking");
                    break;
                case "2":
                    userService.Withdraw(user, "Savings");
                    break;
                case "0":
                    return;
                default:
                    menuService.incorrectMenuSelection();
                    menuService.withdrawMenuOptions();
            }
        }
    }

    /**
     * The Deposit Menu. Uses the user information to allow the logged in user to deposit money from the chosen
     * account.
     * Checking:    Lets the user deposit money from their Checking Account.
     * Savings:     Lets the user deposit money from their Savings Account.
     * Back:        Takes the user back to the Log In Menu.
     */
    public void DepositMenu( User user ){
        menuService.depositMenuPrompt();
        while(true){
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    userService.Deposit(user, "Checking");
                    break;
                case "2":
                    userService.Deposit(user, "Savings");
                    break;
                case "0":
                    return;
                default:
                    menuService.incorrectMenuSelection();
                    menuService.depositMenuOptions();
            }
        }
    }

    /**
     * The Transfer Menu. Uses the user information to allow the logged in user to transfer money from the chosen
     * account to their other account
     * Checking:    Lets the user transfer money from their Checking Account to their Savings Account.
     * Savings:     Lets the user withdraw money from their Savings Account to their Checking Account.
     * Back:        Takes the user back to the Log In Menu.
     */
    public void TransferMenu( User user ){
        menuService.transferFromMenuPrompt();
        while(true){
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    userService.TransferFrom(user, "Checking");
                    break;
                case "2":
                    userService.TransferFrom(user, "Savings");
                    break;
                case "0":
                    return;
                default:
                    menuService.incorrectMenuSelection();
                    menuService.transferFromMenuOptions();
            }
        }
    }
}
