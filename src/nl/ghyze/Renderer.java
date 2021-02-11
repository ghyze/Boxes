package nl.ghyze;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import nl.ghyze.boxes.Block;
import nl.ghyze.boxes.BlockType;
import nl.ghyze.boxes.Field;
import nl.ghyze.util.FpsCounter;

public class Renderer {
	
	FpsCounter counter = new FpsCounter(10);
	private Game game;
	
	public Renderer(Game game){
		this.game = game;
	}

	public void render(Graphics gr, int w, int h, long time){
		counter.tick();
		gr.setColor(new Color(200,200,200));
		gr.fillRect(0, 0, w, h);
		
		Field field = game.getField();
		List<Block> blocks = field.getBlocks();

		for (Block block : blocks){
			drawBlock(block, gr);
		}
		if (field.getActiveBlocks() != null){
			for (Block activeBlock : field.getActiveBlocks()){
				drawBlock(activeBlock, gr);
			}
		}
		
		gr.setColor(Color.white);
		gr.drawString("Score: "+game.getScore(), 10, 10);
		gr.drawString("FPS: "+counter.getFps(), 10, 30);
		
		drawInventory(gr, BlockType.BLACK);
		drawInventory(gr, BlockType.WHITE);
		drawInventory(gr, BlockType.BLUE);
		drawInventory(gr, BlockType.RED);
		drawInventory(gr, BlockType.GREEN);
		
		if (game.isGameover()){
			gr.fillRect(320, 280, 160, 40);
			gr.setColor(Color.black);
			gr.drawString("Game over!", 360, 310);
		}
	}
	
	private void drawInventory(Graphics gr, BlockType type){
		gr.setColor(type.getColor());
		gr.drawString(type.getName(), 10, 50+(type.getIndex() * 20));
		gr.drawString(""+game.getInventory()[type.getIndex()], 80, 50+(type.getIndex() * 20));
	}
	
	private void drawBlock(Block block, Graphics gr){
		gr.setColor(block.getType().getColor());
		
		int x = 200 + (block.getCol() * 20);
		int y = 450 - (block.getRow() * 20);
		if (block.isMoving()){
			y -= game.getMovingBlockOffset();
		}
		
		gr.fillRect(x, y, 15, 15);
	}
}
