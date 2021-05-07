package ua.tqs.p2_testcontainers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Testcontainers
@SpringBootTest
class P2TestcontainersApplicationTests {

	@Container
	public static PostgreSQLContainer container = new PostgreSQLContainer()
			.withUsername("duke")
			.withPassword("password")
			.withDatabaseName("test");

	@Autowired
	private EmployeeRepository employeeRepository;

	// requires Spring Boot >= 2.2.6
	@DynamicPropertySource
	static void properties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.password", container::getPassword);
		registry.add("spring.datasource.username", container::getUsername);
	}

	@Test
	@Order(1)
	void testCreateEmployee() {
		Employee employee = new Employee();
		employee.setName("diogo");
		employee.setEmail("diogo@ua.pt");
		employeeRepository.save(employee);
		System.out.println("Employee Created!");
	}

	@Test
	@Order(2)
	void testUpdateEmployee() {
		Employee employee = employeeRepository.findByName("diogo");
        employee.setName("diogo");
		employee.setEmail("diogo@ua.pt");
		employeeRepository.save(employee);
		System.out.println("Employee updated!");
	}

	@Test
	@Order(3)
	void testDeleteEmployee() {
		Employee employee = employeeRepository.findByName("diogo");
		employeeRepository.delete(employee);
		Employee employee_deleted = employeeRepository.findByName("diogo");
		assert employee_deleted == null;
		System.out.println("Employee deleted!");
	}

}
