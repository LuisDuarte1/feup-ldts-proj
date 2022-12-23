package ldts.terrarialike.controller;

import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.Enemy;
import ldts.terrarialike.model.Player;
import ldts.terrarialike.model.Position;
import ldts.terrarialike.model.World;
import ldts.terrarialike.utils.WorldUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.Temporal;


public class FollowPlayerAI implements EnemyAI {

    public static final int WAIT_FRAMES = 3;
    public static final Duration ATTACK_TIMEOUT = Duration.ofSeconds(5);


    private int frameTimeout;
    private Player player;
    private double range;

    private WorldUtils worldUtils;
    private Temporal lastTimeAttacked;

    public FollowPlayerAI(Player player, int range, WorldUtils worldUtils) {
        this.player = player;
        this.range = range;
        this.lastTimeAttacked = Instant.now();
        this.worldUtils = worldUtils;
    }

    @Override
    public void tick(Enemy thisEnemy, World world) {
        frameTimeout = (frameTimeout + 1) % WAIT_FRAMES;
        Instant ripTimeout = (Instant) lastTimeAttacked.plus(ATTACK_TIMEOUT);
        if(frameTimeout % WAIT_FRAMES != 0){
            return;
        }
        double distance = Math.sqrt(Math.pow(player.getPosition().getX() - thisEnemy.getPosition().getX(), 2) +
                Math.pow(player.getPosition().getY() - thisEnemy.getPosition().getY(), 2));
        if (distance <= range && distance >= 2) {
            if(player.getPosition().getX() < thisEnemy.getPosition().getX()) {
                try {
                    moveLeft(thisEnemy, world);
                } catch (InvalidPositionException ignored) {
                }
            }
            else if(player.getPosition().getX() > thisEnemy.getPosition().getX()) {
                try {
                    moveRight(thisEnemy, world);
                } catch (InvalidPositionException ignored) {
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
        setNewPosition(thisEnemy, world, right, upRight, worldUtils);
    }

    private static void setNewPosition(Enemy thisEnemy, World world, Position right, Position upRight, WorldUtils worldUtils) throws InvalidPositionException {
        Position up = new Position(thisEnemy.getPosition().getX(), thisEnemy.getPosition().getY() + 1);
        if (worldUtils.getBlock(right, world) == null && worldUtils.getEntity(right, world) == null) {
            thisEnemy.setPosition(right);
        }
        else if (worldUtils.getBlock(upRight, world) == null && worldUtils.getEntity(upRight, world) == null && worldUtils.getBlock(up, world) == null) {
            thisEnemy.setPosition(upRight);
        }
    }

    private void moveLeft(Enemy thisEnemy, World world) throws InvalidPositionException {
        Position left = new Position(thisEnemy.getPosition().getX() - 1, thisEnemy.getPosition().getY());
        Position upLeft =  new Position(thisEnemy.getPosition().getX() - 1, thisEnemy.getPosition().getY() + 1);
        setNewPosition(thisEnemy, world, left, upLeft, worldUtils);
    }

}
