import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TravelPlan {
	
	public static final int travelTimeMinutes = 60;

	public String getPlan (
			LocalDate dateOfPlan,
			String weather,
			LocalTime timeOfFirstApptStart,
			LocalTime timeOfLastApptEnd
			) {
		if (trainIsFeasible(dateOfPlan, timeOfFirstApptStart, timeOfLastApptEnd))
			 return getTrainPlan(dateOfPlan, timeOfFirstApptStart, timeOfLastApptEnd);
		else if (weatherIsGood(weather))
			return getCarPlan(dateOfPlan, timeOfFirstApptStart);
		else
			return reschedulePlan(dateOfPlan);
		
	}

	private String reschedulePlan(LocalDate dateOfPlan) {
		String formattedDate = formatDate(dateOfPlan);
		return "Please cancel or reschedule your appointments on " + formattedDate + ".";
	}

	private String formatDate(LocalDate date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");
		String text = date.format(formatter);
		return text;
	}

	private String getCarPlan(LocalDate dateOfPlan, LocalTime timeOfFirstApptStart) {
		return String.format("Please drive on %s, and leave the house at %s.", 
		formatDate(dateOfPlan), 
		formatTime(timeOfFirstApptStart.minusMinutes(travelTimeMinutes))
		);
	}

	private Object formatTime(LocalTime time) {
		String pattern;
		if (time.getMinute() == 0) pattern = "h a";
		else pattern = "h:mm a";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		String text = time.format(formatter);
		return text;
	}

	private boolean weatherIsGood(String weather) {
		return weather.equals("Sunny") || weather.equals("Cloudy");
	}

	private String getTrainPlan(
		LocalDate dateOfPlan, LocalTime timeOfFirstApptStart, LocalTime timeOfLastApptEnd) {
		ArrayList<LocalTime> trainTimes = getTrainTimes(dateOfPlan);
		LocalTime t1 = timeOfFirstApptStart.minusMinutes(travelTimeMinutes);
		LocalTime 	trainToCity = trainTimes.get(0), 
					trainBack = trainTimes.get(trainTimes.size() - 1);
		for(int i = 1; i < trainTimes.size(); i++) {
			if (t1.isAfter(trainTimes.get(i)))
			trainToCity = trainTimes.get(i);
			else break;
		}
		for(int j = trainTimes.size() - 2; j >= 0; j--) {
			if(timeOfLastApptEnd.isBefore(trainTimes.get(j))) trainBack = trainTimes.get(j);
			else break;
		}
		return String.format(
				"Please take the %s train to go to the city, and %s train to get back home on %s.",
				formatTime(trainToCity), formatTime(trainBack), formatDate(dateOfPlan));			
	}

	private ArrayList<LocalTime> getTrainTimes(LocalDate dateOfPlan) {
		LocalTime tFirst, tLast;
		int intervalMinutes;
		if(isWeekend(dateOfPlan)) {
			tFirst = LocalTime.of(    10, 0);
			tLast = LocalTime.of(12 + 10, 0);
			intervalMinutes = 120;
		}
		else {
			tFirst = LocalTime.of(     6, 0);
			tLast = LocalTime.of(12 + 11, 0);
			intervalMinutes = 30;
		}
		ArrayList<LocalTime> trainTimes = new ArrayList<LocalTime>();
		LocalTime aTrainTime = tFirst;
		while (aTrainTime.isBefore(tLast)) {
			trainTimes.add(aTrainTime);
			aTrainTime = aTrainTime.plusMinutes(intervalMinutes);
		}
		trainTimes.add(tLast);
		return trainTimes;
	}

	private boolean trainIsFeasible(
		LocalDate dateOfPlan, LocalTime timeOfFirstApptStart, LocalTime timeOfLastApptEnd) {
		LocalTime t1, t2;
		if (isWeekend(dateOfPlan)) {
			t1 = LocalTime.of(11   , 0);
			t2 = LocalTime.of(10+12, 0);
		}
		else {
		t1 = LocalTime.of( 7     , 0);
		t2 = LocalTime.of(11 + 12, 0);
		}
		return timeOfFirstApptStart.isAfter(t1) &&
			   timeOfLastApptEnd.isBefore(t2);
	}

	private boolean isWeekend(LocalDate dateOfPlan) {
		DayOfWeek dayOfWeek = dateOfPlan.getDayOfWeek();
		return (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY);
	}
}
