package com.course.microservice.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.course.microservice.entity.EmployeeAssignment;

@Repository
public interface EmployeeAssignmentRepository extends PagingAndSortingRepository<EmployeeAssignment, Long> {

}
