package org.apache.dubbo.springboot.demo.mapper;

import org.apache.dubbo.springboot.demo.model.dao.UserInfos;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author caijizhou
 * @date 2023/09/05 11:30
 */
@Mapper
@Repository
public interface UserInfosDao {
    int createUserInfo(@Param("user") UserInfos userInfos);

    List<UserInfos> queryAllUserInfo();

    List<UserInfos> queryAllByUserName(@Param("phonenumber") String phoneNumber);
}
