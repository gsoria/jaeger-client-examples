package com.example.jaeger;

import io.jaegertracing.Configuration;
import io.jaegertracing.Configuration.ReporterConfiguration;
import io.jaegertracing.Configuration.SamplerConfiguration;
import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.samplers.ConstSampler;

public final class Tracing {

    public static final Integer JAEGER_UDP_DEFAULT_PORT=6831;

    private Tracing() {
    }

    public static JaegerTracer init(String service, String hostname, Boolean useThriftUDP) {
        SamplerConfiguration samplerConfig = SamplerConfiguration.fromEnv()
                .withType(ConstSampler.TYPE)
                .withParam(1);

        ReporterConfiguration reporterConfig;

        if(useThriftUDP){
            reporterConfig = new ReporterConfiguration()
                    .withSender(new Configuration.SenderConfiguration()
                            .withAgentHost(hostname)
                            .withAgentPort(JAEGER_UDP_DEFAULT_PORT))
                    .withLogSpans(true);

        } else{
           reporterConfig = new ReporterConfiguration().withSender(new Configuration.SenderConfiguration().withEndpoint("http://" + hostname + ":14268/api/traces"))
                .withLogSpans(true);
        }

        Configuration config = new Configuration(service)
                .withSampler(samplerConfig)
                .withReporter(reporterConfig);

        return config.getTracer();
    }
}