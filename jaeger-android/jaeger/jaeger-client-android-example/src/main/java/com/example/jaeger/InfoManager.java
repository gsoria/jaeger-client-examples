package com.example.jaeger;

import com.google.common.collect.ImmutableMap;

import io.jaegertracing.internal.JaegerTracer;
import io.opentracing.Scope;
import io.opentracing.Span;

public class InfoManager {

    private final JaegerTracer tracer;

    InfoManager(JaegerTracer tracer){
        this.tracer = tracer;
    }

    public void getInfo() {
        Span span = tracer.buildSpan("get-info").start();
        String brand = getBrand(span);
        span.setTag("brand", brand);

        String model = getModel(span);
        span.setTag("model", model);
        span.finish();
    }
    private String  getBrand(Span rootSpan){
        Span span = tracer.buildSpan("get-brand").asChildOf(rootSpan).startManual();
        //Scope scope = tracer.buildSpan("get-brand").startActive(true);
        String brand = android.os.Build.BRAND;
        //scope.span().log(ImmutableMap.of("event", "get-brand", "value", brand));
        span.log(ImmutableMap.of("event", "get-brand", "value", brand));
        span.finish();
        return brand;
    }

    private String  getModel(Span rootSpan){
        //Scope scope = tracer.buildSpan("get-model").startActive(true);
        Span span = tracer.buildSpan("get-model").asChildOf(rootSpan).startManual();
        String model = android.os.Build.MODEL;
        //scope.span().log(ImmutableMap.of("event", "get-model", "value", model));
        span.log(ImmutableMap.of("event", "get-model", "value", model));
        span.finish();
        return model;
    }
}
