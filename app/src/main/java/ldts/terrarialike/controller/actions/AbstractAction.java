package ldts.terrarialike.controller.actions;


import com.googlecode.lanterna.input.KeyStroke;
import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.model.Player;

import java.util.List;

public abstract class AbstractAction {

     public boolean usesArrows() {
          return usesArrows;
     }

     private boolean usesArrows;

     protected AbstractAction(boolean usesArrows) {
          this.usesArrows = usesArrows;
     }

     public  abstract List<GameEvent> processAction(List<KeyStroke> arrowKeys, Player player);
}
