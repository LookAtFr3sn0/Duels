package com.lookatfr3sn0.duels;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DuelCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return false;

        if (label.equalsIgnoreCase("duel")) {
            if (args.length == 1) {
                Player target = sender.getServer().getPlayer(args[0]);
                if (target == null || !target.isOnline() || target == sender) {
                    sender.sendMessage("§cPlayer not found or invalid.");
                    return true;
                }
                // You can implement direct challenge logic here
                sender.sendMessage("§aYou challenged " + target.getName() + " to a duel!");
                // For now, just queue the player
                DuelManager.queuePlayer((Player) sender);
            } else {
                DuelManager.queuePlayer((Player) sender);
            }
            return true;
        }
        return false;
    }
}
