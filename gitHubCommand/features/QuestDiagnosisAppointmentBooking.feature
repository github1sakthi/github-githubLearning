#Author: Devisakthi Krishnakumar@cognizant.com
Feature: Test complex search input


	@QuestDiagnosisAppointmentBookingTest
	Scenario: User searches an appointment for Diagnosis test
		Given Open browser and load quest diagnostics page
		When User enters location
		And  User enters reason for testing
		And User enters date and time and clicks Continue
		Then Should reload the page with appointment details

	@QuestDiagnosisAppointmentBookingTest
	Scenario: User searches for a weekend appointment
			Given Open browser and load quest diagnostics page
		When User enters location
		And  User enters reason for testing
		And User enters a weekend date and time and clicks Continue
		Then Should reload the page with error message
