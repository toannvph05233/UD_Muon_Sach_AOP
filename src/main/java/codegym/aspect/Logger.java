package codegym.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {
    @AfterThrowing(value = "execution(* codegym.controller.HomeController.oder(..))")
    public void checkEx(){
        System.out.println("----------------");
        System.out.println("Gặp lỗi");
    }

    @After(value = "execution(* codegym.controller.HomeController.oder(..))")
    public void logOderDone(){
        System.out.println("----------------");
        System.out.println("Done");
    }


}
