package ldts.terrarialike.utils;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.model.Position;

import java.util.List;

public class InputUtils {
    public static Boolean getDirection(List<KeyStroke> arrowKeys) {
        switch (arrowKeys.get(0).getKeyType()){
            case ArrowLeft -> {
                return false;
            }
            case ArrowRight -> {
                return true;
            }
            default -> {
                return null;
            }
        }
    }

    public static Position getDesiredPosition(List<KeyStroke> arrowKeys, Player player) throws InvalidPositionException {
        if(arrowKeys.size() == 1){
            switch (arrowKeys.get(0).getKeyType()){
                case ArrowUp -> {
                    return new Position(player.getPosition().getX(), player.getPosition().getY() + 1);
                }
                case ArrowDown -> {
                    return new Position(player.getPosition().getX(), player.getPosition().getY() - 1);
                }
                case ArrowLeft -> {
                    return new Position(player.getPosition().getX() - 1, player.getPosition().getY());
                }
                case ArrowRight -> {
                    return  new Position(player.getPosition().getX() + 1, player.getPosition().getY());
                }
                default -> throw  new RuntimeException("ArrowList has  non Arrow Type... This should never happen... Good luck");
            }
        } else if(arrowKeys.size() == 2){
            //diagonals
            if(arrowKeys.contains(new KeyStroke(KeyType.ArrowUp)) && arrowKeys.contains(new KeyStroke(KeyType.ArrowRight)))
                return new Position(player.getPosition().getX() + 1, player.getPosition().getY() + 1);
            if(arrowKeys.contains(new KeyStroke(KeyType.ArrowUp)) && arrowKeys.contains(new KeyStroke(KeyType.ArrowLeft)))
                return new Position(player.getPosition().getX() - 1, player.getPosition().getY() + 1);
            if(arrowKeys.contains(new KeyStroke(KeyType.ArrowDown)) && arrowKeys.contains(new KeyStroke(KeyType.ArrowLeft)))
                return new Position(player.getPosition().getX() - 1, player.getPosition().getY() - 1);
            if(arrowKeys.contains(new KeyStroke(KeyType.ArrowDown)) && arrowKeys.contains(new KeyStroke(KeyType.ArrowRight)))
                return new Position(player.getPosition().getX() + 1, player.getPosition().getY() - 1);
        }
        return  null;
    }

    public static List<KeyStroke> filterArrowKeys(List<KeyStroke> keyStrokeList){
        return keyStrokeList.stream().filter((KeyStroke i) -> switch (i.getKeyType()) {
            case ArrowUp, ArrowDown, ArrowLeft, ArrowRight -> true;
            default -> false;
        }).toList();
    }
}
