package com.seedollar.sandbox.springcloud.aggregation;

import com.seedollar.sandbox.springcloud.SpringCloudStreamAggregationProcessorApplication;
import com.seedollar.sandbox.springcloud.SpringCloudStreamAggregationSinkApplication;
import com.seedollar.sandbox.springcloud.SpringCloudStreamAggregationSourceApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.aggregate.AggregateApplicationBuilder;

/**
 * Created by seedollar on 7/21/17.
 */
@SpringBootApplication
public class SpringCloudStreamAggregationBuilderApplication {

    public static void main(String[] args) {
        new AggregateApplicationBuilder()
                .from(SpringCloudStreamAggregationSourceApplication.class).namespace("source").args("--fixedDelay=4000")
                .via(SpringCloudStreamAggregationProcessorApplication.class)
                .to(SpringCloudStreamAggregationSinkApplication.class).namespace("summary").run(args);
    }
}
