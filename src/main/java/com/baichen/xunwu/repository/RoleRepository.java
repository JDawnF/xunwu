package com.baichen.xunwu.repository;

import java.util.List;

import com.baichen.xunwu.entity.Role;
import org.springframework.data.repository.CrudRepository;


public interface RoleRepository extends CrudRepository<Role, Long> {
	List<Role> findRolesByUserId(Long userId);
}
