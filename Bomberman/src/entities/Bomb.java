package entities;

import java.awt.image.BufferedImage;
import java.util.Timer;

public class Bomb extends Entity {
	
	public int timer = 0 , maxTimer = 3;
	Timer timer1 = new Timer();

	public Bomb(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
				
		timer++;;
		if(timer == maxTimer) {
			timer = 0;
			timer++;
			if(timer > maxTimer)
				timer = 0; 
		}

	}

	
}
