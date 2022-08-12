package com.example.demo.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.Employee;
import com.example.demo.exception.IncorrectColumnException;
import com.example.demo.exception.IncorrectSalaryException;

public class CSVHelper {
	public static String TYPE = "text/csv";
	static String[] headers = { "id", "login", "name", "salary", "startDate" };
	public static boolean hasCSVFormat(MultipartFile file) 
	{
		if (!TYPE.equals(file.getContentType())) 
		{
			return false;
	    }
	    return true;
	}
	public static List<Employee> csvToEmployee(InputStream is)
	{
		LocalDate empStartDate;
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) 
		{
			List<Employee> employees = new ArrayList<Employee>();
		      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
		      for (CSVRecord csvRecord : csvRecords) 
		      {
		    	  String empId = csvRecord.get("id");
		    	  String empLogin = csvRecord.get("login");
		    	  String empName = csvRecord.get("name");
		    	  Double empSalary = Double.parseDouble(csvRecord.get("salary"));
		    	  String empDate = csvRecord.get("startDate");
		    	  if (empDate.length() == "yyyy-mm-dd".length())
		    	  {
		    		  empStartDate = LocalDate.parse(empDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
 		    	  } 
		    	  else 
		    	  {
		    		  empStartDate = LocalDate.parse(empDate, DateTimeFormatter.ofPattern("dd-MMM-yy"));
 		    	  }
		    	  if (empId.startsWith("#"))
		    	  {
		    		  continue;
		    	  }
		    	  else if (csvRecord.size() != headers.length)
		    	  {
		    		  throw new IncorrectColumnException("Incorrect amount of column");
		    	  }
		    	  else if (empSalary < 0.0)
		    	  {
		    		  throw new IncorrectSalaryException("Salary amount incorrect");
		    	  }
		    	  else
		    	  {
		    		  Employee employee = new Employee();
		    		  employee.setId(empId);
		    		  employee.setName(empName);
		    		  employee.setLogin(empLogin);
		    		  employee.setSalary(empSalary);
		    		  employee.setStartDate(empStartDate);
		    		  employees.add(employee);
		    	  }
		      }
		      return employees;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}
}
