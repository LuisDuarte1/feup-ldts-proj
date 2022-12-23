package ldts.terrarialike.controller.actions;

import com.googlecode.lanterna.input.KeyStroke;
import ldts.terrarialike.model.Inventory;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.utils.InputUtils;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class EmptyActionTest {

    @Test

    public void testProcessAction() {
        InputUtils inputUtils = Mockito.mock(InputUtils.class);
        Player player = Mockito.mock(Player.class);
        Inventory inventory = Mockito.mock(Inventory.class);
        Mockito.when(player.getInventory()).thenReturn(inventory);
        List<KeyStroke> arrowKeys = new ArrayList<>();

        EmptyAction emptyAction = new EmptyAction(inputUtils);
        emptyAction.processAction(null, player);

        Mockito.verify(inventory, Mockito.times(1)).selectEmpty();


    }
}
