package com.axonactive.demo_spring.repostory;

import com.axonactive.demo_spring.entity.Department;
import com.axonactive.demo_spring.entity.LocationEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepostory extends JpaRepository<Department, Long> {

    Optional<List<Department>> findDepartmentsByNameLikeOrderByNameAsc(String name,Pageable range);

    Optional<List<Department>> findDepartmentsByNameLikeOrderByNameDesc(String name, Pageable range);

}
