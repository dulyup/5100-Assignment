package com.assign6;

import java.io.*;
import java.util.*;

/**
 * Atm class
 *
 * @author lyupingdu
 * @date 2017/10/19
 */
public class Atm {
    /**
     * ATM can only access integer value
     * currentUser only be initialised after login
     */
    private int availableAmountMachine;
    private int transactionFee;
    private HashMap<String, AtmUser> atmUserList;
    private AtmUser currentUser;
    private HashMap<String, ArrayList> transaction;
    private ArrayList<String> transactionList = new ArrayList<>();
    private GetData get = new GetData();

    public Atm(int availableAmountMachine, int transactionFee) {
        /*initialiseUsers();*/
        this.availableAmountMachine = availableAmountMachine;
        this.transactionFee = transactionFee;
        readFromFile();
    }

    /**
     * Welcome screen before login
     */
    public void run() {
        readFromFile();
        System.out.println("Welcome!");
        System.out.println("1 - Login");
        System.out.println("2 - New User");
        System.out.println("3 - Forget Password");
        int choice = get.getNumber();
        if (choice == 1) {
            login();
        }
        if (choice == 2) {
            createNewUser();
        }
        if (choice == 3) {
            forgotPassword();
        }
    }

    /**
     * create a new user, store in AtmUesr and userList
     * write into a file
     */
    private void createNewUser() {
        System.out.println("Please enter your name: ");
        String name = get.getInput();
        System.out.println("Please enter your address: ");
        String address = get.getInput();
        System.out.println("Please enter your phoneNumber: ");
        String phoneNumber = get.getInput();
        System.out.println("Please enter your Date of Birth(mm/dd/yyyy): ");
        String dateOfBirth = get.getInput();
        System.out.println("Please set password for your account");
        String password = get.getInput();
        //create a new unique bank account number for new user
        String temp = getCardNumber();
        while (atmUserList.containsKey(temp)) {
            temp = getCardNumber();
        }
        String bankAccountNumber = temp;
        AtmUser atmUser = new AtmUser(name, dateOfBirth, address, phoneNumber, bankAccountNumber, password, 0);
        atmUserList.put(bankAccountNumber, atmUser);
        System.out.println("Congratulations! Sign up successfully!");
        System.out.println("Your account number is: "+bankAccountNumber);
        writeToFile(atmUserList, "atmUserList.ser");
        run();
    }

    /**
     * create a new bank account number
     */
    private String getCardNumber() {
        Random random = new Random();
        String cardNumber = "";
        for (int i = 0; i < 8; i++) {
            cardNumber += random.nextInt(10);
        }
        return cardNumber;
    }

    /**
     * Validate bank account number and password
     * When login, pass data to currentUser
     */
    private void login() {
        System.out.println("Please enter your bank account number: ");
        String bankAccountNumber = get.getInput();
        if (!atmUserList.containsKey(bankAccountNumber)) {
            System.out.println("Sorry, the account number is not available, please try again!");
            login();
        } else {
            System.out.println("Please enter your password: ");
            String password = get.getInput();
            if (atmUserList.get(bankAccountNumber).getPassword().equals(password)) {
                System.out.println("\nLogin Successfully!");
                currentUser = atmUserList.get(bankAccountNumber);
                showMainMenu();
            } else {
                System.out.println("Sorry, wrong password, please try again!");
                login();
            }
        }
    }

    /**
     * Validate user's name, age, phoneNumber
     * Call changePassword method
     */
    private void forgotPassword() {
        int choice = 0;
        System.out.println("Please enter your bank account number: ");
        String bankAccountNumber = get.getInput();
        AtmUser tempUser = atmUserList.get(bankAccountNumber);
        System.out.println("Please enter your name: ");
        if (get.getInput().equals(tempUser.getName())) {
            System.out.println("Please enter your date of birth(mm/dd/yyyy): ");
            if (get.getInput().equals(tempUser.getDateOfBirth())) {
                System.out.println("Please enter your phone number: ");
                if (get.getInput().equals(tempUser.getPhoneNumber())) {
                    currentUser = tempUser;
                    changePassword();
                } else {
                    System.out.println("Sorry, wrong phone number!");
                    System.out.println("1 - Try again   2 - Cancel");
                    choice = get.getNumber();
                }
            } else {
                System.out.println("Sorry, wrong date of birth!");
                System.out.println("1 - Try again   2 - Cancel");
                choice = get.getNumber();
            }
        } else {
            System.out.println("Sorry, wrong information!");
            System.out.println("1 - Try again   2 - Cancel");
            choice = get.getNumber();
        }
        if (choice == 1) {
            forgotPassword();
        } else if (choice == 2) {
            run();
        }
    }

