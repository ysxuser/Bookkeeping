package com.ysx.springboot.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="b_consume_detail")
public class BConsumeDetail {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="id")
private int id;
@Column(name="userID")
private int userID;
@Column(name="groupID")
private int groupID;
@Column(name="total")
private BigDecimal total;
@Column(name="remark")
private String remark; 
@Column(name="createtime")
private String createtime;

public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getUserID() {
	return userID;
}
public void setUserID(int userID) {
	this.userID = userID;
}
public int getGroupID() {
	return groupID;
}
public void setGroupID(int groupID) {
	this.groupID = groupID;
}
public BigDecimal getTotal() {
	return total;
}
public void setTotal(BigDecimal total) {
	this.total = total;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public String getCreatetime() {
	return createtime;
}
public void setCreatetime(String createtime) {
	this.createtime = createtime;
} 

}
