package org.example.springboot.threadlocal.threadlocal;

public class MyThreadLocal {


    public static final ThreadLocal<String> userThreadLocal = new ThreadLocal<String> ();

    public static void startTransaction(String generatedId) {
        userThreadLocal.set(generatedId);
    }

    public static String getTransactionId() {
        return userThreadLocal.get();
    }

    public static void endTransaction() {
        userThreadLocal.remove();
    }
}
