### a)Identify a couple of examples on the use of AssertJ expressive methods chaining.

AssertJ is a library used for writing assertions in Java tests. Allow us to do verifications like a chain. 
Here we have an example:

assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName()); 

### b)Identify an example in which you mock the behavior of the repository (and avoid involving a database).

We can mock the beahaviour of the repository to simulate the expected results and avoid involving a database in our tests.

Here we have an example:
 In "EmployeeService_UnitTest", we mock the behavior of the repository in the attribute employeeRepository.

### c)What is the difference between standard @Mock and @MockBean?

The main difference between @Mock and @MockBean is:
- @Mock anotation creates a mock object that take the place of a real implementation. It must be used inside a Unit Test. 
- @MockBean anotation creates a mock object and add it to a Spring test context. It is used to mock objects in Integration Tests.

### d)What is the role of the file “application-integrationtest.properties”? In which conditions will it be used?

If we want to do integration tests in our API, and we want to test our database behaviour as well, we can do it by using in-memory DB or by using a real database.
So if we choose the second option, we need to use @TestPropertySource annotation. This anotation will access our "application-integrationtest.properties" file that contains the information needed to connect our application to the database.
