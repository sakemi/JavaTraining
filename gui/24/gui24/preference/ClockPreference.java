package gui24.preference;

import java.awt.Font;
import java.awt.Point;
import java.util.EnumMap;
import java.util.Map;
import java.util.prefs.Preferences;

public class ClockPreference {
	private final Preferences pref;

	public ClockPreference(Preferences pref) {
		this.pref = pref;
	}

	public void save(Map<Data, Integer> data, String font, Point point) {
		for (Map.Entry<Data, Integer> e : data.entrySet()) {
			pref.putInt(e.getKey().toString(), e.getValue());
		}
		pref.put(Data.FONT.toString(), font);
		pref.putDouble(Data.X.toString(), point.getX());
		pref.putDouble(Data.Y.toString(), point.getY());
	}

	public Map<Data, Object> load() {
		Map<Data, Object> data = new EnumMap<>(Data.class);
		data.put(Data.FONT, pref.get(Data.FONT.toString(), Font.SERIF));
		data.put(Data.X, pref.getDouble(Data.X.toString(), 0));
		data.put(Data.Y, pref.getDouble(Data.Y.toString(), 0));
		data.put(Data.SIZE, pref.getInt(Data.SIZE.toString(), 16));
		data.put(Data.COLOR_R, pref.getInt(Data.COLOR_R.toString(), 0));
		data.put(Data.COLOR_G, pref.getInt(Data.COLOR_G.toString(), 0));
		data.put(Data.COLOR_B, pref.getInt(Data.COLOR_B.toString(), 0));
		data.put(Data.BACKGROUND_R, pref.getInt(Data.BACKGROUND_R.toString(), 255));
		data.put(Data.BACKGROUND_G, pref.getInt(Data.BACKGROUND_G.toString(), 255));
		data.put(Data.BACKGROUND_B, pref.getInt(Data.BACKGROUND_B.toString(), 255));
		return data;
	}
}
