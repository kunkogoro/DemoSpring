package com.axonactive.demo_spring.service;

import com.axonactive.demo_spring.entity.Department;
import com.axonactive.demo_spring.entity.ReponseObject;
import com.axonactive.demo_spring.service.dto.DepartmentDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DepartmentService {
    ResponseEntity<ReponseObject>  getAllDepartment();
//    List<Department> getAllDepartment();
    ResponseEntity<ReponseObject> saveDepartment(DepartmentDTO departmentDTO);
    ResponseEntity<ReponseObject> getDepartmentById(Long id);
    ResponseEntity<ReponseObject> updateDepartment(Department department);
    ResponseEntity<ReponseObject> deleteDepartmentById(Long id);
}
