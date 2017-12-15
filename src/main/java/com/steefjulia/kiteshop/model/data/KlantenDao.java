package com.steefjulia.kiteshop.model.data;

import javax.transaction.Transactional;

import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

import com.steefjulia.kiteshop.model.Klant;




@Repository
@Transactional
public interface KlantenDao extends CrudRepository<Klant, Integer> {

}
