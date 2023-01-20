package com.elib.controller;

import java.sql.ResultSet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elib.dao.Userdboperations;
import com.elib.model.User;
import com.elib.util.ResponseHandler;

@RestController
@RequestMapping("/user/")
public class UserController {
	Logger logger = LogManager.getLogger("ElibraryFlowProject");
	@Autowired
	Userdboperations userdb;
	@Autowired
	ResponseHandler response;

	@GetMapping(value = "/verify", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> UserVerify(@RequestBody User user) {
		try {
			logger.info("user verify Controller Entry");
			if (String.valueOf(user.getCustomerId()).length() == 6) {
				logger.info("userid = " + user.getCustomerId());
				ResultSet result = userdb.getPassword(user.getCustomerId());
				if (result.next()) {
					return response.generateResponse("password data succefully retrived", HttpStatus.OK,
							result.getString("CUSTOMER_PASSWORD"));
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

}
