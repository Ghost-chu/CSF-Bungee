package com.ghostchu.csfbungee;

import com.ghostchu.csfbungee.logger.CSFBungeeLogger;
import net.md_5.bungee.api.event.ProxyReloadEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

import java.util.Timer;
import java.util.TimerTask;

public final class CSFBungee extends Plugin implements Listener {
    private CSFBungeeLogger replaceLogger;
    private final Timer timer = new Timer("logger replace daemon", true);
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Starting up...");
        getLogger().info("Please do not go and ask brunyman to support this. This plugin has no affiliation with brunyman.");
        getProxy().getPluginManager().registerCommand(this,new CSFReloadCommand(this));
        getLogger().info("Please wait... Logger filter injecting...");
        replaceLogger = new CSFBungeeLogger(this);
        replaceLogger.reloadConfig();
        getProxy().getPluginManager().registerListener(this,this);
        replaceLogger.inject(getLogger());
        scanAndInject();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() { // BungeeCord no event for hot-loading plugins, so we need watch all plugins state by our self
                scanAndInject();
            }
        }, 0, 1000);
        getLogger().info("CSFBungee now loaded and start working!");
    }

    public CSFBungeeLogger getReplaceLogger() {
        return replaceLogger;
    }

    public void scanAndInject(){
        getProxy().getPluginManager().getPlugins().forEach(plugin -> replaceLogger.inject(plugin.getLogger()));
        replaceLogger.inject(getProxy().getLogger());
    }

    @EventHandler
    public void onPluginLoaded(ProxyReloadEvent event){
        getProxy().getPluginManager().getPlugins().forEach(plugin -> replaceLogger.inject(plugin.getLogger()));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Cleaning up the loggers...");
        timer.cancel();
        getProxy().getPluginManager().getPlugins().forEach(plugin -> plugin.getLogger().setFilter(null));
        getProxy().getLogger().setFilter(null);
    }
}
