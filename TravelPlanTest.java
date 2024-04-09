import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

class TravelPlanTest {

	@Test
	void testWeekendTrainFeasible001() {
		TravelPlan planner = new TravelPlan();
		LocalDate dateOfPlan = LocalDate.of(2024, 3, 10);
		String weather = "Sunny";
		LocalTime timeOfFirstApptStart = LocalTime.of(12, 15);
		LocalTime timeOfLastApptEnd = LocalTime.of(3+12, 45);
		
		String expectedPlan 
			= "Please take the 10 AM train to go to the city, and 4 PM train to get back home on 2024-Mar-10.";
		String actualPlan = planner.getPlan(dateOfPlan, weather, timeOfFirstApptStart, timeOfLastApptEnd);
		assertEquals(expectedPlan, actualPlan);
	}
	
	@Test
	void testWeekendTrainFeasible002() {
		TravelPlan planner = new TravelPlan();
		LocalDate dateOfPlan = LocalDate.of(2024, 3, 9);
		String weather = "Sunny";
		LocalTime timeOfFirstApptStart = LocalTime.of( 12, 15);
		LocalTime timeOfLastApptEnd = LocalTime.of(12 + 9, 45);
		
		String expectedPlan 
			= "Please take the 10 AM train to go to the city, and 10 PM train to get back home on 2024-Mar-09.";
		String actualPlan = planner.getPlan(dateOfPlan, weather, timeOfFirstApptStart, timeOfLastApptEnd);
		assertEquals(expectedPlan, actualPlan);
	}
	
	@Test
	void testRainyEarlyAppointmentWeekday003() {
		TravelPlan planner = new TravelPlan();
		String decision = 
				planner.getPlan(
						LocalDate.of(2023, 9, 26),
						"Rainy",
						LocalTime.of( 6, 0),
						LocalTime.of(11, 0)
						);
		assertEquals(
				"Please cancel or reschedule your appointments on 2023-Sep-26.",
				decision);
	}
	
	@Test
	void testSnowEarlyAppointmentWeekend004() {
		TravelPlan planner = new TravelPlan();
		String decision = 
				planner.getPlan(
						LocalDate.of(2023, 10, 8),
						"Snowy",
						LocalTime.of( 6, 55),
						LocalTime.of(14, 10)
						);
		assertEquals(
				"Please cancel or reschedule your appointments on 2023-Oct-08.",
				decision);
	}
	
	@Test
	void testSnowLateAppointmentWeekend005() {
		TravelPlan planner = new TravelPlan();
		String decision = 
				planner.getPlan(
						LocalDate.of(2023, 10, 7),
						"Snowy",
						LocalTime.of(12, 55),
						LocalTime.of(22, 01)
						);
		assertEquals(
				"Please cancel or reschedule your appointments on 2023-Oct-07.",
				decision);
	}
	
	@Test
	void testWeekendCar006() {
		TravelPlan planner = new TravelPlan();
		LocalDate dateOfPlan = LocalDate.of(2024, 3, 10);
		String weather = "Sunny";
		LocalTime timeOfFirstApptStart = LocalTime.of(5+12, 35);
		LocalTime timeOfLastApptEnd = LocalTime.of(10+12, 30);
		
		String expectedPlan 
			= "Please drive on 2024-Mar-10, and leave the house at 4:35 PM.";
		String actualPlan = planner.getPlan(dateOfPlan, weather, timeOfFirstApptStart, timeOfLastApptEnd);
		assertEquals(expectedPlan, actualPlan);
	}
	
	@Test
	void testWeekdayCar007() {
		TravelPlan planner = new TravelPlan();
		LocalDate dateOfPlan = LocalDate.of(2024, 3, 19);
		String weather = "Cloudy";
		LocalTime timeOfFirstApptStart = LocalTime.of(8, 45);
		LocalTime timeOfLastApptEnd = LocalTime.of(11+12, 30);
		
		String expectedPlan 
			= "Please drive on 2024-Mar-19, and leave the house at 7:45 AM.";
		String actualPlan = planner.getPlan(dateOfPlan, weather, timeOfFirstApptStart, timeOfLastApptEnd);
		assertEquals(expectedPlan, actualPlan);
	}
	
