Let's take some time to analyze such scenario:

```gherkin
Feature: Price

  Scenario: Price is unit price times quantity

    Given a selection of a quantity of any sweet with a unit price
    When I ask for the price
    Then the price is unit price * quantity
```

Scenario title: `Price is unit price times quantity`

* Price of what
* Unit price of what

It seems that Price is the Price of a certain quantity of a type of sweet;
The Unit price is then the unit price of such type of sweet;

May be a more readable scenario could be:

```
Given any kind of sweet
When I ask the price for any quantity of such sweet
Then the total price should be quantity * unit price of the sweet
```

but do we effectively ask for a price, or is it simply a consequence:

```
Given any quantity of any kind of sweet
Then the total price should be the quantity * unit price of the sweet
```

Let's suppose now some reduction applies: ...

```
Given there is a 10% reduction when a quantity threshold is reached
And any quantity of any kind of sweet
Then the total price should be less or equal to quantity * unit price of the sweet
```
