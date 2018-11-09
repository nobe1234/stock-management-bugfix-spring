package jp.co.rakus.stockmanagement.web;

import javax.validation.constraints.NotNull;

/**
 * 書籍在庫更新用のフォーム.
 * 
 * @author soheinobe
 *
 */
public class BookStockForm {

	/** id */
	private Integer id;
	/** 在庫 */
//	@Size(min = 0)
	@NotNull(message = "在庫を入力してください")
	private Integer stock;

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

}
