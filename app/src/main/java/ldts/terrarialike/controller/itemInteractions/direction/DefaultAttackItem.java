package ldts.terrarialike.controller.itemInteractions.direction;

import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.itemInteractions.DirectionItemInteraction;
import ldts.terrarialike.model.*;
import ldts.terrarialike.utils.WorldUtils;

import java.util.ArrayList;
import java.util.List;

public class DefaultAttackItem extends DirectionItemInteraction {

    private static final int RANGE=3;
    private static final int DAMAGE=5;

    private static List<Enemy> findNearEnemies(World one) {
        List<Enemy> allEnemies = one.getEnemiesList();
        Player player = one.getPlayer();
        return allEnemies.stream().filter((Enemy e1) -> {
            return Math.sqrt(Math.pow(player.getPosition().getX() - e1.getPosition().getX(), 2) +
                    Math.pow(player.getPosition().getY() - e1.getPosition().getY(), 2)) <= RANGE;
        }).toList();
    }
    @Override
    public List<GameEvent> execute(World one, InteractionType interactionType, Item item, WorldUtils worldUtils) {
        if(interactionType == InteractionType.ATTACK){
            if(direction == null){
                System.err.println("DefaultAttackItem: no direction set.");
                return new ArrayList<>();
            }
            int count = 0;
            for(Enemy enemy : findNearEnemies(one)){
                if(direction){
                    if(enemy.getPosition().getX() >= one.getPlayer().getPosition().getX()){  // z < p -> z
                        enemy.setHp(enemy.getHp()-DAMAGE);
                        count++;

                    }
                } else{
                    if(enemy.getPosition().getX() <= one.getPlayer().getPosition().getX()){
                        enemy.setHp(enemy.getHp()-DAMAGE);
                        count++;
                    }
                }
            }
            if(count > 0) one.getPlayer().getPlayerLogs().addLogString(
                    String.format("Player damaged %d enemies for %d damage", count,DAMAGE));

        }
        return new ArrayList<>();
    }
}
