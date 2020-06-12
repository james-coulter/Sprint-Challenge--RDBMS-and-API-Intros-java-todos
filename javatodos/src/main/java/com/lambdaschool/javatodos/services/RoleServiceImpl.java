package com.lambdaschool.javatodos.services;

import com.lambdaschool.javatodos.models.Role;
import com.lambdaschool.javatodos.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Transactional
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepo;

    @Override
    public Role getRoleById(long id) {
        return roleRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Role " + id + " not found"));
    }

    @Override
    public Role save(Role role) {
        Role newRole = new Role();
        newRole.setUsers(new ArrayList<>());
        newRole.setRolename(role.getRolename());
        return roleRepo.save(newRole);
    }
}