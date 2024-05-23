/*
 * DO NOT MAKE ANY CHANGES
 */
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import org.junit.Assume;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ApplicationActionListenerTest {

    @Test
    public void testApplicationImplementsActionListener() {
        assertTrue("-APPLICATION CLASS: Application class should implement ActionListener", ActionListener.class.isAssignableFrom(Application.class));
    }
    @Test
    public void testActionPerformedMethod() {
    try {
        Method method = Application.class.getMethod("actionPerformed", ActionEvent.class);
    } catch (NoSuchMethodException e) {
        fail("MISSING: -APPLICATION CLASS: actionPerformed method not found");
    }
}

}
