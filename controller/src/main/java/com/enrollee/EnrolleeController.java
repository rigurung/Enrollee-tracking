package com.enrollee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.service.EnrolleeServiceImpl;
import com.vo.AppResponse;
import com.vo.DependentVO;
import com.vo.EnrolleeVO;

@RestController
@RequestMapping(value = "/customers/enrollee")
@CrossOrigin("*")
public class EnrolleeController {
	
	@Autowired
	private EnrolleeServiceImpl enrolleeService;

	@GetMapping("/all")
	public List<EnrolleeVO> getEnrollees() {
		List<EnrolleeVO> enrolleeVOs = enrolleeService.getAllEnrollees();
		return enrolleeVOs;
	}
	
	@GetMapping("/info")
	public EnrolleeVO getEnrollee(@RequestParam(name = "id") int enrolleeID) {
		EnrolleeVO enrolleeVO = enrolleeService.getEnrollee(enrolleeID);
		return enrolleeVO;
	}
	
	@GetMapping("/{enrolleeID}")
	public EnrolleeVO getEnrolleePath(@PathVariable int enrolleeID) {
		EnrolleeVO enrolleeVO = enrolleeService.getEnrollee(enrolleeID);
		return enrolleeVO;
	}
	
	@PostMapping("/add")
	public AppResponse addEnrollee(@RequestBody EnrolleeVO enrolleeVO) {
		AppResponse appResponse = enrolleeService.addEnrollee(enrolleeVO);
		return appResponse; 
	}
	
	@PutMapping("/update")
	public AppResponse updateEnrollee(@RequestBody EnrolleeVO enrolleeVO) {
		AppResponse appResponse = enrolleeService.updateEnrollee(enrolleeVO);
		return appResponse; 
	}

	@DeleteMapping("/delete/{enrolleeID}")
	public AppResponse deleteEnrollee(@PathVariable int enrolleeID) {
		AppResponse appResponse = enrolleeService.deleteEnrollee(enrolleeID);
		return appResponse; 
	}
	@PostMapping("/dependents/add")
	public AppResponse addDependent(@RequestBody EnrolleeVO enrolleeVO) {
		AppResponse appResponse = enrolleeService.addDependent(enrolleeVO);
		return appResponse;
	}
	
}
