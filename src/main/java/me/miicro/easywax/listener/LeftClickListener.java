package me.miicro.easywax.listener;

import me.miicro.easywax.EasyWax;
import me.miicro.easywax.handler.SignWaxHandler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LeftClickListener implements Listener {

    private final SignWaxHandler signWaxHandler;

    public LeftClickListener(EasyWax plugin, SignWaxHandler signWaxHandler) {
        this.signWaxHandler = signWaxHandler;
    }

    @EventHandler
    public void leftClick(PlayerInteractEvent e) {
        if (e.getAction() != Action.LEFT_CLICK_BLOCK && e.getAction() != Action.LEFT_CLICK_AIR) {
            return;
        }
        signWaxHandler.attemptToWax(e.getClickedBlock(), e.getPlayer());
        return;
    }
}
