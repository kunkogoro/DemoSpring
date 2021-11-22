package com.axonactive.demo_spring.repostory;

import com.axonactive.demo_spring.entity.Department;
import com.axonactive.demo_spring.entity.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepostory extends JpaRepository<Employee, Long> {
    @Query("select e from Employee e where e.department = ?1")
    List<Employee> findAllEmployeeByIdDepartment(Department department);

    Optional<List<Employee>> findEmployeesByFirstNameLikeOrderByFirstNameAsc(String firstName, Pageable range);

    Optional<List<Employee>> findEmployeesByFirstNameLikeOrderByFirstNameDesc(String firstName, Pageable range);

    Optional<List<Employee>> findEmployeesByLastNameLikeOrderByLastNameAsc(String lastName, Pageable range);

    Optional<List<Employee>> findEmployeesByLastNameLikeOrderByLastNameDesc(String lastName, Pageable range);

    Optional<List<Employee>> findEmployeesByAddressLikeOrderByAddressAsc(String address, Pageable range);

    Optional<List<Employee>> findEmployeesByAddressLikeOrderByAddressDesc(String address, Pageable range);

    Optional<List<Employee>> findEmployeesByCityLikeOrderByCityAsc(String city, Pageable range);

    Optional<List<Employee>> findEmployeesByCityLikeOrderByCityDesc(String city, Pageable range);

    Optional<List<Employee>> findEmployeesByEmailLikeOrderByEmailAsc(String email, Pageable range);

    Optional<List<Employee>> findEmployeesByEmailLikeOrderByEmailDesc(String email, Pageable range);

    Optional<Employee> findEmployeeByEmail(String email);

    Optional<Employee> findEmployeeByEmailAndIdNot(String email,Long id);

}
