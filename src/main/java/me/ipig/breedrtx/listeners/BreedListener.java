package me.ipig.breedrtx.listeners;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityEnterLoveModeEvent;



public class BreedListener implements Listener {
    @EventHandler
    public void onBreed(EntityEnterLoveModeEvent event) {
        Player player = Bukkit.getPlayer("iPig");
        if (event.getEntity().getType().equals(EntityType.PIG)) {
            if (player != null) {
                double distance = event.getEntity().getLocation().distance(player.getLocation());
                if (distance < 10) {
                    event.getEntity().getPathfinder().moveTo(player.getLocation());
                    event.getEntity().getWorld().spawnParticle(Particle.HEART, event.getEntity().getLocation(), 4);
                    event.getEntity().getWorld().spawn(event.getEntity().getLocation(), event.getEntityType().getEntityClass());
                }
            }

        }
    }

}
