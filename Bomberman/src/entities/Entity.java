package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;
import World.Camera;

public class Entity {
	
	public static BufferedImage BOMB_EN = Game.spritesheet.getSprite(64, 24, 8, 8);
	public static BufferedImage BRINCK_EN = Game.spritesheet.getSprite(96, 0, 8, 8);
	public static BufferedImage ENEMY_EN = Game.spritesheet.getSprite(40, 16, 8, 8);
	public static BufferedImage DOOR_EN = Game.spritesheet.getSprite(0, 8, 8, 8);
	
	protected double x;
	protected double y;
	protected int width;
	protected int height;
	
	private BufferedImage sprite;
	
	public Entity(int x, int y, int width, int height, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
	}
	
	public void setX(int newX ) {
		this.x = newX;
	}
	
	public void setY(int newY ) {
		this.y = newY;
	}

	public int getX() {
		return (int)this.x;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void tick() {

	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX()- Camera.x,this.getY() - Camera.y,null);
	}
	
	
}
