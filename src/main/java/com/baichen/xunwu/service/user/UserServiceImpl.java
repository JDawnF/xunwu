package com.baichen.xunwu.service.user;

import com.baichen.xunwu.entity.Role;
import com.baichen.xunwu.entity.User;
import com.baichen.xunwu.repository.RoleRepository;
import com.baichen.xunwu.repository.UserRepository;
import com.baichen.xunwu.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 2019-11-30 23:01
 * @Author: baichen
 * @Description
 */
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User findUserByName(String userName) {
        User user = userRepository.findByName(userName);
        if (user == null) {
            return null;
        }
        // 返回用户ID对应的角色列表
        List<Role> roles = roleRepository.findRolesByUserId(user.getId());
        if (roles == null || roles.isEmpty()) {
            throw new DisabledException("权限非法");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        //
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        user.setAuthorityList(authorities);
        return user;
    }
}
