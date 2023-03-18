package sdbms;

import java.util.Scanner;

import customexception.InvalidChoiceException;

public class Solution
{
	public static void main(String[] args)
	{
		System.out.println("WELCOME TO STUDENTDATABASE PROJECT");
		System.out.println("-------------------------");
		Scanner sc =new Scanner(System.in);
		StudentManagementSystem s1= new StudentManagementSysIMPL();
		while(true)
		{
			System.out.println("1:addStudent\n2:displayStudent\n3:displayAllStudent\n4:removeStudent\n5:removeAllStudent"
					+ "\n6:updateStudent\n7:countStudent\n8:sortStudent\n9:getStudentwithhighestmarks\n10:getStudentwithLowestmarks\n11:EXIT");
			System.out.println("enter a choice");
			int choice=sc.nextInt();
			switch(choice)
			{
			case 1:
				s1.addStudent();
				break;
			case 2:
				s1.displayStudent();
				break;
			case 3:
				s1.displayAllStudent();
				break;
			case 4:
				s1.removeStudent();
				break;
			case 5:
				s1.removeAllStudent();
				break;
			case 6:
				s1.updateStudent();
				break;
			case 7:
				s1.countStudent();
				break;
			case 8:
				s1.sortStudent();
				break;
			case 9:
				s1.getStudentwithhighestmarks();
				break;
			case 10:
				s1.getStudentWithLowestMarks();
				break;
			case 11:
				System.out.println("THANK YOU !!!");
				System.exit(0);
			default:
				try {
					throw new InvalidChoiceException("Invalid choice!");
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}

			}//end of switch
			System.out.println("---------------------");

		}//end of while loop

	}//end of main()

}// end of class
