package sdbms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import customexception.InvalidChoiceException;
import customexception.StudentNotFoundException;
import customsorting.SortStudentByAge;
import customsorting.SortStudentByName;
import customsorting.SortStudentBymarks;
import customsorting.SortstudentbyId;

public class StudentManagementSysIMPL implements StudentManagementSystem 
{

	Scanner sc=new Scanner(System.in);

	Map<String,Student> db=new LinkedHashMap<String,Student>();


	@Override
	public void addStudent() 
	{
		System.out.println("enter the student Name:");
		String name=sc.next();
		System.out.println("enter the student age:");
		int age=sc.nextInt();
		System.out.println("enter the student marks:");
		int marks=sc.nextInt();

		Student s=new Student(name,age,marks);
		db.put(s.getId(),s);
		System.out.println("Student record inserted successfully");
		System.out.println("Student id is:"+s.getId());
	}
	@Override
	public void displayStudent()
	{
		System.out.println("enter the student id");
		String id=sc.next();
		id=id.toUpperCase();
		if(db.containsKey(id))
		{
			Student std=db.get(id);
			System.out.println("Student details are as follows");
			System.out.println("Student id= "+std.getId());
			System.out.println("Student Name= "+std.getName());
			System.out.println("Student Age= "+std.getAge());
			System.out.println("Student Marks= "+std.getMarks());
		}
		else
		{
			try 
			{
				String message="Student with the id = "+id+" is not found";
				throw new StudentNotFoundException(message);
			}
			catch(StudentNotFoundException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	@Override
	public void displayAllStudent() 
	{
		if(db.size()!=0)
		{
			System.out.println("Student details are as follow");
			System.out.println("------------------------------");
			Set<String> keys=db.keySet();
			for(String key:keys)
			{
				System.out.println(db.get(key));
			}
		}
		else
		{
			try 
			{
				String message="Student DataBase is Empty.... Nothing to Display";
				throw new StudentNotFoundException(message);
			}
			catch(StudentNotFoundException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	@Override
	public void removeStudent() 
	{
		System.out.println("Eneter the Student id: ");
		String id=sc.next();
		id=id.toUpperCase();
		if(db.containsKey(id))
		{
			System.out.println("Student Record Found!...");
			System.out.println(db.get(id));
			db.remove(id);
			System.out.println("Student Data is Removed Successfully!........");
		}
		else
		{
			try 
			{
				String message="Student with the id = "+id+" is not found";
				throw new StudentNotFoundException(message);
			}
			catch(StudentNotFoundException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	@Override
	public void removeAllStudent()
	{
		if(db.size()!=0)
		{
			System.out.println("Available Student Records: "+db.size());
			db.clear();
			System.out.println("All the Student record deleted Successfully..!");
		}
		else
		{
			try 
			{
				String message="Student DataBase is Empty.... Nothing to Delet";
				throw new StudentNotFoundException(message);
			}
			catch(StudentNotFoundException e)
			{
				System.out.println(e.getMessage());
			} 
		}
	}
	@Override
	public void updateStudent() 
	{
		System.out.println("Eneter the Student id: ");
		String id=sc.next();
		id=id.toUpperCase();
		if(db.containsKey(id))
		{
			Student std=db.get(id);
			System.out.println("1:Update Age\n2:Update Name\n3:Update Marks\n");
			System.out.println("Enter your coice: ");

			int choice=sc.nextInt();

			switch(choice)
			{
			case 1: 
				System.out.println("Enter age: ");
				int age=sc.nextInt();
				std.setAge(age);
				System.out.println("Age Updated Successfully");
				break;

			case 2:
				System.out.println("Enter Name: ");
				String name=sc.next();
				std.setName(name);
				System.out.println("Name Updated Successfully");
				break;

			case 3:
				System.out.println("Enter Marks: ");
				int marks=sc.nextInt();
				std.setMarks(marks);
				System.out.println("Marks Updated Successfully");
				break;

			default:
				try {
					throw new InvalidChoiceException("Invalid Choice");
				}
				catch(Exception e)
				{
					System.out.println(e.getMessage());
				}
			}
		}
		else
		{
			try 
			{
				String message="Student with the id = "+id+" is not found";
				throw new StudentNotFoundException(message);
			}
			catch(StudentNotFoundException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	@Override
	public void countStudent()
	{
		System.out.println("number of student records: "+db.size());
	}
	@Override
	public void sortStudent() 
	{
		if(db.size()>=2) {

			List<Student> list= new ArrayList<Student>();//s1 s2

			Set<String> keys = db.keySet();//jsp101 jsp102
			for(String key : keys)
			{
				list.add(db.get(key));//getting &adding student object in al
			}
			System.out.println("1:sort student By Id\n2:sort student By Age\n3:sort student By Name");
			System.out.println("4:sort student By marks");
			System.out.println("Enter a choice");
			int choice =sc.nextInt();
			switch(choice) {
			case 1:
				Collections.sort(list,new SortstudentbyId());
				display(list);

				break;
			case 2:
				Collections.sort(list,new SortStudentByAge());
				display(list);

				break;

			case 3:
				Collections.sort(list,new SortStudentByName());
				display(list);

				break;
			case 4:
				Collections.sort(list,new SortStudentBymarks());
				display(list);

				break;
			default:
				String message="Invalid choice!,kindly enter valid choice";
				try {
					throw new InvalidChoiceException(message);
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
		else 
		{
			try {
				String message="No Sufficient Student Record to Sort!!";
				throw new StudentNotFoundException(message);
			}
			catch(StudentNotFoundException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	private static void display(List<Student> list)
	{
		for(Student s:list)
		{
			System.out.println(s);
		}
	}

	@Override
	public void getStudentwithhighestmarks() 
	{
		if(db.size()>=2) {
			List<Student> list= new ArrayList<Student>();//s1 s2

			Set<String> keys = db.keySet();//jsp101 jsp102
			for(String key : keys)
			{
				list.add(db.get(key));//getting &adding student object in al
			}
			Collections.sort(list,new SortStudentBymarks());
			System.out.println(list.get(list.size()-1));
		}
		else
		{
			try {
				String message="No Sufficient Student Record to Compare!!";
				throw new StudentNotFoundException(message);
			}
			catch(StudentNotFoundException e)
			{
				System.out.println(e.getMessage());
			}

		}
	}
	@Override
	public void getStudentWithLowestMarks()
	{
		if(db.size()>=2) {
			List<Student> list= new ArrayList<Student>();//s1 s2

			Set<String> keys = db.keySet();//jsp101 jsp102
			for(String key : keys)
			{
				list.add(db.get(key));//getting &adding student object in al
			}
			Collections.sort(list,new SortStudentBymarks());
			System.out.println(list.get(0));
		}
		else
		{
			try {
				String message="No Sufficient Student Recor to compare!!";
				throw new StudentNotFoundException(message);
			}
			catch(StudentNotFoundException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}


}

