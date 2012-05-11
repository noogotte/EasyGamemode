package fr.noogotte.easygamemode;

import javax.xml.ws.Response;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.aumgn.bukkitutils.command.CommandsRegistration;
import fr.aumgn.bukkitutils.command.messages.FrenchMessages;
import fr.noogotte.easygamemode.commands.Commands;
import fr.noogotte.easygamemode.listener.SignListener;

public class EasyGamemode extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        SignListener signListener = new SignListener(this);
        pm.registerEvents(signListener, this);
        CommandsRegistration registration = new CommandsRegistration(
                this, new FrenchMessages());
        registration.register(new Commands());
    }
}