	@Test
	void testWeekdayCar008() {
		TravelPlan planner = new TravelPlan();
		LocalDate dateOfPlan = LocalDate.of(2024, 5, 21);
		String weather = "Sunny";
		LocalTime timeOfFirstApptStart = LocalTime.of(5, 55);
		LocalTime timeOfLastApptEnd = LocalTime.of(11, 30);
		
		String expectedPlan 
			= "Please drive on 2024-May-21, and leave the house at 4:55 AM.";
		String actualPlan = planner.getPlan(dateOfPlan, weather, timeOfFirstApptStart, timeOfLastApptEnd);
		assertEquals(expectedPlan, actualPlan);
	}
	
	@Test
	void testWeekdayCar009() {
		TravelPlan planner = new TravelPlan();
		LocalDate dateOfPlan = LocalDate.of(2024, 5, 22);
		String weather = "Cloudy";
		LocalTime timeOfFirstApptStart = LocalTime.of(4, 05);
		LocalTime timeOfLastApptEnd = LocalTime.of(8, 35);
		
		String expectedPlan 
			= "Please drive on 2024-May-22, and leave the house at 3:05 AM.";
		String actualPlan = planner.getPlan(dateOfPlan, weather, timeOfFirstApptStart, timeOfLastApptEnd);
		assertEquals(expectedPlan, actualPlan);
	}
	
	@Test
	void testCar010() {
		TravelPlan planner = new TravelPlan();
		LocalDate dateOfPlan = LocalDate.of(2024, 4, 23);
		String weather = "Cloudy";
		LocalTime timeOfFirstApptStart = LocalTime.of(5, 00);
		LocalTime timeOfLastApptEnd = LocalTime.of(12, 35);
		
		String expectedPlan 
			= "Please drive on 2024-Apr-23, and leave the house at 4 AM.";
		String actualPlan = planner.getPlan(dateOfPlan, weather, timeOfFirstApptStart, timeOfLastApptEnd);
		assertEquals(expectedPlan, actualPlan);
	}
	
	@Test
	void testCar011() {
		TravelPlan planner = new TravelPlan();
		LocalDate dateOfPlan = LocalDate.of(2020, 1, 1);
		String weather = "Cloudy";
		LocalTime timeOfFirstApptStart = LocalTime.of(23, 01);
		LocalTime timeOfLastApptEnd = LocalTime.of(23, 55);
		
		String expectedPlan 
			= "Please drive on 2020-Jan-01, and leave the house at 10:01 PM.";
		String actualPlan = planner.getPlan(dateOfPlan, weather, timeOfFirstApptStart, timeOfLastApptEnd);
		assertEquals(expectedPlan, actualPlan);
	}
	
	@Test
	void testCar012() {
		TravelPlan planner = new TravelPlan();
		LocalDate dateOfPlan = LocalDate.of(1920, 1, 1);
		String weather = "Sunny";
		LocalTime timeOfFirstApptStart = LocalTime.of(23, 00);
		LocalTime timeOfLastApptEnd = LocalTime.of(23, 55);
		
		String expectedPlan 
			= "Please drive on 1920-Jan-01, and leave the house at 10 PM.";
		String actualPlan = planner.getPlan(dateOfPlan, weather, timeOfFirstApptStart, timeOfLastApptEnd);
		assertEquals(expectedPlan, actualPlan);
	}
	
	@Test
	void testWeekdayTrainFeasible013() {
		TravelPlan planner = new TravelPlan();
		LocalDate dateOfPlan = LocalDate.of(2024, 3, 12);
		String weather = "Rainy";
		LocalTime timeOfFirstApptStart = LocalTime.of(   7, 35);
		LocalTime timeOfLastApptEnd = LocalTime.of(12 + 10, 45);
		
		String expectedPlan 
			= "Please take the 6:30 AM train to go to the city, and 11 PM train to get back home on 2024-Mar-12.";
		String actualPlan = planner.getPlan(dateOfPlan, weather, timeOfFirstApptStart, timeOfLastApptEnd);
		assertEquals(expectedPlan, actualPlan);
	}
	
	@Test
	void testWeekdayTrainFeasible014() {
		TravelPlan planner = new TravelPlan();
		LocalDate dateOfPlan = LocalDate.of(2024, 3, 13);
		String weather = "Rainy";
		LocalTime timeOfFirstApptStart = LocalTime.of(   8, 35);
		LocalTime timeOfLastApptEnd = LocalTime.of(12 + 10, 45);
		
		String expectedPlan 
			= "Please take the 7:30 AM train to go to the city, and 11 PM train to get back home on 2024-Mar-13.";
		String actualPlan = planner.getPlan(dateOfPlan, weather, timeOfFirstApptStart, timeOfLastApptEnd);
		assertEquals(expectedPlan, actualPlan);
	}
	
