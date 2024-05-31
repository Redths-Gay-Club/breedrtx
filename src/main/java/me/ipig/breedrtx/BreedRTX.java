package me.ipig.breedrtx;

import me.ipig.breedrtx.listeners.BreedListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class BreedRTX extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BreedListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
