package com.lookatfr3sn0.duels;

import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class DuelListener implements Listener {
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (DuelManager.isInDuel(e.getPlayer())) {
            DuelManager.endDuel(e.getEntity());
        }
    }

    public void onPlayerQuit(PlayerQuitEvent e) {
        if (DuelManager.isInDuel(e.getPlayer())) {
            DuelManager.endDuel(e.getPlayer());
        } else {
            DuelManager.leaveQueue(e.getPlayer());
        }
    }
}
