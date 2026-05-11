package me.miicro.easywax.listener;

import net.kyori.adventure.text.Component;
import org.bukkit.block.Block;
import org.bukkit.block.sign.Side;
import org.bukkit.event.block.SignChangeEvent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.entity.PlayerMock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class SignChangeListenerTest {
    private ServerMock server;
    private SignChangeListener underTest;

    @BeforeEach
    public void setup() {
        server = MockBukkit.mock();
    }

    @AfterEach
    public void tearDown() {
        MockBukkit.unmock();
    }

    private SignChangeListener listenerWithWaxed(boolean waxed) {
        return new SignChangeListener() {
            @Override
            protected boolean isWaxed(Block block) {
                return waxed;
            }
        };
    }

    @Test
    public void onSignChange_signWarning() {
        underTest = listenerWithWaxed(false);
        PlayerMock player = server.addPlayer();
        SignChangeEvent event = new SignChangeEvent(mock(Block.class), player, List.of(Component.text("Text")), Side.FRONT);
        underTest.onSignChange(event);
        System.out.println("nextMessage: " + player.nextMessage());
        System.out.println("nextComponentMessage: " + player.nextComponentMessage());
        //assertNotNull(player.nextComponentMessage());
    }

    @Test
    public void onSignChange_noSignWarning() {
        underTest = listenerWithWaxed(true);
        PlayerMock player = server.addPlayer();
        SignChangeEvent event = new SignChangeEvent(mock(Block.class), player, List.of(Component.text("Text")), Side.FRONT);
        underTest.onSignChange(event);
        assertNull(player.nextMessage());
    }
}
