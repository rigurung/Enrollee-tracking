package com.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.dependent.DependentRespository;
import com.enrollee.Enrollee;
import com.enrollee.EnrolleeRepository;
import com.vo.AppResponse;
import com.vo.DependentVO;
import com.vo.EnrolleeVO;

@RunWith(JUnit4.class)
public class EnrolleeServiceImplTest {
	@Mock
	EnrolleeRepository enrolleeRepo;
	@Mock
	DependentRespository dependentRepo;
	@InjectMocks
	EnrolleeServiceImpl enrolleService;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllEnrollees() {
		Enrollee enrolle = new Enrollee();
		enrolle.setName("test name");
		// mock the behavior of any other class or interface methods
		Mockito.when(enrolleeRepo.findAll()).thenReturn(List.of(enrolle));
		// call actual method of test class when mocking is done
		List<EnrolleeVO> enrolleeVOs = enrolleService.getAllEnrollees();
		// assert:
		assertEquals(1, enrolleeVOs.size());
		assertEquals(enrolle.getName(), enrolleeVOs.get(0).getName());
	}
	
	@Test
	public void testGetEnrollee() {
		Enrollee enrollee = new Enrollee();
		enrollee.setName("test name 2");
		Mockito.when(enrolleeRepo.findById(1)).thenReturn(Optional.of(enrollee));
		EnrolleeVO rEnrolleeVO = enrolleService.getEnrollee(1);
		assertEquals(enrollee.getName(), rEnrolleeVO.getName());
		assertEquals(enrollee.getEnrolleeID(), rEnrolleeVO.getEnrolleeID());
	}
	
	@Test
	public void testAddEnrollee() {
		Enrollee enrollee = new Enrollee();
		Mockito.when(enrolleeRepo.save(any())). thenReturn(enrollee);
		AppResponse appResponse = enrolleService.addEnrollee(new EnrolleeVO());
		assertEquals(200, appResponse.getCode());
		assertEquals("Enrollee has been added successfully!!", appResponse.getMessage());
	}
	
	@Test
	public void testAddEnrollee_else() {
		Mockito.when(enrolleeRepo.save(any())).thenReturn(null);
		AppResponse appResponse = enrolleService.addEnrollee(new EnrolleeVO());
		assertEquals(400, appResponse.getCode());
		assertEquals("Enrollee was not added!!", appResponse.getMessage());
	}
	
	@Test
	public void testUpdateEnrollee() {
		Enrollee enrollee = new Enrollee();
		enrollee.setName("Rojina");
		
		EnrolleeVO enrolleeVO = new EnrolleeVO();
		enrolleeVO.setEnrolleeID(1);
		
		Mockito.when(enrolleeRepo.findById(1)).thenReturn(Optional.of(enrollee));
		Mockito.when(enrolleeRepo.save(any())).thenReturn(enrollee); 
		
		AppResponse appResponse = enrolleService.updateEnrollee(enrolleeVO);
		assertEquals(200, appResponse.getCode());
		assertEquals("Enrollee has been updated successfully!!", appResponse.getMessage());
	}

	@Test
	public void testUpdateEnrollee_else() {
		Enrollee enrollee = new Enrollee();
		enrollee.setName("Rojina");
		EnrolleeVO enrolleeVO = new EnrolleeVO();
		enrolleeVO.setEnrolleeID(2);
		Mockito.when(enrolleeRepo.findById(2)).thenReturn(Optional.empty());
		AppResponse appResponse = enrolleService.updateEnrollee(enrolleeVO);
		assertEquals(400, appResponse.getCode());
		assertEquals("Enrollee was not updated/not found!!", appResponse.getMessage());
	}
	
	@Test
	public void testDeleteEnrollee() {
		doNothing().when(enrolleeRepo).deleteById(isA(Integer.class));
		AppResponse appResponse = enrolleService.deleteEnrollee(1);
		assertEquals(200, appResponse.getCode());
		assertEquals("Enrollee has been deleted successfully!!", appResponse.getMessage());
	}
	
	@Test
	public void testDeleteEnrollee_catch(){
		doThrow(new IllegalArgumentException()).when(enrolleeRepo).deleteById(anyInt());
		AppResponse appResponse = enrolleService.deleteEnrollee(1);
		assertEquals(400, appResponse.getCode());
		assertEquals("Error in deleting enrollee!!", appResponse.getMessage());
	}
	
	@Test
	public void testAddDependent() {
		DependentVO dependentVO = new DependentVO();
		dependentVO.setName("Test Dependent");
		
		EnrolleeVO enrolleeVO = new EnrolleeVO();
		enrolleeVO.setDependentsVO(List.of(dependentVO));
		
		Enrollee enrollee = new Enrollee();
		enrollee.setName("Test Enrollee");
		
		Mockito.when(enrolleeRepo.findById(enrolleeVO.getEnrolleeID())).thenReturn(Optional.of(enrollee));
		
		AppResponse appResponse = enrolleService.addDependent(enrolleeVO);
		assertEquals(200, appResponse.getCode());
		assertEquals("Dependent has been added successfully!!", appResponse.getMessage());
	}
	
	@Test
	public void testAddDependent_else() {
		DependentVO dependentVO = new DependentVO();
		dependentVO.setName("Test Dependent");
		
		EnrolleeVO enrolleeVO = new EnrolleeVO();
		enrolleeVO.setDependentsVO(List.of(dependentVO));
		
		Mockito.when(enrolleeRepo.findById(enrolleeVO.getEnrolleeID())).thenReturn(Optional.empty());
		AppResponse appResponse = enrolleService.addDependent(enrolleeVO);
		assertEquals(200, appResponse.getCode());
		assertEquals("Dependent has been added successfully!!", appResponse.getMessage());
	}
	
	@Test
	public void testAddDependent_catch() {
		DependentVO dependentVO = new DependentVO();
		dependentVO.setName("Test Dependent");
		
		EnrolleeVO enrolleeVO = new EnrolleeVO();
		enrolleeVO.setDependentsVO(List.of(dependentVO));
		Mockito.when(enrolleeRepo.findById(anyInt())).thenReturn(null);
		
		
		AppResponse appResponse = enrolleService.addDependent(enrolleeVO);
		assertEquals(400, appResponse.getCode());
		assertEquals("Error in adding dependent.", appResponse.getMessage());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
