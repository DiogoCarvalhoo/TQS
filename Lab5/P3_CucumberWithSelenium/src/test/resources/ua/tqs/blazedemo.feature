Feature: Blaze Demo

  Scenario: Purchase a flight ticket from "Boston" to "Berlin"
    Given I am on "https://blazedemo.com/"
      And I pick "Boston" for departure city
      And I pick "Berlin" for destination city
      And I press "Find Flights" button
     
      Then "Flights from Boston to Berlin" appear as h3 title
      And I press "Choose This Flight" button
      
      Then "Your flight from TLV to SFO has been reserved." appear as h2 title
      And I fill "name" field with "Diogo Carvalho"
      And I fill "address" field with "Rua de Belmonte"
      And I fill "city" field with "Belmonte"
      And I fill "state" field with "Castelo Branco"
      And I fill "zip code" field with "12345"

      And I press "Purchase Flight" button
      Then "Thank you for your purchase today!" appear as h1 title
