package me.miicro.easywax.command;

import me.miicro.easywax.handler.SignWaxHandler;
import org.bukkit.command.Command;
import org.bukkit.command.ConsoleCommandSender;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.PlayerMock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class EasyWaxCommandTest {

    private ServerMock server;
    private SignWaxHandler signWaxHandler;
    private EasyWaxCommand command;
    private Command mockCommand;

    @BeforeEach
    public void setup() {
        server = MockBukkit.mock();
        signWaxHandler = Mockito.mock(SignWaxHandler.class);
        command = new EasyWaxCommand(signWaxHandler);
        mockCommand = Mockito.mock(Command.class);
    }

    @AfterEach
    public void teardown() {
        MockBukkit.unmock();
    }

    @Test
    public void whenPlayerSendsCommand_playerAddedToWaxers() {
        PlayerMock player = server.addPlayer();
        command.onCommand(player, mockCommand, "waxit", new String[]{});

        verify(signWaxHandler, times(1)).addPlayerWaxingSign(player.getUniqueId());
    }

    @Test
    public void whenNonPlayerSendsCommand_sendWarning() {
        ConsoleCommandSender console = server.getConsoleSender();
        command.onCommand(console, mockCommand, "waxit", new String[]{});

        verifyNoInteractions(signWaxHandler);
        assertNotNull(server.getConsoleSender().nextMessage());
    }
}
