package fr.noogotte.easygamemode.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import fr.aumgn.bukkitutils.command.Command;
import fr.aumgn.bukkitutils.command.CommandArgs;
import fr.aumgn.bukkitutils.command.NestedCommands;
import fr.aumgn.bukkitutils.command.exception.CommandUsageError;
import fr.noogotte.easygamemode.exception.NoTargetInServer;

@NestedCommands(name = "easygamemode")
public class Commands extends EasyGamemodeCommand {
	
	@Command(name = "gamemode", min = 0, max = 1)
	public void gamemodeCommand(Player player, CommandArgs args) {
		if(args.length() == 0) {
			toggleGameMode(player);
		} else if (args.length() == 1) {
			Player target = Bukkit.getPlayer(args.get(0));
			if(target == null) {
				throw new NoTargetInServer();
			} else {
				toggleGameMode(player, args);
			}
		} else {
			throw new CommandUsageError();
		}
	}
	
	public static void toggleGameMode(Player player) {
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

    public void toggleGameMode(Player player, CommandArgs args) {
    	Player target = Bukkit.getPlayer(args.get(0));
        if (target.getGameMode() == GameMode.CREATIVE) {
            target.setGameMode(GameMode.SURVIVAL);
            player.sendMessage(ChatColor.GOLD + "Vous avez changer le gamemode de "
                    + ChatColor.BLUE + target.getName());
        } else {
            target.setGameMode(GameMode.CREATIVE);
            player.sendMessage(ChatColor.GOLD + "Vous avez chang� le gamemode de " +
            		ChatColor.DARK_AQUA + target.getName());
        }
    }
}
