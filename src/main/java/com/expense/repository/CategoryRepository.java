package com.expense.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.expense.entity.CategoryEntity;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Integer> {

	List<CategoryEntity> findAll();
	
	CategoryEntity findBycategoryName(String categoryName);

	
	
}
