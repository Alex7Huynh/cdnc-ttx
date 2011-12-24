/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;


/**
 *
 * @author Fate
 */
@Aspect
public class LoggingAspect {
    @Before("   call(void java.io.PrintStream.println(String)) " +
            "&& !within(util..*)")
    public void beforePrintlnCall() {
        System.out.println("About to make call to print Hello World");
    }
 
    @After("    call(void java.io.PrintStream.println(String)) " +
           "&&  !within(util..*)")
    public void afterPrintlnCall() {
        System.out.println("Just made call to print Hello World");
    }    
}
