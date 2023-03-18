package customsorting;

import java.util.Comparator;

import sdbms.Student;

public class SortstudentbyId  implements Comparator<Student>
{
 @Override
public int compare(Student x , Student y)
{
	 return x.getId().compareTo(y.getId());
}
	

}
