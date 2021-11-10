package com.revature;

import com.revature.service.MenuService;
import com.revature.service.UserService;

import java.util.Scanner;

public class Main {
    UserService userService;
    MenuService menuService;
    Scanner scanner;
    private static Main driver = new Main();

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
        driver.mainMenu();
    }

    /**
     * The Main Menu. Gives the user the ability to Create an Account, Log In, or Exit the program.
     *
     * Create a new user:   Has the user enter all that is necessary to create an account and then logs them in.
     * Log In:              Has the User enter their username and Password. Sends that user to the Log In Menu
     *                      If the user is back to this method after logging in then they have asked to Log Out,
     *                      so the user is set to null so that while loop runs again.
     * Exit:                Lets the User know they are closing the program, then exits the program
     */
    public void mainMenu(){
        menuService.mainMenuPrompt();
        while (true) {
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    userService.createUser();
                    driver.LogInMenu();
                    break;
                case "2": {
                    userService.Login();
                    if (userService.getCurrentUser() != null) {
                        driver.LogInMenu();
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
     *                      from their accounts.
     * Deposit Balance:     Allows the user to go to the Deposit Balances Menu where the user can deposit money
     *                      from their accounts.
     * Transfer Balance:    Allows the user to go to the Transfer Balances Menu where the user can transfer money
     *                      between their accounts.
     * Account Services:    Allows the user to go to the Account Services Menu where the user can changer their
     *                      username or password or add another person to their account.
     * Sign Out:            Sends the user back to the Main Menu, then sets the user to null essentially logging
     *                      out the user.
     */
    public void LogInMenu( ){
        menuService.logInMenuPrompt();
        while (true){
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    driver.CheckBalanceMenu();
                    break;
                case "2":
                    driver.WithdrawMenu();
                    break;
                case "3":
                    driver.DepositMenu();
                    break;
                case "4":
                    driver.TransferMenu();
                    break;
                case "5":
                    driver.AccountServicesMenu();
                    break;
                case "0":
                    userService.Logout();
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
    public void CheckBalanceMenu(){
        menuService.checkBalanceMenuPrompt();
        while(true){
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    userService.ViewBalance("All");
                    break;
                case "2":
                    userService.ViewBalance("Checking");
                    break;
                case "3":
                    userService.ViewBalance("Savings");
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
    public void WithdrawMenu(){
        menuService.withdrawMenuPrompt();
        while(true){
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    userService.Withdraw("Checking");
                    break;
                case "2":
                    userService.Withdraw("Savings");
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
    public void DepositMenu(){
        menuService.depositMenuPrompt();
        while(true){
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    userService.Deposit("Checking");
                    break;
                case "2":
                    userService.Deposit("Savings");
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
    public void TransferMenu(){
        menuService.transferFromMenuPrompt();
        while(true){
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    userService.TransferFrom("Checking");
                    break;
                case "2":
                    userService.TransferFrom("Savings");
                    break;
                case "0":
                    return;
                default:
                    menuService.incorrectMenuSelection();
                    menuService.transferFromMenuOptions();
            }
        }
    }

    /**
     * The Account Services Menu. Uses the user information to allow the logged in user to change their username or
     * password and add another person to their account.
     * Change Username:         Lets the user change their Username.
     * Change Password:         Lets the user change their Password.
     * Add Person to Account:   Lets the user add another person to their account.
     * Back:                    Takes the user back to the Log In Menu.
     */
    public void AccountServicesMenu(){
        menuService.accountServicesMenuPrompt();
        while(true){
            String choice = scanner.nextLine();
            switch (choice) {
                case "1":
                    userService.ChangeUsername();
                    break;
                case "2":
                    userService.ChangePassword();
                    break;
                case "3":
                    userService.newPerson();
                    break;
                case "0":
                    return;
                default:
                    menuService.incorrectMenuSelection();
                    menuService.accountServicesMenuOptions();
            }
        }
    }
}
