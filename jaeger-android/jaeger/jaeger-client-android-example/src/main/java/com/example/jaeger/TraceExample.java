package com.example.jaeger;

import io.jaegertracing.internal.JaegerTracer;

public class TraceExample {

    public void createExampleTrace(String service, String hostname, Boolean useTrift) {
        JaegerTracer tracer = Tracing.init(getService(service, useTrift), hostname, useTrift);
        new InfoManager(tracer).getInfo();
        tracer.close();
    }

    private static String getService(String service, Boolean useTrift) {
        if (useTrift) {
            service = service + "-udp";
        } else {
            service = service + "-http";
        }
        return service;
    }

    public static TraceExample build(){
        return new TraceExample();
    }
}
