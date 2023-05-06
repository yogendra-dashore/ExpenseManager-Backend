package com.expense.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.expense.entity.VendorEntity;

@Repository
public interface VendorRepository extends CrudRepository<VendorEntity, Integer> {


	VendorEntity findByvendorName(String vendorName);
	
	List<VendorEntity> findAll();
    
	VendorEntity findByvendorId(Integer vendorId);

}

	
	

