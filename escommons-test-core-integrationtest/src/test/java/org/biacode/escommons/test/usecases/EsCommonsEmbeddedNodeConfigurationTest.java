package org.biacode.escommons.test.usecases;

import org.biacode.escommons.test.helper.AbstractEsCommonsTestCoreIntegrationTest;
import org.elasticsearch.client.Client;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Arthur Asatryan.
 * Date: 2019-04-06
 * Time: 20:07
 */
public class EsCommonsEmbeddedNodeConfigurationTest extends AbstractEsCommonsTestCoreIntegrationTest {

    //region Dependencies
    @Autowired
    private Client client;
    //endregion

    //region Test methods
    @Test
    public void testEmbeddedServer() {
        assertThat(client).isNotNull();
    }
    //endregion

}
