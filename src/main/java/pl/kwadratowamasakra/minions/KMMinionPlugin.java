/*
 *
 *  * Copyright 2023 ~Author: radek203
 *
 */

package pl.kwadratowamasakra.minions;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.kwadratowamasakra.minions.commands.MinionCommand;
import pl.kwadratowamasakra.minions.listeners.*;
import pl.kwadratowamasakra.minions.methods.Minion;
import pl.kwadratowamasakra.minions.methods.ServerHelper;

import java.util.ArrayList;
import java.util.List;

public class KMMinionPlugin extends JavaPlugin {

    private final ServerHelper serverHelper = new ServerHelper();

    @Override
    public final void onEnable() {
        new AnimationRunner(this);
        new DiscoArmorTask(this);

        Bukkit.getPluginManager().registerEvents(new BlockListeners(this), this);
        Bukkit.getPluginManager().registerEvents(new ArmorStandClick(this), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(this), this);

        getCommand("minion").setExecutor(new MinionCommand(this));
    }

    @Override
    public final void onDisable() {
        //TODO Add a data storage system
        final List<Minion> toRemove = new ArrayList<>();
        for (final Minion minion : serverHelper.getMinions()) {
            minion.destroyMinion();
            toRemove.add(minion);
        }
        for (final Minion minion : toRemove) {
            serverHelper.removeMinion(minion);
        }
    }

    public final ServerHelper getServerHelper() {
        return serverHelper;
    }
}
