package World;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Main.Game;
import entities.Bomb;
import entities.Enemy;
import entities.Entity;

public class World {
	
	public static Tile[] tiles;
	public static int WIDTH, HEIGHT;
	public static final int TILE_SIZE = 8;
	
	
	public World(String path)  {
		try {
			BufferedImage map = ImageIO.read(getClass().getResource(path));
			int [] pixels = new int[map.getWidth() * map.getHeight()];
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			tiles = new Tile[map.getWidth() * map.getHeight()];
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0,map.getWidth());
			for(int xx = 0; xx < map.getWidth(); xx++) {
				for (int yy = 0; yy < map.getHeight(); yy++) {
					int pixelAtual = pixels[xx + (yy * map.getWidth())];
					//chão/Floor
					tiles[xx+(yy*WIDTH)] = new FloorTile(xx*8,yy*8,Tile.TILE_FLOOR);
					if(pixels[xx+ (yy*map.getWidth())]== 0xFF000000) {
						//000000 Preto Parede forte
						tiles[xx+(yy*WIDTH)] = new WallTile(xx*8,yy*8,Tile.TILE_WALLF);
						
					} else if (pixels[xx+(yy*map.getWidth())] == 0xFFFF7B00) {
						//Parede
						tiles[xx+(yy*WIDTH)] = new WallfTile(xx*8,yy*8,Tile.TILE_WALL);
					
					} else if (pixels[xx+(yy*map.getWidth())] == 0xFFFF00DC) {
						//Porta
						tiles[xx+(yy*WIDTH)] = new DoorTile(xx*8,yy*8,Tile.TILE_DOOR);	
						
					} else if (pixelAtual == 0xFFFF0000) {
						//Enemy
						//Game.entities.add(new Enemy(xx*8,yy*8,8,8,Entity.ENEMY_EN));
						Enemy en = new Enemy(xx*8,yy*8,8,8,Entity.ENEMY_EN);
						Game.entities.add(en);
						Game.entities.add(en);
						
					} else if (pixelAtual == 0xFF0026FF) {
						//Bomb
						Game.entities.add(new Bomb(xx*8,yy*8,8,8,Entity.BOMB_EN));
						
					} else if (pixelAtual == 0xFFFF00DC) {
						//Door
						
					} else if (pixelAtual == 0xFFFFD800) {
						//player
						 Game.player.setX(xx*8);
						 Game.player.setY(yy*8);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static boolean isFree(int xnext, int ynext) {
		int x1 = xnext / TILE_SIZE;
		int y1 = ynext / TILE_SIZE;
		
		int x2 = (xnext+TILE_SIZE-1) / TILE_SIZE;
		int y2 = ynext / TILE_SIZE;
		
		int x3 = xnext / TILE_SIZE;
		int y3 = (ynext+TILE_SIZE-1) / TILE_SIZE;
		
		int x4 = (xnext+TILE_SIZE-1)  / TILE_SIZE;
		int y4 = (ynext+TILE_SIZE-1)  / TILE_SIZE;
		
		return !(tiles[x1 + (y1*World.WIDTH)] instanceof WallTile || 
				tiles[x2 + (y2*World.WIDTH)] instanceof WallTile ||
				tiles[x3 + (y3*World.WIDTH)] instanceof WallTile ||
				tiles[x4 + (y4*World.WIDTH)] instanceof WallTile ||
				tiles[x1 + (y1*World.WIDTH)] instanceof WallfTile ||
				tiles[x2 + (y2*World.WIDTH)] instanceof WallfTile ||
				tiles[x3 + (y3*World.WIDTH)] instanceof WallfTile ||
				tiles[x4 + (y4*World.WIDTH)] instanceof WallTile);
	}
	
	
	public void render(Graphics g) {
		int xstart = Camera.x/8;	// >> (divisão com o simbulo (>>) assim pode ser mais rapida 
		int ystart = Camera.y/8;
		
		int xfinal = xstart + (Game.WIDTH / 8);
		int yfinal = ystart + (Game.HEIGHT / 8);
		
		for(int xx = xstart; xx <= xfinal; xx++) {
			for(int yy = ystart; yy <= yfinal; yy++) {
				if(xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
					continue;
				Tile tile = tiles[xx + (yy*WIDTH)];
				tile.render(g);
					}
			
			}

		}
	
	
	
	}
