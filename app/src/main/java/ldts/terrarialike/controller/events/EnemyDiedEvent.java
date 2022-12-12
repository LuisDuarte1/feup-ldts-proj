package ldts.terrarialike.controller.events;

import ldts.terrarialike.controller.GameEvent;
import ldts.terrarialike.model.Enemy;
import ldts.terrarialike.model.World;

import java.util.ArrayList;
import java.util.List;

public class EnemyDiedEvent implements GameEvent{
    private Enemy enemy;
    public EnemyDiedEvent(Enemy enemy) {
        this.enemy = enemy;
    }
    @Override
    public List<GameEvent> execute(World world) {
        List<GameEvent> list = new ArrayList<>();
        world.removeEnemy(enemy);
        return list;
    }

}
