package dockerdemo.example.dockerdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import dockerdemo.example.dockerdemo.model.Employee;

public interface EmployeeService {
	
	public Iterable<Employee> getEmployees();
	
	public Page<Employee> getEmployeesByPage(int offset, int limit);
	
	public List<Employee> getEmployeeByName(String name);
	
	public Optional<Employee> findEmployeeById(int id);
	
	public void deleteEmployeeById(int id);
	
	public Employee saveEmployee(Employee employee);
	
	public void updateEmployee(Employee employee, int id);
}
