
package com.example.rulesplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public class RulesPlugin extends JavaPlugin implements CommandExecutor {

    private String rules = "No rules have been set yet! Use /createrules to set the rules.";

    @Override
    public void onEnable() {
        // Register commands
        this.getCommand("rules").setExecutor(this);
        this.getCommand("createrules").setExecutor(this);
        getLogger().info("RulesPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("RulesPlugin has been disabled!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("rules")) {
            sender.sendMessage(ChatColor.GREEN + "Server Rules: " + ChatColor.WHITE + rules);
            return true;
        } else if (command.getName().equalsIgnoreCase("createrules") || command.getName().equalsIgnoreCase("crules")) {
            if (!sender.hasPermission("rulesplugin.createrules")) {
                sender.sendMessage(ChatColor.RED + "You do not have permission to create rules!");
                return true;
            }
            if (args.length == 0) {
                sender.sendMessage(ChatColor.RED + "Please provide the rules to set.");
                return true;
            }
            // Update the rules
            rules = String.join(" ", args);
            sender.sendMessage(ChatColor.GREEN + "Rules have been updated to: " + ChatColor.WHITE + rules);
            return true;
        }
        return false;
    }
}
