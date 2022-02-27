package selenidegithub;

import junitexersises.ParametrizedTests;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JunitCore {

    public static void main(String[] args) throws Exception {
        // lookup classes with annotation @Test
        // here we go with class SimpleTest.class

        Class clazz = SelenideGithubTests.class;
        for (Method method : clazz.getDeclaredMethods()) {
            BeforeAll beforeAllAnnotation = method.getAnnotation(BeforeAll.class);
            if (beforeAllAnnotation != null) {
                method.invoke(clazz.getConstructor().newInstance());
            }}

        // run all methods with @Test
        for (Method method : clazz.getDeclaredMethods()) {
            Test methodAnnotation = method.getAnnotation(Test.class);
            BeforeEach beforeAllAnnotation = method.getAnnotation(BeforeEach.class);
            AfterEach afterAllAnnotation = method.getAnnotation(AfterEach.class);
            if (beforeAllAnnotation != null){
                method.invoke(clazz.getConstructor().newInstance());
            } else if (methodAnnotation != null) {
                // run method with @Test
                try {
                    method.invoke(clazz.getConstructor().newInstance());
                } catch (InvocationTargetException e) {
                    if (e.getCause() instanceof AssertionError) {
                        System.out.println("Test failed: " + method.getName());
                        continue;
                    } else {
                        System.out.println("Test broken: " + method.getName());
                        continue;
                    }
                }
                System.out.println("Test passed: " + method.getName());
            }
        }

        for (Method method : clazz.getDeclaredMethods()) {
            AfterAll afterAllAnnotation = method.getAnnotation(AfterAll.class);
            if (afterAllAnnotation != null) {
                method.invoke(clazz.getConstructor().newInstance());
            }}

        // run all methods with @Test

        // print results
    }
}
