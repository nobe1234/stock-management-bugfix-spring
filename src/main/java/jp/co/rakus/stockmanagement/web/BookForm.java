package jp.co.rakus.stockmanagement.web;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

/**
 * 書籍関連のリクエストパラメータが入るフォーム.
 * 
 * @author igamasayuki
 *
 */
public class BookForm {

	// 文字列はブランクへ変更
	// TODO:
	/** id */
	private Integer id;
	/** 在庫 */
//	@Size(min = 0)
	@NotNull(message = "在庫を入力してください")
	private Integer stock;
	/** 著者名 */
	@NotBlank(message = "著者名を入力してください")
	private String name;
	/** 著者 */
	@NotBlank(message = "著者を入力してください")
	private String author;
	/** 出版社 */
	@NotBlank(message = "出版社を入力してください")
	private String publisher;
	/** 価格 */
//	@Size(min = 0)
	@NotNull(message = "価格を入力してください")
	private Integer price;
	/** ISBNコード */
	@NotBlank(message = "ISBNコードを入力してください")
	private String isbncode;
	/** 発売日 */
	@NotBlank(message = "発売日を入力してください")
	private String saledate;
	/** 説明 */
	@NotBlank(message = "説明を入力してください")
	private String explanation;
	/** 画像 */
	private MultipartFile image;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public String getIsbncode() {
		return isbncode;
	}

	public void setIsbncode(String isbncode) {
		this.isbncode = isbncode;
	}

	public String getSaledate() {
		return saledate;
	}

	public void setSaledate(String saledate) {
		this.saledate = saledate;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

}
