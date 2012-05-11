package fr.noogotte.easygamemode.listener;

import org.bukkit.ChatColor;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import fr.noogotte.easygamemode.EasyGamemode;
import fr.noogotte.easygamemode.commands.Commands;

public class SignListener implements Listener {
    public EasyGamemode plugin;

    public SignListener(EasyGamemode easygamemode) {
        this.plugin = easygamemode;
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onSignChange(SignChangeEvent event) {
        Player player = event.getPlayer();
        
        if (!event.getLine(0).toLowerCase().contains("easyg")) {
            return;
        }

        if (!player.hasPermission("easyG.SignPlace")) {
            event.getBlock().breakNaturally();
            player.sendMessage(ChatColor.RED + "");
            return;
        }

        event.setLine(0, ChatColor.RED + "[EasyG]");
        event.setLine(1, ChatColor.GREEN + "Changement de");
        event.setLine(2, ChatColor.GREEN + "Gamemode");
        player.sendMessage(ChatColor.BLUE + "Vous avez crée un panneau de changement de gamemode !");
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerInteract(PlayerInteractEvent event) {
    	
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        if (!(event.getClickedBlock().getState() instanceof Sign)) {
            return;
        }

        Sign sign = (Sign) event.getClickedBlock().getState();
        String l1 = sign.getLine(0);
        if (!l1.contains("[EasyG]")) {
            return;
        }

        Player player = event.getPlayer();

        if (!player.hasPermission("easyG.SignUse")) {
            player.sendMessage(ChatColor.RED + "");
            return;
        }

        Commands.toggleGameMode(player);
    }
}