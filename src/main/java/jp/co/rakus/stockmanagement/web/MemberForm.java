package jp.co.rakus.stockmanagement.web;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * メンバー関連のリクエストパラメータが入るフォーム.
 * 
 * @author igamasayuki
 *
 */
public class MemberForm {
	/** 名前 */
	@NotBlank(message = "名前は必須です。")
	@Size(min = 4, max = 10, message = "名前は４文字以上、１０文字以下で設定してください。")
	private String name;
	/** メールアドレス */
	@NotBlank(message = "メールアドレスは必須です。")
	@Email(message = "メール形式が正しくありません。")
	@Size(min = 4, max = 30, message = "メールアドレスは４文字以上、30文字以下で設定してください。")
	private String mailAddress;
	/** パスワード */
	@NotBlank(message = "パスワードは必須です。")
	@Size(min = 4, max = 10, message = "パスワードは４文字以上、１０文字以下で設定してください。")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
