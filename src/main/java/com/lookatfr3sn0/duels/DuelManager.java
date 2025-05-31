package com.lookatfr3sn0.duels;

import org.bukkit.entity.Player;

import java.util.*;

public class DuelManager {
    private static Duels plugin;
    private static final Queue<Player> queue = new LinkedList<>();
    private static final Map<Player, Player> ongoingDuels = new HashMap<>();

    public static void init(Duels instance) {
        plugin = instance;
    }

    public static void queuePlayer(Player player) {
        if (queue.contains(player) || ongoingDuels.containsKey(player)) {
            player.sendMessage("&cYou cannot queue at this time.");
            return;
        }

        queue.add(player);
        player.sendMessage("&aYou are now in the duel queue. Waiting for an opponent...");

        if (queue.size() >= 2) {
            Player opponent = queue.poll();
            Player challenger = queue.poll();
            startDuel(challenger, opponent);
        }
    }

    public  static void leaveQueue(Player player) {
        if (queue.remove(player)) {
            player.sendMessage( "&aYou have left the duel queue.");
        } else {
            player.sendMessage( "&cYou are not in the duel queue.");
        }
    }

    public static void startDuel(Player p1, Player p2) {
        ongoingDuels.put(p1, p2);
        ongoingDuels.put(p2, p1);

        /*
        p1.teleport(arena);
        p2.teleport(arena);
        */
        giveKit(p1, "default");
        giveKit(p2, "default");

        p1.sendMessage("&aDuel started against " + p2.getName() + "!");
        p2.sendMessage("&cDuel started against " + p1.getName() + "!");
    }

    public static void endDuel(Player loser) {
        Player winner = ongoingDuels.get(loser);
        ongoingDuels.remove(winner);
        ongoingDuels.remove(loser);

        winner.sendMessage("&aYou won the duel");
        loser.sendMessage("&cYou lost the duel");

        /*
        winner.teleport(spawn);
        loser.teleport(spawn);
        */
    }

    public static void giveKit(Player player, String kit) {
        player.getInventory().clear();
        //todo Add kits retrieval logic
    }

    public static boolean isInDuel(Player player) {
        return ongoingDuels.containsKey(player);
    }
}
