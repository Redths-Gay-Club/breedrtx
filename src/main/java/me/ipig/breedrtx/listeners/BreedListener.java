package me.ipig.breedrtx.listeners;

import me.ipig.breedrtx.BreedRTX;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityEnterLoveModeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.io.BukkitObjectInputStream;


public class BreedListener implements Listener {
    BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
    boolean canBreed = false;

    @EventHandler
    public void onEat(PlayerItemConsumeEvent event) {
        if (event.getPlayer().getName().equals("iPig") && event.getItem().getType().equals(Material.CARROT)) {
            canBreed = true;
            new BukkitRunnable() {
                @Override
                public void run() {
                    canBreed=false;
                }
            }.runTaskLater(BreedRTX.getPlugin(), 10*20);
        }
    }
    @EventHandler
    public void onBreed(EntityEnterLoveModeEvent event) {
        Player player = Bukkit.getPlayer("iPig");
        if (event.getEntity().getType().equals(EntityType.PIG)) {
            if (player != null) {
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        double distance = event.getEntity().getLocation().distance(player.getLocation());
                        if (distance < 10 && canBreed) {
                            event.getEntity().getPathfinder().moveTo(player.getLocation());
                            event.getEntity().getWorld().spawnParticle(Particle.HEART, event.getEntity().getLocation(), 4);
                            Entity entity = event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.PIG);
                            if (entity instanceof Ageable) {
                                ((Ageable) entity).setBaby();
                            }
                            canBreed = false;
                            event.setCancelled(true);
                            this.cancel();
                        }
                    }
                }.runTaskTimer(BreedRTX.getPlugin(), 1, 10*20);

            }

        }
    }

}
