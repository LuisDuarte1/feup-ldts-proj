package ldts.terrarialike.controller.generators;

import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.model.*;

import java.util.List;
import java.util.Random;

public class EnemyGeneratorFactory {

    private static final int DISTANCE_TO_PLAYER=50;
    private static final int MIN_ENEMIES=2;
    private static final int MAX_ENEMIES_TO_GENERATE=10;

    private World world;
    private Random random;


    public EnemyGeneratorFactory(World world) {
        this.world = world;
        this.random = new Random();
    }

    private List<Enemy> getMobsNearPlayer(){
        List<Enemy> allEnemies = world.getEnemiesList();
        Player player = world.getPlayer();
        return allEnemies.stream().filter((Enemy e1) -> {
            return Math.sqrt(Math.pow(player.getPosition().getX() - e1.getPosition().getX(), 2) +
                    Math.pow(player.getPosition().getY() - e1.getPosition().getY(), 2)) <= DISTANCE_TO_PLAYER;
        }).toList();
    }

    private Enemy generateEnemy(Integer chunkID) throws InvalidPositionException {
        float megaProb = random.nextFloat();
        Chunk desiredChunk = null;
        for(Chunk chunk: world.getChunks()){
            if(chunk.getChunkID() == chunkID){
                desiredChunk = chunk;
                break;
            }
        }
        Block randomBlock = desiredChunk.getBlocks().get(random.nextInt(0, desiredChunk.getBlocks().size()));
        Integer xPos = randomBlock.getPosition().getX();
        Integer yPos = world.findMaxHeightOfXPos(xPos) + 1;

        if(desiredChunk == null) throw new RuntimeException("Couldn't find chunkID");
        if(megaProb <= 0.10){
            return new Skeleton(new Position(xPos, yPos),world.getPlayer());
        }
        return new Zombie(new Position(xPos,yPos),world.getPlayer());
    }

    public void generateInitialEnemies(){
        for(Chunk chunk : world.getChunks()){
            float prob = random.nextFloat();
            if(prob <= 0.25){
                try {
                    Integer numberOfEnemies = random.nextInt(3);
                    for(int i= 0; i < numberOfEnemies; i++){
                        world.addEnemy(generateEnemy(chunk.getChunkID()));
                    }
                } catch (InvalidPositionException ignored) {
                }
            }
        }


    }

    public void generateEnemiesIfNecessary(){
        List<Enemy> mobsNearPlayer = getMobsNearPlayer();
        if(mobsNearPlayer.size() <= MIN_ENEMIES){
            Player player = world.getPlayer();
            Integer chunkID = world.getChunkID(player.getPosition().getX());
            int chunkDiff = DISTANCE_TO_PLAYER/Chunk.CHUNK_SIZE;
            int count = 0;
            for(int i = -chunkDiff; i < chunkDiff; i++){
                float prob = random.nextFloat();
                if(count >= MAX_ENEMIES_TO_GENERATE) break;
                if(prob <= 0.25){
                    int numberOfEnemies = random.nextInt(MAX_ENEMIES_TO_GENERATE/2);
                    count += numberOfEnemies;
                    for(int e= 0; e < numberOfEnemies; e++){
                        try {
                            world.addEnemy(generateEnemy(chunkID));
                        } catch (InvalidPositionException ignore) {
                        }
                    }
                }
            }
        }

    }


}
