package me.miicro.easywax.listener;

import me.miicro.easywax.handler.SignWaxHandler;
import net.kyori.adventure.text.Component;
import org.bukkit.event.player.PlayerQuitEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;

import static org.mockito.Mockito.*;

public class PlayerQuitListenerTest {

    private ServerMock server;
    private SignWaxHandler signWaxHandler;
    private PlayerQuitListener underTest;

    @BeforeEach
    public void setup() {
        server = MockBukkit.mock();
        signWaxHandler = mock(SignWaxHandler.class);
        underTest = new PlayerQuitListener(signWaxHandler);
    }

    @AfterEach
    public void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    public void onPlayerLeave() {
        PlayerQuitEvent event = new PlayerQuitEvent(server.addPlayer(), Component.text("PlayerQuitListenerTest"), PlayerQuitEvent.QuitReason.DISCONNECTED);
        underTest.onPlayerLeave(event);
        verify(signWaxHandler, times(1)).removePlayer(event.getPlayer().getUniqueId());
    }
}
