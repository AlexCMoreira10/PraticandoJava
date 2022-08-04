package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;
import World.World;

public class Player extends Entity{

	public boolean right, up, left, down;
	
	public int right_dir = 0, left_dir = 1, up_dir =2, down_dir=3;
	public int dir = right_dir;
	
	
	public double speed = 0.7;
	
	private int frames = 0, maxFrames = 10, index = 0, maxIndex =1; 
	private boolean moved = false;
	private BufferedImage[] rightPlayer;
	private BufferedImage[] leftPlayer;
	private BufferedImage[] upPlayer;
	private BufferedImage[] downPlayer;
	
	
	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
		
		rightPlayer = new BufferedImage[2];
		leftPlayer = new BufferedImage[2];
		upPlayer = new BufferedImage[2];
		downPlayer = new BufferedImage[2];
		
		for(int i =0; i < 2; i++) {
			leftPlayer[i] = Game.spritesheet.getSprite(0+ (i*8), 0, 8, 8);
		
		}
		
		for(int i =0; i < 2; i++) {
			rightPlayer[i] = Game.spritesheet.getSprite(72 + (i*8), 0, 8, 8);
		
		}
			for (int i =0; i < 2; i++) {
				upPlayer[i] = Game.spritesheet.getSprite(31 +(i*8), 0, 8, 8);
				
		}
		
			for (int i =0; i < 2; i++) {
				downPlayer[i] = Game.spritesheet.getSprite(16 + (i*8), 0, 8, 8);

			}
		
	}
	
	public void tick () {
		moved = false;
		if(right && World.isFree((int)(x+speed),this.getY())) {
			moved = true;
			dir = right_dir;
			x+=speed; 
			}
		else if(left && World.isFree((int)(x-speed),this.getY())) {
			moved = true;
			dir = left_dir;
			x-= speed; 
		}
		
		else if(up && World.isFree(this.getX() ,(int)(y-speed))) {
			moved = true;
			dir = up_dir;
			y-=speed;
		}
		
		else if(down && World.isFree(this.getX() ,(int)(y+speed))) {
			moved = true;
			dir = down_dir;
			y+=speed;
		}
		
		if(moved) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex)
					index = 0;
			}
		}
		
		Camera.x = Camera.clamp(this.getX() - (Game.WIDTH/2),0, World.WIDTH*8 - Game.WIDTH);
		Camera.y = Camera.clamp(this.getY() - (Game.HEIGHT/2),0, World.HEIGHT*8 - Game.HEIGHT);
		
	}
	
	public void render(Graphics g) {
		if(dir == right_dir) {
		g.drawImage(rightPlayer[index],this.getX()- Camera.x,this.getY()-Camera.y,null);
		} else if (dir == left_dir) {
			g.drawImage(leftPlayer[index],this.getX()- Camera.x,this.getY()-Camera.y,null);	
		}else if (dir == up_dir) {
			g.drawImage(upPlayer[index],this.getX()- Camera.x,this.getY()-Camera.y,null);
		} else if (dir == down_dir) {
			g.drawImage(downPlayer[index],this.getX()- Camera.x,this.getY()-Camera.y,null);
		}
		
		
	}
	

}
