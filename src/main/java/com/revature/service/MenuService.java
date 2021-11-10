package com.revature.service;

/**
 * MenuService should provide messages to the user to prompt them to perform certain actions based on the selection
 * of the option.
 */
public class MenuService {
    /**
     * Provides the user with the Welcome Screen, which includes the Main Menu Options.
     */
    public void mainMenuPrompt(){
        System.out.println("Welcome to RevEstate!");
        mainMenuOptions();
    }

    /**
     * Provides Main Menu Options to the user to prompt them to choose if the user wants to Create an Account, Log In,
     * or Exit the application.
     */
    public void mainMenuOptions(){
        System.out.println("1) Create an Account");
        System.out.println("2) Log In to your Account");
        System.out.println("0) Exit the Application");
    }

    /**
     * Provides the user with the Log In Menu Screen, which includes th Log In Menu Options.
     */
    public void logInMenuPrompt(){
        System.out.println("\n--- Log In Menu ---");
        logInMenuOptions();
    }

    /**
     * Provides Log In Menu Options to the user to prompt them to choose if the user wants to Check Balances, Withdraw
     * Money, Deposit Money, Transfer Money, or Sign Out of their account.
     */
    public void logInMenuOptions(){
        System.out.println("1) Check Balances");
        System.out.println("2) Withdraw Money");
        System.out.println("3) Deposit Money");
        System.out.println("4) Transfer Money");
        System.out.println("5) Account Services");
        System.out.println("0) Sign Out");
    }

    /**
     * Provides the user with the Check Balance Menu Screen, which includes the Check Balance Menu Options.
     */
    public void checkBalanceMenuPrompt(){
        System.out.println("\n--- Check Balance ---");
        checkBalanceMenuOptions();
    }

    /**
     * Provides Check Balance Menu Options to the user to prompt them to choose if the user wants to Check the Balances
     * of All their accounts or just that of their Checking or Savings Account. The User can also choose to go back to
     * the Log In Menu.
     */
    public void checkBalanceMenuOptions(){
        System.out.println("1) All Balances");
        System.out.println("2) Checking Balance");
        System.out.println("3) Savings Balance");
        System.out.println("0) Back");
    }

    /**
     * Provides the user with the Withdraw Menu Screen, which includes the Withdraw Menu Options.
     */
    public void withdrawMenuPrompt(){
        System.out.println("\n--- Withdraw Balance ---");
        withdrawMenuOptions();
    }

    /**
     * Provides Withdraw Menu Options to the user to prompt them to choose if the user wants to Withdraw Money from
     * their Checking or Savings Account. The user can also choose to go back to the Log In Menu.
     */
    public void withdrawMenuOptions(){
        System.out.println("Withdraw From:");
        System.out.println("    1) Checking");
        System.out.println("    2) Savings");
        System.out.println("    0) Back");
    }

    /**
     * Provides the user with the Deposit Menu Screen, which includes the Deposit Menu Options.
     */
    public void depositMenuPrompt(){
        System.out.println("\n--- Deposit Balance ---");
        depositMenuOptions();
    }

    /**
     * Provides Deposit Menu Options to the user to prompt them to choose if the user wants to Deposit Money from
     * their Checking or Savings Account. The user can also choose to go back to the Log In Menu.
     */
    public void depositMenuOptions(){
        System.out.println("Deposit To:");
        System.out.println("    1) Checking");
        System.out.println("    2) Savings");
        System.out.println("    0) Back");
    }

    /**nf
     * Provides the user with the Transfer Menu Screen, which includes the Transfer Menu Options.
     */
    public void transferFromMenuPrompt(){
        System.out.println("\n--- Transfer Balance ---");
        transferFromMenuOptions();
    }

    /**
     * Provides Transfer Menu Options to the user to prompt them to choose if the user wants to Transfer Money from
     * their Checking or Savings Account to their other account. The user can also choose to go back to the Log In Menu.
     */
    public void transferFromMenuOptions(){
        System.out.println("Transfer:");
        System.out.println("    1) From Checking to Savings");
        System.out.println("    2) From Savings to Checking");
        System.out.println("    0) Back");
    }

    public void  accountServicesMenuPrompt(){
        System.out.println("\n--- Account Services ---");
        accountServicesMenuOptions();
    }

    public void accountServicesMenuOptions(){
        System.out.println("1) Change Username");
        System.out.println("2) Change Password");
        System.out.println("3) Add Person to Account");
        System.out.println("0) Back");
    }

    /**
     * Print Statements with inputs
     */
    // Prints out the Account Number with input of the account number
    public void printAccountNumber( int account_no ){
        System.out.println("Account Number: " + account_no + "\n");
    }

    // Prints out the balances for both Checking and Savings accounts
    public void printBalances( double checking, double savings ){
        System.out.println("Checking Balance: $" + String.format("%.2f", checking));
        System.out.println("Savings Balance:  $" + String.format("%.2f", savings));
    }

    // Prints out the balance for Checking account
    public void printCheckingBalance( double checking ){
        System.out.println("Checking Balance: $" + String.format("%.2f", checking));
    }

