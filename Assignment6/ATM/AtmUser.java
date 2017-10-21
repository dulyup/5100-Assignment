package com.assign6;

import java.io.Serializable;

/**
 * AtmUser class
 *
 * @author lyupingdu
 * @date 2017/10/18
 */
public class AtmUser extends User implements Serializable {

    private static final long serialVersionUID = 46483824464083179L;
    private String password;
    private double availableBalance;

    public AtmUser(String name, String dateOfBirth, String address, String phoneNumber, String bankAccountNumber, String password, double availableBalance) {
        super(name, dateOfBirth, address, phoneNumber, bankAccountNumber);
        this.password = password;
        this.availableBalance = availableBalance;
    }

    public String getPassword() {
        return password;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }
}
