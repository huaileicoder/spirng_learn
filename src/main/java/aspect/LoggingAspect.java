package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(public * service.UserService.*(..))")
    public void doAccessCheck() {
        System.err.println("[before] do access check!");
    }

    @Around("execution(public * service.MailService.*(..))")
    public Object doLogging(ProceedingJoinPoint pjp) throws Throwable {
        System.err.println("[around] start:" + pjp.getSignature());
        Object retVal = pjp.proceed();
        System.err.println("[around] end:" + pjp.getSignature());
        return retVal;
    }
}
