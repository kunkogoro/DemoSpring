package com.axonactive.demo_spring.service.implement;

import com.axonactive.demo_spring.entity.Department;
import com.axonactive.demo_spring.entity.Employee;
import com.axonactive.demo_spring.entity.ReponseObject;
import com.axonactive.demo_spring.repostory.DepartmentRepostory;
import com.axonactive.demo_spring.repostory.EmployeeRepostory;
import com.axonactive.demo_spring.service.EmployeeService;
import com.axonactive.demo_spring.service.dto.EmployeeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepostory employeeRepostory;
    private final DepartmentRepostory departmentRepostory;

    @Override
    public ResponseEntity<ReponseObject> getAllEmployee() {
        List<Employee> employees = employeeRepostory.findAll();
        return employees.size() == 0 ? ResponseEntity.ok().body(
                new ReponseObject(String.valueOf(HttpStatus.NOT_FOUND), "Not Found Employee", "")
        ) : ResponseEntity.ok().body(
                new ReponseObject(String.valueOf(HttpStatus.FOUND), "Found Employee", employees)
        );
    }

    @Override
    public ResponseEntity<ReponseObject> saveEmployee(EmployeeDTO employeeDTO) {

        Optional<Department> department = departmentRepostory.findById(employeeDTO.getDepartment());

        if (department.isPresent()) {
            try {
                Employee employee = new Employee();
                employee.setFirstName(employeeDTO.getFirstName());
                employee.setLastName(employeeDTO.getLastName());
                employee.setAddress(employeeDTO.getAddress());
                employee.setGender(employeeDTO.getGender());
                employee.setEmail(employeeDTO.getEmail());
                employee.setCity(employeeDTO.getCity());
                employee.setDepartment(department.get());
                employee.setDoB(employeeDTO.getDoB());
                Employee employeeSave = employeeRepostory.save(employee);
                return ResponseEntity.ok().body(
                        new ReponseObject(String.valueOf(HttpStatus.OK), "Save Employee Success", employeeSave)
                );
            } catch (Exception exception) {
                return ResponseEntity.ok().body(
                        new ReponseObject(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR), exception.getMessage(), "")
                );
            }

        } else {
            return ResponseEntity.ok().body(
                    new ReponseObject(String.valueOf(HttpStatus.NOT_FOUND), "Not Found Department with id " + employeeDTO.getDepartment(), "")
            );
        }
    }

    @Override
    public ResponseEntity<ReponseObject> getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepostory.findById(id);
        return employee.isPresent() ?
                ResponseEntity.ok().body(
                        new ReponseObject(String.valueOf(HttpStatus.OK), "Found Employee", employee)
                ) : ResponseEntity.ok().body(
                new ReponseObject(String.valueOf(HttpStatus.NOT_FOUND), "Not Found Employee with id = " + id, "")
        );
    }

    @Override
    public ResponseEntity<ReponseObject> getAllEmployeeByDepartmentId(Long id) {

        Optional<Department> department = departmentRepostory.findById(id);
        if (department.isPresent()) {
            List<Employee> employees = employeeRepostory.findAllEmployeeByIdDepartment(department.get());

            return employees.size() == 0 ? ResponseEntity.ok().body(
                    new ReponseObject(String.valueOf(HttpStatus.NOT_FOUND), "Not Found Departmant", "")
            ) : ResponseEntity.ok().body(
                    new ReponseObject(String.valueOf(HttpStatus.FOUND), "Found Employee", employees)
            );
        } else {
            return ResponseEntity.ok().body(
                    new ReponseObject(String.valueOf(HttpStatus.NOT_FOUND), "not Found Employee with id deparment = " + id, "")
            );
        }
    }

    @Override
    public ResponseEntity<ReponseObject> updateEmployee(EmployeeDTO employeeDTO, Long id) {

        Optional<Employee> employeeByEmail = employeeRepostory.findEmployeeByEmail(employeeDTO.getEmail());
        if (employeeByEmail.isPresent()) {
            if (!employeeByEmail.get().getId().equals(id)) {
                return ResponseEntity.ok().body(
                        new ReponseObject(String.valueOf(HttpStatus.EXPECTATION_FAILED), "Exists email", "")
                );
            }
        }
        Optional<Employee> employeeOld = employeeRepostory.findById(id);
        if (employeeOld.isPresent()) {
            Optional<Department> department = departmentRepostory.findById(employeeDTO.getDepartment());
            if (department.isPresent()) {
                Employee employeeSave = new Employee();
                employeeSave.setId(id);
                employeeSave.setFirstName(employeeDTO.getFirstName());
                employeeSave.setLastName(employeeDTO.getLastName());
                employeeSave.setEmail(employeeDTO.getEmail());
                employeeSave.setGender(employeeDTO.getGender());
                employeeSave.setCity(employeeDTO.getCity());
                employeeSave.setDepartment(department.get());
                employeeSave.setAddress(employeeDTO.getAddress());
                employeeSave.setDoB(employeeDTO.getDoB());
                employeeRepostory.save(employeeSave);
                return ResponseEntity.ok().body(
                        new ReponseObject(String.valueOf(HttpStatus.OK), "Update Success", employeeSave)
                );
            } else {
                return ResponseEntity.ok().body(
                        new ReponseObject(String.valueOf(HttpStatus.NOT_FOUND), "Not Found Department with id " + employeeDTO.getDepartment(), "")
                );
            }
        } else {
            return saveEmployee(employeeDTO);
        }
    }

    @Override
    public ResponseEntity<ReponseObject> deleteEmployeeById(Long id) {
        if (employeeRepostory.existsById(id)) {
            employeeRepostory.deleteById(id);
            return ResponseEntity.ok().body(
                    new ReponseObject(String.valueOf(HttpStatus.OK), "Delete Empoyee with id = " + id + " success", "")
            );
        } else {
            return ResponseEntity.ok().body(
                    new ReponseObject(String.valueOf(HttpStatus.NOT_FOUND), "Not found Employee with " + id, "")
            );
        }
    }

    @Override
    public ResponseEntity<ReponseObject> getPartEmployee(int min, int max, String search, String sort, String mainAttribute) {
        Pageable range = PageRequest.of(min, max);
        Optional<List<Employee>> optional = Optional.empty();
        switch (mainAttribute) {
            case "lastName":
                if (sort.equals("asc")) {
                    optional = employeeRepostory.findEmployeesByLastNameLikeOrderByLastNameAsc("%" + search + "%", range);
                } else {
                    optional = employeeRepostory.findEmployeesByLastNameLikeOrderByLastNameDesc("%" + search + "%", range);
                }
                break;
            case "firstName":
                if (sort.equals("asc")) {
                    optional = employeeRepostory.findEmployeesByFirstNameLikeOrderByFirstNameAsc("%" + search + "%", range);
                } else {
                    optional = employeeRepostory.findEmployeesByFirstNameLikeOrderByFirstNameDesc("%" + search + "%", range);
                }
                break;
            case "address":
                if (sort.equals("asc")) {
                    optional = employeeRepostory.findEmployeesByAddressLikeOrderByAddressAsc("%" + search + "%", range);
                } else {
                    optional = employeeRepostory.findEmployeesByAddressLikeOrderByAddressDesc("%" + search + "%", range);
                }
                break;
            case "email":
                if (sort.equals("asc")) {
                    optional = employeeRepostory.findEmployeesByEmailLikeOrderByEmailAsc("%" + search + "%", range);
                } else {
                    optional = employeeRepostory.findEmployeesByEmailLikeOrderByEmailDesc("%" + search + "%", range);
                }
                break;
            case "city":
                if (sort.equals("asc")) {
                    optional = employeeRepostory.findEmployeesByCityLikeOrderByCityAsc("%" + search + "%", range);
                } else {
                    optional = employeeRepostory.findEmployeesByCityLikeOrderByCityDesc("%" + search + "%", range);
                }
                break;
        }
        return optional.isPresent() ?
                ResponseEntity.ok().body(
                        new ReponseObject(String.valueOf(HttpStatus.FOUND), "Found Departmant", optional)
                ) : ResponseEntity.ok().body(
                new ReponseObject(String.valueOf(HttpStatus.NOT_FOUND), "Not Found Department", "")
        );
    }

    @Override
    public ResponseEntity<ReponseObject> getEmployeeByEmail(String email,Long id) {
        Optional<Employee> employeeByEmail = employeeRepostory.findEmployeeByEmailAndIdNot(email,id);
        if (employeeByEmail.isPresent()) {
            return ResponseEntity.ok().body(
                    new ReponseObject(String.valueOf(HttpStatus.FOUND), "Exists email", employeeByEmail)
            );
        } else {
            return ResponseEntity.ok().body(
                    new ReponseObject(String.valueOf(HttpStatus.NOT_FOUND), "Oke", "")
            );
        }
    }
}
