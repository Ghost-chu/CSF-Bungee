package com.ghostchu.csfbungee.logger;

import com.ghostchu.csfbungee.CSFBungee;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSFBungeeLogger implements Filter {
    private final CSFBungee plugin;
    private Configuration configuration;
    private List<String> filter;
    private final List<Pattern> filterPattern = new ArrayList<>();

    public CSFBungeeLogger(CSFBungee plugin) {
        this.plugin = plugin;
        reloadConfig();
    }

    public void reloadConfig(){
        if (!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdir();

        File file = new File(plugin.getDataFolder(), "config.yml");

        if (!file.exists()) {
            try (InputStream in = plugin.getResourceAsStream("config.yml")) {
                Files.copy(in, file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        filter = configuration.getStringList("Messages-To-Hide-Filter");
        filterPattern.clear();
        for (String s : filter) {
            if(s.startsWith("@")) {
                s = StringUtils.removeStart(s,"@");
                try {
                    filterPattern.add(Pattern.compile(s));
                } catch (java.util.regex.PatternSyntaxException compileException) {
                    // ignored
                }
            }
        }
    }

    /**
     * Inject our filter logger into any logger
     * @param logger logger
     */
    public void inject(Logger logger){
        if(logger.getFilter() != this && logger != plugin.getLogger()) {
            logger.setFilter(this);
            plugin.getLogger().info("Injected into logger: " + logger.getName());
        }
    }
    /**
     * Replace args in raw to args
     *
     * @param raw  text
     * @param args args
     * @return filled text
     */
    public static String fillArgs(String raw, Object[] args) {
        if (StringUtils.isEmpty(raw)) {
            return "";
        }
        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                raw = StringUtils.replace(raw, "{" + i + "}", args[i] == null ? "" : args[i].toString());
            }
        }
        return raw;
    }
    /**
     * Check if a given log record should be published.
     *
     * @param record a LogRecord
     * @return true if the log record should be published.
     */
    @Override
    public boolean isLoggable(LogRecord record) {
        String finalMessage = fillArgs(record.getMessage(),record.getParameters());
        for (String filterStr : filter) {
            if(finalMessage.contains(filterStr))
                return false;
        }
        for (Pattern pattern : filterPattern) {
            Matcher matcher = pattern.matcher(finalMessage);
            if(matcher.matches())
                return false;
            if(matcher.find())
                return false;
        }
        return true;
    }
}
