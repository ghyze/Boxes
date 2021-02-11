package nl.ghyze;

import java.awt.Canvas;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import nl.ghyze.input.Input;
import nl.ghyze.util.FpsDelay;

public class BoxesApp{

	Canvas canvas = new Canvas();
	Frame frame;
	Renderer renderer;
	Input input = new Input();
	Game game;
	
	public BoxesApp(){
		frame = new Frame();
		frame.add(canvas);
		frame.setSize(800, 600);
		frame.setVisible(true);
		frame.setFocusableWindowState(true);
	    canvas.createBufferStrategy(3);
	    canvas.addKeyListener(input);
	    game = new Game(input);
	    renderer = new Renderer(game);
	    run();
	}
	
	public void run(){
		long start = System.nanoTime();
		FpsDelay delay = new FpsDelay(120);
		while (true ){
			game.tick(System.nanoTime());
			game.pause(!canvas.hasFocus());
			BufferStrategy strategy = canvas.getBufferStrategy();
		     do {
		         do {
		             Graphics graphics = strategy.getDrawGraphics();
		             
		             int w = canvas.getWidth();
		             int h = canvas.getHeight();
		             long time = System.nanoTime() - start;
		             
		             renderer.render(graphics, w, h, time);
		             
		             graphics.dispose();
		         } while (strategy.contentsRestored());
		         strategy.show();
		     } while (strategy.contentsLost());
		     delay.delay();
		}
	}
	
	public static void main(String[] args) {
		BoxesApp app = new BoxesApp();
	}

}
