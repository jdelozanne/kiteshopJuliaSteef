package com.steefjulia.kiteshop.model.data;

import javax.transaction.Transactional;

import org.springframework.data.repository.*;
import org.springframework.stereotype.Repository;

import com.steefjulia.kiteshop.model.Adres;




@Repository
@Transactional
public interface AdresDao extends CrudRepository<Adres, Integer> {
	

}