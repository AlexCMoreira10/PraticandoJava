package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;
import World.World;

public class Enemy extends Entity{
	
	private double speed = 0.3;
	private int maskx = 8,masky = 8,maskw = 8,maskh = 8;
	
	private int frames = 0, maxFrames = 25, index = 0, maxIndex = 1;
	private BufferedImage[] sprites;
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, null);

		sprites = new BufferedImage[2];
		sprites[0] = Game.spritesheet.getSprite(40, 16,8, 8);
		sprites[1] = Game.spritesheet.getSprite(48, 16,8, 8);
		// TODO Auto-generated constructor stub
	}
	
	
	public void tick() {
		
		if ((int)x < Game.player.getX() && World.isFree((int)(x+speed), this.getY())
				&& !isCollidding((int)(x+speed), this.getY())) {
			x+=speed;
		}

		else if ((int) x > Game.player.getX() && World.isFree((int)(x-speed), this.getY())
				&& !isCollidding((int)(x-speed), this.getY())){
			x-=speed;
		}
		
		else if ((int)y < Game.player.getY() && World.isFree(this.getX(), (int)(y+speed))
				&& !isCollidding(this.getX(), (int)(y+speed))) {
			y+=speed;
		} 
		
		else if ((int)y > Game.player.getY() && World.isFree(this.getX(), (int)(y-speed))
				&& !isCollidding(this.getX(), (int)(y-speed))) {
			y-=speed;
		}
		
		
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex)
					index = 0;
			}
		}

			
	/*	public boolean isColiddingWithPlay() {
			Rectangle enemyCurrent = new Rectangle(this.getX() + maskx,this.getY() + masky);
			
			return false;
		}*/
					
		public boolean isCollidding(int xnext,int ynext) {
			Rectangle enemyCurrent = new Rectangle(xnext,ynext,World.TILE_SIZE,World.TILE_SIZE);
			
			for(int i = 0; i < Game.enemies.size();  i++) {
				Enemy e = Game.enemies.get(i);
				if(e == this)
					continue;
				Rectangle targetEnemy =  new Rectangle(e.getX(),e.getY(),World.TILE_SIZE,World.TILE_SIZE);
				if(targetEnemy.intersects(enemyCurrent)){
					return true;
				}
			}
			
			return false;
		}
	
	
	
	public void render(Graphics g) {
		g.drawImage(sprites[index],this.getX()- Camera.x ,this.getY() - Camera.y,null);
		//g.setColor(Color.blue);
		//g.fillRect(this.getX()- Camera.x ,this.getY() - Camera.y, 8, 8);
	}
	
	
}
