package com.lambdaschool.javatodos.services;

import com.lambdaschool.javatodos.models.Role;

public interface RoleService {

    Role getRoleById(long id);
    Role save(Role role);
}
