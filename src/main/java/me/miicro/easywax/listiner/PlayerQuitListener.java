package me.miicro.easywax.listiner;

import me.miicro.easywax.handler.SignWaxHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener implements Listener {

    private final SignWaxHandler signWaxHandler;

    public PlayerQuitListener(SignWaxHandler signWaxHandler) {
        this.signWaxHandler = signWaxHandler;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        signWaxHandler.removePlayer(event.getPlayer().getUniqueId());
    }
}
