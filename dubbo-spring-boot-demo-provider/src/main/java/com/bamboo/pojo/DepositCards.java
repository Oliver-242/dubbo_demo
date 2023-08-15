package com.bamboo.pojo;

public class DepositCards {
    private String userName;
    private int userId;
    private String cardId;
    private long money;

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
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
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
