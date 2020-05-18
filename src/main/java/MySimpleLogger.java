import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MySimpleLogger {
    @Pointcut("execution(* SampleService.doService(..))")
    public void myTraceCall() {
    }

    @Around("MySimpleLogger.myTraceCall()")
    public Object myTrace(ProceedingJoinPoint joinPoint) throws Throwable
    {
        System.out.println("myTrace:before call "
                +joinPoint.getTarget().getClass().getName()
                +"."+joinPoint.getSignature().getName());

        Object retVal = null;
        try
        {
            retVal = joinPoint.proceed();
        }
        finally
        {
            System.out.println("myTrace:after call "+
                    joinPoint.getTarget().getClass().getName()
                    +"."+joinPoint.getSignature().getName() + " retval=" +retVal);
        }
        return retVal;
    }

}
