package me.miicro.autowaxsign;

import me.miicro.autowaxsign.command.EasyWaxCommand;
import me.miicro.autowaxsign.handler.SignWaxHandler;
import me.miicro.autowaxsign.listiner.LeftClickListener;
import me.miicro.autowaxsign.listiner.PlayerQuitListener;
import me.miicro.autowaxsign.listiner.SignChangeListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public class AutoWaxSign extends JavaPlugin {

    private final Logger logger = getLogger();

    @Override
    public void onEnable() {
        SignWaxHandler signWaxHandler = new SignWaxHandler(this, logger);
        String COMMAND = "waxit";
        Objects.requireNonNull(this.getCommand(COMMAND)).setExecutor(new EasyWaxCommand(signWaxHandler));
        this.getServer().getPluginManager().registerEvents(new LeftClickListener(this, signWaxHandler), this);
        this.getServer().getPluginManager().registerEvents(new PlayerQuitListener(signWaxHandler), this);
        this.getServer().getPluginManager().registerEvents(new SignChangeListener(), this);
    }
}
