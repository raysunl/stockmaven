/**
 * 
 */
package com.sunstock.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体类父类
 * 
 * @author wangjianjun
 */
/**
 * @author wangjianjun
 *
 */
@SuppressWarnings("serial")
public class BaseEntity implements Serializable{

	/**
	 * 主键
	 */
	private String id;

	/**
	 * 创建人ID
	 */
	private String createUserId;

	/**
	 * 创建人姓名
	 */
	private String createUserName;
	/**
	 * 创建时间
	 */
	private Date createDate;
	/**
	 * 修改人ID
	 */
	private String updateUserId;
	/**
	 * 修改人姓名
	 */
	private String updateUserName;
	/**
	 * 修改时间
	 */
	private Date updateDate;

	/**
	 * 删除操作人ID
	 */
	private String deleteUserId;

	/**
	 * 删除时间
	 */
	private Date deleteDate;

	
	/**
	 * 可用
	 */
	public static Integer VALID = 1;
	/**
	 * 不可用
	 */
	public static Integer UNVALID = 0;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getDeleteUserId() {
		return deleteUserId;
	}

	public void setDeleteUserId(String deleteUserId) {
		this.deleteUserId = deleteUserId;
	}

	public Date getDeleteDate() {
		return deleteDate;
	}

	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}

}
