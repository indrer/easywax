package me.miicro.easywax.listiner;

import me.miicro.easywax.message.MessageHandler;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        Sign sign = (Sign) e.getBlock().getState();
        Player player = e.getPlayer();
        if (!sign.isWaxed()) {
            MessageHandler.warnAboutUnwaxedSign(player);
        }
        return;
    }
}
