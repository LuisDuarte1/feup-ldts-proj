package ldts.terrarialike.controller.itemInteractions;

import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.controller.ItemInteraction;
import ldts.terrarialike.model.*;

import java.util.ArrayList;
import java.util.List;

public class SwordItemInteraction extends ItemInteraction {

    private int range;
    private int damage;

    public SwordItemInteraction(int range, int damage) {
        this.range = range;
        this.damage = damage;
    }

    private static List<Enemy> findNearEnemies(World one,int range) {
        List<Enemy> allEnemies = one.getEnemiesList();
        Player player = one.getPlayer();
        return allEnemies.stream().filter((Enemy e1) -> {
            return Math.sqrt(Math.pow(player.getPosition().getX() - e1.getPosition().getX(), 2) +
                    Math.pow(player.getPosition().getY() - e1.getPosition().getY(), 2)) <= range;
        }).toList();
    }
    @Override
    public List<GameEvent> execute(World one, InteractionType interactionType, Item item) {
        if(interactionType == InteractionType.ATTACK){
            if(direction == null){
                System.err.println("DefaultAttackItem: no direction set.");
                return new ArrayList<>();
            }
            int count = 0;
            for(Enemy enemy : findNearEnemies(one, range)){
                if(direction){
                    if(enemy.getPosition().getX() >= one.getPlayer().getPosition().getX()){
                        enemy.setHp(enemy.getHp()-damage);
                        count++;

                    }
                } else{
                    if(enemy.getPosition().getX() <= one.getPlayer().getPosition().getX()){
                        enemy.setHp(enemy.getHp()-damage);
                        count++;
                    }
                }
            }
            if(count > 0) one.getPlayer().getPlayerLogs().addLogString(
                    String.format("Player damaged %d enemies for %d damage", count,damage));

        }
        return new ArrayList<>();
    }
}
