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

public class StudentCloneableTest {

    @Test
    public void testStudentImplementsCloneable() {
        assertTrue("-STUDENT CLASS: Student class should implement Cloneable", Cloneable.class.isAssignableFrom(Student.class));
    }
    
    @Test
    public void testCloneMethod() {
        try {
            Student student1 = new Student("John", 3.5);
            Method cloneMethod = Student.class.getMethod("clone");
            Student student2 = (Student) cloneMethod.invoke(student1);

            Method getNameMethod = Student.class.getMethod("getName");
            Method getGpaMethod = Student.class.getMethod("getGPA");

            assertEquals("-STUDENT CLASS: clone method does not clone student correctly", getNameMethod.invoke(student1), getNameMethod.invoke(student2));
            assertEquals("-STUDENT CLASS: clone method does not clone student correctly", getGpaMethod.invoke(student1), getGpaMethod.invoke(student2));
            assertNotSame("-STUDENT CLASS: clone method does not return a new object", student1, student2);
        } catch (NoSuchMethodException e) {
            fail("MISSING: -STUDENT CLASS: " + e.getMessage() + " method not found");
        } catch (Exception e) {
            fail("Unexpected error occurred: " + e.getMessage());
        }
    }
}
