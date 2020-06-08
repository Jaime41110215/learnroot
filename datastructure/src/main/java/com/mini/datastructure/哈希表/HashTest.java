package com.mini.datastructure.哈希表;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author wanghongchao
 * @time 2020/5/25
 */
public class HashTest {

    public static void main(String[] args) throws InterruptedException {
        Map<String,String> map = new HashMap<>();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(),"");
                        }
                    },"ftf" + i).start();
                }
            }
        },"ftf");
        t.start();
        t.join();
    }

}
