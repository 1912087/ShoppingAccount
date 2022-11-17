package com.example.shoppingAccount;

public class Wallet {

    public Wallet(int budget) {
        this.budget = budget;
    }

    private int budget;

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }
}