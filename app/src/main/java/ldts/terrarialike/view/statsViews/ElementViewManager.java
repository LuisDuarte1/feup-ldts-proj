package ldts.terrarialike.view.statsViews;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import ldts.terrarialike.utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class ElementViewManager {

    private List<Pair<ElementView, TextGraphics>> elementViewList;

    private TextGraphics textGraphics;

    private TerminalSize terminalSize;

    public ElementViewManager(TextGraphics textGraphics) {
        this.textGraphics = textGraphics;
        this.elementViewList = new ArrayList<>();
        this.terminalSize = textGraphics.getSize();
    }

    public void addElementViewPercentage(ElementView elementView, double percentage){
        if(containsElementView(elementView)) return;
        TextGraphics elementViewTextGraphics = textGraphics.newTextGraphics(
                new TerminalPosition((int) (terminalSize.getColumns()*(1-percentage)), 0),
                new TerminalSize((int) (terminalSize.getColumns()*percentage),terminalSize.getRows()));

        elementViewList.add(new Pair<>(elementView, elementViewTextGraphics));
    }

    public void addElementView(ElementView elementView, TerminalPosition terminalPosition, TerminalSize terminalSize){
        if(containsElementView(elementView)) return;
        TextGraphics elementViewTextGraphics = textGraphics.newTextGraphics(terminalPosition, terminalSize);

        elementViewList.add(new Pair<>(elementView, elementViewTextGraphics));
    }

    public void draw(){
        textGraphics.fill(' ');
        for (Pair<ElementView, TextGraphics> i:
             elementViewList) {
            i.first.draw(i.second);
        }
    }

    private boolean containsElementView(ElementView elementView) {
        for (Pair<ElementView, TextGraphics> pog:
        elementViewList){
            if(pog.first == elementView) return true;
        }
        return false;
    }

    public List<Pair<ElementView, TextGraphics>> getElementViewList() {
        return elementViewList;
    }



}
