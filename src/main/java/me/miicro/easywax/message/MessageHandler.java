package me.miicro.easywax.message;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageHandler {

    private static final Component PREFIX =
            Component.text("<EasyWax> ", NamedTextColor.GOLD);

    private static final Component READY_TO_WAX =
            PREFIX.append(Component.text("Ready to wax - punch a sign.", NamedTextColor.WHITE));

    private static final Component WAX_CANCELLED =
            PREFIX.append(Component.text("Sign waxing cancelled. Run /waxit again and punch a sign to wax it.", NamedTextColor.WHITE));

    private static final Component WAX_SUCCESSFUL = PREFIX.append(Component.text("Sign waxed.", NamedTextColor.WHITE));

    private static final Component ONLY_PLAYER_CAN_SEND_COMMAND = PREFIX.append(Component.text("Only player can send command.", NamedTextColor.RED));

    private static final Component UNWAXED_SIGN_WARNING = PREFIX.append(Component.text("Your sign can be edited by other players, run /waxit to wax your sign.", NamedTextColor.RED));

    public static void sendWaxSuccess(Player player) {
        player.sendMessage(WAX_SUCCESSFUL);
    }

    public static void sendReadyToWax(Player player) {
        player.sendMessage(READY_TO_WAX);
    }

    public static void sendWaxCancelled(Player player) {
        player.sendMessage(WAX_CANCELLED);
    }

    public static void onlyPlayerCanSendCommand(CommandSender sender) {
        sender.sendMessage(ONLY_PLAYER_CAN_SEND_COMMAND);
    }

    public static void warnAboutUnwaxedSign(Player player) {
        player.sendMessage(UNWAXED_SIGN_WARNING);
    }
}
