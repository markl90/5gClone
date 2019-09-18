Feature: Performing an end to end order journey

#  Background:

  @test
  Scenario: Successful search with primary search bar
    Given the user is on the landing page
    When the the user selects the phones link
    And the user specifies a search selecting a result
      | brand       | Capacity   | Colour     | Product   |
      | Apple       | 256GB      | Space Grey | iPhone Xs |
    Then selects their contract details
      | Length    |  Data |
      | 24 month  | 8GB   |
    And specifies details on the product setup section
      | Phone Quantity  | Are you switching mobile provider | Spending cap amount |
      |       1         | Yes, and keep my number           | none                |
    And selects option extras
      | Extra minutes option | Extra Data Option |
      | Helpline extra       | Data 1GB          |
    Then the basket contains the phone with the chosen plan
    When the user enters generated user details
    Then the order is successful and confirmation of the details is presented


  Scenario: Help Step
    Given the helper step
