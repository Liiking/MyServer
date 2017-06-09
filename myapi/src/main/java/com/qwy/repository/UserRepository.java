package com.qwy.repository;

import com.qwy.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by qwy on 17/6/9.
 *
 */
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
