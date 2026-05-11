package me.miicro.easywax.listener;

import me.miicro.easywax.handler.SignWaxHandler;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockbukkit.mockbukkit.MockBukkit;
import org.mockbukkit.mockbukkit.ServerMock;

import static org.mockito.Mockito.*;

public class LeftClickListenerTest {

    private ServerMock server;
    private SignWaxHandler signWaxHandler;
    private LeftClickListener underTest;

    @BeforeEach
    public void setup() {
        server = MockBukkit.mock();
        signWaxHandler = mock(SignWaxHandler.class);
        underTest = new LeftClickListener(null, signWaxHandler);
    }

    @AfterEach
    public void tearDown() {
        MockBukkit.unmock();
    }

    @Test
    public void leftClick_LeftClickBlock() {
        Block block = mock(Block.class);
        PlayerInteractEvent event = new PlayerInteractEvent(server.addPlayer(), Action.LEFT_CLICK_BLOCK, null, block, BlockFace.EAST, EquipmentSlot.HAND);
        underTest.leftClick(event);

        verify(signWaxHandler, times(1)).attemptToWax(block, event.getPlayer());
    }

    @Test
    public void leftClick_LeftClickAir() {
        PlayerInteractEvent event = new PlayerInteractEvent(server.addPlayer(), Action.LEFT_CLICK_AIR, null, null, BlockFace.EAST, EquipmentSlot.HAND);
        underTest.leftClick(event);
        verify(signWaxHandler, times(1)).attemptToWax(null, event.getPlayer());
    }

    @Test
    public void leftClick_IncorrectAction() {
        PlayerInteractEvent event = new PlayerInteractEvent(server.addPlayer(), Action.RIGHT_CLICK_AIR, null, null, BlockFace.EAST, EquipmentSlot.HAND);
        underTest.leftClick(event);
        verify(signWaxHandler, never()).attemptToWax(null, event.getPlayer());
    }
}
