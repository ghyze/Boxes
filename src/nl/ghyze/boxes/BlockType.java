package nl.ghyze.boxes;

import java.awt.Color;

public enum BlockType {

	BLACK(0, Color.black, "Black"),
	WHITE(1, Color.white, "White"),
	BLUE(2, Color.blue, "Blue"),
	RED(3, Color.red, "Red"),
	GREEN(4, Color.green, "Green");
	
	private int index;
	private Color color;
	private String name;
	
	BlockType(int index, Color color, String name){
		this.index = index;
		this.color = color;
		this.name = name;
	}
	
	public int getIndex(){
		return index;
	}
	
	public Color getColor(){
		return color;
	}
	
	public String getName(){
		return name;
	}
}
