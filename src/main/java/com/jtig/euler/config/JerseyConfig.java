package com.jtig.euler.config;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

@Component
@ApplicationPath("api")
//@ComponentScan({"com.jtig.euler.config"})
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        packages("com.jtig.euler");
        configureSwagger();
    }

    private void configureSwagger() {
        register(ApiListingResource.class);
        register(SwaggerSerializers.class);

        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setBasePath("/api");
        beanConfig.setResourcePackage("com.jtig.euler.service");
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan(true);
    }
}
