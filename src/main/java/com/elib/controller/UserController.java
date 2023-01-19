package com.elib.controller;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elib.dao.Userdboperations;
import com.elib.model.User;
import com.elib.util.ResponseHandler;

@RestController
@RequestMapping("/user/")
public class UserController {

	@Autowired
	Userdboperations userdb;
	@Autowired
	ResponseHandler response;

	@GetMapping(value = "/verify", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> UserVerify(@RequestBody User user) {
		try {	
			if (String.valueOf(user.getCustomerId()).length() == 6) {
				System.out.println("userid = " + user.getCustomerId());
				ResultSet result = userdb.getPassword(user.getCustomerId());
				if (result.next()) {
					System.out.println("User Password retrieved");
					String password = result.getString("CUSTOMER_PASSWORD");
					return response.generateResponse("password data succefully retrived", HttpStatus.OK, password);
				} else {
					System.out.println("invalid details");
					return response.generateResponse("No Data found", HttpStatus.NOT_FOUND, null);
				}

			} else {
				System.out.println("invalid details");
				return response.generateResponse("User id length not matching", HttpStatus.NOT_FOUND, null);
			}
		} catch (NumberFormatException e) {
			return response.generateResponse(e.toString(), HttpStatus.NOT_FOUND, null);
		} catch (Exception e) {
			return response.generateResponse(e.toString(), HttpStatus.NOT_FOUND, null);
		}
	}

	@PostMapping(value = "/addnewuser", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addNewUser(@RequestBody User user) {
		try {
			int userid = user.getCustomerId();
			String password = user.getCustomerPassword();
			if (String.valueOf(userid).length() == 6) {
				ResultSet resultSet = userdb.checkUserId(userid);
				if (resultSet.next()) {
					System.out.println("Already Exist");
					return response.generateResponse("Already Exist user", HttpStatus.NOT_FOUND, null);
				} else {
					int insertCount = userdb.InsertUserDetail(userid, password);
					if (insertCount < 1) {
						System.out.println("User Details not added");
						return response.generateResponse("user detail not added", HttpStatus.NOT_FOUND, null);
					} else {
						System.out.println("user Details added successfully");
						return response.generateResponse("user detail added succefully", HttpStatus.OK, insertCount);

					}
				}
			} else {
				System.out.println("invalid Credentials");
				return response.generateResponse("invalid Credentials", HttpStatus.NOT_FOUND, null);
			}
		} catch (NumberFormatException e) {
			return response.generateResponse(e.toString(), HttpStatus.NOT_FOUND, null);

		} catch (Exception e) {
			return response.generateResponse(e.toString(), HttpStatus.NOT_FOUND, null);

		}
	}




}
