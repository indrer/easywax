package me.miicro.autowaxsign.command;

import me.miicro.autowaxsign.handler.SignWaxHandler;
import me.miicro.autowaxsign.message.MessageHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class EasyWaxCommand implements CommandExecutor {
    private final SignWaxHandler signWaxHandler;

    public EasyWaxCommand(SignWaxHandler signWaxHandler) {
        this.signWaxHandler = signWaxHandler;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {

        if (sender instanceof Player) {
            signWaxHandler.addPlayerWaxingSign(((Player) sender).getUniqueId());
        } else {
            MessageHandler.onlyPlayerCanSendCommand(sender);
        }
        return true;
    }
}
