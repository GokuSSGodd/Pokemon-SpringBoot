package org.example.jpademo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * The {@code PokemonAspect} class demonstrates the concept of Aspect-Oriented Programming (AOP)
 * in Spring. AOP allows developers to separate cross-cutting concerns—such as logging,
 * transaction management, and security—from the core business logic.
 *
 * <p>This aspect intercepts method calls made to the {@code PokemonController} and
 * logs when a method starts and ends, improving observability and maintainability.</p>
 *
 * <h3>Annotations and Concepts Explained:</h3>
 *
 * <ul>
 *   <li><b>@Aspect</b> – Marks this class as an Aspect, meaning it contains advice
 *       (code) that should be executed at certain join points (places in the program)
 *       such as method calls or exceptions. It tells Spring AOP that this class defines cross-cutting logic.</li>
 *
 *   <li><b>@Component</b> – Registers the class as a Spring-managed bean, allowing
 *       the Spring container to detect and instantiate it during component scanning.
 *       This ensures the aspect is active and can intercept method executions.</li>
 *
 *   <li><b>@Slf4j</b> – A Lombok annotation that automatically generates an
 *       {@code org.slf4j.Logger} instance named {@code log}. This removes boilerplate
 *       code such as manually creating a logger with {@code LoggerFactory.getLogger()}.</li>
 *
 *   <li><b>@Pointcut</b> – Defines a specific point in the program execution where advice
 *       should be applied. In this example, the expression:
 *       <pre>{@code execution(* org.example.jpademo.controller.PokemonController.*(..))}</pre>
 *       targets all methods (with any return type and parameters) within the
 *       {@code PokemonController} class.</li>
 *
 *   <li><b>@Around</b> – Declares "around advice," which means the annotated method
 *       executes both <i>before</i> and <i>after</i> the target method runs.
 *       It can control whether the target method actually executes and can also
 *       modify its return value or handle exceptions.</li>
 *
 *   <li><b>Join Point</b> – A point during the execution of a program, such as a method
 *       invocation or exception handling, where additional behavior (advice) can be applied.
 *       In this case, the join point is every method call inside {@code PokemonController}.</li>
 *
 *   <li><b>ProceedingJoinPoint</b> – A subclass of {@code JoinPoint} used with
 *       {@code @Around} advice. It provides a way to control the execution of the target method
 *       by calling {@code proceed()}. It also gives access to the method’s metadata
 *       (name, arguments, signature, etc.), allowing for detailed logging or manipulation.</li>
 *
 *   <li><b>log.info(...)</b> – Logs information messages using the SLF4J logger.
 *       These statements mark when the method starts and ends, providing traceability
 *       and helping developers debug or monitor application flow.</li>
 * </ul>
 *
 * <h3>Overall Behavior:</h3>
 * <p>
 * When any method in {@code PokemonController} is called, this aspect intercepts it.
 * The {@code logGetPokemon()} method logs a "Method Started" message before the method executes,
 * proceeds to run the actual controller logic via {@code proceedingJoinPoint.proceed()},
 * and then logs a "Method Ended" message afterward.
 * </p>
 */
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
