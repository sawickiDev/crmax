package com.crmax.persistence.service;

import com.crmax.persistence.dao.RoleDao;
import com.crmax.persistence.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleDao roleDao;

    @Override
    public Role findRoleByName(String roleName) {
        return roleDao.findByRole(roleName);
    }
}
