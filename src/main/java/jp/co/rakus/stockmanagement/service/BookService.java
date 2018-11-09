package jp.co.rakus.stockmanagement.service;

import java.util.List;

import jp.co.rakus.stockmanagement.domain.Book;
import jp.co.rakus.stockmanagement.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 書籍関連サービスクラス.
 * 
 * @author igamasayuki
 *
 */
@Service
public class BookService {

	@Autowired
	BookRepository bookRepository;

	public Integer findMaxId() {
		return bookRepository.findMaxId();
	}

	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public Book findOne(Integer id) {
		return bookRepository.findOne(id);
	}

//	public Book save(Book book){
//		return bookRepository.save(book);
//	}

	public Book update(Book book) {
		return bookRepository.update(book);
	}

	/**
	 * 書籍情報追加用のメソッド.
	 * 
	 * @param book
	 * @return 追加用のメソッド。
	 */
	public Book insert(Book book) {
		return bookRepository.insert(book);
	}

//	public void delete(Integer id){
//		bookRepository.delete(id);
//	}
}
