package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy {

	public double x, y;
	public int width, heigth;
	Ball Ball;
	
	public Enemy(int x, int y){
		this.x = x;
		this.y = y;
		this.width = 5;
		this.heigth = 35;
	}
	
	public void tick() {
		y += (Game.ball.y - y) * 0.2;
		
	}
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.fillRect((int)x, (int) y, width, heigth);
	}
	
}
