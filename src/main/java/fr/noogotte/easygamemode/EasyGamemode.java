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

public class EasyGamemode extends JavaPlugin {

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        SignListener signListener = new SignListener(this);
        pm.registerEvents(signListener, this);
        getCommand("easy-gamemode").
            setPermissionMessage(getPermissionMessage());
    }

    public String getPermissionMessage() {
        return ChatColor.RED + "Vous n'avez pas la permission !";
    }

    public void toggleGameMode(Player player) {
        if (player.getGameMode() == GameMode.CREATIVE) {
            player.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(ChatColor.AQUA + "Votre gamemode est maintenant en " +
                    ChatColor.GOLD + "SURVIVAL");
        } else { 
            player.setGameMode(GameMode.CREATIVE);
            player.sendMessage(ChatColor.AQUA + "Votre gamemode est maintenant en " +
                    ChatColor.GOLD + "CREATIVE");
        }
    }

    public void toggleGameMode(Player responsible, Player target) {
        if (target.getGameMode() == GameMode.CREATIVE) {
            target.setGameMode(GameMode.SURVIVAL);
            target.sendMessage(ChatColor.AQUA + "Le gamemode de " + 
            		ChatColor.DARK_AQUA + target.getName()+ 
                    ChatColor.AQUA + " est maintenant en " +
                    ChatColor.GOLD + "SURVIVAL");
            responsible.sendMessage(ChatColor.GOLD + "Vous avez changer le gamemode de "
                    + ChatColor.BLUE + target.getName());
        } else {
            target.setGameMode(GameMode.CREATIVE);
            target.sendMessage(ChatColor.AQUA + "Le gamemode de " +
                    ChatColor.DARK_AQUA + target.getName() +
                    ChatColor.AQUA + " est maintenant en " +
                    ChatColor.GOLD + "CREATIVE");
            responsible.sendMessage(ChatColor.GOLD + "Vous avez chang� le gamemode de " +
            		ChatColor.DARK_AQUA + target.getName());
        }
    }
    
    public void fly(Player player) {
    	player.setFlying(true);
    	player.sendMessage(ChatColor.BLUE + "Vous etes en mode vol");
    	
    }
    
    public void fly(Player responsible, Player target) {
    	target.setFlying(true);
    	responsible.sendMessage(ChatColor.BLUE + "Vous avez mis" + ChatColor.BOLD+ target.getName() + ChatColor.BLUE +"en mode fly");
    	
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Commande utilisable seulement en tant que joueur");
            return true;
        }

        if (args.length > 2) {
            return false;
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            if(player.hasPermission("easyG.cmd.me")) {
                toggleGameMode(player);
            } else {
                player.sendMessage(getPermissionMessage());
            }
        } else if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            
            if(player.hasPermission("easyG.cmd.other")) {
                if (target == null) {
                    player.sendMessage(ChatColor.RED + "Le joueur " +
                            ChatColor.DARK_AQUA + args[0] +
                            ChatColor.RED + " n'existe pas !");
                } else {
                    toggleGameMode(player, target);
                }
            } else {
                player.sendMessage(getPermissionMessage());
            }
        } 
        return true;
    }
}