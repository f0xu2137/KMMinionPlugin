package pl.kwadratowamasakra.minions;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.kwadratowamasakra.minions.commands.MinionCommand;
import pl.kwadratowamasakra.minions.listeners.*;
import pl.kwadratowamasakra.minions.methods.ServerHelper;

public class KMMinionPlugin extends JavaPlugin {

    private final ServerHelper serverHelper = new ServerHelper();

    @Override
    public final void onEnable() {
        new AnimationRunner(this);
        new DiscoArmorTask(this);

        Bukkit.getPluginManager().registerEvents(new PlaceListener(this), this);
        Bukkit.getPluginManager().registerEvents(new ArmorStandClick(this), this);
        Bukkit.getPluginManager().registerEvents(new InventoryListener(this), this);

        getCommand("minion").setExecutor(new MinionCommand(this));
    }

    @Override
    public final void onDisable() {

    }

    public final ServerHelper getServerHelper() {
        return serverHelper;
    }
}
