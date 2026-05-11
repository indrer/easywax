package me.miicro.easywax.handler;

import me.miicro.easywax.PluginProvider;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;
import org.mockbukkit.mockbukkit.block.BlockMock;
import org.mockbukkit.mockbukkit.entity.PlayerMock;
import org.mockito.Mockito;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SignWaxHandlerTest {
    private ServerMock server;
    private Logger logger;
    private PluginProvider plugin;
    private SignWaxHandler underTest;

    @BeforeEach
    public void setUp() {
        server = MockBukkit.mock();
        plugin = mock(PluginProvider.class);
        when(plugin.getServer()).thenReturn(server);
        logger = mock(Logger.class);
        underTest = new SignWaxHandler(plugin, logger);
    }

    @AfterEach
    public void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    public void onlinePlayer_addPlayerWaxingSign() {
        PlayerMock player = server.addPlayer();
        BlockMock block = new BlockMock(Material.OAK_SIGN);
        underTest.addPlayerWaxingSign(player.getUniqueId());

        assertNotNull(player.nextMessage());
        assertTrue(underTest.getPlayersWaxingSigns().contains(player.getUniqueId()));
    }

    @Test
    public void offlinePlayer_addPlayerWaxingSign() {
        PlayerMock player = server.addPlayer();
        player.disconnect();
        underTest.addPlayerWaxingSign(player.getUniqueId());

        verify(logger).warning(anyString());
        assertNull(player.nextMessage());
        assertFalse(underTest.getPlayersWaxingSigns().contains(player.getUniqueId()));
    }

    @Test
    public void existingPlayer_removePlayerWaxingSign() {
        PlayerMock player = server.addPlayer();
        underTest.addPlayerWaxingSign(player.getUniqueId());
        assertTrue(underTest.getPlayersWaxingSigns().contains(player.getUniqueId()));

        underTest.removePlayer(player.getUniqueId());
        assertFalse(underTest.getPlayersWaxingSigns().contains(player.getUniqueId()));
    }


    @Test
    public void noPlayerReadyToWax_attemptToWax() {
        PlayerMock player = server.addPlayer();
        BlockMock block = new BlockMock(Material.OAK_SIGN);
        underTest.attemptToWax(block, player);

        assertNull(player.nextMessage());
    }

    @Test
    public void notSign_attemptToWax() {
        PlayerMock player = server.addPlayer();
        BlockMock block = new BlockMock(Material.ACACIA_DOOR);
        underTest.addPlayerWaxingSign(player.getUniqueId());

        underTest.attemptToWax(block, player);
        assertNotNull(player.nextMessage());
        assertFalse(underTest.getPlayersWaxingSigns().contains(player.getUniqueId()));
    }

    @Test
    void attemptToWax_validSign() {
        PlayerMock player = server.addPlayer();
        underTest.addPlayerWaxingSign(player.getUniqueId());
        player.nextMessage();

        SignWaxHandler spy = Mockito.spy(underTest);
        Block block = mock(Block.class);

        doNothing().when(spy).waxSign(block);


        SignWaxHandler testHandler = new SignWaxHandler(plugin, logger) {
            @Override
            protected boolean isSign(Block b) {
                return true;
            }

            @Override
            protected void waxSign(Block b) {
            }
        };

        testHandler.addPlayerWaxingSign(player.getUniqueId());
        player.nextMessage();

        testHandler.attemptToWax(block, player);

        assertNotNull(player.nextMessage());
    }
}
