package com.expense.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.expense.entity.SubCategoryEntity;

@Repository
public interface SubCategoryRepository extends CrudRepository<SubCategoryEntity, Integer> {

List<SubCategoryEntity> findAll();
	
	SubCategoryEntity findBysubcategoryName(String subcategoryName);

	SubCategoryEntity findBysubcategoryId(Integer subcategoryId);
	
	
}
