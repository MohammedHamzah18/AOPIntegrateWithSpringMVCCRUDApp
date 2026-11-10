
package com.example.employeemanagement.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmployeeLoggingAspect {

    @Before("execution(* com.example.employeemanagement.service.*.*(..))")
    public void logBefore() {
        System.out.println("AOP LOG: Employee service method started");
    }
    @AfterReturning("execution(* com.example.employeemanagement.service.*.*(..))")
    public void logAfterReturning() {
        System.out.println("AOP LOG: Employee service method completed successfully");
    }

    @AfterThrowing(
            pointcut = "execution(* com.example.employeemanagement.service.*.*(..))",
            throwing = "exception"
    )
    public void logAfterThrowing(Exception exception) {

        System.out.println("AOP ERROR: Employee operation failed");
        System.out.println("Exception: " + exception.getMessage());
    }
    @After("execution(* com.example.employeemanagement.service.*.*(..))")
    public void logAfter() {
        System.out.println("AOP LOG: Employee service method execution finished");
    }
    @Around("execution(* com.example.employeemanagement.service.*.*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        System.out.println("AOP LOG: Before method execution");

        Object result = joinPoint.proceed();

        System.out.println("AOP LOG: After method execution");

        return result;
    }

}
