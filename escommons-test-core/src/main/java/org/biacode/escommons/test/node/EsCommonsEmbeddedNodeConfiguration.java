package org.biacode.escommons.test.node;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeValidationException;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.transport.Netty4Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Collections;

import static java.lang.String.format;

/**
 * Created by Arthur Asatryan.
 * Date: 2019-04-06
 * Time: 19:00
 */
@Configuration
public class EsCommonsEmbeddedNodeConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(EsCommonsEmbeddedNodeConfiguration.class);

    //region Dependencies
    @Value("${escommons.host}")
    private String esCommonsHost = "localhost";

    @Value("${escommons.port}")
    private Integer esCommonsPort = 9372;

    @Value("${escommons.cluster.name}")
    private String esCommonsClusterName = "escommons";

    @Value("${escommons.path.home}")
    private String esCommonsPathHome = "/tmp/escommons/home";

    @Value("${escommons.path.data}")
    private String esCommonsPathData = "/tmp/escommons/data";

    @Value("${escommons.path.repo}")
    private String esCommonsPathRepo = "/tmp/escommons/repo";

    @Value("${escommons.http.type}")
    private String esCommonsHttpType = "netty4";

    @Value("${escommons.http.host}")
    private String esCommonsHttpHost = "localhost";

    @Value("${escommons.http.port}")
    private Integer esCommonsHttpPort = 9200;

    @Value("${escommons.http.enabled}")
    private Boolean esCommonsHttpEnabled = true;

    @Value("${escommons.node.max_local_storage_nodes}")
    private Integer esCommonsNodeMaxLocalStorageNodes = 10;

    @Value("${escommons.plugins}")
    private Collection<Class<? extends Plugin>> classpathPlugins = Collections.singletonList(Netty4Plugin.class);
    //endregion

    //region Constructors
    public EsCommonsEmbeddedNodeConfiguration() {
        LOGGER.debug("Initializing - {} with default configurations - {}", getClass().getCanonicalName(), this.toString());
    }
    //endregion

    //region Public methods
    @Bean
    public Client embeddedServer() {
        final Settings settings = Settings.builder()
                .put("cluster.name", esCommonsClusterName)
                .put("path.home", esCommonsPathHome)
                .put("path.data", esCommonsPathData)
                .put("path.repo", esCommonsPathRepo)
                .put("network.host", esCommonsHost)
                .put("transport.tcp.port", esCommonsPort)
                .put("http.type", esCommonsHttpType)
                .put("http.host", esCommonsHttpHost)
                .put("http.port", esCommonsHttpPort)
                .put("http.enabled", esCommonsHttpEnabled)
                .put("node.max_local_storage_nodes", esCommonsNodeMaxLocalStorageNodes)
                .build();
        final EsCommonsEmbeddedNode node = new EsCommonsEmbeddedNode(settings, classpathPlugins);
        try {
            final Node startedNode = node.start();
            Runtime.getRuntime().addShutdownHook(new Thread(() -> IOUtils.closeQuietly(startedNode)));
            return startedNode.client();
        } catch (final NodeValidationException e) {
            LOGGER.error("NodeValidationException occurs when trying to start a node - {}", node);
            throw new IllegalStateException(format("NodeValidationException occurs when trying to start a node - %s", node), e);
        } finally {
            IOUtils.closeQuietly(node);
        }
    }
    //endregion

    //region Properties getters and setters
    public String getEsCommonsHost() {
        return esCommonsHost;
    }

    public void setEsCommonsHost(final String esCommonsHost) {
        this.esCommonsHost = esCommonsHost;
    }

    public Integer getEsCommonsPort() {
        return esCommonsPort;
    }

    public void setEsCommonsPort(final Integer esCommonsPort) {
        this.esCommonsPort = esCommonsPort;
    }

    public String getEsCommonsClusterName() {
        return esCommonsClusterName;
    }

    public void setEsCommonsClusterName(final String esCommonsClusterName) {
        this.esCommonsClusterName = esCommonsClusterName;
    }

    public String getEsCommonsPathHome() {
        return esCommonsPathHome;
    }

    public void setEsCommonsPathHome(final String esCommonsPathHome) {
        this.esCommonsPathHome = esCommonsPathHome;
    }

    public String getEsCommonsPathData() {
        return esCommonsPathData;
    }

    public void setEsCommonsPathData(final String esCommonsPathData) {
        this.esCommonsPathData = esCommonsPathData;
    }

    public String getEsCommonsPathRepo() {
        return esCommonsPathRepo;
    }

    public void setEsCommonsPathRepo(final String esCommonsPathRepo) {
        this.esCommonsPathRepo = esCommonsPathRepo;
    }

    public String getEsCommonsHttpType() {
        return esCommonsHttpType;
    }

    public void setEsCommonsHttpType(final String esCommonsHttpType) {
        this.esCommonsHttpType = esCommonsHttpType;
    }

    public String getEsCommonsHttpHost() {
        return esCommonsHttpHost;
    }

    public void setEsCommonsHttpHost(final String esCommonsHttpHost) {
        this.esCommonsHttpHost = esCommonsHttpHost;
    }

    public Integer getEsCommonsHttpPort() {
        return esCommonsHttpPort;
    }

    public void setEsCommonsHttpPort(final Integer esCommonsHttpPort) {
        this.esCommonsHttpPort = esCommonsHttpPort;
    }

    public Boolean getEsCommonsHttpEnabled() {
        return esCommonsHttpEnabled;
    }

    public void setEsCommonsHttpEnabled(final Boolean esCommonsHttpEnabled) {
        this.esCommonsHttpEnabled = esCommonsHttpEnabled;
    }

    public Integer getEsCommonsNodeMaxLocalStorageNodes() {
        return esCommonsNodeMaxLocalStorageNodes;
    }

    public void setEsCommonsNodeMaxLocalStorageNodes(final Integer esCommonsNodeMaxLocalStorageNodes) {
        this.esCommonsNodeMaxLocalStorageNodes = esCommonsNodeMaxLocalStorageNodes;
    }

    public Collection<Class<? extends Plugin>> getClasspathPlugins() {
        return classpathPlugins;
    }

    public void setClasspathPlugins(final Collection<Class<? extends Plugin>> classpathPlugins) {
        this.classpathPlugins = classpathPlugins;
    }
    //endregion

    //region Equals, HashCode and ToString
    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("esCommonsHost", esCommonsHost)
                .append("esCommonsPort", esCommonsPort)
                .append("esCommonsClusterName", esCommonsClusterName)
                .append("esCommonsPathHome", esCommonsPathHome)
                .append("esCommonsPathData", esCommonsPathData)
                .append("esCommonsPathRepo", esCommonsPathRepo)
                .append("esCommonsHttpType", esCommonsHttpType)
                .append("esCommonsHttpHost", esCommonsHttpHost)
                .append("esCommonsHttpPort", esCommonsHttpPort)
                .append("esCommonsHttpEnabled", esCommonsHttpEnabled)
                .append("esCommonsNodeMaxLocalStorageNodes", esCommonsNodeMaxLocalStorageNodes)
                .append("classpathPlugins", classpathPlugins)
                .toString();
    }
    //endregion

}
