package program;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import entities.Employee;

public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		List<Employee> employees = new ArrayList<>();

		System.out.print("Enter full file path: ");
		String path = scanner.nextLine();
		File file = new File(path);
		System.out.print("Enter salary: ");
		double empSalary = scanner.nextDouble();
		scanner.close();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = br.readLine();
			while (line != null) {
				String[] empData = line.split(",");
				employees.add(new Employee(empData[0], empData[1], Double.parseDouble(empData[2])));
				line = br.readLine();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}

		List<String> emails = employees.stream()
				.filter(e -> e.getSalary() > empSalary)
				.map(e -> e.getEmail())
				.collect(Collectors.toList());

		Double totalSalary = employees.stream()
				.filter(e -> e.getName().charAt(0) == 'M')
				.map(e -> e.getSalary())
				.reduce(0.0, (x,y) -> x + y);
		
		System.out.println("Email of people whose salary is more than " + empSalary + " :");
		emails.forEach(System.out::println);
		System.out.println("Sum of salary of people whose name starts with 'M': " + totalSalary);

	}

}
