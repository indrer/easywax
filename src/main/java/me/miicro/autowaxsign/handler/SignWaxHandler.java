package me.miicro.autowaxsign.handler;

import me.miicro.autowaxsign.AutoWaxSign;
import me.miicro.autowaxsign.message.MessageHandler;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.UUID;
import java.util.logging.Logger;

public class SignWaxHandler {

    private final HashSet<UUID> playersWaxingSigns = new HashSet<>();
    private final AutoWaxSign autoWaxSign;
    private final Logger logger;

    public SignWaxHandler(AutoWaxSign autoWaxSign, Logger logger) {
        this.autoWaxSign = autoWaxSign;
        this.logger = logger;
    }

    public void attemptToWax(Block block, Player player) {
        UUID uuid = player.getUniqueId();
        if (!playersWaxingSigns.contains(uuid)) {
            return;
        }
        if (!isSign(block)) {
            MessageHandler.sendWaxCancelled(player);
            playersWaxingSigns.remove(uuid);
            return;
        }
        Sign sign = (Sign) block.getState();
        sign.setWaxed(true);
        sign.update();
        playersWaxingSigns.remove(uuid);
    }

    public void addPlayerWaxingSign(UUID uuid) {
        Player player = autoWaxSign.getServer().getPlayer(uuid);
        if (player == null || !player.isOnline()) {
            playersWaxingSigns.remove(uuid);
            logger.warning("Player " + uuid + " could not be found.");
            return;
        }
        playersWaxingSigns.add(uuid);
        MessageHandler.sendReadyToWax(player);
    }

    public void removePlayer(UUID uuid) {
        playersWaxingSigns.remove(uuid);
    }

    private boolean isSign(Block b) {
        return Tag.ALL_SIGNS.isTagged(b.getType()) || Tag.ALL_HANGING_SIGNS.isTagged(b.getType());
    }
}
