package com.ashwin.dropwizarddemo;

import com.ashwin.dropwizarddemo.health.TemplateHealthCheck;
import com.ashwin.dropwizarddemo.resources.HelloWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "helloworld";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        // Do nothing
    }

    @Override
    public void run(HelloWorldConfiguration config, Environment environment) throws Exception {
        final TemplateHealthCheck healthCheck = new TemplateHealthCheck(config.getTemplate());
        environment.healthChecks().register("template", healthCheck);

        final HelloWorldResource helloWorldResource = new HelloWorldResource(config.getTemplate(), config.getDefaultName());
        environment.jersey().register(helloWorldResource);
    }
}