    // Prints out the balance for Savings account
    public void printSavingsBalance( double savings ){
        System.out.println("Savings Balance:  $" + String.format("%.2f", savings));

    }

    // Prints out the account in which you are Depositing to
    public void printDepositAcc( String account ){
        System.out.println("Depositing funds to " + account );
    }

    // Prints out the account in which you are Withdrawing from
    public void printWithdrawAcc( String account ){
        System.out.println("Withdrawing funds from " + account );
    }

    // Prints out the account in which you are Transferring from
    public void printTransferFromAcc( String account ){
        System.out.println("Transferring funds from " + account );
    }

    // Prints out the account in which you are Transferring to
    public void printTransferToAcc( String account ){
        System.out.println("Transferring funds to " + account );
    }

    // Text letting the user know that they are logged in.
    public void loginText( String username ){
        System.out.println("Welecome " + username + "! You are now Logged In.");
    }

    // Text letting the user know that they are logged out.
    public void logoutText( String username ){
        System.out.println("Logging " + username + " out.");
    }

    // Text letting the user know that they successfully changed their username.
    public void successfulUsernameChange(String username){
        System.out.println("Username change to " + username);
    }

    // Text letting the user know that they successfully added a person to their account
    public void successfulPersonAdded(String fname, String lname){
        System.out.println(fname + " " + lname + " added to account.");
    }

    /**
     *      One Line Prompts
     */
    // Prompts the user for their Username
    public void enterUsernamePrompt(){
        System.out.println("Please enter your Username: ");
    }

    // Prompts the user for their Password
    public void enterPasswordPrompt(){
        System.out.println("Please enter your Password: ");
    }

    // Prompts user for their First Name
    public void enterFirstNamePrompt(){
        System.out.println("Enter First Name: ");
    }

    // Prompts user for their Last Name
    public void enterLastNamePrompt(){
        System.out.println("Enter Last Name: ");
    }

    // Prompts user to enter the amount they want to Withdraw. Lets the user know that they can exit back to
    // Withdraw Menu.
    public void enterAmountWithdrawPrompt(){
        System.out.println("To Exit back to Withdraw Menu type 0");
        System.out.println("Withdraw Amount: ");
    }

    // Prompts the user to enter the amount they want to Deposit. Lets the user know how to exit back to the
    // Deposit Menu.
    public void enterAmountDepositPrompt(){
        System.out.println("To Exit back to Deposit menu type in 0");
        System.out.println("Deposit Amount: ");
    }

    // Prompts the user to enter the amount they want to Transfer From. Lets the user know how to exit back to the
    // Transfer Menu.
    public void enterAmountTransferFromPrompt(){
        System.out.println("To Exit back to Transfer menu type in 0");
        System.out.println("Transfer Amount: ");
    }

    /**
     *      One Line Printed Statements
     */
    // Lets the user know that their menu option does not exist
    public void incorrectMenuSelection(){
        System.out.println("Please enter a valid option.");
    }

    // Tells not valid username or password in bold
    public void notValidUsernamePassword(){
        System.out.println("\033[1mInvalid Username or Password\033[0m");
    }

    // Lets the user know their account was created
    public void accountCreatedPrint(){
        System.out.println("\nYou have created an account.\n");
    }

    // Lets the user know the length restrictions of the Username
    public void usernameLengthPrint(){
        System.out.println("\nYour Username must be at least 5 characters.");
    }

    // Lets the user know the length restrictions of the Username. Also let the user know they have to type exit to
    // go back to the Login menu.
    public void updateUsernameLengthPrint(){
        System.out.println("\nYour Username must be at least 5 characters.");
        System.out.println("Type exit to go back to Log In Menu.");
    }

    // Lets the user know that the password must be longer than 8 characters
    public void passwordLengthPrint(){
        System.out.println("\nYour Password must be longer than 8 characters.");
    }

    // Lets the user know that the password must be longer than 8 characters
    public void updatePasswordLengthPrint(){
        System.out.println("\nYour Password must be longer than 8 characters.");
        System.out.println("Type exit to go back to Log In Menu.");
    }

    // Lets the user know the Account prompt they chose doesn't exist
    public void invalidAccountPrint(){
        System.out.println("\033[1mACCOUNT DOES NOT EXIST\033[0m");
    }

    // Lets the user know that they don't have enough money to withdraw from the account they chose
    public void invalidFunds(){
        System.out.println("          \033[1mINSUFFICIENT FUNDS\033[0m");
    }

    // Lets the user know that the Username they chose is already in user
    public void invalidUsername(){
        System.out.println("\033[1mUsername in use. Please try again.\033[0m");
    }

    // Lets the user know that they did not enter the requested 4 Digits. And to try again
    public void invalidType(){
        System.out.println("You did not enter a number. Please try again.");
    }

    // Text letting the user know that the program is closing.
    public void textClosing(){
        System.out.println("Closing Program");
    }

    // Text asking the user to enter the name of the new user.
    public void newPersonText(){
        System.out.println("Please enter the name of the new user on your account.");
    }

    // Text letting the user know that they successfully changed their username.
    public void successfulPasswordChange(){
        System.out.println("Password updated.");
    }
}
