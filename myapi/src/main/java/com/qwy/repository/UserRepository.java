package com.qwy.repository;

import com.qwy.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qwy on 17/6/9.
 * 用户管理仓库
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    @Modifying      // 说明该方法是修改操作
    @Transactional  // 说明该方法是事务性操作
    // 定义查询
    // @Param注解用于提取参数
    @Query("update UserEntity us set us.username=:qUsername, us.password=:qPassword where us.id=:qId")
    public void updateUser(@Param("qUsername") String username, @Param("qPassword") String password, @Param("qId") Integer id);
}
