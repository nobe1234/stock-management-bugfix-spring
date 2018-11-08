package jp.co.rakus.stockmanagement.repository;

import jp.co.rakus.stockmanagement.domain.Member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

/**
 * membersテーブル操作用のリポジトリクラス.
 * 
 * @author igamasayuki
 */
@Repository
public class MemberRepository {

	/**
	 * ResultSetオブジェクトからMemberオブジェクトに変換するためのクラス実装&インスタンス化
	 */
	private static final RowMapper<Member> MEMBER_ROW_MAPPER = (rs, i) -> {
		Integer id = rs.getInt("id");
		String name = rs.getString("name");
		String mailAddress = rs.getString("mail_address");
		String password = rs.getString("password");
		return new Member(id, name, mailAddress, password);
	};

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * メールアドレスとパスワードからメンバーを取得.
	 * 
	 * @param mailAddress メールアドレス
	 * @param password    パスワード
	 * @return メンバー情報.メンバーが存在しない場合はnull.
	 */
	public Member findByMailAddressAndPassword(String mailAddress, String password) {
		String sql = "SELECT id,name,mail_address,password FROM members "
				+ "WHERE mail_address= :mailAddress and password= :password ";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password",
				password);
		// 非検査例外のため、try-catchを外してもいいが、その場合はqueryを使って返したリストを
		// コントローラー側で取り出して行を行う必要がある。 queryforobjectは必ず１件とる。
		try {
			Member member = jdbcTemplate.queryForObject(sql, param, MEMBER_ROW_MAPPER);
			return member;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * メールアドレスで検索を行うメソッド.
	 * 
	 * メールアドレスの重複チェック用。
	 * 
	 * @param mailAddress
	 * @return
	 */
	public Member findByMailAddress(String mailAddress) {
		String sql = "SELECT id,name,mail_address,password FROM members WHERE mail_address = :mailAddress";

		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress);
		Member member = jdbcTemplate.queryForObject(sql, param, MEMBER_ROW_MAPPER);
		return member;
	}

	/**
	 * メンバー情報を保存 または 更新する.
	 * 
	 * @param member 保存または更新するメンバー情報
	 * @return 保存または更新されたメンバー情報
	 */
	public Member save(Member member) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(member);
		if (member.getId() == null) {
			jdbcTemplate.update("INSERT INTO members(name,mail_address,password) values(:name,:mailAddress,:password)",
					param);
		} else {
			jdbcTemplate.update(
					"UPDATE members SET name=:name,mail_address=:mailAddress,password=:password WHERE id=:id", param);
		}
		return member;
	}

}
