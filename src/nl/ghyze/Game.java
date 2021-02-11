package nl.ghyze;

import java.util.ArrayList;
import java.util.List;

import nl.ghyze.boxes.Block;
import nl.ghyze.boxes.BlockType;
import nl.ghyze.boxes.Field;
import nl.ghyze.input.Input;

public class Game {

	Input input;
	
	private boolean paused = false;
	private Field field;
	private boolean gravityEffect = false;
	
	private long start = 0;
	
	private int score = 0;
	
	private int movingBlockOffset = 0;
	
	private boolean gameover = false;
	
	private int[] inventory = new int[5];
	
	public Game(Input input){
		this.input = input;
		field = new Field();
	}
	
	public void pause(boolean paused){
		this.paused = paused;
	}
	
	public boolean isPaused(){
		return paused;
	}
	
	public Field getField(){
		return field;
	}
	
	public int getScore(){
		return score;
	}
	
	public void tick(long time){
		if (start == 0){
			start = time;
		}
		if (!gameover && !paused){
			checkUserInput();
			if (movingBlockOffset <= 0){
				if (gravityEffect){
					applyGravityEffect();
				} else {
					moveActiveBlocks();
				}
				movingBlockOffset = 20;
			} else {
				movingBlockOffset --;
			}
			checkGameover();
		}
	}
	
	private void checkUserInput(){
		if (input.left){
			field.userMoveActiveBlockLeft();
			input.left = false;
		}
		
		if (input.right){
			field.userMoveActiveBlockRight();
			input.right = false;
		}
	}

	private void moveActiveBlocks() {
		List<Block> activeBlocks = field.getActiveBlocks();
		if (activeBlocks == null || activeBlocks.isEmpty()){
			activeBlocks = new ArrayList<Block>();
			int col = (int) (Math.random() * field.getWidth());
			for (int i = 1; i <= 3; i++){
					Block block = newBlock(col);
					block.setRow(block.getRow() + i);
					activeBlocks.add(block);
			}
			field.setActiveBlocks(activeBlocks);
		} else {
			for (Block block : field.getActiveBlocks()){
				block.setRow((block.getRow() -1));
			}
		}
		for (Block block : field.getActiveBlocks()){
			block.setMoving(true);
		}
		
		boolean reachedBottom = false;
		for (Block activeBlock : field.getActiveBlocks()){
			Block bottom = field.getBlock(activeBlock.getRow() - 1, activeBlock.getCol());
			if (bottom != null || activeBlock.getRow() == 0){
				reachedBottom = true;
			}
		}
		if (reachedBottom){
			for (Block activeBlock : field.getActiveBlocks()){
				activeBlock.setMoving(false);
				field.addBlock(activeBlock);
				field.setActiveBlocks(null);
			}
			findMatches();
		}
	}

	private void applyGravityEffect() {
		boolean blocksMoved = false;
		for (Block block : field.getBlocks()){
			if (block.getRow() > 0){
				Block below = field.getBlock(block.getRow() -1, block.getCol());
				if (below == null){
					block.setRow(block.getRow() -1);
					block.setMoving(true);
					blocksMoved = true;
				} else {
					block.setMoving(false);
				}
			} else {
				block.setMoving(false);
			}
		}
		if (!blocksMoved){
			gravityEffect = false;
			findMatches();
		}
	}

	private void findMatches() {
		List<List<Block>> matches = new ArrayList<List<Block>>();
		List<Block> alreadyMatched = new ArrayList<Block>();
		for (Block block : field.getBlocks()){
			if (!alreadyMatched.contains(block)){
				List<Block> newMatch = matchField(block, new ArrayList<Block>());
				matches.add(newMatch);
				alreadyMatched.addAll(newMatch);
			}
		}
		
		for (List<Block> match : matches){
			if (match.size() >= 3){
				gravityEffect = true;
				int index = 0;
				BlockType type = null;
				for (Block block : match){
					type = block.getType();
					score += index;
					index ++;
					field.getBlocks().remove(block);
				}
				inventory[type.getIndex()] = inventory[type.getIndex()] + match.size();
			}
		}
	}
	
	private List<Block> matchField(Block source, List<Block> match){
		BlockType type = source.getType();
		match.add(source);
		int row = source.getRow();
		int col = source.getCol();
		if (row -1 > 0){
			Block check = field.getBlock(row -1, col);
			if (shouldCheck(check, type, match)){
				matchField(check, match);
			}
		} 
		if (row +1 < field.getWidth()){
			Block check = field.getBlock(row +1, col);
			if (shouldCheck(check, type, match)){
				matchField(check, match);
			}
		} 
		if (col -1 > 0){
			Block check = field.getBlock(row, col-1);
			if (shouldCheck(check, type, match)){
				matchField(check, match);
			}
		} 
		if (col +1 < field.getHeight()){
			Block check = field.getBlock(row, col+1);
			if (shouldCheck(check, type, match)){
				matchField(check, match);
			}
		}
		
		return match;
	}
	
	private boolean shouldCheck(Block block, BlockType type, List<Block> exclude){
		if (block == null){
			return false;
		}
		
		if (block.getType() != type){
			return false;
		}
		
		for (Block toExclude : exclude){
			if (toExclude == block){
				return false;
			}
		}
		
		return true;
	}
	
	private Block newBlock(int col){
		int row = 20;
		int typeInt = (int) (Math.random() * 5);
		BlockType type = BlockType.BLACK;
		switch (typeInt){
			case 1: type = BlockType.WHITE; break;
			case 2: type = BlockType.BLUE; break;
			case 3: type = BlockType.RED; break;
			case 4: type = BlockType.GREEN; break;
		}
		Block block = new Block(type);
		block.setCol(col);
		block.setRow(row);
		return block;
	}
	
	public int getMovingBlockOffset(){
		return movingBlockOffset;
	}
	
	public boolean checkGameover(){
		if (gameover){
			return true;
		}
		for (Block block : field.getBlocks()){
			if (block.getRow() > field.getHeight()){
				gameover = true;
				return true;
			}
		}
		return false;
	}

	public boolean isGameover() {
		return gameover;
	}

	public int[] getInventory() {
		return inventory;
	}
	
}
