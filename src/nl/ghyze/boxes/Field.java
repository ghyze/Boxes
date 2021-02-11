package nl.ghyze.boxes;

import java.util.ArrayList;
import java.util.List;

import nl.ghyze.input.Direction;

public class Field {

	private final int width = 10;
	private final int height = 20;

	private final List<Block> blocks;
	
	private List<Block> activeBlocks;
	
	public Field(){
		blocks  = new ArrayList<Block>();
	}
	
	public void addBlock(Block block){
		Block existing = getBlock(block.getRow(), block.getCol());
		if (existing != null){
			blocks.remove(existing);
		}
		blocks.add(block);
	}
	
	public void setActiveBlocks(List<Block> block){
		activeBlocks = block;
	}
	
	public List<Block> getActiveBlocks(){
		return activeBlocks;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}
	
	public List<Block> getBlocks(){
		return blocks;
	}
	
	public Block getBlock(int row, int col){
		for (Block block : blocks){
			if (block.getRow() == row &&
					block.getCol() == col){
				return block;
			}
		}
		return null;
	}
	
	public void userMoveActiveBlockLeft(){
		if (activeBlocks != null){
			boolean shouldMove = true;
			for (Block block : activeBlocks){
				shouldMove = shouldMove && 
						block.getCol() > 0 && 
						getBlock(block.getRow(), block.getCol()-1) == null;
			}
			if (shouldMove){
				for (Block block : activeBlocks){
					block.setCol(block.getCol() -1);
				}	
			}
		}
	}
	
	public void userMoveActiveBlockRight(){
		if (activeBlocks != null){
			boolean shouldMove = true;
			for (Block block : activeBlocks){
				Block blockRight = getBlock( block.getRow(), block.getCol()+1);
//				System.out.println("blockRight: "+blockRight+" shouldMove: "+shouldMove +" col: "+block.getCol()+" width: "+width);
				shouldMove = shouldMove && 
						block.getCol() < width && 
						blockRight == null;
			}
			if (shouldMove){
				for (Block block : activeBlocks){
					block.setCol(block.getCol() +1);
				}	
			}
		}
	}
}
