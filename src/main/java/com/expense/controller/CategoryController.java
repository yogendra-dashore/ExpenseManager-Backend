package com.expense.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expense.bean.CustomResponse;
import com.expense.entity.CategoryEntity;
import com.expense.repository.CategoryRepository;

@CrossOrigin
@RestController
public class CategoryController {

	@Autowired
	CategoryRepository categoryRepository;

	@PostMapping("/categoryadd")
	public ResponseEntity<CustomResponse<CategoryEntity>> savecategory(@RequestBody CategoryEntity categoryEntity) {
		System.out.println("wer"+categoryEntity.getCategoryName());
		CategoryEntity category = categoryRepository.findBycategoryName(categoryEntity.getCategoryName());

		CustomResponse<CategoryEntity> resp = new CustomResponse<>();
       System.out.println(category);
		if(category==null) 
		{
			  System.out.println("inside if");
			categoryRepository.save(categoryEntity);

			resp.setData(categoryEntity);
			resp.setMsg("Category Added Successfully");
			return ResponseEntity.ok(resp);
		} else {
			
			System.out.println("inside else");
			resp.setMsg("Category Already Exist");
			return  ResponseEntity.unprocessableEntity().body(resp);
		}
	}

	@GetMapping("/allcategory")
	public ResponseEntity<CustomResponse<List<CategoryEntity>>> getAll() {

		List<CategoryEntity> category = categoryRepository.findAll();
		CustomResponse<List<CategoryEntity>> resp = new CustomResponse<>();
		resp.setData(category);
		resp.setMsg("all Category feched");

		return ResponseEntity.ok(resp);
	}

	@DeleteMapping("/deleteCategoryById/{categoryid}")
    public ResponseEntity<CustomResponse<CategoryEntity>> deleteCategoryByIdd(@PathVariable("categoryid") Integer categoryid)
    {
    	CategoryEntity categoryEntity = categoryRepository.findBycategoryid(categoryid);
    	categoryRepository.deleteById(categoryid);
    	
    	CustomResponse<CategoryEntity> resp = new CustomResponse<>();
    	
    	resp.setData(categoryEntity);
    	resp.setMsg("Category Deleted");
    	return ResponseEntity.ok(resp);
    	
    }
	
	
	
}
