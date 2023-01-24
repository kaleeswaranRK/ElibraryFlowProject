package com.elib.controller;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elib.dao.Userdboperations;
import com.elib.model.Response;
import com.elib.model.User;
import com.elib.util.ResponseHandler;

@RestController
@RequestMapping("/user/")
public class UserController {
	Logger logger = LogManager.getLogger(UserController.class);
	@Autowired
	Userdboperations userdb;
	@Autowired
	ResponseHandler response;

	@GetMapping(value = "/verify", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response UserVerify(@RequestBody User user) {
		Response res = new Response();
		try {
			logger.info("user verify API Entry");
			logger.info("userid = " + user.getCustomerId());
			String password = userdb.getPassword(user.getCustomerId());
			if (password == null) {
				logger.info("user verify API Exit");
				res.setMessage("No data Found");
				res.setStatus(HttpStatus.NOT_FOUND);
				res.setDateTime(new Timestamp(new Date().getTime()));
				res.setData(null);
				return res;

			} else {
				logger.info("user verify API Exit");
				res.setMessage("password data succefully retrived");
				res.setStatus(HttpStatus.OK);
				res.setDateTime(new Timestamp(new Date().getTime()));
				res.setData(new JSONObject().put("hash", password));
				return res;
			}
		} catch (NumberFormatException e) {
			logger.error(e);
			logger.info("user verify API Exit");
			res.setMessage("data fetching not successfull");
			res.setStatus(HttpStatus.NOT_FOUND);
			res.setDateTime(new Timestamp(new Date().getTime()));
			res.setData(null);
			return res;
		} catch (Exception e) {
			logger.error(e);
			logger.info("user verify API Exit");
			res.setMessage("data fetching not successfull");
			res.setStatus(HttpStatus.NOT_FOUND);
			res.setDateTime(new Timestamp(new Date().getTime()));
			res.setData(null);
			return res;
		}

	}
}