    /**
     * Show main menu
     * User choose what he wants to do
     */
    private void showMainMenu() {
        System.out.println("\n1 - View Available Balance");
        System.out.println("2 - Withdrawal");
        System.out.println("3 - Deposit");
        System.out.println("4 - Recent Transaction");
        System.out.println("5 - Change Password");
        System.out.println("6 - Exit");
        int chooseTransaction = get.getNumber();
        if (chooseTransaction > 6 || chooseTransaction < 0) {
            System.out.println("Sorry, please enter again!");
            showMainMenu();
        }
        performTransaction(chooseTransaction);
    }

    /**
     * Perform transactions according to what users choose
     */
    private void performTransaction(int chooseTransaction) {
        if (chooseTransaction == 1) {
            viewAvailableBalance();
        }
        if (chooseTransaction == 2) {
            withdrawal();
        }
        if (chooseTransaction == 3) {
            deposit();
        }
        if (chooseTransaction == 4) {
            viewRecentTransaction();
        }
        if (chooseTransaction == 5) {
            changePassword();
        }
        if (chooseTransaction == 6) {
            exit();
        }
    }

    private void viewAvailableBalance() {
        System.out.println("Your Available Balance is: " + currentUser.getAvailableBalance());
        showMainMenu();
    }

    /**
     * user can't withdraw money more than user.availableAmount and availableAmountMachine
     * when success, minus transaction fee
     * update user.availableAmount and availableAmountMachine
     * when success, record transaction name and amount
     * write transaction into file
     */
    private void withdrawal() {
        double availableBalance = currentUser.getAvailableBalance();
        System.out.println("Your available balance is: " + currentUser.getAvailableBalance());
        System.out.println("Please enter the amount you want to withdraw: ");
        int withdrawalAmount = get.getNumber();
        int choice = 0;
        if (withdrawalAmount > availableAmountMachine) {
            System.out.println("Sorry, there's no sufficient balance on the machine!");
            System.out.println("1 - Try again   2 - Cancel");
            choice = get.getNumber();
        } else if (withdrawalAmount > currentUser.getAvailableBalance()) {
            System.out.println("Sorry, there's no sufficient balance in your account!");
            System.out.println("1 - Try again   2 - Cancel");
            choice = get.getNumber();
        }
        if (choice == 1) {
            withdrawal();
        } else if (choice == 2) {
            showMainMenu();
        }
        System.out.println("Withdrawal Successfully!");
        System.out.println("Transaction Fee is: " + transactionFee);
        availableAmountMachine -= withdrawalAmount;
        availableBalance -= withdrawalAmount;
        availableBalance -= transactionFee;
        currentUser.setAvailableBalance(availableBalance);
        transactionList.add("Withdrawal: " + withdrawalAmount + " , Available Balance: " + availableBalance);
        transaction.put(currentUser.getBankAccountNumber(), transactionList);
        writeToFile(transaction, "transaction.ser");
        showMainMenu();
    }

    /**
     * when success, minus transaction fee
     * write transaction into file
     */
    private void deposit() {
        double availableBalance = currentUser.getAvailableBalance();
        System.out.println("Your available balance is: " + availableBalance);
        System.out.println("Please enter the amount you want to save: ");
        int depositAmount = get.getNumber();
        System.out.println("Deposit Successfully!");
        System.out.println("Transaction Fee is: " + transactionFee);
        availableAmountMachine += depositAmount;
        availableBalance += depositAmount;
        availableBalance -= transactionFee;
        transactionList.add("Deposit: " + depositAmount + " , Available Balance: " + availableBalance);
        transaction.put(currentUser.getBankAccountNumber(), transactionList);
        currentUser.setAvailableBalance(availableBalance);
        writeToFile(transaction, "transaction.ser");
        showMainMenu();
    }

