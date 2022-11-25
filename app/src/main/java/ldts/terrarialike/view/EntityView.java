package ldts.terrarialike.view;

import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.model.Entity;

public interface EntityView {
    void draw(TextGraphics graphics, Entity entity);
}
