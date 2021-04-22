package com.vo;

import java.util.Date;

import com.vo.EnrolleeVO.Gender;

public class DependentVO {
	private int dependentID;
	private String name;
	private Date birthDate;
	private Gender gender;
	private EnrolleeVO enrolleeVO;
	
	public DependentVO() {
		
	}
	
	public DependentVO(int dependentID, String name, Date birthDate, Gender gender, EnrolleeVO enrolleeVO) {
		super();
		this.dependentID = dependentID;
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.enrolleeVO = enrolleeVO;
	}
	public int getDependentID() {
		return dependentID;
	}
	public void setDependentID(int dependentID) {
		this.dependentID = dependentID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public EnrolleeVO getEnrolleeVO() {
		return enrolleeVO;
	}
	public void setEnrolleeVO(EnrolleeVO enrolleeVO) {
		this.enrolleeVO = enrolleeVO;
	}
	
	
	
}
