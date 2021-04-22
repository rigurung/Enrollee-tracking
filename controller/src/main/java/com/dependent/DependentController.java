package com.dependent;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.DependentService;
import com.vo.AppResponse;
import com.vo.DependentVO;
import com.vo.EnrolleeVO;

@RestController
@RequestMapping("customers/enrollee/dependents")
public class DependentController {

	@Autowired
	DependentService dependentService ;
	
//	@PostMapping("/add")
//	public AppResponse addDependent(@RequestBody DependentVO dependentVO) {
//		AppResponse appResponse = dependentService.addDependent(dependentVO);
//		return appResponse;
//	}
	
	@GetMapping("/get/{dependentID}")
	public DependentVO getDependent(@PathVariable int dependentID) {
		DependentVO dependetVo = dependentService.getDependent(dependentID);
		return dependetVo;
	}
}
