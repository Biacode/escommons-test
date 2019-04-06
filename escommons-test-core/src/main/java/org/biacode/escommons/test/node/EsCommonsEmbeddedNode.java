package org.biacode.escommons.test.node;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.InternalSettingsPreparer;
import org.elasticsearch.node.Node;
import org.elasticsearch.plugins.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

/**
 * Created by Arthur Asatryan.
 * Date: 2019-04-06
 * Time: 18:51
 */
public class EsCommonsEmbeddedNode extends Node {

    private static final Logger LOGGER = LoggerFactory.getLogger(EsCommonsEmbeddedNode.class);

    //region Constructors
    EsCommonsEmbeddedNode(final Settings settings, final Collection<Class<? extends Plugin>> classpathPlugins) {
        super(InternalSettingsPreparer.prepareEnvironment(settings, null), classpathPlugins, true);
        LOGGER.info("Initializing EsCommonsEmbeddedNode with settings - {} classpathPlugins - {}", settings, classpathPlugins);
    }
    //endregion

    //region Public methods
    @Override
    protected void registerDerivedNodeNameWithLogger(final String nodeName) {
        LOGGER.info("Registering derived node name - {}", nodeName);
    }
    //endregion
}
