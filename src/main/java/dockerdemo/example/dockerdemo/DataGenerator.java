package dockerdemo.example.dockerdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import dockerdemo.example.dockerdemo.model.Address;
import dockerdemo.example.dockerdemo.model.Employee;
import dockerdemo.example.dockerdemo.repository.EmployeeRepository;

@Service
@Profile("!prod") //set this commandLineRunner only work in test and dev, not in product.
public class DataGenerator implements CommandLineRunner {
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
		
		
		Employee employee1 = new Employee("sam", new Address(202, "Chicago", "IL"));
		Employee employee2 = new Employee("bob", new Address(404, "Tampa", "FL"));
		Employee employee3 = new Employee("Jack", new Address(300, "NewYork", "NY"));
		employeeRepository.save(employee1);
		employeeRepository.save(employee2);
		employeeRepository.save(employee3);
		
	}

}
