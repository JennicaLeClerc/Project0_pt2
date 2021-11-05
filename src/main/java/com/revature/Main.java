package com.revature;

import com.revature.model.BankingTypes;
import com.revature.model.User;
import com.revature.service.MenuService;
import com.revature.service.UserService;

import com.revature.model.Users;
import com.revature.service.Project0Service;

//import java.util.LinkedList;
import java.util.Scanner;
import java.util.List;


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
     * The Main Menu. Gives the user the ability to create an account, log in, or exit the program.
     *
     * Create a new user:   Has the user enter all that is neccessary to create an account and then logs them in.
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
                    break;
                case "4":
                    break;
                case "0":
                    return;
                default:
                    menuService.incorrectMenuSelection();
                    menuService.logInMenuOptions();
            }
        }
    }

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

}
