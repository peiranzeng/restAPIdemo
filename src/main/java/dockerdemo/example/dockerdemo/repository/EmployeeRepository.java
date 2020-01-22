package dockerdemo.example.dockerdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import dockerdemo.example.dockerdemo.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>, PagingAndSortingRepository<Employee, Integer>{
	
	List<Employee> findByName(String name);
	
	List<Employee> findByAddressCity(String city);
	
}
