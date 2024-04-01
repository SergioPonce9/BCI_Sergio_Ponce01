/**
 * COPYRIGHT (C) 2015 Coopeuch Ltda.
 * All Rights Reserved.
 */
package cl.bci;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket cpbApi() {
        String packageName = getClass().getPackage().getName();
        packageName = packageName.substring(0, packageName.lastIndexOf('.'));
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(packageName))
                .build();
    }
}
