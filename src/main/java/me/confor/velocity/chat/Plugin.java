package me.confor.velocity.chat;

import com.google.inject.Inject;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import me.confor.velocity.chat.modules.GlobalChat;
import org.slf4j.Logger;

import java.nio.file.Path;

@com.velocitypowered.api.plugin.Plugin(
        id = "chat",
        name = "Chat",
        version = BuildConstants.VERSION,
        authors = {"confor"}
)
public class Plugin {

    private final ProxyServer server;
    private final Logger logger;

    Config config;

    @Inject
    public Plugin(ProxyServer server, Logger logger, @DataDirectory Path dataDirectory) {
        this.server = server;
        this.logger = logger;

        logger.info("Loading plugin...");
        this.config = new Config(dataDirectory);
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        this.register(new GlobalChat(this.server, this.logger, this.config));
    }

    private void register(Object x) {
        this.server.getEventManager().register(this, x);
    }
}
