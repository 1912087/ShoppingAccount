package com.example.shoppingAccount.category;


public class category_Item {
    Integer image_list;
    String name_list;
    String account_list;
    String search_list;

    public Integer getImage_list() {
        return image_list;
    }

    public void setImage_list(Integer image_list) {
        this.image_list = image_list;
    }

    public String getName_list() {
        return name_list;
    }

    public void setName_list(String name_list) {
        this.name_list = name_list;
    }

    public String getAccount_list() {
        return account_list;
    }

    public void setAccount_list(String account_list) {
        this.account_list = account_list;
    }

    public String getSearch_list() {
        return search_list;
    }

    public void setSearch_list(String search_list) {
        this.search_list = search_list;
    }

    public category_Item(Integer image_list, String name_list, String account_list, String search_list) {
        this.image_list = image_list;
        this.name_list = name_list;
        this.account_list = account_list;
        this.search_list = search_list;
    }
}
