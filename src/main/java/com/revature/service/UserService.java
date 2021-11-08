package com.revature.service;

import com.revature.model.User;
import java.util.*;

public class UserService {
    Scanner scanner;
    MenuService menuService;

    public UserService(Scanner scanner, MenuService menuService){
        this.scanner = scanner;
        this.menuService = menuService;
    }

    List<String> usernameList = new ArrayList<>();
    List<User> userpinList = new ArrayList<>();

    private static int current_account_num = 1110; // The number has to have these modifiers for the incrementation to work

    /**
     * Creates a user with First Name, Last Name, Username, Pincode, Account No., Checking Balance, Savings Balance.
     */
    public User createUser(){
        String[] names = Name();
        String[] credentials = UsernameAndPinCreater();
        int account_number = Account_Number();
        double[] balances = InitialBalances();

        userpinList.add( new User(credentials[0], credentials[1])) ;
        User newuser = new User(account_number, credentials[0], credentials[1], names[0], names[1], balances[0], balances[1]);
        menuService.accountCreatedPrint();
        return newuser;
    }

    /**
     * Log in user has you input your username and pin. For each user in the userlist, checks if the username
     * exists and if it does, checks to see if the pin is correct for that user. If the username and password is
     * correct it returns the user. If not it returns that it is invalid.
    */
     public User Login(){
        String[] credentials = UsernameAndPinInput();

        for(User user: userpinList){
            String tempUsername = user.getUsername();
            if(tempUsername.equals(credentials[0])){
                if(user.getPincode().equals(credentials[1])){
                    menuService.loginText();
                    return user;
                }
            }
        }
        menuService.notValidUsernamePin();
        return null;
    }

    /**
     * Asking for a non-empty First and Last Name from user then returns them.
     */
    public String[] Name(){
        String fname;
        String lname;
        Scanner scanner = new Scanner(System.in); // Creating Scanner Object
        do{
            menuService.enterFirstNamePrompt();
            fname = scanner.nextLine();
        }
        while( fname == null || fname.length() <= 1 );

        do{
            menuService.enterLastNamePrompt();
            lname = scanner.nextLine(); // nextLine is for Strings
        }
        while( lname == null || lname.length() <= 1 );

        return new String[]{fname, lname};
    }

    /**
     * Gives the new user the next Account Number then returns the Account Number.
     */
    public int Account_Number(){
        current_account_num++;
        menuService.printAccountNumber(current_account_num);
        return current_account_num;
    }

    /**
     * Creates a Username and Pincode string
     */
    public String[] UsernameAndPinCreater(){
        String username = UserName();
        String pincode = PinCode();
        scanner.nextLine();
        return new String[]{username, pincode};
    }

    /**
     * Asks for Username and Pincode for login purposes. Returns string of Username and Pincode
     */
    public String[] UsernameAndPinInput(){
        menuService.enterUsernamePrompt();
        String username = scanner.nextLine();

        menuService.enterPinPrompt();
        String pincode = String.valueOf(scanner.nextInt());
        return new String[]{username, pincode};
    }

    /**
     * Has the user create a Username where the length has to be between 5 and 10 characters. Returns the Username.
     */
    public String UserName(){
        boolean isnewuser;
        String username;
        menuService.usernameLengthPrint();
        do{
            do{
                menuService.enterUsernamePrompt();
                username = scanner.nextLine();
                isnewuser = GoodUserName( username );
            }
            while( username == null || username.length() > 10 || username.length() < 5);
        }
        while( !isnewuser );
        usernameList.add(username);
        return username;
    }

    /**
     * Checks if the chosen Username is already in use. Returns true if unique Username. Returns false if Username
     * is already in use.
     */
    public boolean GoodUserName( String username ){
        boolean isgood = true;
        for( String i : usernameList){
            if( i.equals( username ) ){
                menuService.invalidUsername();
                isgood = false;
            }
        }
        return isgood;
    }

    /**
     * Has the user create a 4 Digit Pin. Catches if they do not enter 4 Digits. Returns a String of the Pin.
     */
    public String PinCode(){
        int pin;
        int numberOfDigits;
        menuService.pinLengthPrint();
        do{
            menuService.enterPinPrompt();
            try{
                pin = scanner.nextInt();
            }
            catch ( InputMismatchException e ){
                menuService.invalidType();
                pin = 0;
                scanner.nextLine();
            }
            numberOfDigits = String.valueOf(pin).length();
        }
        while( numberOfDigits != 4 );
        return String.valueOf(pin);
    }

