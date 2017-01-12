package application;

public final class Boundary {
	private final double x;
	private final double y;
	private final double width;
	private final double height;

	public Boundary(double x, double y, double width, double height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public boolean isHit(Boundary b){
		return inBound(b.x, b.y) || inBound(b.x, b.y + b.height) || inBound(b.x + b.width, b.y) || inBound(b.x + b.width, b.y + b.height);
	}

	private boolean inBound(double bx, double by){
		if(x <= bx && bx <= x + width && y <= by && by <= y + height){
			return true;
		}
		return false;
	}
}
