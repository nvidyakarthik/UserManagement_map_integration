package com.epicproportionstour.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;

@Aspect
public class LoggingAspect {

   /** Following is the definition for a pointcut to select
    *  all the methods available. So advice will be called
    *  for all the methods.
    */
   @Pointcut("execution(* com.epicproportionstour.*.controller.*.*(..))")
   private void selectAll(){}

   /** 
    * This is the method which I would like to execute
    * before a selected method execution.
    */
   @Before("selectAll()")
   public void beforeAdvice(JoinPoint joinPoint){
	  //System.out.println("hijacked : " + joinPoint.getSignature().getName());
	  //System.out.println("Join point kind : " + joinPoint.getKind());
	 // System.out.println("Signature declaring type : "+ joinPoint.getSignature().getDeclaringTypeName());
	  System.out.println("*************************************************************");  
	  System.out.println("INSIDE CLASS : "+ joinPoint.getTarget().getClass().getName()); 
	  System.out.println("ENTERING METHOD  : " + joinPoint.getSignature().getName());
	  System.out.println("ARGUMENTS PASSED : " + Arrays.toString(joinPoint.getArgs()));
	  
	  //System.out.println("This class : " + joinPoint.getThis().getClass().getName());
   }

   /** 
    * This is the method which I would like to execute
    * after a selected method execution.
    */
   @After("selectAll()")
   public void afterAdvice(JoinPoint joinPoint){
	   System.out.println("INSIDE CLASS : "+ joinPoint.getTarget().getClass().getName()); 
	   System.out.println("EXITING METHOD  : " + joinPoint.getSignature().getName());
	   System.out.println("*************************************************************");  
	   
   }
}
