import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Student implements Serializable {
	private double gpa;
	private String name;
	
	public Student(String name, double gpa) {
		this.name = name;
		this.gpa = gpa;
	}
}
