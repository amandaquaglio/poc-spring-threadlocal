package org.example.springboot.threadlocal;

import lombok.extern.slf4j.Slf4j;
import org.example.springboot.threadlocal.component.MyTask;
import org.example.springboot.threadlocal.configuration.MyConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Slf4j
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfiguration.class);
        ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");

        executeTask(context, "Thread 1", taskExecutor);
        executeTask(context, "Thread 2", taskExecutor);
        executeTask(context, "Thread 3", taskExecutor);

        int count = taskExecutor.getActiveCount();
        log.info("Active Threads : " + count);
        taskExecutor.shutdown();
    }

    private static void executeTask(ApplicationContext context, String transactionId, ThreadPoolTaskExecutor taskExecutor) {
        MyTask task = (MyTask) context.getBean("myTask");
        task.setTransactionId(transactionId);
        taskExecutor.execute(task);
    }

}
