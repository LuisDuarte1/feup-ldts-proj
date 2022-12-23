package ldts.terrarialike.controller.actions;


import com.googlecode.lanterna.input.KeyStroke;
import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.utils.InputUtils;

import java.util.List;

public abstract class AbstractAction {

     private boolean usesArrows;

     protected InputUtils inputUtils;

     public AbstractAction(boolean usesArrows, InputUtils inputUtils) {
          this.usesArrows = usesArrows;
          this.inputUtils = inputUtils;
     }


     public boolean usesArrows() {
          return usesArrows;
     }

     public  abstract List<GameEvent> processAction(List<KeyStroke> arrowKeys, Player player);
}
