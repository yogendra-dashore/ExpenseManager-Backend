package com.expense.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.expense.bean.CustomResponse;
import com.expense.entity.UserEntity;
import com.expense.repository.UserRepository;
import com.expense.service.TokenGenerator;

@CrossOrigin
@RestController
public class SessionController {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TokenGenerator tokenGenerator;
	
	
	@PostMapping("/signUp")
	public ResponseEntity<CustomResponse<UserEntity>> signUp(@RequestBody UserEntity userEntity)
	{
		UserEntity email = userRepository.findByEmailAndPassword(userEntity.getEmail(), userEntity.getPassword());
		//UserEntity password1 = userRepository.findByEmail(userEntity.getPassword());
		
		if(email == null)
		{
			
			userRepository.save(userEntity);	
			CustomResponse<UserEntity> resp = new CustomResponse<>();
			
			resp.setData(userEntity);
			resp.setMsg("User Added Successfully");
			return ResponseEntity.ok(resp);
		}
		else
		{
			CustomResponse<UserEntity> resp = new CustomResponse<>();
			
			resp.setData(userEntity);
			resp.setMsg("User Already Exist");
			 
			return ResponseEntity.unprocessableEntity().body(resp);
		}
		
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<CustomResponse<UserEntity>> login(@RequestBody UserEntity userEntity)
	{
		UserEntity email1 = userRepository.findByEmail(userEntity.getEmail());
		if(email1 == null)
		{
			
			
			CustomResponse<UserEntity> resp = new CustomResponse<>();
			
			resp.setData(userEntity);
			resp.setMsg("Please Enter Valid Email");
			return ResponseEntity.unprocessableEntity().body(resp);
		}
		else {
			
			String token = tokenGenerator.generateToken(16);
			email1.setToken(token);
			userRepository.save(email1);
			
			CustomResponse<UserEntity> resp = new CustomResponse<>();
			
			resp.setData(userEntity);
			resp.setMsg("Login Successfull");
			return ResponseEntity.ok(resp);
			
		}
	}

	
	@GetMapping("/forgotPassword")
	public ResponseEntity<CustomResponse<UserEntity>> forgotPasseord(@RequestParam("email") String email){
		
		UserEntity tempUserByEmail = userRepository.findByEmail(email);
		CustomResponse<UserEntity> usr = new CustomResponse<>();
		if(tempUserByEmail == null)
		{
			usr.setData(null);
			usr.setMsg("Otp will share to your Email, if email is exists !!");
			return ResponseEntity.ok(usr);
		}
		else
		{
			Integer min = 100000;
			Integer max = 999999;
			Integer temp = (int) (Math.random() * (max - min + 1) + min)  ;
			System.out.println("Temp: " + temp);
			System.out.println("-----------------"+temp+"-----------------");
			tempUserByEmail.setOtp(temp);
			userRepository.save(tempUserByEmail);
			usr.setData(tempUserByEmail);
			usr.setMsg("Otp will share to your Email, if email is exists (OTP set)!!");
			return ResponseEntity.ok(usr);
		}
	}
	
	@GetMapping("/resetPassword")
	public ResponseEntity<CustomResponse<UserEntity>> resetPassword(@RequestParam("otp") Integer otp, @RequestParam("password") String password, @RequestParam("confirmpassword") String conformpassword)
	{
		UserEntity user = userRepository.findByOtp(otp);
			if((password.equals(conformpassword)) && !(user.getPassword().equals(password)))
			{
				CustomResponse<UserEntity> usr = new CustomResponse<>();
				user.setPassword(conformpassword);
				userRepository.save(user);
				usr.setData(user);
				usr.setMsg("Password sucessfully reset !!");
				return ResponseEntity.ok(usr);
			}
			else
			{
				CustomResponse<UserEntity> usr = new CustomResponse<>();
				usr.setData(null);
				usr.setMsg("Please enter password and conformpassword same !!");
				return ResponseEntity.ok(usr);
			}
		}
	
}
