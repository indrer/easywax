package me.miicro.easywax;

import me.miicro.easywax.command.EasyWaxCommand;
import me.miicro.easywax.handler.SignWaxHandler;
import me.miicro.easywax.listiner.LeftClickListener;
import me.miicro.easywax.listiner.PlayerQuitListener;
import me.miicro.easywax.listiner.SignChangeListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.logging.Logger;

public class EasyWax extends JavaPlugin {

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
