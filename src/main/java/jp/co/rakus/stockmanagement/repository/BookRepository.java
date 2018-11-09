package jp.co.rakus.stockmanagement.repository;

import java.util.Date;
import java.util.List;

import jp.co.rakus.stockmanagement.domain.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * booksテーブル操作用のリポジトリクラス.
 * 
 * @author igamasayuki
 */
@Repository
public class BookRepository {
	/**
	 * ResultSetオブジェクトからBookオブジェクトに変換するためのクラス実装&インスタンス化
	 */
	private static final RowMapper<Book> BOOK_ROW_MAPPER = (rs, i) -> {
		Integer id = rs.getInt("id");
		String name = rs.getString("name");
		String author = rs.getString("author");
		String publisher = rs.getString("publisher");
		Integer price = rs.getInt("price");
		String isbncode = rs.getString("isbncode");
		Date saledate = rs.getDate("saledate");
		String explanation = rs.getString("explanation");
		String image = rs.getString("image");
		Integer stock = rs.getInt("stock");
		return new Book(id, name, author, publisher, price, isbncode, saledate, explanation, image, stock);
	};

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<Book> findAll() {
		List<Book> books = jdbcTemplate.query(
				"SELECT id,name,author,publisher,price,isbncode,saledate,explanation,image,stock FROM books order by price",
				BOOK_ROW_MAPPER);
		return books;
	}

	// TODO:現在連番で取られているidの最大を返す。＋１したい。
	public Integer findMaxId() {
		SqlParameterSource param = new MapSqlParameterSource();
		Integer id = jdbcTemplate.queryForObject("SELECT max(id) FROM books", param, Integer.class);
		return id;
	}

	public Book findOne(Integer id) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		List<Book> bookList = jdbcTemplate.query(
				"SELECT id,name,author,publisher,price,isbncode,saledate,explanation,image,stock FROM books WHERE id=:id",
				param, BOOK_ROW_MAPPER);
		if (bookList.size() == 0) {
			return null;
		} else {
			for (Book book : bookList) {
				return book;
			}

		}
		return null;
	}

	public Book update(Book book) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(book);
		if (book.getId() == null) {
			throw new NullPointerException();
		}
		jdbcTemplate.update("UPDATE books SET stock=:stock WHERE id=:id", param);

		return book;
	}

	public Book insert(Book book) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(book);
		jdbcTemplate.update(
				"INSERT INTO BOOKS (id,name,author,publisher,price,isbncode,saledate,explanation,image,stock) "
						+ "VALUES(:id,:name,:author,:publisher,:price,:isbncode,:saledate,:explanation,:image,:stock)",
				param);
		return book;
	}
}
