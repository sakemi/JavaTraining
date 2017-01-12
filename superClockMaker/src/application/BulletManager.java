package application;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;

public class BulletManager {
	private static final BulletManager INSTANCE = new BulletManager();
	private final List<Bullet> bullets = new ArrayList<>();
	private static final int MAX = 750;

	private BulletManager(){
		//singleton
	}

	public static BulletManager getInstance(){
		return INSTANCE;
	}

	public void createBullet(double x, int velocity, double width, double height){
		bullets.add(new Bullet(x, velocity, width, height));
	}

	public void createBullet(double x, double y, int velocity, double width, double height){
		bullets.add(new Bullet(x, y, velocity, width, height));
	}

	public void endGame(){
		bullets.clear();
	}

	public void draw(GraphicsContext gc, Boundary clock) throws Hit{
		for(int i = 0; i < bullets.size(); i++){
			Bullet b = bullets.get(i);
			if(b.draw(gc, clock)){
				throw new Hit();
			}
			if(b.getY() > MAX){
				bullets.remove(i);
			}
		}
	}

	private class Bullet{
		private final double width;
		private final double height;
		private final double x;
		private double y = 0;
		private final int velocity;

		public Bullet(double x, int velocity, double width, double height){
			this.x = x;
			this.velocity = velocity;
			this.width = width;
			this.height = height;
		}

		public Bullet(double x, double y, int velocity, double width, double height){
			this.x = x;
			this.velocity = velocity;
			this.width = width;
			this.height = height;
			this.y = y;
		}

		public boolean draw(GraphicsContext gc, Boundary clock){
			gc.fillOval(x, y, width, height);
			y += velocity;
			return isHit(clock);
		}

		private boolean isHit(Boundary clock){
			return clock.isHit(new Boundary(x, y, width, height));
		}

		public double getY(){
			return y;
		}
	}
}
