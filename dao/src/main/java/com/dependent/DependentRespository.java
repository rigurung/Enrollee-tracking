package com.dependent;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DependentRespository extends JpaRepository<Dependent, Integer>{

}
