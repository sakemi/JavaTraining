package gui24.ui;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ClockMouseListener implements MouseMotionListener, MouseListener {
	private final ClockFrame window;
	private final Point start = new Point();

	public ClockMouseListener(ClockFrame window) {
		this.window = window;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		if (e.getButton() == MouseEvent.BUTTON3) {
			window.showPopup(e.getX(), e.getY());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		start.setLocation(e.getPoint());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point eventLocation = e.getLocationOnScreen();
		int x = eventLocation.x - start.x;
		int y = eventLocation.y - start.y;
		// window.setLocation(eventLocation.x - xDist, eventLocation.y - yDist);
		window.setLocation(x, y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
