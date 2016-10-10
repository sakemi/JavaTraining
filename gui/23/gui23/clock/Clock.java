package gui23.clock;

import java.util.Calendar;

public class Clock {
	public String getNow() {
		Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);

		String time = hour + ":" + minute + ":" + second;
		return time;
	}
}
