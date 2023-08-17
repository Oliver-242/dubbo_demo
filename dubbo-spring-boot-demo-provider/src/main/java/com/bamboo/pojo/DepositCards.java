package com.bamboo.pojo;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Data
public class DepositCards {
    private String userName;
    private int userId;
    private String cardId;
    private long money;
}
