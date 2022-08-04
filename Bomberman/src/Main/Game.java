package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
//import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import World.World;
import entities.Enemy;
import entities.Entity;
import entities.Player;
import graficos.Spritesheet;

public class Game extends Canvas implements Runnable,KeyListener{
	//Canvas possui as propriedades para começar a criar uma janela
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	private boolean isRunning = true;
	public static final int WIDTH = 120;
	public static final int HEIGHT = 90;
	private final int SCALE = 5;

	private BufferedImage image;

	public static List<Entity> entities;
	public static List<Enemy> enemies;

	
	public static Spritesheet spritesheet;

	public static Player player;
	
	public static World world;
	
	public Game() {
		addKeyListener(this);

		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		initFrame();
		
		
		//Iniiciar objetos
		
		image = new BufferedImage(WIDTH, HEIGHT,BufferedImage.TYPE_INT_RGB);
		entities = new ArrayList<Entity>();
		enemies = new ArrayList<Enemy>();
		spritesheet = new Spritesheet("/spritesheet.png");
		player = new Player(0,0,8,8,spritesheet.getSprite(0,0,8,8));
		
		world = new World("/Map1.png");
		entities.add(player);
	}
	
	public void initFrame() {
		frame = new JFrame("Bomber "); //nome do jogo
		frame.add(this);
		frame.setResizable(true); //false aqui dentro é para não redimensionar a janela
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();

	}
	
	
	public synchronized void stop() {
		isRunning = false;
		
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
	
	public static void main(String arg[]) {
		Game game = new Game();
		game.start();
	} 
	
	public void tick() {
		for (int i =0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}

	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;		
			}

		
		Graphics g = image.getGraphics();
		g.setColor(new Color(0,120,0));
		g.fillRect(0 , 0, WIDTH*SCALE, HEIGHT*SCALE);
		
		/* Render Game*/
		world.render(g);
		
				//aqui loop de renderização
		for (int i =0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}

		/*Fim do render game*/
		
		g.dispose(); // Limpar imagens e otimização 
		
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH*SCALE,  HEIGHT*SCALE, null);
		bs.show();
		
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning) {
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1 ) {
				tick();
				render();
				frames++;
				delta--;
			}
			if(System.currentTimeMillis() - timer >= 1000) {
				// System.out.println("FPS " +frames);
				frames = 0;
				timer+=1000;
			}
			
		}
		
		stop();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT ) {
			player.right = true;
			
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT ) {
			player.left = true;
		
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = true;
			
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = true;
		
		}

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_RIGHT ) {
			player.right = false;
				
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT ) {
			player.left = false;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.up = false;
		}
		
		else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.down = false;
		}

		
	}
	
	

}
