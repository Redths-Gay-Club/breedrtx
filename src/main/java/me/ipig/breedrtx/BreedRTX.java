package me.ipig.breedrtx;

import me.ipig.breedrtx.listeners.BreedListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class BreedRTX extends JavaPlugin {
    private static BreedRTX plugin;
    @Override
    public void onEnable() {

        plugin = this;
        getServer().getPluginManager().registerEvents(new BreedListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    public static BreedRTX getPlugin() {
        return plugin;
    }
}
