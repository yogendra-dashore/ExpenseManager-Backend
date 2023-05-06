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
import com.expense.entity.VendorEntity;
import com.expense.repository.VendorRepository;

@CrossOrigin
@RestController
public class VendorController {

	@Autowired
	VendorRepository vendorRepository;
	
	@PostMapping("/vendoradd")
	public ResponseEntity<CustomResponse<VendorEntity>> addVendor(@RequestBody VendorEntity vendorEntity){
		
		VendorEntity veEntity = vendorRepository.findByvendorName(vendorEntity.getVendorName());
		
		CustomResponse<VendorEntity> resp = new CustomResponse<>();
		
		
		if(veEntity == null) {
			
			vendorRepository.save(vendorEntity);
			
			resp.setData(vendorEntity);
			resp.setMsg("Vendor Addes Successfully");
			return ResponseEntity.ok(resp);
		}
		else {
			
			resp.setMsg("Vendor Already Exist");
			
			return ResponseEntity.unprocessableEntity().body(resp);
			
		}
		
	}
	
	@GetMapping("/getAllVen")
	public ResponseEntity<CustomResponse<List<VendorEntity>>> getAllVendor() {

		List<VendorEntity> vendorlist = vendorRepository.findAll();
		CustomResponse<List<VendorEntity>> resp = new CustomResponse<>();
		resp.setData(vendorlist);
		resp.setMsg("All Vendor feched");

		return ResponseEntity.ok(resp);
	}
	
	
	@DeleteMapping("/deleteVenById/{vendorId}")
    public ResponseEntity<CustomResponse<VendorEntity>> deleteVendoreByIdd(@PathVariable("vendorId") Integer vendorId)
    {
    	VendorEntity vendorEntity = vendorRepository.findByvendorId(vendorId);
    	vendorRepository.deleteById(vendorId);
    	
    	CustomResponse<VendorEntity> resp = new CustomResponse<>();
    	
    	resp.setData(vendorEntity);
    	resp.setMsg("Vendor Deleted Successfully");
    	return ResponseEntity.ok(resp);
    	
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
