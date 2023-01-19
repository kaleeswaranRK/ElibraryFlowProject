package com.elib.controller;

import java.util.List;

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
@RequestMapping("/book")
public class BooksController {
	@Autowired
	Userdboperations userdb;
	@Autowired
	ResponseHandler response;
	@GetMapping(value = "/category", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCategory() {
		try {
			List<Category> categories = userdb.getCategories();
			return response.generateResponse("Categories fetched succefully", HttpStatus.OK, categories);
		} catch (Exception e) {
			return response.generateResponse(e.toString(), HttpStatus.NOT_FOUND, null);

		}

	}

	@GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getBooks(@RequestBody Category category) {
		try {
			List<BookProduct> books = userdb.getBooksbyCategory(category.getCategoryId());
			return response.generateResponse("Books fetched successfully", HttpStatus.OK, books);
		} catch (Exception e) {
			return response.generateResponse(e.toString(), HttpStatus.NOT_FOUND, null);
		}
	}
}