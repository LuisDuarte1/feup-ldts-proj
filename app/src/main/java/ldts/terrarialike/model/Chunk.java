package ldts.terrarialike.model;


import ldts.terrarialike.exceptions.InvalidPositionException;

import java.util.ArrayList;
import java.util.List;

public class Chunk {
        private final int position;
        private List<Block> blocks = new ArrayList<>(); // Lista de blocos no chunk

        public Chunk(int position) {
            this.position = position;
        }

        public List<Block> getBlocks() {
            return this.blocks;
        }

        public List<Block> setBlocks(List<Block> blocks) {
            return this.blocks = blocks;
        }

        public int getPosition() {
            return this.position;
        }

        public void addBlock(Block block) throws InvalidPositionException {
            if (validCoords(block) && notIn(block)) {
                blocks.add(block);
            }
            else {
                throw new InvalidPositionException("Block has an invalid position");
            }
        }

        public void removeBlock(Position pos) throws BlockNotFoundException {
            boolean found = false;
            for (Block b : blocks) {
                if (b.getPosition().equals(pos)) {
                    blocks.remove(b);
                    found = true;
                    break;
                }
            }
            if (!found) {
                throw new BlockNotFoundException("Block not found");
            }
        }

        // Check if a block is already in chunk
        public boolean notIn(Block block) {
         return !blocks.contains(block);
        }

        // Check if block coordinates are contained in assigned chunk
        public boolean validCoords(Block block) {
            if (block.getPosition().getX() >= 16 * position && block.getPosition().getX() < 16 * position + 16 && block.getPosition().getY() >= Position.Y_MIN  && block.getPosition().getY() <= Position.Y_MAX) {
                return true;
            }
        else {
                return false;
            }
        }
    }



