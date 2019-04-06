package org.biacode.escommons.test.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by Arthur Asatryan.
 * Date: 2019-04-06
 * Time: 19:46
 */
@Configuration
@ComponentScan(basePackages = {
        "org.biacode.escommons.test.configuration",
        "org.biacode.escommons.test.node"
})
@PropertySource(value = {
        "classpath:escommons-test.properties",
        "file:${user.home}/escommons-test.properties",
        "file:${user.home}/escommons/escommons-test.properties"
}, ignoreResourceNotFound = true)
public class EsCommonsTestAnnotationDrivenConfiguration {
}
