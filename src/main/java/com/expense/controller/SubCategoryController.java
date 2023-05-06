package com.expense.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expense.bean.CustomResponse;
import com.expense.entity.SubCategoryEntity;
import com.expense.repository.SubCategoryRepository;

@CrossOrigin
@RestController
public class SubCategoryController {

	@Autowired
	SubCategoryRepository subRepository;
	
	
	@PostMapping("/subCatAdd")
	public ResponseEntity<CustomResponse<SubCategoryEntity>> addSubCategory(@RequestBody SubCategoryEntity subEntity){
		
		SubCategoryEntity subCategoryEntity = subRepository.findBysubcategoryName(subEntity.getSubcategoryName());
		CustomResponse<SubCategoryEntity> resp = new CustomResponse<>();
		
		if(subCategoryEntity == null) {
			subRepository.save(subEntity);
			
			resp.setData(subEntity);
			resp.setMsg("SubCategory Added Successfully");
			return ResponseEntity.ok(resp);
		}
		else {
			
			resp.setMsg("SubCategory Already Exist");
			return ResponseEntity.unprocessableEntity().body(resp);
		}
	}
	
	@GetMapping("/getAllSUbCategory")
	public ResponseEntity<CustomResponse<List<SubCategoryEntity>>> getAllSubCat(){
		
		List<SubCategoryEntity> subEntity = subRepository.findAll();
		
		CustomResponse<List<SubCategoryEntity>> resp = new CustomResponse<>();
		resp.setData(subEntity);
		resp.setMsg("All SubCategory Feched");
		
		return ResponseEntity.ok(resp);
		
	}
	
	@DeleteMapping("/deleteSubCategory/{subcategoryId}")
	public ResponseEntity<CustomResponse<SubCategoryEntity>> deleteSubCatById(@PathVariable("subcategoryId") Integer subcategoryId){
		
		SubCategoryEntity subEntity = subRepository.findBysubcategoryId(subcategoryId);
		subRepository.deleteById(subcategoryId);
		
		CustomResponse<SubCategoryEntity> resp = new CustomResponse<>();
		
		resp.setData(subEntity);
		resp.setMsg("SubCategory Deleted Successfully");
		return ResponseEntity.ok(resp);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
