package me.miicro.easywax.listener;

import me.miicro.easywax.message.MessageHandler;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        Player player = e.getPlayer();
        if (!isWaxed(e.getBlock())) {
            MessageHandler.warnAboutUnwaxedSign(player);
        }
        return;
    }

    protected boolean isWaxed(Block block) {
        if (!(block.getState() instanceof Sign sign)) {
            return true;
        }
        return sign.isWaxed();
    }
}
