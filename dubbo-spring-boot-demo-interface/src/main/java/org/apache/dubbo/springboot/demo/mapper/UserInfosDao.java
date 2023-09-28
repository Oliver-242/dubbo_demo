package org.apache.dubbo.springboot.demo.mapper;

import org.apache.dubbo.springboot.demo.model.entity.UserInfos;
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

    UserInfos queryAllByPhoneNumber(@Param("phonenumber") String phoneNumber);

    int deleteUserByUserId(@Param("userId") long userId);

    int modStatusByUserId(@Param("status") String status, @Param("userId") long userId);
}
