package com.chason.class02;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class _01_GCProducter {

    private static class CardInfo {

        BigDecimal price = new BigDecimal(0.0);
        String name = "chason";

        int age = 5;
        Date birthdate = new Date();

        public void m() {}

    }

    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws Exception {

        executor.setMaximumPoolSize(50);
        for (;;) {
            modelFit();
            Thread.sleep(1000);
        }

    }

    private static void modelFit() {
        List<CardInfo> taskList = getAllCardInfo();
        taskList.forEach(info -> {
            executor.scheduleWithFixedDelay(() -> {
                info.m();
            }, 2, 3, TimeUnit.SECONDS);
        });

    }

    private static List<CardInfo> getAllCardInfo() {
        List<CardInfo> list = new ArrayList<>();

        for (int i=0; i<100; i++) {
            CardInfo ci = new CardInfo();
            list.add(ci);
        }

        return list;
    }


}
