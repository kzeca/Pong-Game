package pong;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
	
	public boolean up;
	public boolean down;
	public int x, y, width, heigth;
	
	public Player(int x, int y) {
		this.x = x;
		this.y = y;
		this.width = 5;
		this.heigth = 35;
	}
	
	public void tick() {
		if(up) y--;
		else if(down) y++;
		if(y+heigth > Game.HEIGTH) y = Game.HEIGTH - heigth;
		else if(y < 0) y = 0;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, width, heigth);
		
	}
	
}