    /**
     * Enter new password twice
     * After changing, show login page
     */
    private void changePassword() {
        System.out.println("Please enter your new password");
        String temp = get.getInput();
        System.out.println("Please confirm your new password");
        String temp2 = get.getInput();
        if (temp.equals(temp2)) {
            System.out.println("Change password successfully!");
            currentUser.setPassword(temp);
            writeToFile(atmUserList, "atmUserList.ser");
            run();
        } else {
            System.out.println("Sorry, please try again!");
            changePassword();
        }
    }

    /**
     * Get data from transactionList,
     * If recent transactions less than 10,print all
     * Otherwise, only print latest 10
     * Assume have 12 transactions，print latest 3，index=size-1=11，11，10，9; 9=size-3
     */
    private void viewRecentTransaction() {
        transactionList = transaction.get(currentUser.getBankAccountNumber());
        System.out.println(transactionList.size());
        int recentTransaction = 10;
        int transactionNumber = transactionList.size();
        if(transactionNumber == 0){
            System.out.println("You don't have transaction history!");
        }else if (transactionNumber > recentTransaction) {
            for (int index = transactionNumber - 1; index >= transactionNumber - recentTransaction; index--) {
                System.out.println(transactionList.get(index));
            }
        } else {
            for (String eachTransaction : transactionList
                    ) {
                System.out.println(eachTransaction);
            }
        }
        showMainMenu();
    }

    /**
     * Set currentUser = null
     * write atmUserList and transaction to file
     */
    private void exit() {
        writeToFile(atmUserList, "atmUserList.ser");
        writeToFile(transaction, "transaction.ser");
        System.out.println("Thank you! Bye!");
        return;
    }

    /**
     * Serialize the atmUserList
     * Be called when user exit
     * May pass fileName as a parameter-"atmUserList.ser","transactionList.ser"-user common code to serialize two files
     */
    private void writeToFile(Object content, String fileName) {
        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileName));
            //write list to file
            os.writeObject(content);
            /*System.out.println("serializable is done");*/
            os.flush();
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deserialize
     * be called when atm was initialised
     */
    private void readFromFile() {
        try {
            //deserialize the List
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(
                    "atmUserList.ser"));
            atmUserList = (HashMap<String, AtmUser>) is.readObject();
            ObjectInputStream is2 = new ObjectInputStream(new FileInputStream(
                    "transaction.ser"));
            transaction = (HashMap<String, ArrayList>) is2.readObject();
            /*System.out.println("deserializable is done");*/
            is.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getAvailableAmountMachine() {
        return availableAmountMachine;
    }

    public int getTransactionFee() {
        return transactionFee;
    }

    public HashMap<String, AtmUser> getAtmUserList() {
        return atmUserList;
    }

    public void setAvailableAmountMachine(int availableAmountMachine) {
        this.availableAmountMachine = availableAmountMachine;
    }

    public void setTransactionFee(int transactionFee) {
        this.transactionFee = transactionFee;
    }

    /**
     * Test
     * May initialise some users
     * Not necessary
     */
    /*private void initialiseUsers() {
        atmUserList = new HashMap<>();
        AtmUser atmUser1 = new AtmUser("Tim", "11/11/1970", "street1", "1234",
                "12345678", "123", 25000);
        AtmUser atmUser2 = new AtmUser("Amy", "12/12/1970", "street2", "2345",
                "87654321", "123", 500);
        AtmUser atmUser3 = new AtmUser("Ben", "1/1/1970", "street3", "3456",
                "34567890", "123", 1000);
        atmUserList.put("12345678", atmUser1);
        atmUserList.put("87654321", atmUser2);
        atmUserList.put("34567890", atmUser3);
        transaction = new HashMap<>();
        transactionList.add("Withdrawal - 100 / Available Balance - 24890");
        transactionList.add("Withdrawal - 1000 / Available Balance - 25880");
        transaction.put("12345678", transactionList);
        writeToFile(atmUserList, "atmUserList.ser");
        writeToFile(transaction, "transaction.ser");
    }*/
}
