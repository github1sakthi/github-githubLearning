#Author: Devisakthi Krishnakumar@cognizant.com
Feature: Test complex search input

	@OrbitzFlightSearchTest
	Scenario: User searches for ticket in orbitz
Given Open browser and loaded orbitz portal
When User clicks on flight tab
And  Enters flying from and flying to airport codes
And Enters departing and returning dates
And Clicks advanced option link and choses nonStop checkbox
And Selects Emirates as the prefered airline
And clicks on Search button
Then Should load results page with available flights

