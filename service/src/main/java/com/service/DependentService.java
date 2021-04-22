package com.service;

import com.vo.AppResponse;
import com.vo.DependentVO;

public interface DependentService {

	AppResponse addDependent(DependentVO dependentVO);

	DependentVO getDependent(int dependentID);

}
