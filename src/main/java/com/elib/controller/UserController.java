package com.elib.controller;

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
			logger.info("user verify API Entry");
			logger.info("userid = " + user.getCustomerId());
			String password = userdb.getPassword(user.getCustomerId());
			if (password == null) {
				logger.info("user verify API Exit");
				return response.generateResponse("No Data found", HttpStatus.NOT_FOUND, null);
			} else {
				logger.info("user verify API Exit");
				return response.generateResponse("password data succefully retrived", HttpStatus.OK, password);
			}
		}
		catch (NumberFormatException e) {
			logger.error(e);
			return response.generateResponse(e.toString(), HttpStatus.NOT_FOUND, null);
		} catch (Exception e) {
			logger.error(e);
			return response.generateResponse(e.toString(), HttpStatus.NOT_FOUND, null);
		}
	}

}