    /**
     * Makes sure that the initial balance for Checking and Savings is 0. Prints out Balances. Returns the balances.
     */
    public double[] InitialBalances(){
        double checking = 0;
        double savings = 0;
        menuService.printBalances( checking, savings );
        return new double[]{checking, savings};
    }

    /**
     * Checks if you have enough money in the Account Type (Checking or Savings) you selected for the Amount you want.
     * If you don't have enough funds in that account, you are shown your balances. If you do have enough fund,
     * that Amount is Withdrawn from the selected Account Type.
     */
    public void Withdraw( User user, String account, double amount ) {
        //menuService.printWithdrawAcc(account); // I think only Transfer From method is using this one anymore
        switch (account) {
            case "Checking":
                if (user.getChecking_balance() < amount) {
                    menuService.invalidFunds();
                    ViewBalance(user);
                    break;
                } else {
                    user.setChecking_balance(user.getChecking_balance() - amount);
                    ViewBalance(user, account);
                }
                break;
            case "Savings":
                if (user.getSavings_balance() < amount) {
                    menuService.invalidFunds();
                    ViewBalance(user);
                    break;
                } else {
                    user.setSavings_balance(user.getSavings_balance() - amount);
                    ViewBalance(user, account);
                }
                break;
            default:
                menuService.invalidAccountPrint();
                ViewBalance(user);
                break;
        }
    }

    /**
     * Overloading Withdraw Method. Does the same as above but specifically asks the User how much they want to
     * withdraw. If the amount was returned null they wanted exit withdraw screen, so exits to Withdraw Menu Page.
     */
    public void Withdraw( User user, String account) {
        menuService.printWithdrawAcc(account);
        double amount = AmountWithdraw();
        if (amount == Double.parseDouble(null)){
            return;
        }

        switch (account) {
            case "Checking":
                if (user.getChecking_balance() < amount) {
                    menuService.invalidFunds();
                    ViewBalance(user);
                    break;
                } else {
                    user.setChecking_balance(user.getChecking_balance() - amount);
                    ViewBalance(user, account);
                }
                break;
            case "Savings":
                if (user.getSavings_balance() < amount) {
                    menuService.invalidFunds();
                    ViewBalance(user);
                    break;
                } else {
                    user.setSavings_balance(user.getSavings_balance() - amount);
                    ViewBalance(user, account);
                }
                break;
            default:
                menuService.invalidAccountPrint();
                ViewBalance(user);
                break;
        }
    }

    /**
     * Asks the User how much they would like to withdraw. The Amount (int) must be a positive value and  a multiple
     * of 20. Returns a double Amount. Returns the Amount as null if Amount is not an integer and Exits to
     * Withdraw Menu Page.
     */
    public double AmountWithdraw(){
        int amount;
        do{
            menuService.enterAmountWithdrawPrompt();
            try{
                amount = scanner.nextInt();
            }
            catch ( InputMismatchException e ){
                menuService.invalidType();
                scanner.nextLine();
                return Double.parseDouble(null);
            }
        }
        while( amount > 0 & amount%20 != 0 );
        double Amount = amount;
        return Amount;
    }

    /**
     * Adds the amount the user wants to Deposit to their desired Account Type (Checking or Savings). Then prints out
     * the users current balance.
     */
    public void Deposit( User user, String account, double amount){
        // menuService.printDepositAcc( account ); // I think the only method using this method anymore is Transfer From
        switch ( account ){
            case "Checking":
                user.setChecking_balance(user.getChecking_balance() + amount);
                break;
            case "Savings":
                user.setSavings_balance(user.getSavings_balance() + amount);
                break;
            default:
                menuService.invalidAccountPrint();
                break;
        }
        ViewBalance( user,account );
    }

    /**
     * Overloading Deposit Method. Asks the User how much they would like to Deposit. Adds the amount you want to
     * Deposit to your desired Account Type (Checking or Savings). Then prints out the users current balance. If the
     * Amount was returned null the user wanted exit deposit screen, so exits to Deposit Menu Page.
     */
    public void Deposit( User user, String account){
        menuService.printDepositAcc( account );
        double amount = AmountDeposit();
        if (amount == Double.parseDouble(null)){
            return;
        }

        switch ( account ){
            case "Checking":
                user.setChecking_balance(user.getChecking_balance() + amount);
                break;
            case "Savings":
                user.setSavings_balance(user.getSavings_balance() + amount);
                break;
            default:
                menuService.invalidAccountPrint();
                break;
        }
        ViewBalance( user,account );
    }

