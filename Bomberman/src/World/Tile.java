package World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Main.Game;

public class Tile {
	
	public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(96,0,8,8);
	public static BufferedImage TILE_WALLF = Game.spritesheet.getSprite(88,0,8,8);
	public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(120,32,8,8);
	public static BufferedImage TILE_DOOR = Game.spritesheet.getSprite(0,8,8,8);
	
	private BufferedImage sprite;
	private int x,y;
	
	public Tile(int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite,x - Camera.x, y - Camera.y,null);
	}
}
