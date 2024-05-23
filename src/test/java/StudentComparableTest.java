/*
 * DO NOT MAKE ANY CHANGES
 */

import org.junit.Assume;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class StudentComparableTest {

    @Test
    public void testStudentImplementsComparable() {
        assertTrue("-STUDENT CLASS: Student class should implement Comparable", Comparable.class.isAssignableFrom(Student.class));
    }
    
    @Test
    public void testCompareTo(){
        try {
            Student student1 = new Student("John", 3.5);
            Student student2 = new Student("Jane", 3.5);
            Student student3 = new Student("Jack", 3.7);
            Student student4 = new Student("Jill", 3.3);

            Method compareToMethod = Student.class.getMethod("compareTo", Student.class);

            assertEquals("-STUDENT CLASS: compareTo method does not return correct value when gpa is equal", 0, compareToMethod.invoke(student1, student2));
            assertEquals("-STUDENT CLASS: compareTo method does not return correct value when gpa is equal", 0, compareToMethod.invoke(student2, student1));
            assertTrue("-STUDENT CLASS: compareTo method does not return correct value", (int) compareToMethod.invoke(student1, student3) < 0);
            assertTrue("-STUDENT CLASS: compareTo method does not return correct value", (int) compareToMethod.invoke(student1, student4) > 0);
        } catch (NoSuchMethodException e) {
            fail("MISSING: -STUDENT CLASS: compareTo method not found");
        } catch (Exception e) {
            fail("Unexpected error occurred: " + e.getMessage());
        }
}

}
