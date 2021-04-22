package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dependent.Dependent;
import com.dependent.DependentRespository;
import com.enrollee.Enrollee;
import com.enrollee.EnrolleeRepository;
import com.vo.AppResponse;
import com.vo.DependentVO;
import com.vo.EnrolleeVO;

@Service
public class DependentServiceImpl implements DependentService{
	@Autowired
	EnrolleeRepository enrolleeRepository;
	@Autowired
	DependentRespository dependentRespository;

	
	@Override
	public AppResponse addDependent(DependentVO dependentVO) {
		Dependent dependent = new Dependent();
		BeanUtils.copyProperties(dependentVO, dependent);
		Optional<Enrollee> enrolleeOptional = enrolleeRepository.findById(dependentVO.getEnrolleeVO().getEnrolleeID());

		if(enrolleeOptional.isPresent()) {
			enrolleeOptional.get().setDependents(List.of(dependent));
		} else {
			enrolleeRepository.save(dependent.getEnrollee());
		}
		Optional<Dependent> dependentCheck = dependentRespository.findById(dependent.getDependentID());
		AppResponse appResponse = new AppResponse(); 
		if (dependentCheck.isPresent()) {
			appResponse.setCode(200);
			appResponse.setMessage("Enrollee has been updated successfully!!");
		} else {
			appResponse.setCode(400);
			appResponse.setMessage("Enrollee was not updated/not found!!");
		}
		return appResponse;
	}


	@Override
	public DependentVO getDependent(int dependentID) {
		Optional<Dependent> dependent = dependentRespository.findById(dependentID);
		DependentVO dependentVO = new DependentVO();
		BeanUtils.copyProperties(dependent.get(), dependentVO);
		EnrolleeVO enrolleeVO = new EnrolleeVO();
		BeanUtils.copyProperties(dependent.get().getEnrollee(), enrolleeVO);
		dependentVO.setEnrolleeVO(enrolleeVO);
		return dependentVO;
	}
}
