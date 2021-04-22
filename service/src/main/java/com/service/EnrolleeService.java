package com.service;

import java.util.List;

import com.vo.AppResponse;
import com.vo.DependentVO;
import com.vo.EnrolleeVO;

public interface EnrolleeService {

	List<EnrolleeVO> getAllEnrollees();

	EnrolleeVO getEnrollee(int enrolleeID);

	AppResponse addEnrollee(EnrolleeVO enrolleeVO);

	AppResponse updateEnrollee(EnrolleeVO enrolleeVO);

	AppResponse deleteEnrollee(int enrolleeID);

	AppResponse addDependent(EnrolleeVO enrolleeVO);

}
