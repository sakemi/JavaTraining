package util;

import javafx.scene.paint.Color;

public class PropUtil {
	public static String getColorCode(Color color) {
		return String.format("#%02X%02X%02X%02X", (int) (color.getRed() * 255), (int) (color.getGreen() * 255),
				(int) (color.getBlue() * 255), (int) (color.getOpacity() * 255));
	}

	public static Color generateColor(String colorCode) {
		double r = (double) Integer.valueOf(colorCode.substring(1, 3), 16) / 255d;
		double g = (double) Integer.valueOf(colorCode.substring(3, 5), 16) / 255d;
		double b = (double) Integer.valueOf(colorCode.substring(5, 7), 16) / 255d;
		double o = (double) Integer.valueOf(colorCode.substring(7, 9), 16) / 255d;

		return new Color(r, g, b, o);
	}
}
