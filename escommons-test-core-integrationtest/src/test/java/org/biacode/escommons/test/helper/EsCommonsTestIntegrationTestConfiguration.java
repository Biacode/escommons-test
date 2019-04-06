package org.biacode.escommons.test.helper;

import org.biacode.escommons.test.configuration.EsCommonsTestAnnotationDrivenConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by Arthur Asatryan.
 * Date: 2019-04-06
 * Time: 20:29
 */
@Configuration
@Import(EsCommonsTestAnnotationDrivenConfiguration.class)
@ComponentScan(basePackages = {
        "org.biacode.escommons.test.helper",
        "org.biacode.escommons.test.usecases"
})
public class EsCommonsTestIntegrationTestConfiguration {
}
