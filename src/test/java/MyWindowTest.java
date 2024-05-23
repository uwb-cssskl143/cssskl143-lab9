/*
 * DO NOT MAKE ANY CHANGES
 */
import java.awt.event.MouseEvent;

import org.junit.Assume;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class MyWindowTest {
    
    @Test
    public void testMyWindowImplementsMouseListenerMethods() {
        try {
            Class<?> myWindowClass = Class.forName("MyWindow");

            Method mouseClickedMethod = myWindowClass.getMethod("mouseClicked", MouseEvent.class);
            Method mouseReleasedMethod = myWindowClass.getMethod("mouseReleased", MouseEvent.class);
            Method mouseEnteredMethod = myWindowClass.getMethod("mouseEntered", MouseEvent.class);
            Method mouseExitedMethod = myWindowClass.getMethod("mouseExited", MouseEvent.class);

        } catch (ClassNotFoundException e) {
            Assume.assumeNoException("MyWindow class does not exist", e);
        } catch (NoSuchMethodException e) {
            fail("MISSING: -MYWINDOW CLASS: " + e.getMessage() + " method not found");
        }
    }
}

