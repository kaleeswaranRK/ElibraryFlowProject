package com.elib.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elib.dao.Userdboperations;
import com.elib.model.BookCart;
import com.elib.util.ResponseHandler;

@RestController
@RequestMapping("/cart")
public class CartCotroller {
	@Autowired
	Userdboperations userdb;
	@Autowired
	ResponseHandler response;
	@GetMapping(value = "/cartview", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCartItems(@RequestParam int userid) {
		try {
			List<BookCart> books = userdb.getCartItems(userid);
			return response.generateResponse("Cart items fetched successfully", HttpStatus.OK, books);
		} catch (Exception e) {
			return response.generateResponse(e.toString(), HttpStatus.NOT_FOUND, null);

		}

	}

	@PostMapping(value = "/addtocart", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> addToCart(@RequestParam Map<String, String> input) {
		try {
			String bookname = input.get("bookname");
			int bookQuantity = Integer.parseInt(input.get("bookQuantity"));
			double bookPrice = Double.parseDouble(input.get("bookPrice"));
			int userid = Integer.parseInt(input.get("userid"));
			int insertCount = userdb.addToCart(bookname, bookQuantity, bookPrice, userid);
			if (insertCount < 1) {
				System.out.println("book not added to cart");
				return response.generateResponse("book not added to cart", HttpStatus.NOT_FOUND, null);

			} else {
				System.out.println("book added successfully");
				return response.generateResponse("book added successfully", HttpStatus.OK, insertCount);

			}
		} catch (Exception e) {
			return response.generateResponse(e.toString(), HttpStatus.NOT_FOUND, null);

		}

	}

	@PostMapping(value = "/updatecart", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateCart(@RequestParam Map<String, String> input) {
		try {
			String bookname = input.get("bookname");
			int insertCount = userdb.updateCart(bookname);
			if (insertCount < 1) {
				System.out.println("book not remove from cart");
				return response.generateResponse("book not remove from cart", HttpStatus.NOT_FOUND, null);
			} else {
				System.out.println("book removed successfully");
				return response.generateResponse("book removed successfully", HttpStatus.OK, insertCount);

			}
		} catch (Exception e) {
			return response.generateResponse(e.toString(), HttpStatus.NOT_FOUND, null);
		}
	}

}
