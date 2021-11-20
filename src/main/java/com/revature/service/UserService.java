package com.revature.service;

import com.revature.model.*;
import com.revature.persistence.*;

import java.util.*;

public class UserService {
    public User currentUser;
    private Person currentPerson;
    private final UserDao userDao;
    private final PersonDao personDao;
    private final BankingDao bankingDao;
    private final Scanner scanner;
    private final MenuService menuService;

    public UserService(Scanner scanner, MenuService menuService){
        this.scanner = scanner;
        this.menuService = menuService;
        this.userDao = new UserDao();
        this.personDao = new PersonDao();
        this.bankingDao = new BankingDao();
    }

    /**
     * Creates a user with First Name, Last Name, Username, Password, Account No., Checking Balance, Savings Balance.
     */
    public void createUser(){
        String[] names = Name();
        String[] credentials = UsernameAndPasswordCreater();
        //int account_number = Account_Number();
        double[] balances = InitialBalances();

        currentPerson = new Person(names[0], names[1]);
        currentUser = new User(credentials[0], credentials[1], balances[0], balances[1]);

        //currentPerson.setAccountNo(currentUser.getAccountNo());

        userDao.create(currentUser); // This has to be created first to be able to link the person table to it.

        Account_Number();
        currentPerson.setAccountNo(currentUser.getAccountNo());

        personDao.create(currentPerson);

        menuService.accountCreatedPrint();
        menuService.loginText(currentUser.getUsername());
    }

    /**
     * Log in the user by having them input their username and password. For each user in the database, checks if the
     * username exists and if it does, checks to see if the password is correct for that user. If the username and
     * password is correct it returns the user. If not it returns that it is invalid.
    */
    public void Login(){
        String[] credentials = UsernameAndPasswordInput();

        User user = userDao.getByUsername(credentials[0]);
        if(user != null){
            if(user.getPassword().equals(credentials[1])){
                menuService.loginText(user.getUsername());
                currentUser = user;
            }
            else{
                menuService.notValidUsernamePassword();
            }
        }
        else{
            menuService.notValidUsernamePassword();
        }
    }

    /**
     * If the user is logging out. The current user stored in runtime should be set to null so that the next user can be
     * logged in.
     */
    public void Logout(){
        menuService.logoutText(currentUser.getUsername());
        currentUser = null;
        currentPerson = null;
    }

    /**
     * Asking for a non-empty First and Last Name from user, then returns them.
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
     * Gives the new user the next Account Number, then returns the Account Number.
     */
    public void Account_Number(){
        currentUser.setAccountNo(userDao.getAccountNo(currentUser.getUsername()));
        menuService.printAccountNumber(currentUser.getAccountNo());
    }

    /**
     * Creates a Username and Password string
     */
    public String[] UsernameAndPasswordCreater(){
        String username = UserName();
        String password = Password();
        return new String[]{username, password};
    }

    /**
     * Asks for Username and Password for login purposes. Returns string of Username and Password.
     */
    public String[] UsernameAndPasswordInput(){
        menuService.enterUsernamePrompt();
        String username = scanner.nextLine();

        menuService.enterPasswordPrompt();
        String password = scanner.nextLine();
        return new String[]{username, password};
    }

    /**
     * Has the user create a unique Username where the length has to be over 5 characters long. Returns the Username.
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
                if(isnewuser){
                    menuService.invalidUsername();
                }
            }
            while( username == null || username.length() < 5);
        }
        while( !isnewuser );
        return username;
    }

    /**
     * Has the user create a unique Username where the length has to be over 5 characters long. If the user
     * types in exit then their username doesn't change then they go back to the Account Services Menu.
     * Returns the Username.
     */
    public String UpdateUserName(){
        boolean isnewuser;
        String username;
        menuService.updateUsernameLengthPrint();
        do{
            do{
                menuService.enterUsernamePrompt();
                username = scanner.nextLine();
                isnewuser = GoodUserName( username );
                if(username.equals("exit")){
                    return currentUser.getUsername();
                }
                if(!isnewuser){
                    menuService.invalidUsername();
                }
            }
            while( username == null || username.length() < 5);
        }
        while( !isnewuser );
        return username;
    }

    /**
     * Checks if the chosen Username is already in use. Returns true if unique Username. Returns false if Username
     * is already in use.
     */
    private boolean GoodUserName(String username){
        return userDao.getByUsername(username) == null;
    }