	@Test
	void testWeekdayTrainFeasible015() {
		TravelPlan planner = new TravelPlan();
		LocalDate dateOfPlan = LocalDate.of(2024, 3, 14);
		String weather = "Rainy";
		LocalTime timeOfFirstApptStart = LocalTime.of(   9, 35);
		LocalTime timeOfLastApptEnd = LocalTime.of(12 + 10, 45);
		
		String expectedPlan 
			= "Please take the 8:30 AM train to go to the city, and 11 PM train to get back home on 2024-Mar-14.";
		String actualPlan = planner.getPlan(dateOfPlan, weather, timeOfFirstApptStart, timeOfLastApptEnd);
		assertEquals(expectedPlan, actualPlan);
	}
	
	@Test
	void testWeekdayTrainFeasible016() {
		TravelPlan planner = new TravelPlan();
		LocalDate dateOfPlan = LocalDate.of(2024, 3, 15);
		String weather = "Rainy";
		LocalTime timeOfFirstApptStart = LocalTime.of(  10, 35);
		LocalTime timeOfLastApptEnd = LocalTime.of(12 + 10, 45);
		
		String expectedPlan 
			= "Please take the 9:30 AM train to go to the city, and 11 PM train to get back home on 2024-Mar-15.";
		String actualPlan = planner.getPlan(dateOfPlan, weather, timeOfFirstApptStart, timeOfLastApptEnd);
		assertEquals(expectedPlan, actualPlan);
	}
	
	@Test
	void testWeekdayTrainFeasible017() {
		TravelPlan planner = new TravelPlan();
		LocalDate dateOfPlan = LocalDate.of(2024, 3, 19);
		String weather = "Rainy";
		LocalTime timeOfFirstApptStart = LocalTime.of(  11, 35);
		LocalTime timeOfLastApptEnd = LocalTime.of(12 + 10, 45);
		
		String expectedPlan 
			= "Please take the 10:30 AM train to go to the city, and 11 PM train to get back home on 2024-Mar-19.";
		String actualPlan = planner.getPlan(dateOfPlan, weather, timeOfFirstApptStart, timeOfLastApptEnd);
		assertEquals(expectedPlan, actualPlan);
	}
	
	@Test
	void testWeekdayTrainFeasible018() {
		TravelPlan planner = new TravelPlan();
		LocalDate dateOfPlan = LocalDate.of(2024, 3, 20);
		String weather = "Rainy";
		LocalTime timeOfFirstApptStart = LocalTime.of(  12, 35);
		LocalTime timeOfLastApptEnd = LocalTime.of(12 + 10, 45);
		
		String expectedPlan 
			= "Please take the 11:30 AM train to go to the city, and 11 PM train to get back home on 2024-Mar-20.";
		String actualPlan = planner.getPlan(dateOfPlan, weather, timeOfFirstApptStart, timeOfLastApptEnd);
		assertEquals(expectedPlan, actualPlan);
	}
	
	@Test
	void testWeekdayTrainFeasible019() {
		TravelPlan planner = new TravelPlan();
		LocalDate dateOfPlan = LocalDate.of(2024, 3, 21);
		String weather = "Rainy";
		LocalTime timeOfFirstApptStart = LocalTime.of(  12, 35);
		LocalTime timeOfLastApptEnd = LocalTime.of(12 + 9, 00);
		
		String expectedPlan 
			= "Please take the 11:30 AM train to go to the city, and 9:30 PM train to get back home on 2024-Mar-21.";
		String actualPlan = planner.getPlan(dateOfPlan, weather, timeOfFirstApptStart, timeOfLastApptEnd);
		assertEquals(expectedPlan, actualPlan);
	}
	
	@Test
	void testWeekdayTrainFeasible020() {
		TravelPlan planner = new TravelPlan();
		LocalDate dateOfPlan = LocalDate.of(2024, 3, 22);
		String weather = "Rainy";
		LocalTime timeOfFirstApptStart = LocalTime.of(  12, 35);
		LocalTime timeOfLastApptEnd = LocalTime.of(12 + 10, 10);
		
		String expectedPlan 
			= "Please take the 11:30 AM train to go to the city, and 10:30 PM train to get back home on 2024-Mar-22.";
		String actualPlan = planner.getPlan(dateOfPlan, weather, timeOfFirstApptStart, timeOfLastApptEnd);
		assertEquals(expectedPlan, actualPlan);
	}
	
		
}
