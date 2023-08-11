package com.bamboo.pojo;

public class DepositCards {
    private String userName;
    private int userId;
    private String cardId;
    private int money;

//    public String getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int id) {
//        this.userId = id;
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCardId() {
        return userName;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Student{" +
                "userid='" + userId + '\'' +
                ", username='" + userName + '\'' +
                ", cardid=" +cardId +
                ", money='" + money + '\'' +
                '}';
    }
}
