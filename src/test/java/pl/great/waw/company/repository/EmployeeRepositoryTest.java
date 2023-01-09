package pl.great.waw.company.repository;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import pl.great.waw.company.exceptions.PeselAlreadyExistException;
import pl.great.waw.company.model.Employee;

import java.math.BigDecimal;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EmployeeRepositoryTest {

    @Test
    void create() throws PeselAlreadyExistException {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee employee = new Employee("22222222", "bartek", "porebski", BigDecimal.TEN);

        Employee employee1 = employeeRepository.create(employee);
        assertEquals(employee1, employee);

    }

    @Test
    void read() throws PeselAlreadyExistException {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee employee = new Employee("29123123", "bartek", "porebski", BigDecimal.TEN);
        employeeRepository.create(employee);
        Employee read = employeeRepository.read("29123123");
        assertEquals(employee, read);
    }

    @Test
    void update() throws PeselAlreadyExistException {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee employee = new Employee("29123123", "bartek", "porebski", BigDecimal.TEN);
        Employee employee1 = new Employee("55555555", "sdddd", "sadasdsads", BigDecimal.TEN);
        employeeRepository.create(employee);
        Employee update = employeeRepository.update("29123123", employee1);
        assertEquals(employee1, update);
    }

    @Test
    void delete() throws PeselAlreadyExistException {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        Employee employee = new Employee("29123123", "bartek", "porebski", BigDecimal.TEN);
        Employee employee1 = new Employee("55555555", "sdddd", "sadasdsads", BigDecimal.TEN);
        employeeRepository.create(employee);
        employeeRepository.create(employee1);
        employeeRepository.delete("29123123");
        assertEquals(1, employeeRepository.size());
    }

    @Test
    void random() throws PeselAlreadyExistException {
        int randomEmployee = 100000;

        Faker faker = new Faker(new Locale("pl"));
        EmployeeRepository employeeRepository = new EmployeeRepository();

        for (int i = 0; i < randomEmployee; i++) {
            String pesel = faker.idNumber().invalid();
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            BigDecimal salary = new BigDecimal(faker.number().randomNumber());
            Employee employee = new Employee(pesel, firstName, lastName, salary);
            employeeRepository.create(employee);
        }
        assertEquals(randomEmployee, employeeRepository.size());
    }
}