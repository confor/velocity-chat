package me.confor.velocity.chat;

import com.google.inject.Inject;
import com.moandjiezana.toml.Toml;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
    //private final ProxyServer server;
    private final Logger logger;
    static final long CONFIG_VERSION = 2;

    Path dataDir;
    Toml toml;

    @Inject
    public Config(ProxyServer server, Logger logger, @DataDirectory Path dataDir) {
        //this.server = server;
        this.logger = logger;
        this.dataDir = dataDir;

        loadFile();
    }

    private void loadFile() {
        File dataDirectoryFile = this.dataDir.toFile();
        if (!dataDirectoryFile.exists())
            dataDirectoryFile.mkdir(); // TODO ensure it succeeds

        File dataFile = new File(dataDirectoryFile, "config.toml");

        // create default config if it doesn't exist
        if (!dataFile.exists()) {
            try {
                InputStream in = this.getClass().getResourceAsStream("/config.toml");
                Files.copy(in, dataFile.toPath());
            } catch (IOException e) {
                logger.error("Can't write default configuration file, filesystem/permissions error?");
                throw new RuntimeException("Can't write config");
            }
        }

        this.toml = new Toml().read(dataFile);

        // make sure the config makes sense for the current plugin's version
        long version = this.toml.getLong("config_version", 0L);
        if (version != CONFIG_VERSION) {
            logger.error("ERROR: config.toml uses an unknown version number (!= " + CONFIG_VERSION + ")");
            throw new RuntimeException("Can't use the existing configuration file: version mismatch (intended for another, older version?)");
        }
    }

    public Boolean getBool(String key) {
        return this.toml.getBoolean(key);
    }

    public String getString(String key) {
        return this.toml.getString(key);
    }
}
