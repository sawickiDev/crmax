package com.crmax.persistence.dao;

import com.crmax.persistence.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, Integer>{

    Role findByRole(String roleName);

}
