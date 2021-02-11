package nl.ghyze.boxes;

public class Block {

	private BlockType type;
	
	private int row;
	private int col;
	
	private boolean moving = false;
	
	public Block(BlockType type){
		this.type = type;
	}

	public BlockType getType() {
		return type;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("Block: [").append(row).append(",").append(col).append("]");
		return sb.toString();
	}
}
