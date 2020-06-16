package util;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.util.concurrent.ScheduledExecutorService;

public class HttpClientClient {
    PoolingHttpClientConnectionManager manager=null;
    ScheduledExecutorService monitorExcutor;

}
