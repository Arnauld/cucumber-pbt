Feature: Price

  Scenario: Price is unit price times quantity

    Given a selection of a quantity of any sweet with a unit price
    When I ask for the price
    Then the price is unit price * quantity