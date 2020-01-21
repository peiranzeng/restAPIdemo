package dockerdemo.example.dockerdemo.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import dockerdemo.example.dockerdemo.model.Employee;
import dockerdemo.example.dockerdemo.service.EmployeeService;

@RestController
@RequestMapping(path ="/employees", produces= MediaType.APPLICATION_JSON_VALUE)
public class DockerController {
	
	@Autowired
	EmployeeService employeeService;
	
	
	@GetMapping
	//applied pagination
	//implement spring-hateos
	public ResponseEntity<Page<Employee>> getEmployee(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "20") int limit) {
		
		
		Page<Employee> employees = employeeService.getEmployeesByPage(offset, limit); 
		
		if(employees.getSize() == 0){
			
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			
		}
		
		for(Employee employee : employees) {
			Link link = ControllerLinkBuilder.linkTo(this.getClass())
					.slash(employee.getId())
					.withSelfRel();
			
			employee.add(link);
		}
		
		return ResponseEntity.ok(employees);
	}
	
	@GetMapping(params = "name")
	public ResponseEntity<List<Employee>> getEmployeeByName(@RequestParam("name") String name) {
		
		List<Employee> employees = employeeService.getEmployeeByName(name);
		
		if(employees.size() == 0) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		
		return ResponseEntity.ok(employees);
	}
	
	
	
	@DeleteMapping("/{id}")
	public void DeleteEmployeeById(@PathVariable("id") int id) {
		
		employeeService.deleteEmployeeById(id);
		
	}
	
	@PostMapping()
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee, 
			HttpServletRequest request, UriComponentsBuilder builder) throws URISyntaxException {
		
		Employee e = employeeService.saveEmployee(employee);
		
		UriComponents uriComponents;
		uriComponents = builder.path("employees").path("" + e.getId()).build();
		
		return ResponseEntity.created(uriComponents.toUri()).build();
		
	}
	
	@PutMapping("/{id}")
	public void updateEmployee(@PathVariable("id")int id, @RequestBody Employee employee) {
		
		employeeService.updateEmployee(employee, id);
		
	}
	
	@ExceptionHandler
	public ResponseEntity UriExceptionHandler(URISyntaxException e) {
		
		String str = "{id:" + e.getInput() + "}";		
        
		return new ResponseEntity(str, HttpStatus.CREATED);
	}
	
	
	
	
}
