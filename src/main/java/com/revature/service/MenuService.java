package com.revature.service;

/**
 * MenuService should provide messages to the user to prompt them
 * to perform certain actions based on the selection of the option
 */
public class MenuService {
    public void mainMenuPrompt(){
        System.out.println("Welcome to RevEstate!");
        mainMenuOptions();
    }

    public void mainMenuOptions(){
        System.out.println("1) Create an Account");
        System.out.println("2) Login to your Account");
        System.out.println("0) Exit the Application");
    }

    public void logInMenuPrompt(){
        System.out.println("\n--- Log In Menu ---");
        logInMenuOptions();
    }

    public void logInMenuOptions(){
        System.out.println("1) Check Balances");
        System.out.println("2) Withdraw Money");
        System.out.println("3) Deposit Money");
        System.out.println("4) Transfer Money");
        System.out.println("0) Sign Out");
    }

    public void checkBalanceMenuPrompt(){
        System.out.println("\n--- Check Balance ---");
        checkBalanceMenuOptions();
    }

    public void checkBalanceMenuOptions(){
        System.out.println("1) All Balances");
        System.out.println("2) Checking Balance");
        System.out.println("3) Savings Balance");
        System.out.println("0) Back");
    }

    public void withdrawMenuPrompt(){
        System.out.println("\n--- Withdraw Balance ---");
        withdrawMenuOptions();
    }

    public void withdrawMenuOptions(){
        System.out.println("Withdraw From:");
        System.out.println("    1) Checking");
        System.out.println("    2) Savings");
        System.out.println("    0) Back");
    }

    public void depositMenuPrompt(){
        System.out.println("\n--- Deposit Balance ---");
        withdrawMenuOptions();
    }

    public void depositMenuOptions(){
        System.out.println("Deposit To:");
        System.out.println("    1) Checking");
        System.out.println("    2) Savings");
        System.out.println("    0) Back");
    }

    public void transferFromMenuPrompt(){
        System.out.println("\n--- Transfer Balance ---");
        withdrawMenuOptions();
    }

    public void transferFromMenuOptions(){
        System.out.println("Transfer:");
        System.out.println("    1) From Checking to Savings");
        System.out.println("    2) From Savings to Checking");
        System.out.println("    0) Back");
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

    /**
     *      One Line Prompts
     */
    // Prompts the user for their Username
    public void enterUsernamePrompt(){
        System.out.println("Please enter your Username: ");
    }

    // Prompts the user for their Pin
    public void enterPinPrompt(){
        System.out.println("Please enter your Pin: ");
    }

    // Prompts user for their First Name
    public void enterFirstNamePrompt(){
        System.out.println("Enter First Name: ");
    }

    // Prompts user for their Last Name
    public void enterLastNamePrompt(){
        System.out.println("Enter Last Name: ");
    }

    public void enterAmountWithdrawPrompt(){
        System.out.println("To Exit back to Log In menu type in any letter");
        System.out.println("Amount must me in increments of $20");
        System.out.println("Withdraw Amount: ");
    }

    public void enterAmountDepositPrompt(){
        System.out.println("To Exit back to Log In menu type in any letter");
        System.out.println("Amount must me in increments of $20");
        System.out.println("Deposit Amount: ");
    }

    public void enterAmountTransferFromPrompt(){
        System.out.println("To Exit back to Log In menu type in any letter");
        System.out.println("Amount must me in increments of $20");
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
    public void notValidUsernamePin(){
        System.out.println("\033[1mInvalid Username or Pin\033[0m");
    }

    // Lets the user know their account was created
    public void accountCreatedPrint(){
        System.out.println("\nYou have created an account.\n");
    }

    // Lets the user know the length restrictions of the Username
    public void usernameLengthPrint(){
        System.out.println("\nYour Username must be at least 5 characters and no more than 10.");
    }

    // Lets the user know that the pin must be 4 DIGITS long
    public void pinLengthPrint(){
        System.out.println("\nYour Pin must be 4 digits.");
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

    // Text letting you know that you are logged in.
    public void loginText(){
        System.out.println("You are now Logged In.");
    }

    // Text letting you know that the program is closing.
    public void textClosing(){
        System.out.println("Closing Program");
    }
}
