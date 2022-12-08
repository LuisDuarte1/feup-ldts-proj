package ldts.terrarialike.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ldts.terrarialike.exceptions.InvalidPositionException;
import ldts.terrarialike.exceptions.InvalidSizeException;

public class World {
    private List<Chunk> chunksList = new ArrayList<>();
    private int seed = 0;


    private List<Enemy> enemiesList = new ArrayList<>();
    

    private Player player = null;



    public World() throws InvalidPositionException, InvalidSizeException {
        Random random = new Random();
        this.seed = random.nextInt();
        this.player = new Player(new Position(0,0), 100);
    }

    public World(Integer seed) throws InvalidPositionException, InvalidSizeException{
        this.seed = seed;
        this.player = new Player(new Position(0,0), 100);
        
    }
    public Player getPlayer() {
        return player;
    }

    public int getSeed() {
        return seed;
    }

    public boolean tryAddChunk(Chunk chunk){
        for (Chunk c : this.chunksList) {
            if(c.equals(chunk)) return false;
        }
        this.chunksList.add(chunk);
        //TODO: sorting to make the chunk in place ig
        return true;
    }


    public List<Chunk> getChunks(){
        return chunksList;
    }
        


    public void addEnemy(Enemy enemy){
        enemiesList.add(enemy);
    }

    public void removeEnemy(Enemy enemy){
        //maybe check hp?
        if(enemiesList.contains(enemy)){
            enemiesList.remove(enemy);
        }
 
    }
    public List<Enemy> getEnemiesList() {
        return enemiesList;
    }

}
