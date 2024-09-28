package com.application.cursova.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Before("execution(* com.application.cursova.controller.*.*(..))")
    public void logBeforeControllerMethods(JoinPoint joinPoint) {
        logger.info("Метод контролера викликаний: {}", joinPoint.getSignature().getName());
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            try {
                logger.info("Введені значення: {}", objectMapper.writeValueAsString(args));
            } catch (Exception e) {
                logger.error("Помилка перетворення введених даних в JSON", e);
            }
        }
    }

    @AfterReturning(pointcut = "execution(* com.application.cursova.service.*.*(..))", returning = "result")
    public void logAfterServiceMethods(JoinPoint joinPoint, Object result) {
        logger.info("Метод сервісу викликаний: {}", joinPoint.getSignature().getName());
        if (result != null) {
            try {
                logger.info("Результат у форматі JSON: {}", objectMapper.writeValueAsString(result));
            } catch (Exception e) {
                logger.error("Помилка перетворення результату в JSON", e);
            }
        }
    }

    @Before("execution(* com.application.cursova.repository.*.*(..))")
    public void logBeforeRepositoryMethods(JoinPoint joinPoint) {
        logger.info("Метод репозиторію викликаний: {}", joinPoint.getSignature().getName());
    }

    @AfterThrowing(pointcut = "execution(* com.application.cursova..*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Помилка у методі: {} з повідомленням: {}", joinPoint.getSignature().getName(), error.getMessage());
    }
}