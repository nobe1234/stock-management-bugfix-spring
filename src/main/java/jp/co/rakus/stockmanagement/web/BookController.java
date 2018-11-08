package jp.co.rakus.stockmanagement.web;

import java.util.List;

import jp.co.rakus.stockmanagement.domain.Book;
import jp.co.rakus.stockmanagement.service.BookService;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 書籍関連処理を行うコントローラー.
 * 
 * @author igamasayuki
 *
 */
@Controller
@RequestMapping("/book")
@Transactional
public class BookController {

	@Autowired
	private BookService bookService;

	/**
	 * フォームを初期化します.
	 * 
	 * @return フォーム
	 */
	@ModelAttribute
	public BookForm setUpForm() {
		return new BookForm();
	}

	/**
	 * 書籍リスト情報を取得し書籍リスト画面を表示します.
	 * 
	 * @param model モデル
	 * @return 書籍リスト表示画面
	 */
	@RequestMapping(value = "list")
	public String list(Model model) {
		List<Book> bookList = bookService.findAll();

		model.addAttribute("bookList", bookList);
		return "book/list";
	}

	/**
	 * 書籍詳細情報を取得し書籍詳細画面を表示します.
	 * 
	 * @param id    書籍ID
	 * @param model モデル
	 * @return 書籍詳細画面
	 */
	@RequestMapping(value = "show/{bookId}")
	public String show(@PathVariable("bookId") Integer id, Model model) {
		Book book = bookService.findOne(id);
		model.addAttribute("book", book);
		return "book/show";
	}

	/**
	 * 書籍登録画面へ移行を行う.
	 * 
	 * @return 書籍登録画面
	 */
	@RequestMapping("/bookForm")
	public String toUpdateForm() {
		return "/book/bookForm";
	}

	/**
	 * 書籍更新、および書籍登録を行います.
	 * 
	 * @param bookForm   フォーム
	 * @param result リザルト情報
	 * @param model  モデル
	 * @return 書籍リスト画面
	 */
	@RequestMapping(value = "update")
	public String update(@Validated BookForm bookForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return show(bookForm.getId(), model);
		}
		Book book = new Book();
		//わからない
		if (bookForm.getId() == null) {
			BeanUtils.copyProperties(bookForm, book);
			bookService.update(book);
		} else {
			book = bookService.findOne(bookForm.getId());
			book.setStock(bookForm.getStock());
			bookService.update(book);
		}

		return list(model);
	}

}
