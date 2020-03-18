package pong;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable, KeyListener{

	
	private boolean isRunning = true;
	public static int WIDTH = 160;
	public static int HEIGTH = 100;
	public static int SCALE = 3;
	public static Player player;
	public static Enemy enemy;
	public static Ball ball;
	private Thread thread;
	private static BufferedImage layer;
	
	public Game(){
		this.setPreferredSize(new Dimension(WIDTH*SCALE, HEIGTH*SCALE));
		this.addKeyListener(this);
		player = new Player(WIDTH-5, 40);
		enemy  = new Enemy(0, 40);
		ball = new Ball(WIDTH/2 - 3, HEIGTH/2 - 3);
	}
	
	public static void main(String[] args){
		Game game = new Game();
		JFrame frame = new JFrame("Pong");
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(game);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		layer = new BufferedImage(WIDTH, HEIGTH, BufferedImage.TYPE_INT_RGB);
		new Thread(game).start();
	}
	
	public void tick() {
		player.tick();
		enemy.tick();
		ball.tick();
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = layer.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGTH);
		player.render(g);
		enemy.render(g);
		ball.render(g);
		g=bs.getDrawGraphics();
		g.drawImage(layer, 0, 0, WIDTH*SCALE, HEIGTH*SCALE, null);
		bs.show();
	}
	
	
	@Override
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000/amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		while(isRunning) {
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta>=1) {
				tick();
				render();
				frames++;
				delta--;
			}
			if(System.currentTimeMillis() - timer == 1000) {
				frames = 0;
				timer+=1000;
			}
		}
		stop();
	}

	public synchronized void stop() {
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) player.up = true;
		else if(e.getKeyCode()==KeyEvent.VK_DOWN) player.down = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_UP) player.up = false;
		else if(e.getKeyCode()==KeyEvent.VK_DOWN) player.down = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
