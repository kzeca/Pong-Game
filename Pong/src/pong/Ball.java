package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball {
	public double x, y, dx, dy, speed = 2.1;
	public int width, heigth;
	
	
	public Ball(int x, int y){
		this.x = x;
		this.y = y;
		this.width = 3;
		this.heigth = 3;
		double angle = new Random().nextInt(60);
		dx = Math.cos(Math.toRadians(angle));
		dy = Math.sin(Math.toRadians(angle));
	}
	
	public void tick() {
		if(y+(dy*speed) + heigth >= Game.HEIGTH) {
			dy *= -1;
		}
		else if(y + (dy*speed) < 0) dy *= -1;
		if(x > Game.WIDTH) {
			System.out.println("Ponto do inimigo!");
			new Game();
			return;
		}
		else if(x<=0) {
			System.out.println("Ponto do Player!");
			new Game();
			return;
		}
		
		Rectangle bounds = new Rectangle((int)(x+(dx*speed)),(int)(y+(dy*speed)), width, heigth);
		Rectangle boundsPlayer = new Rectangle(Game.player.x, Game.player.y, Game.player.width, Game.player.heigth);
		Rectangle boundsEnemy = new Rectangle((int)Game.enemy.x, (int)Game.enemy.y, Game.enemy.width, Game.enemy.heigth);
		
		if(bounds.intersects(boundsPlayer)) {
			double angle = new Random().nextInt(60);
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dx>0) dx*=-1;
		}else if(bounds.intersects(boundsEnemy)) {
			double angle = new Random().nextInt(60);
			dx = Math.cos(Math.toRadians(angle));
			dy = Math.sin(Math.toRadians(angle));
			if(dx<0) dx*=-1;
		}
		
		x+=dx*speed;
		y+=dy*speed;
	}
	
	public void render(Graphics g){
		g.setColor(Color.WHITE);
		g.fillOval((int)x, (int) y, width, heigth);
	}

}
