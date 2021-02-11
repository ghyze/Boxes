package nl.ghyze.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Input implements KeyListener, MouseListener{

	public boolean up = false;
	public boolean down = false;
	public boolean left = false;
	public boolean right = false;
	
	public Input(){
//		System.out.println("Input created!");
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
//		System.out.println("key pressed: "+arg0.getKeyCode());
		if (arg0.getKeyCode() == KeyEvent.VK_UP){
			
		} else if (arg0.getKeyCode() == KeyEvent.VK_LEFT){
			left = true;
		} else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT){
			right = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
//		System.out.println("key released: "+arg0.getKeyCode());
		if (arg0.getKeyCode() == KeyEvent.VK_ESCAPE){
			System.exit(0);
		} else if (arg0.getKeyCode() == KeyEvent.VK_LEFT){
			left = false;
		} else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT){
			right = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
//		System.out.println("key typed: "+arg0.getKeyChar());
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
//		System.out.println("Mouse click");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
//		System.out.println("Mouse entered");
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
//		System.out.println("Mouse exited");
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
//		System.out.println("Mouse pressed");
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
//		System.out.println("Mouse released");
	}

}
