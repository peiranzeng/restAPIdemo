package dockerdemo.example.dockerdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import dockerdemo.example.dockerdemo.model.Employee;
import dockerdemo.example.dockerdemo.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	@Cacheable(value="employees")
	public Iterable<Employee> getEmployees() {
		
		return employeeRepository.findAll();
	}
	
	@Override
	@Cacheable(value="employees")
	public Page<Employee> getEmployeesByPage(int offset, int limit) {
		
		Pageable page = PageRequest.of(offset, limit);
		
		return employeeRepository.findAll(page);
		
		
	}

	@Override
	@Cacheable(value="employees", key="#name")
	public List<Employee> getEmployeeByName(String name) {
		
		return employeeRepository.findByName(name);
		
	}

	@Override
	@CacheEvict(value = "employees", allEntries=true)
	public void deleteEmployeeById(int id) {
		employeeRepository.deleteById(id);
		
	}

	@Override
	@CachePut(value = "employees", key = "#result.id")
	public Employee saveEmployee(Employee employee) {
		
		return employeeRepository.save(employee);
	}

	@Override
	@CachePut(value = "employees", key = "#id")
	public void updateEmployee(Employee employee, int id) {
		
		employee.setId(id);
		
		employeeRepository.save(employee);
		
	}

}
