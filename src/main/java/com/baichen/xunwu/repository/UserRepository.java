package com.baichen.xunwu.repository;

import com.baichen.xunwu.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.method.P;



public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String userName);
}
