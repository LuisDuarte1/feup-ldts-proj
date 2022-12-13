package ldts.terrarialike.controller;

import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Enemy;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.model.World;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.Temporal;

public class FollowPlayerAI implements EnemyAI {

    private static final int WAIT_FRAMES = 4;
    private static final Duration ATTACK_TIMEOUT = Duration.ofSeconds(5);


    private int frameTimeout;
    private Player player;
    private double range;

    private Temporal lastTimeAttacked;

    public FollowPlayerAI(Player player, int range) {
        this.player = player;
        this.range = range;
        this.lastTimeAttacked = Instant.now();
    }

    @Override
    public void tick(Enemy thisEnemy, World world) {
        frameTimeout = (frameTimeout + 1) % WAIT_FRAMES;
        Instant ripTimeout = (Instant) lastTimeAttacked.plus(ATTACK_TIMEOUT);
        if(frameTimeout % WAIT_FRAMES == 0){
            return;
        }
        double distance = Math.sqrt(Math.pow(player.getPosition().getX() - thisEnemy.getPosition().getX(), 2) +
                Math.pow(player.getPosition().getY() - thisEnemy.getPosition().getY(), 2));
        if (distance <= range && distance >= 2) {
            if(player.getPosition().getX() < thisEnemy.getPosition().getX()) {
                try {
                    moveLeft(thisEnemy, world);
                } catch (InvalidPositionException e) {
                    return;
                }
            }
            else if(player.getPosition().getX() > thisEnemy.getPosition().getX()) {
                try {
                    moveRight(thisEnemy, world);
                } catch (InvalidPositionException e) {
                    return;
                }
            }

        }
        else if (distance < 2 && ripTimeout.compareTo(Instant.now()) < 0) {
            player.getPlayerLogs().addLogString(String.format("Player got attacked and lost %d hp", thisEnemy.getDamage()));
            player.setHp(player.getHp() - thisEnemy.getDamage());
            lastTimeAttacked = Instant.now();
        }
    }

    private void moveRight(Enemy thisEnemy, World world) throws InvalidPositionException {
        Position right = new Position(thisEnemy.getPosition().getX() + 1, thisEnemy.getPosition().getY());
        Position upRight =  new Position(thisEnemy.getPosition().getX() + 1, thisEnemy.getPosition().getY() + 1);
        Position up = new Position(thisEnemy.getPosition().getX(), thisEnemy.getPosition().getY() + 1);
        if (world.getBlock(right) == null && world.getEntity(right) == null) {
            thisEnemy.setPosition(right);
        }
        else if (world.getBlock(upRight) == null && world.getEntity(upRight) == null && world.getBlock(up) == null) {
            thisEnemy.setPosition(upRight);
        }
    }

    private void moveLeft(Enemy thisEnemy, World world) throws InvalidPositionException {
        Position left = new Position(thisEnemy.getPosition().getX() - 1, thisEnemy.getPosition().getY());
        Position upLeft =  new Position(thisEnemy.getPosition().getX() - 1, thisEnemy.getPosition().getY() + 1);
        Position up = new Position(thisEnemy.getPosition().getX(), thisEnemy.getPosition().getY() + 1);
        if (world.getBlock(left) == null && world.getEntity(left) == null) {
            thisEnemy.setPosition(left);
        }
        else if (world.getBlock(upLeft) == null && world.getEntity(upLeft) == null && world.getBlock(up) == null) {
            thisEnemy.setPosition(upLeft);
        }
    }

}
