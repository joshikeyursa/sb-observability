package com.joshi.keyur.sb.observability.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
@Configuration
public class OtlpConfiguration {
    @Bean
    OtlpHttpSpanExporter buildOtlpHttpSpanExporter(@Value("${tracing.url}")String tracingUrl){
        return OtlpHttpSpanExporter.builder()
                .setEndpoint(tracingUrl)
                .build();
    }
}
