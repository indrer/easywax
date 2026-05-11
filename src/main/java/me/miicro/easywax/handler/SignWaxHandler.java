package me.miicro.easywax.handler;

import me.miicro.easywax.PluginProvider;
import me.miicro.easywax.message.MessageHandler;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.UUID;
import java.util.logging.Logger;

public class SignWaxHandler {

    private final HashSet<UUID> playersWaxingSigns = new HashSet<>();
    private final PluginProvider pluginProvider;
    private final Logger logger;

    public SignWaxHandler(PluginProvider pluginProvider, Logger logger) {
        this.pluginProvider = pluginProvider;
        this.logger = logger;
    }

    public void attemptToWax(Block block, Player player) {
        UUID uuid = player.getUniqueId();
        if (!playersWaxingSigns.contains(uuid)) {
            return;
        }
        if (block == null || !isSign(block)) {
            MessageHandler.sendWaxCancelled(player);
            playersWaxingSigns.remove(uuid);
            return;
        }
        waxSign(block);
        playersWaxingSigns.remove(uuid);
        MessageHandler.sendWaxSuccess(player);
    }

    public void addPlayerWaxingSign(UUID uuid) {
        Player player = pluginProvider.getServer().getPlayer(uuid);
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

    public HashSet<UUID> getPlayersWaxingSigns() {
        return playersWaxingSigns;
    }

    protected void waxSign(Block block) {
        Sign sign = (Sign) block.getState();
        sign.setWaxed(true);
        sign.update();
    }

    protected boolean isSign(Block b) {
        return Tag.ALL_SIGNS.isTagged(b.getType()) || Tag.ALL_HANGING_SIGNS.isTagged(b.getType());
    }
}
