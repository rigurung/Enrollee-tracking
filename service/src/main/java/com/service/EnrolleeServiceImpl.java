package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
@Transactional
public class EnrolleeServiceImpl implements EnrolleeService {

	@Autowired
	private EnrolleeRepository enrolleeRepository;
	@Autowired
	private DependentRespository dependentRepository;

	@Override
	public List<EnrolleeVO> getAllEnrollees() {
		List<Enrollee> enrolleeEntities = enrolleeRepository.findAll();
		List<EnrolleeVO> enrolleeVOs = new ArrayList<EnrolleeVO>();

		for (Enrollee enrollee : enrolleeEntities) {
			EnrolleeVO enrolleeVO = new EnrolleeVO();
			BeanUtils.copyProperties(enrollee, enrolleeVO);
			enrolleeVOs.add(enrolleeVO);
		}
		return enrolleeVOs;
	}

	@Override
	public EnrolleeVO getEnrollee(int enrolleeID) {
		EnrolleeVO enrolleeVO = new EnrolleeVO();
		Optional<Enrollee> enrollee = enrolleeRepository.findById(enrolleeID);
		if (enrollee.isPresent()) {
			BeanUtils.copyProperties(enrollee.get(), enrolleeVO);
		}
		return enrolleeVO;
	}

	@Override
	public AppResponse addEnrollee(EnrolleeVO enrolleeVO) {
		Enrollee enrollee = new Enrollee();
		BeanUtils.copyProperties(enrolleeVO, enrollee);
		Enrollee addedEnrollee = enrolleeRepository.save(enrollee);
		AppResponse appResponse = new AppResponse();
		if (addedEnrollee != null) {
			appResponse.setCode(200);
			appResponse.setMessage("Enrollee has been added successfully!!");
		} else {
			appResponse.setCode(400);
			appResponse.setMessage("Enrollee was not added!!");
		}
		return appResponse;
	}

	@Override
	public AppResponse updateEnrollee(EnrolleeVO enrolleeVO) {
		Optional<Enrollee> enrolleeOptional = enrolleeRepository.findById(enrolleeVO.getEnrolleeID());
		AppResponse appResponse = new AppResponse();
		if (enrolleeOptional.isPresent()) {
			Enrollee enrollee = enrolleeOptional.get();
			BeanUtils.copyProperties(enrolleeVO, enrollee);
			enrolleeRepository.save(enrollee);
			appResponse.setCode(200);
			appResponse.setMessage("Enrollee has been updated successfully!!");
	
		}else {
			appResponse.setCode(400);
			appResponse.setMessage("Enrollee was not updated/not found!!");
		}
		return appResponse;
	}

	@Override
	public AppResponse deleteEnrollee(int enrolleeID) {
		AppResponse appResponse = new AppResponse();
		try {
//			enrolleeRepository.findById(enrolleeID).ifPresent(Example -> enrolleeRepository.deleteById(enrolleeID));
			enrolleeRepository.deleteById(enrolleeID);
			appResponse.setCode(200);
			appResponse.setMessage("Enrollee has been deleted successfully!!");
			return appResponse;
		} catch (Exception e) {
			appResponse.setCode(400);
			appResponse.setMessage("Error in deleting enrollee!!");
			return appResponse;
		}
	}

	@Override
	public AppResponse addDependent(EnrolleeVO enrolleeVO) {
		Optional<Enrollee> enrolleeOp = enrolleeRepository.findById(enrolleeVO.getEnrolleeID());
		List<Dependent> dependents = new ArrayList<Dependent>();
		for (DependentVO vos : enrolleeVO.getDependentsVO()) {
			Dependent dependent = new Dependent();
			BeanUtils.copyProperties(vos, dependent);
			dependents.add(dependent);
		}
		AppResponse appResponse = new AppResponse();
		try {
			if (enrolleeOp.isPresent()) {
				dependents.forEach(x -> x.setEnrollee(enrolleeOp.get()));
				dependentRepository.saveAll(dependents);
			} else {
				Enrollee enrollee = new Enrollee();
				BeanUtils.copyProperties(enrolleeVO, enrollee);
				dependents.forEach(x -> x.setEnrollee(enrollee));
				dependentRepository.saveAll(dependents);
			}
			appResponse.setCode(200);
			appResponse.setMessage("Dependent has been added successfully!!");
			return appResponse;

		} catch (Exception e) {
			appResponse.setCode(400);
			appResponse.setMessage("Error in adding dependent.");
			return appResponse;
		}
	}
}
