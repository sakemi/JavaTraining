package ch10.ex03;

public class WorkingDay {
	// if-else
	public boolean isWorkIfElse(DayOfWeek dayOfWeek) {
		if (dayOfWeek == DayOfWeek.SUNDAY || dayOfWeek == DayOfWeek.SATURDAY) {
			return false;
		} else if (dayOfWeek == DayOfWeek.MONDAY || dayOfWeek == DayOfWeek.TUESDAY || dayOfWeek == DayOfWeek.WEDNESDAY
				|| dayOfWeek == DayOfWeek.THURSDAY || dayOfWeek == DayOfWeek.FRIDAY) {
			return true;
		} else {
			throw new IllegalArgumentException();
		}
	}

	// switch
	public boolean isWorkSwitch(DayOfWeek dayOfWeek) {
		switch (dayOfWeek) {
		case SUNDAY:
		case SATURDAY:
			return false;
		case MONDAY:
		case TUESDAY:
		case WEDNESDAY:
		case THURSDAY:
		case FRIDAY:
			return true;
		default:
			throw new IllegalArgumentException();
		}
	}
}