    /**
     * Asks the User how much they would like to Deposit. The Amount (int) must be non-zero and  a dollar amount so
     * remainder must be zero if divided by .01. Returns a double Amount. Returns the Amount as null if Amount is
     * not an integer and Exits to Deposit Menu Page.
     */
    public double AmountDeposit(){
        double amount;
        do{
            menuService.enterAmountDepositPrompt();
            try{
                amount = scanner.nextInt();
            }
            catch ( InputMismatchException e ){
                menuService.invalidType();
                scanner.nextLine();
                return Double.parseDouble(null);
            }
        }
        while( amount > 0 & amount%0.01 != 0 );
        return amount;
    }

    /**
     * Transferring Amount from chosen Account Type (Checking or Savings) to other account. Checks if the balance
     * during Withdraw changes. Since if it doesn't change then the Amount either was 0 or too much to Withdraw.
     * If it passes this test, then it Deposits those funds to the Chosen Account.
     */
    public void TransferFrom( User user, String account, double amount ){
        double tempamount = 0;
        switch ( account ){
            case "Checking":
                tempamount = user.getChecking_balance();
                menuService.printTransferFromAcc( "Checking" );
                Withdraw(user, "Checking", amount);
                if( tempamount != user.getChecking_balance() ){
                    menuService.printTransferToAcc( "Savings" );
                    Deposit(user, "Savings", amount);
                }
                break;
            case "Savings":
                tempamount = user.getSavings_balance();
                menuService.printTransferFromAcc( "Savings" );
                Withdraw(user, "Savings", amount);
                if( tempamount != user.getSavings_balance() ){
                    menuService.printTransferToAcc( "Checking" );
                    Deposit(user, "Checking", amount);
                }
                break;
            default:
                menuService.invalidAccountPrint();
                break;
        }
    }

    /**
     * Overloading TransferFrom Method. Does the same as above but specifically Asks the user how much they want to
     * Transfer using AmountTransferFrom Method. Transfer the Amount specified by the user from the Account they chose
     * to their other account. Then prints out the users current balances. If the Amount was returned null the user
     * wanted exit deposit screen, so exits to Transfer Menu Page.
     */
    public void TransferFrom( User user, String account){
        double amount = AmountTransferFrom();
        if (amount == Double.parseDouble(null)){
            return;
        }

        double tempamount = 0;
        switch ( account ){
            case "Checking":
                tempamount = user.getChecking_balance();
                menuService.printTransferFromAcc( "Checking" );
                Withdraw(user, "Checking", amount);
                if( tempamount != user.getChecking_balance() ){
                    menuService.printTransferToAcc( "Savings" );
                    Deposit(user, "Savings", amount);
                }
                break;
            case "Savings":
                tempamount = user.getSavings_balance();
                menuService.printTransferFromAcc( "Savings" );
                Withdraw(user, "Savings", amount);
                if( tempamount != user.getSavings_balance() ){
                    menuService.printTransferToAcc( "Checking" );
                    Deposit(user, "Checking", amount);
                }
                break;
            default:
                menuService.invalidAccountPrint();
                break;
        }
    }

    /**
     * Asks the User how much they would like to Transfer. The Amount (int) must be non-zero and  a dollar amount so
     * remainder must be zero if divided by .01. Returns a double Amount. Returns the Amount as null if Amount is
     * not an integer and Exits to Transfer Menu Page.
     */
    public double AmountTransferFrom(){
        double amount;
        do{
            menuService.enterAmountTransferFromPrompt();
            try{
                amount = scanner.nextInt();
            }
            catch ( InputMismatchException e ){
                menuService.invalidType();
                scanner.nextLine();
                return Double.parseDouble(null);
            }
        }
        while( amount > 0 & amount%0.01 != 0 );
        return amount;
    }

    /**
     * Takes in a User and an Account Type (Checking or Savings or All) and prints out the balance(s) for those chosen.
     * Tells you the Account Doesn't Exist if invalid account given.
     */
    public void ViewBalance( User user, String account ) {
        switch( account ) {
            case "Checking":
                menuService.printCheckingBalance(user.getChecking_balance());
                break;
            case "Savings":
                menuService.printSavingsBalance(user.getSavings_balance());
                break;
            case "All":
                ViewBalance( user );
                break;
            default:
                menuService.invalidAccountPrint();
                break;
        }
    }

    /**
     * Takes in a User and prints out the balances for both Checking and Savings
     */
    public void ViewBalance( User user ){
        menuService.printBalances( user.getChecking_balance(), user.getSavings_balance() );
    }
}
