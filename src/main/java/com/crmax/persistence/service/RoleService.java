package com.crmax.persistence.service;

import com.crmax.persistence.model.Role;

public interface RoleService {
    Role findRoleByName(String roleName);
}
