package com.elib.controller;

import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elib.dao.Userdboperations;
import com.elib.model.BookCart;
import com.elib.model.User;
import com.elib.util.ResponseHandler;

@RestController
@RequestMapping("/cart")
public class CartCotroller {
	@Autowired
	Userdboperations userdb;
	@Autowired
	ResponseHandler response;

	Logger logger = LogManager.getLogger("ElibraryFlowProject");

	@GetMapping(value = "/cartview", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCartItems(@RequestBody User user) {
		try {
			logger.info("cart view API Entry");
			List<BookCart> books = userdb.getCartItems(user.getCustomerId());
			if (books.isEmpty()) {
				logger.info("cart view API Exit");
				return response.generateResponse("no product found", HttpStatus.NOT_FOUND, null);
			} else {
				logger.info("cart view API Exit");
				return response.generateResponse("Cart items fetched successfully", HttpStatus.OK, books);
			}

		} catch (Exception e) {
			logger.error(e);
			return response.generateResponse(e.toString(), HttpStatus.NOT_FOUND, null);

		}
	}

	@PostMapping(value = "/addtocart", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addToCart(@RequestParam Map<String, String> input) {
		try {
			logger.info("add to cart API Entry");
			boolean cartAdd = userdb.cartAddCount(input.get("bookName"), Integer.parseInt(input.get("bookQuantity")), Double.parseDouble(input.get("bookPrice")), Integer.parseInt(input.get("userid")));
				if (cartAdd) {
					logger.info("book added successfully");
					logger.info("add to cart API Exit");
					return response.generateResponse("book added successfully", HttpStatus.OK, true);

				} else {
					logger.info("book not added to cart");
					return response.generateResponse("book not added to cart", HttpStatus.NOT_FOUND, null);
				}
			}
		 catch (Exception e) {
			logger.error(e);
			return response.generateResponse(e.toString(), HttpStatus.NOT_FOUND, null);
		}
	}

	@PostMapping(value = "/cartremove", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> cartRemove(@RequestParam Map<String,String> input) {
		try {
			if (userdb.cartRemove( input.get("bookName"),Integer.parseInt(input.get("userid")))) {
				logger.info("book removed successfully");
				return response.generateResponse("book removed successfully", HttpStatus.OK, true);
			} else {
				logger.info("book not remove from cart");
				return response.generateResponse("book not remove from cart", HttpStatus.NOT_FOUND, null);
			}
		} catch (Exception e) {
			logger.error(e);
			return response.generateResponse(e.toString(), HttpStatus.NOT_FOUND, null);
		}
	}
	@PostMapping(value = "/cartreduce", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> cartReduce(@RequestParam Map<String, String> input) {
		try {
			boolean result = userdb.CartReduce(input.get("bookName"), Integer.parseInt(input.get("bookQuantity")), Double.parseDouble(input.get("bookPrice")), Integer.parseInt(input.get("userid")));
				if (result) {
					logger.info("book reduced successfully");
					return response.generateResponse("book reduced successfully", HttpStatus.OK, true);
				} else {
					logger.info("book not added to cart");
					return response.generateResponse("book not reduced", HttpStatus.NOT_FOUND, null);
				}
			}
		 catch (Exception e) {
			logger.error(e);
			return response.generateResponse(e.toString(), HttpStatus.NOT_FOUND, null);
		}
	}

}
