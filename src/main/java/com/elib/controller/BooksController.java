package com.elib.controller;

import java.util.List;

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
import com.elib.model.BookProduct;
import com.elib.model.Category;
import com.elib.util.ResponseHandler;

@RestController
@RequestMapping("/product")
public class BooksController {
	@Autowired
	Userdboperations userdb;
	@Autowired
	ResponseHandler response;
	Logger logger = LogManager.getLogger("ElibraryFlowProject");

	@GetMapping(value = "/getcategories", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCategory() {
		try {
			logger.info("user get Categories API Entry");
			List<Category> categories = userdb.getCategories();
			if (categories.isEmpty()) {
				logger.info("user get Categories API Exit");
				return response.generateResponse("no category found", HttpStatus.NOT_FOUND, null);
			} else {
				logger.info("user get Categories API Exit");
				return response.generateResponse("Categories fetched succefully", HttpStatus.OK, categories);
			}

		} catch (Exception e) {
			logger.error(e);
			return response.generateResponse(e.toString(), HttpStatus.NOT_FOUND, null);
		}
	}

	@GetMapping(value = "/getbooks", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getBooks(@RequestBody Category category) {
		try {
			logger.info("user get Books API Entry");
			List<BookProduct> books = userdb.getBooksbyCategory(category.getCategoryId());
			if (books.isEmpty()) {
				logger.info("user get Books API Exit");
				return response.generateResponse("no books found", HttpStatus.NOT_FOUND, null);
			} else {
				logger.info("user get Books API Exit");
				return response.generateResponse("Books fetched successfully", HttpStatus.OK, books);
			}

		} catch (Exception e) {
			logger.error(e);
			return response.generateResponse(e.toString(), HttpStatus.NOT_FOUND, null);
		}
	}
}