    /**
     * Has the user create a Password that is at least 8 characters long. Returns a String of the Password.
     */
    public String Password(){
        String password;
        int numberOfDigits;
        menuService.passwordLengthPrint();
        do{
            menuService.enterPasswordPrompt();
            password = scanner.nextLine();
            numberOfDigits = password.length();
        }
        while( numberOfDigits <= 8 );
        return password;
    }

    /**
     * Has the user create a Password that is at least 8 characters long. If the user types in exit then their
     * Password doesn't change then they go back to the Account Services Menu. Returns a String of the Password.
     */
    public String UpdatePassword(){
        String password;
        int numberOfDigits;
        menuService.updatePasswordLengthPrint();
        do{
            menuService.enterPasswordPrompt();
            password = scanner.nextLine();
            if(password.equals("exit")){
                return currentUser.getPassword();
            }
            numberOfDigits = password.length();
        }
        while( numberOfDigits <= 8 );
        return password;
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
     * Returns the User that is currently logged in.
     */
    public User getCurrentUser(){
        return currentUser;
    }

    /**
     * Checks if the user has enough money in the Account Type (Checking or Savings) selected for the Amount wanted.
     * If the user doesn't have enough funds in that account, the user is shown their balances. If there are enough
     * funds, that Amount is Withdrawn from the selected Account Type.
     */
    public void Withdraw(String accountType, double amount) {
        switch (accountType) {
            case "Checking":
                try {
                    if (currentUser.getCheckingBalance() < amount) {
                        menuService.invalidFunds();
                        ViewBalance();
                        break;
                    } else {
                        currentUser.setCheckingBalance(currentUser.getCheckingBalance() - amount);
                        LoadToDatabase(currentUser.getAccountNo(), amount,"Withdraw", accountType);
                        ViewBalance(accountType);
                    }
                } catch (NullPointerException e) {
                    menuService.invalidFunds();
                    ViewBalance();
                    break;
                }
                break;
            case "Savings":
                try{
                    if (currentUser.getSavingsBalance() < amount) {
                        menuService.invalidFunds();
                        ViewBalance();
                        break;
                    } else {
                        currentUser.setSavingsBalance(currentUser.getSavingsBalance() - amount);
                        LoadToDatabase(currentUser.getAccountNo(), amount,"Withdraw", accountType);
                        ViewBalance(accountType);
                    }
                }catch (NullPointerException e){
                    menuService.invalidFunds();
                    ViewBalance();
                    break;
                }
                break;
            default:
                menuService.invalidAccountPrint();
                break;
        }
    }

    /**
     * Overloading Withdraw Method. Does the same as above but specifically asks the User how much they want to
     * withdraw. If the amount was returned 0 they wanted exit withdraw screen, so exits to Withdraw Menu Page.
     */
    public void Withdraw(String accountType) {
        menuService.printWithdrawAcc(accountType);
        double amount = AmountWithdraw();

        if(amount == 0) {
            return;
        }
        switch (accountType) {
            case "Checking":
                try {
                    if (currentUser.getCheckingBalance() < amount) {
                        menuService.invalidFunds();
                        ViewBalance();
                        break;
                    } else {
                        currentUser.setCheckingBalance(currentUser.getCheckingBalance() - amount);
                        LoadToDatabase(currentUser.getAccountNo(), amount,"Withdraw", accountType);
                        ViewBalance(accountType);
                    }
                }catch (NullPointerException e){
                    menuService.invalidFunds();
                    ViewBalance();
                    break;
                }
                break;
            case "Savings":
                try {
                    if (currentUser.getSavingsBalance() < amount) {
                        menuService.invalidFunds();
                        ViewBalance();
                        break;
                    } else {
                        currentUser.setSavingsBalance(currentUser.getSavingsBalance() - amount);
                        LoadToDatabase(currentUser.getAccountNo(), amount,"Withdraw", accountType);
                        ViewBalance(accountType);
                    }
                }catch (NullPointerException e){
                    menuService.invalidFunds();
                    ViewBalance();
                    break;
                }
                break;
            default:
                menuService.invalidAccountPrint();
                break;
        }
    }

    /**
     * Asks the User how much they would like to withdraw. The Amount (int) must be a positive, whole value.
     * Returns a double Amount. Returns the Amount as 0 if Amount is not an integer and Exits to Withdraw Menu Page.
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
                return 0;
            }
        } while( amount <= 0 );
        return amount;
    }

    /**
     * Adds the amount the user wants to Deposit to their desired Account Type (Checking or Savings). Then prints out
     * the users current balance.
     */
    public void Deposit(String accountType, double amount){
        switch ( accountType ){
            case "Checking":
                try{
                    currentUser.setCheckingBalance(currentUser.getCheckingBalance() + amount);
                    LoadToDatabase(currentUser.getAccountNo(), amount,"Deposit",accountType);
                    ViewBalance(accountType);
                } catch (NullPointerException e){
                    currentUser.setCheckingBalance(amount);
                    LoadToDatabase(currentUser.getAccountNo(), amount, "Deposit",accountType);
                    ViewBalance(accountType);
                    break;
                }
                break;
            case "Savings":
                try{
                    currentUser.setSavingsBalance(currentUser.getSavingsBalance() + amount);
                    LoadToDatabase(currentUser.getAccountNo(), amount, "Deposit",accountType);
                    ViewBalance(accountType);
                } catch (NullPointerException e){
                    currentUser.setSavingsBalance(amount);
                    LoadToDatabase(currentUser.getAccountNo(), amount, "Deposit",accountType);
                    ViewBalance(accountType);
                    break;
                }
                break;
            default:
                menuService.invalidAccountPrint();
                break;
        }
    }

    /**
     * Overloading Deposit Method. Asks the User how much they would like to Deposit. Adds the amount the user wants to
     * Deposit to the users desired Account Type (Checking or Savings). Then prints out the users current balance.
     * If the Amount was returned 0 the user wanted exit deposit screen, so exits to Deposit Menu Page.
     */
    public void Deposit(String accountType){
        menuService.printDepositAcc( accountType );
        double amount = AmountDeposit();

        if(amount==0) {
            return;
        }

        switch ( accountType ){
            case "Checking":
                try{
                    currentUser.setCheckingBalance(currentUser.getCheckingBalance() + amount);
                    LoadToDatabase(currentUser.getAccountNo(), amount,"Deposit",accountType);
                    ViewBalance(accountType);
                } catch (NullPointerException e){
                    currentUser.setCheckingBalance(amount);
                    LoadToDatabase(currentUser.getAccountNo(), amount,"Deposit",accountType);
                    ViewBalance(accountType);
                    break;
                }
                break;
            case "Savings":
                try{
                    currentUser.setSavingsBalance(currentUser.getSavingsBalance() + amount);
                    LoadToDatabase(currentUser.getAccountNo(), amount,"Deposit",accountType);
                    ViewBalance(accountType);
                } catch (NullPointerException e){
                    currentUser.setSavingsBalance(amount);
                    LoadToDatabase(currentUser.getAccountNo(), amount, "Deposit",accountType);
                    ViewBalance(accountType);
                    break;
                }
                break;
            default:
                menuService.invalidAccountPrint();
                break;
        }
    }

    /**
     * Asks the User how much they would like to Deposit. The Amount (int) must be non-zero whole dollar amount.
     * Returns a double Amount. Returns the Amount as 0 if Amount is not an integer and Exits to Deposit Menu Page.
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
                return 0;
            }
        } while( amount <= 0 );
        return amount;
    }

    /**
     * Transferring Amount from chosen Account Type (Checking or Savings) to other account. Checks if the balance
     * during Withdraw changes. Since if it doesn't change then the Amount either was 0 or too much to Withdraw.
     * If it passes this test, then it Deposits those funds to the Chosen Account.
     */
    public void TransferFrom(String accountType, double amount){
        double tempamount;
        switch ( accountType ){
            case "Checking":
                try {
                    tempamount = currentUser.getCheckingBalance();
                    menuService.printTransferFromAcc("Checking");
                    Withdraw("Checking", amount);
                    if (tempamount != currentUser.getCheckingBalance()) {
                        menuService.printTransferToAcc("Savings");
                        Deposit("Savings", amount);
                    }
                    LoadToDatabase(currentUser.getAccountNo(), amount,"Transfer From", accountType);
                }catch (NullPointerException e){
                    menuService.invalidFunds();
                    ViewBalance();
                    break;
                }
                break;
            case "Savings":
                try {
                    tempamount = currentUser.getSavingsBalance();
                    menuService.printTransferFromAcc("Savings");
                    Withdraw("Savings", amount);
                    if (tempamount != currentUser.getSavingsBalance()) {
                        menuService.printTransferToAcc("Checking");
                        Deposit("Checking", amount);
                    }
                    LoadToDatabase(currentUser.getAccountNo(), amount, "Transfer From", accountType);
                }catch (NullPointerException e){
                    menuService.invalidFunds();
                    ViewBalance();
                    break;
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
     * to their other account. Then prints out the users current balances. If the Amount was returned 0 the user
     * wanted exit deposit screen, so exits to Transfer Menu Page.
     */
    public void TransferFrom(String accountType){
        double amount = AmountTransferFrom();

        if(amount == 0) {
            return;
        }

        double tempamount;
        switch ( accountType ){
            case "Checking":
                try {
                    tempamount = currentUser.getCheckingBalance();
                    menuService.printTransferFromAcc("Checking");
                    Withdraw("Checking", amount);
                    if (tempamount != currentUser.getCheckingBalance()) {
                        menuService.printTransferToAcc("Savings");
                        Deposit("Savings", amount);
                    }
                    LoadToDatabase(currentUser.getAccountNo(), amount, "Transfer From", accountType);
                }catch (NullPointerException e){
                    menuService.invalidFunds();
                    ViewBalance();
                    break;
                }
                break;
            case "Savings":
                try {
                    tempamount = currentUser.getSavingsBalance();
                    menuService.printTransferFromAcc("Savings");
                    Withdraw("Savings", amount);
                    if (tempamount != currentUser.getSavingsBalance()) {
                        menuService.printTransferToAcc("Checking");
                        Deposit("Checking", amount);
                    }
                    LoadToDatabase(currentUser.getAccountNo(), amount, "Transfer From", accountType);
                }catch (NullPointerException e){
                    menuService.invalidFunds();
                    ViewBalance();
                    break;
                }
                break;
            default:
                menuService.invalidAccountPrint();
                break;
        }
    }

    /**
     * Asks the User how much they would like to Transfer. The Amount (int) must be non-zero whole dollar amount.
     * Returns a double Amount. Returns the Amount as 0 if Amount is not an integer and Exits to Transfer Menu Page.
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
                return 0;
            }
        }
        while( amount <= 0 );
        return amount;
    }

    /**
     * Takes in a User and an Account Type (Checking or Savings or All) and prints out the balance(s) for those chosen.
     * Tells the user the Account Doesn't Exist if invalid account given.
     */
    public void ViewBalance(String accountType) {
        switch( accountType ) {
            case "Checking":
                try {
                    menuService.printCheckingBalance(currentUser.getCheckingBalance());
                }catch(NullPointerException e){
                    menuService.printCheckingBalance(0);
                }
                break;
            case "Savings":
                try {
                    menuService.printSavingsBalance(currentUser.getSavingsBalance());
                }catch (NullPointerException e){
                    menuService.printSavingsBalance(0);
                }
                break;
            case "All":
                ViewBalance();
                break;
            default:
                menuService.invalidAccountPrint();
                break;
        }
    }

    /**
     * Takes in a User and prints out the balances for both Checking and Savings
     */
    public void ViewBalance(){
        try{
            menuService.printBalances(currentUser.getCheckingBalance(), currentUser.getSavingsBalance());
        }catch(NullPointerException e){
            menuService.printBalances(0,0);
        }
    }

    /**
     * Creates a bankingDao, having all the transaction information, which then can be uploaded to the Database.
     * Also updates the user's balances.
     */
    public void LoadToDatabase(int accountNo, double amount, String bankingType, String accountType){
        Banking banking = new Banking();

        banking.setAmount(amount);
        banking.setAccountNo(accountNo);

        switch(bankingType){
            case "Withdraw": banking.setBankingTypeID(1);
            case "Deposit": banking.setBankingTypeID(2);
            case "Transfer From": banking.setBankingTypeID(3);
        }

        switch(accountType){
            case "Checking": banking.setAccountTypeID(1);
            case "Savings": banking.setAccountTypeID(2);
        }

        bankingDao.create(banking);
        userDao.updateBalance(currentUser);
    }

    /**
     * Allows the user to change their username.
     */
    public void ChangeUsername(){
        currentUser.setUsername(UpdateUserName());
        userDao.update(currentUser);
        menuService.successfulUsernameChange(currentUser.getUsername());
    }

    /**
     * Allows the user to change their password.
     */
    public void ChangePassword(){
        currentUser.setPassword(UpdatePassword());
        userDao.update(currentUser);
        menuService.successfulPasswordChange();
    }

    /**
     * Allows the user to add a person to their account.
     */
    public void NewPerson(){
        menuService.newPersonText();
        String[] name = Name();
        currentPerson = new Person(name[0], name[1], currentUser.getAccountNo());
        personDao.create(currentPerson);
        menuService.successfulPersonAdded(currentPerson.getFirstName(), currentPerson.getLastName());
    }

    /**
     * Allows the user to see all their account's transactions.
     */
    public void AllTransactions(){
        menuService.allTransactionsPrint(currentUser.getUsername());
        System.out.println("Bad value for type timestamp/date/time");
        //userDao.printAllTransactions(currentUser);
    }
}
