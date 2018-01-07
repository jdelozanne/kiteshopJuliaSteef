/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steefjulia.kiteshop.model.data;

import com.steefjulia.kiteshop.model.Role;
import java.io.Serializable;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author julia
 */
@Repository
@Transactional
public interface RoleDao extends CrudRepository<Role, Integer> {
   public Role findByRole(String role);
}
