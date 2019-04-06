package org.biacode.escommons.test.helper;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Created by Arthur Asatryan.
 * Date: 2019-04-06
 * Time: 20:17
 */
@ContextConfiguration(classes = {EsCommonsTestIntegrationTestConfiguration.class})
public abstract class AbstractEsCommonsTestCoreIntegrationTest extends AbstractJUnit4SpringContextTests {
}
