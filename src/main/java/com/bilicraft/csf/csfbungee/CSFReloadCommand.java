package com.bilicraft.csf.csfbungee;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class CSFReloadCommand extends Command {
    private final CSFBungee plugin;
    /**
     * Construct a new command with no permissions or aliases.
     * @param plugin CSFBungee instance
     */
    public CSFReloadCommand(CSFBungee plugin) {
        super("csfreload","csfbungee.reload");
        this.plugin = plugin;
    }

    /**
     * Execute this command with the specified sender and arguments.
     *
     * @param sender the executor of this command
     * @param args   arguments used to invoke this command
     */
    @Override
    public void execute(CommandSender sender, String[] args) {
        this.plugin.getReplaceLogger().reloadConfig();
        sender.sendMessage(TextComponent.fromLegacyText("CSF-Bungee reloaded."));
    }
}
