package me.miicro.autowaxsign.listiner;

import me.miicro.autowaxsign.AutoWaxSign;
import me.miicro.autowaxsign.handler.SignWaxHandler;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class LeftClickListener implements Listener {

    private final SignWaxHandler signWaxHandler;

    public LeftClickListener(AutoWaxSign plugin, SignWaxHandler signWaxHandler) {
        this.signWaxHandler = signWaxHandler;
    }

    @EventHandler
    public void leftClick(PlayerInteractEvent e) {
        if (e.getAction() != Action.LEFT_CLICK_BLOCK) {
            return;
        }
        Block block = e.getClickedBlock();
        if (block == null) {
            return;
        }

        signWaxHandler.attemptToWax(block, e.getPlayer());
        return;
    }
}
