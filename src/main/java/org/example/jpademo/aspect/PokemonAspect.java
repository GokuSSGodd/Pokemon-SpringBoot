package org.example.jpademo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.SignedObject;

@Slf4j
@Aspect
@Component
public class PokemonAspect {

    @Pointcut("execution(* org.example.jpademo.controller.PokemonController.*(..))")
    private void getPokemonRequest(){}

    @Around("getPokemonRequest()")
    public Object logGetPokemon(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Object methodName = proceedingJoinPoint.getSignature().getName();
        log.info("Method Started called: {}()",  methodName);
        Object returnPoint = proceedingJoinPoint.proceed();
        log.info("Method Ended called: {}()", methodName);
        return returnPoint;
    }
}
