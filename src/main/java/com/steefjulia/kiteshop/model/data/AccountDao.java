/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steefjulia.kiteshop.model.data;

import com.steefjulia.kiteshop.model.Account;
import javax.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author julia
 */
@Repository
@Transactional
public interface AccountDao extends CrudRepository<Account, Integer> {
    public Account findByUsername(String username);
}
