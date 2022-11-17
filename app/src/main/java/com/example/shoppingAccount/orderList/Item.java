package com.example.shoppingAccount.orderList;

public class Item{

    byte[] image;
    public String name;
    String amount;
    String account;

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public byte[] getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public String getAccount() {
        return account;
    }

    public Item(byte[] image, String name, String amount, String account) {
        this.image = image;
        this.name = name;
        this.amount = amount;
        this.account = account;
    }
}

