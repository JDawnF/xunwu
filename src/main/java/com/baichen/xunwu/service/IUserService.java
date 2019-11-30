package com.baichen.xunwu.service;

import com.baichen.xunwu.entity.User;

public interface IUserService {
	User findUserByName(String userName);
}
