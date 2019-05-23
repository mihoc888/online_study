package com.wxs.po;

/**
 * Comments 实体类
 * @date 2019-05-22 15:26:03
 * @version 1.0
 */
public class Comments implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	private Integer id;
	private Integer pid;
	private String text;
	private String createTime;
	private Integer courseId;
	private String userId;

	/** setter and getter method */
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setPid(Integer pid){
		this.pid = pid;
	}
	public Integer getPid(){
		return this.pid;
	}
	public void setText(String text){
		this.text = text;
	}
	public String getText(){
		return this.text;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	public void setCourseId(Integer courseId){
		this.courseId = courseId;
	}
	public Integer getCourseId(){
		return this.courseId;
	}

}