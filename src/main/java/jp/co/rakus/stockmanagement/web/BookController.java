package jp.co.rakus.stockmanagement.web;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

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
import org.springframework.web.multipart.MultipartFile;

import jp.co.rakus.stockmanagement.domain.Book;
import jp.co.rakus.stockmanagement.service.BookService;

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

	@Autowired
	private ServletContext context;

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
		return "book/bookForm";
	}

	/**
	 * 新規書籍登録を行います.
	 * 
	 * @param bookForm フォーム
	 * @param result   リザルト情報
	 * @param model    モデル
	 * @return 書籍リスト画面
	 */
	@RequestMapping(value = "upload")
	public String upload(@Validated BookForm bookForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return toUpdateForm();
		}
		// 書籍情報登録部分 idは連番なので次の値を入れる。
		Book insertBook = new Book();
		BeanUtils.copyProperties(bookForm, insertBook);
		int presentMaxId = bookService.findMaxId();
		int newMaxId = presentMaxId + 1;
		insertBook.setId(newMaxId);

		// 画像アップロード部分
		if (bookForm.getImage() != null) {
			MultipartFile file = bookForm.getImage();
			String fileName = file.getOriginalFilename();
			String path = context.getRealPath("/img/" + fileName);
			try {
				file.transferTo(new File(path));
				insertBook.setImage(fileName);
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			String saleDate = bookForm.getSaledate();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
			Date conversionSaleDate = dateFormat.parse(saleDate);
			insertBook.setSaledate(conversionSaleDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		bookService.insert(insertBook);

		return list(model);
	}

	/**
	 * 書籍の在庫更新を行います.
	 * 
	 * @param form   フォーム
	 * @param result リザルト情報
	 * @param model  モデル
	 * @return 書籍リスト画面
	 */
	@RequestMapping(value = "update")
	public String update(@Validated BookForm form, BindingResult result, Model model) {
		Book book = bookService.findOne(form.getId());
		// 在庫更新部分
		if (result.hasErrors()) {
			return show(form.getId(), model);
		}
		book.setStock(form.getStock());
		bookService.update(book);

		return list(model);
	}

}
