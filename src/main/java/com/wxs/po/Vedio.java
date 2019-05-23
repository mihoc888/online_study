package com.wxs.po;

/**
 * Vedio 实体类
 * @date 2019-05-22 15:26:03
 * @version 1.0
 */
public class Vedio implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Integer courseId;
	private String createTime;
	private String path;
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/** setter and getter method */
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void setCourseId(Integer courseId){
		this.courseId = courseId;
	}
	public Integer getCourseId(){
		return this.courseId;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}
	public String getCreateTime(){
		return this.createTime;
	}
	public void setPath(String path){
		this.path = path;
	}
	public String getPath(){
		return this.path;
	}


}