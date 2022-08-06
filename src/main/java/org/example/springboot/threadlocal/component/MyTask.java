package org.example.springboot.threadlocal.component;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.springboot.threadlocal.threadlocal.MyThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
@Setter
@Slf4j
public class MyTask implements Runnable {

    @Autowired
    private ApplicationContext applicationContext;

    private String transactionId;

    @Override
    public void run() {
        MyThreadLocal.startTransaction(transactionId);
        try {
            printText(String.format("Thread = %d TransactionId = %s", Thread.currentThread().getId(), transactionId));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            printText(String.format("Thread = %d Threadlocal property  %s", Thread.currentThread().getId(), MyThreadLocal.getTransactionId()));
        } finally {
            MyThreadLocal.endTransaction();
        }

    }

    private void printText(String text) {
        log.info("##############################################");
        log.info(text);
        log.info("##############################################");
    }

}
