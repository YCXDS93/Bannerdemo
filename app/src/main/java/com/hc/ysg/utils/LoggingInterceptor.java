package com.hc.ysg.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
/**
 * 时间：2017/9/22、
 * 姓名：于帅光
 * 用途：日志拦截器
 */
public class LoggingInterceptor implements Interceptor {
        @Override public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();


            long t1 = System.nanoTime();

            System.out.println(" request  = " + String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);

            long t2 = System.nanoTime();

            //得出请求网络,到得到结果,中间消耗了多长时间
            System.out.println("response  " + String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    }