package xyz.regulad.velocitygradletemplate;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import org.bstats.velocity.Metrics;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.logging.Logger;

@Plugin(id = "velocitytemplate", name = "VelocityTemplate", version = "1.0.0-SNAPSHOT", description = "A template for Minecraft Velocity Proxy Plugins with Gradle.", url = "https://github.com/regulad/VelocityGradleTemplate", authors = {"regulad"})
public class VelocityTemplate {
    @Inject
    private @NotNull ProxyServer proxyServer;
    @Inject
    private @NotNull Logger logger;
    @Inject
    private @NotNull Metrics.Factory metricsFactory;
    @Inject
    @DataDirectory
    private @NotNull Path path;

    @Subscribe
    public void onProxyInitialization(final @NotNull ProxyInitializeEvent proxyInitializeEvent) {
        final @NotNull Metrics metrics = this.metricsFactory.make(this, 13686);
    }
}
