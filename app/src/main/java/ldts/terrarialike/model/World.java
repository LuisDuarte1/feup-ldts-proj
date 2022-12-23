package ldts.terrarialike.model;

import java.util.ArrayList;
import java.util.Comparator;
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
            //equals only compares position of chunk 
            if(c.equals(chunk)) return false;
        }
        this.chunksList.add(chunk);
        sortChunks();
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
        enemiesList.remove(enemy);
 
    }
    public List<Enemy> getEnemiesList() {
        return enemiesList;
    }

    public void sortChunks(){
        chunksList.sort(new Comparator<Chunk>() {
            @Override
            public int compare(Chunk arg0, Chunk arg1) {
                Integer chunkID0 = arg0.getPosition();
                Integer chunkID1 = arg1.getPosition();

                return chunkID0.compareTo(chunkID1);
            }
            
        });
    }

}
