package com.wt.sys.domain;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;

import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wt
 * @since 2018-08-10
 */
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

	private Long id;
    /**
     * 账号
     */
	@TableField("account_number")
	private String accountNumber;
    /**
     * 密码
     */
	@TableField("pass_word")
	private String passWord;
    /**
     * 姓名
     */
	@TableField("user_name")
	private String userName;
    /**
     * 性别 0：男   1：女   3：其他
     */
	private Integer sex;
    /**
     * 年龄
     */
	private Integer age;
    /**
     * 电话
     */
	private String tel;
	/**
	 * 员工工号
	 */
	@TableField("work_no")
	private String workNo;
	/**
	 * 密码盐值
	 */
	private String salt;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", accountNumber=" + accountNumber +
			", passWord=" + passWord +
			", userName=" + userName +
			", sex=" + sex +
			", age=" + age +
			", tel=" + tel +
			", workNo=" + workNo +
			", salt=" + salt +
			"}";
	}
}
