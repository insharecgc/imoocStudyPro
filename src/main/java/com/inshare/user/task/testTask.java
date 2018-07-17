package com.inshare.user.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class testTask {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //启动后每隔5秒会自动执行此方法
    //@Scheduled(fixedRate = 5000)
    //@Scheduled(cron = "10/5 * * * * ? ")
    public void reportCurrentTime() {
        System.out.println("现在时间："+ dateFormat.format(new Date()));
    }
}
