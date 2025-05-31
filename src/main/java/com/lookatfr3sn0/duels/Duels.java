package com.lookatfr3sn0.duels;

import org.bukkit.plugin.java.JavaPlugin;

public final class Duels extends JavaPlugin {

    private static Duels instance;

    @Override
    public void onEnable() {
        instance = this;
        DuelManager.init(this);
        getCommand("duel").setExecutor(new DuelCommand());
        getServer().getPluginManager().registerEvent(new DuelListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Duels getInstance() {
        return instance;
    }
}
