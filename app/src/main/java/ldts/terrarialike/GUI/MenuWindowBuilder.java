package ldts.terrarialike.GUI;

import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Component;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.Window;

import java.util.Arrays;
import java.util.List;

public class MenuWindowBuilder {

    public static Window build(String title, List<Component> componentList){
        BasicWindow window = new BasicWindow(title);
        window.setHints(List.of(Window.Hint.CENTERED));
        Panel panel = new Panel();
        for(Component c: componentList){
            panel.addComponent(c);
        }
        window.setComponent(panel);
        return window;
    }
}
