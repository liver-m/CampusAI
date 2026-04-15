//HttpClient内部线程池干扰，send同步阻塞 ————干扰测试结果。
package com.zjut.student.loadtest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LoadTest {
    public static void main(String[] args) throws InterruptedException{
        ExecutorService pool = Executors.newFixedThreadPool(5);
        HttpClient client = HttpClient.newHttpClient();
        long start = System.currentTimeMillis();
        for(int i = 0; i< 20;i++){
            pool.submit(()-> {
                try{
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create("http://localhost:8080/slow"))
                            .build();
                    client.send(request, HttpResponse.BodyHandlers.ofString());}
                catch(Exception e){
                    System.out.println("错误：" + e.getMessage());
                    throw new RuntimeException(e);
                }
            });
        }
        pool.shutdown();
        pool.awaitTermination(1, TimeUnit.MINUTES);
        long end = System.currentTimeMillis();
        System.out.println(end- start);
    }
}