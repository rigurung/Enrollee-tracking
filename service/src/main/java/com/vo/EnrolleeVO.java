package com.vo;

import java.util.Date;
import java.util.List;


public class EnrolleeVO {
	private int enrolleeID;
	private String name;
	private Boolean activationStatus;
	private Date birthDate;
	private Gender gender;
	private List<DependentVO> dependentsVO;

	public EnrolleeVO() {

	}

	public EnrolleeVO(int enrolleeID, String name, Boolean activationStatus, Date birthDate, Gender gender,
			List<DependentVO> dependentsVO) {
		super();
		this.enrolleeID = enrolleeID;
		this.name = name;
		this.activationStatus = activationStatus;
		this.birthDate = birthDate;
		this.gender = gender;
		this.dependentsVO = dependentsVO;
	}

	public int getEnrolleeID() {
		return enrolleeID;
	}

	public void setEnrolleeID(int enrolleeID) {
		this.enrolleeID = enrolleeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(Boolean activationStatus) {
		this.activationStatus = activationStatus;
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

	public List<DependentVO> getDependentsVO() {
		return dependentsVO;
	}

	public void setDependentsVO(List<DependentVO> dependentsVO) {
		this.dependentsVO = dependentsVO;
	}

	public enum Gender {
		MALE, 
		FEMALE
	}
}
