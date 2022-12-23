package ldts.terrarialike.controller.actions;

import com.googlecode.lanterna.input.KeyStroke;
import ldts.terrarialike.model.Player;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class EmptyActionTest {

    @Test

    public void testProcessAction() {
        EmptyAction emptyAction = new EmptyAction();
        List<KeyStroke> arrowKeys = new ArrayList<>();
        Player player = Mockito.mock(Player.class);
        emptyAction.processAction(null, player);
    }
}
