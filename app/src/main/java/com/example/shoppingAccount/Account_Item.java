package com.example.shoppingAccount;

public class Account_Item {
    public String account_ac;
    public String date;

    public String getAccount_ac() {
        return account_ac;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setAccount_ac(String account_ac) {
        this.account_ac = account_ac;
    }

    public Account_Item(String account_ac, String date) {
        this.account_ac = account_ac;
        this.date = date;
    }
}
