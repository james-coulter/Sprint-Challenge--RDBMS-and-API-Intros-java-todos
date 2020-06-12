package com.lambdaschool.javatodos.repositories;

import com.lambdaschool.javatodos.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {

}
