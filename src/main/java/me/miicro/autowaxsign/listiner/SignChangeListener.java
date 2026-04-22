package me.miicro.autowaxsign.listiner;

import io.papermc.paper.event.player.PlayerOpenSignEvent;
import me.miicro.autowaxsign.AutoWaxSign;
import net.kyori.adventure.text.Component;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignChangeListener implements Listener {

    private final AutoWaxSign plugin;

    public SignChangeListener(AutoWaxSign plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onSignChange(SignChangeEvent e) {
        if (e.getPlayer().isOp()) {
            return;
        }
        Sign sign = (Sign) e.getBlock().getState();
        sign.setWaxed(true);
        return;
    }

    @EventHandler
    public void onPlayerSignOpen(PlayerOpenSignEvent e) {
        if (e.getPlayer().isOp()) {
            return;
        }
        Sign sign = e.getSign();
        boolean emptySign = isEmptySing(sign);
        if (!emptySign || e.getCause().equals(PlayerOpenSignEvent.Cause.INTERACT)) {
            sign.setWaxed(true);
            e.setCancelled(true);
            return;
        }
        return;
    }

    private boolean isEmptySing(Sign sign) {
        return sign.getSide(Side.FRONT).lines().stream().allMatch(line -> line.equals(Component.empty())) && sign.getSide(Side.BACK).lines().stream().anyMatch(line -> line.equals(Component.empty()));
    }
}
