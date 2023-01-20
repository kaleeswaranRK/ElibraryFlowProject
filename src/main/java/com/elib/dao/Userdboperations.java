package com.elib.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elib.model.BookCart;
import com.elib.model.BookProduct;
import com.elib.model.Category;
import com.elib.util.DataSourceProperty;

@Service
public class Userdboperations {
	@Autowired
	DataSourceProperty datasource;
	Logger logger = LogManager.getLogger("ElibraryFlowProject");

	public ResultSet getUserId(int userid) {
		try {
			logger.info("userDetail Check");
			Connection connection = datasource.getDBConnection();
			System.out.println("DB connection");
			PreparedStatement prepareStatement = connection.prepareStatement("EXEC KALEESWARAN_GET_PASSWORD @id =?");
			prepareStatement.setInt(1, userid);
			ResultSet resultSet = prepareStatement.executeQuery();
			logger.info("query executed");
			return resultSet;
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public String getPassword(int userid) {
		try {
			logger.info("get Password method Entry");
			Connection connection = datasource.getDBConnection();
			System.out.println("DB connection");
			PreparedStatement prepareStatement;
			prepareStatement = connection.prepareStatement("EXEC KALEESWARAN_GET_PASSWORD @id =?");
			prepareStatement.setInt(1, userid);
			ResultSet resultSet = prepareStatement.executeQuery();
			logger.info("query executed");
			if (resultSet.next()) {
				return resultSet.getString("CUSTOMER_PASSWORD");
			} else {
				return null;
			}
		} catch (SQLException e) {
			logger.error(e);
			return null;
		} catch (Exception e) {
			logger.error(e);
			return null;
		}

	}

	public ResultSet checkUserId(int userid) {

		try {
			System.out.println("userId Check");
			Connection connection = datasource.getDBConnection();
			System.out.println("DB connection");
			PreparedStatement prepareStatement;
			prepareStatement = connection.prepareStatement("EXEC KALEESWARAN_VERIFY_ID @id=?");
			prepareStatement.setInt(1, userid);
			ResultSet resultSet = prepareStatement.executeQuery();
			return resultSet;
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public int InsertUserDetail(int userid, String password) {

		try {
			System.out.println("userDetail Insert");
			Connection connection = datasource.getDBConnection();
			System.out.println("DB connection");
			PreparedStatement prepareStatement = connection
					.prepareStatement("EXEC KALEESWARAN_ADD_USER @id =?,@pass=?");
			prepareStatement.setInt(1, userid);
			prepareStatement.setString(2, password);
			int insertCount = prepareStatement.executeUpdate();
			return insertCount;
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return 0;

	}

	public List<Category> getCategories() {
		try {
			List<Category> list = new ArrayList<Category>();
			Connection connection = datasource.getDBConnection();
			System.out.println("DB connection");
			PreparedStatement prepareStatement = connection.prepareStatement("EXEC KALEESWARAN_GET_CATEGORIES");
			ResultSet result = prepareStatement.executeQuery();
			while (result.next()) {
				Category category = new Category(result.getInt("CATEGORY_ID"), result.getString("CATEGORY_NAME"));
				list.add(category);
			}
			return list;
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public List<BookProduct> getBooksbyCategory(int categoryid) {
		try {
			List<BookProduct> list = new ArrayList<BookProduct>();
			Connection connection = datasource.getDBConnection();
			System.out.println("DB connection");
			PreparedStatement prepareStatement = connection
					.prepareStatement("EXEC KALEESWARAN_GET_BOOKS_BY_CATEGORY @id=?");
			prepareStatement.setInt(1, categoryid);
			ResultSet result = prepareStatement.executeQuery();
			while (result.next()) {
				BookProduct book = new BookProduct(result.getInt("BOOK_ID"), result.getString("BOOK_NAME"),
						result.getString("AUTHOR_NAME"), result.getInt("BOOK_QUANTITY"),
						result.getDouble("BOOK_PRICE"));
				list.add(book);
			}
			return list;
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public List<BookCart> getCartItems(int userid) {
		try {
			List<BookCart> cartItems = new ArrayList<BookCart>();
			Connection connection = datasource.getDBConnection();
			System.out.println("DB connection");
			PreparedStatement prepareStatement = connection.prepareStatement("EXEC KALEESWARAN_GET_CART_ITEMS @id=?");
			prepareStatement.setInt(1, userid);
			ResultSet result = prepareStatement.executeQuery();
			while (result.next()) {
				String bookName = result.getString("BOOK_NAME");
				System.out.println("BookName = " + bookName);
				BookProduct book = getBookByName(bookName);
				System.out.println(book.toString());
				BookCart cartItem = new BookCart(result.getInt("BOOK_CART_ID"), book, result.getInt("BOOK_QUANTITY"),
						result.getDouble("BOOK_PRICE"));
				cartItems.add(cartItem);
			}
			return cartItems;
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;
	}

	public ResultSet checkCartItem(int userid, String bookName) {

		try {
			Connection connection = datasource.getDBConnection();
			System.out.println("DB connection");
			PreparedStatement prepareStatement;
			prepareStatement = connection.prepareStatement("EXEC KALEESWARAN_CHECK_CART @id=?,@book=?");
			prepareStatement.setInt(1, userid);
			prepareStatement.setString(2, bookName);
			ResultSet result = prepareStatement.executeQuery();
			if (result.next()) {
				return result;
			} else {
				return null;
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;

	}

	public BookProduct getBookByName(String bookName) {
		try {
			BookProduct book = null;
			Connection connection = datasource.getDBConnection();
			System.out.println("DB connection");
			PreparedStatement prepareStatement = connection
					.prepareStatement("EXEC KALEESWARAN_GET_BOOK_BY_NAME @name=?");
			prepareStatement.setString(1, bookName);
			ResultSet result = prepareStatement.executeQuery();
			if (result.next()) {
				book = new BookProduct(result.getInt("BOOK_ID"), result.getString("BOOK_NAME"),
						result.getString("AUTHOR_NAME"), result.getInt("BOOK_QUANTITY"),
						result.getDouble("BOOK_PRICE"));
				return book;
			} else {
				System.out.println("NO DATA");
				return null;
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return null;

	}

	public boolean addToCart(String bookName, int bookQuantity, double bookPrice, int userid) {
		try {
			Connection connection = datasource.getDBConnection();
			System.out.println("DB connection");
			PreparedStatement prepareStatement = connection
					.prepareStatement("EXEC KALEESWARAN_ADD_TO_CART @name=? ,@quantity=? ,@price=? ,@id=?");
			prepareStatement.setString(1, bookName);
			prepareStatement.setInt(2, bookQuantity);
			prepareStatement.setDouble(3, bookPrice);
			prepareStatement.setInt(4, userid);
			if (prepareStatement.executeUpdate() < 1) {
				return false;
			} else {
				return true;
			}

		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return false;
	}

	public boolean cartRemove(String bookname, int userid) {
		try {
			Connection connection = datasource.getDBConnection();
			System.out.println("DB connection");
			PreparedStatement prepareStatement = connection
					.prepareStatement("EXEC KALEESWARAN_DELETE_PRODUCT @name=?,@id=?");
			prepareStatement.setString(1, bookname);
			prepareStatement.setInt(2, userid);
			if (prepareStatement.executeUpdate() < 1) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return false;

	}

	public boolean cartAddCount(String bookName, int Quantity, double Price, int userid) {
		try {
			ResultSet result = checkCartItem(userid, bookName);
			if (result == null) {
				return addToCart(bookName, Quantity, Price, userid);
			} else {
				int bookCount = result.getInt("BOOK_QUANTITY") + Quantity;
				BookProduct bookByName = getBookByName(bookName);
				if (bookCount > bookByName.getBookQuantity()) {
					return false;
				} else {
					double bookPrice = bookCount * bookByName.getBookPrice();
					Connection connection = datasource.getDBConnection();
					System.out.println("DB connection");
					PreparedStatement prepareStatement = connection
							.prepareStatement("EXEC KALEESWARAN_MODIFY_CART @name=?,@id=?,@quantity=?,@price=?");
					prepareStatement.setString(1, bookName);
					prepareStatement.setInt(2, userid);
					prepareStatement.setInt(3, bookCount);
					prepareStatement.setDouble(4, bookPrice);
					return true;
				}

			}
		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return false;

	}

	public boolean CartReduce(String bookName, int Quantity, double Price, int userid) {
		try {
			ResultSet result = checkCartItem(userid, bookName);
			int bookCount = result.getInt("BOOK_QUANTITY");
			BookProduct bookByName = getBookByName(bookName);
			if (bookCount - Quantity == 0) {
				return cartRemove(bookName, userid);
			} 
			else if (bookCount - Quantity<0) {
				return false;
			}
			else {
				double bookPrice = (bookCount-Quantity) * bookByName.getBookPrice();
				Connection connection = datasource.getDBConnection();
				System.out.println("DB connection");
				PreparedStatement prepareStatement = connection
						.prepareStatement("EXEC KALEESWARAN_MODIFY_CART @name=?,@id=?,@quantity=?,@price=?");
				prepareStatement.setString(1, bookName);
				prepareStatement.setInt(2, userid);
				prepareStatement.setInt(3, (bookCount-Quantity));
				prepareStatement.setDouble(4, bookPrice);
				return true;
			}

		} catch (SQLException e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
		}
		return false;

	}

}
