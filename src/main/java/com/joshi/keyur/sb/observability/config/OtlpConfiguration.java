package com.joshi.keyur.sb.observability.config;

import io.opentelemetry.exporter.otlp.http.metrics.OtlpHttpMetricExporter;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class OtlpConfiguration {
    @Bean
    OtlpHttpSpanExporter buildOtlpHttpSpanExporter(@Value("${tracing.url}")String tracingUrl){
        return OtlpHttpSpanExporter.builder()
                .setEndpoint(tracingUrl)
                .build();
    }
    @Bean
    OtlpHttpMetricExporter buildOtlpHttpMetricExporter(@Value("${tracing.url}")String tracingUrl){
        return OtlpHttpMetricExporter.builder()
                .setEndpoint(tracingUrl)
                .build();
    }

}
