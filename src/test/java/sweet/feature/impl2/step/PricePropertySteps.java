package sweet.feature.impl2.step;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import sweet.feature.impl2.domain.OrderLine;
import sweet.feature.impl2.domain.Price;
import sweet.feature.impl2.domain.Prices;
import sweet.feature.impl2.domain.Sweet;
import sweet.feature.impl2.generator.OrderLineGenerator;
import sweet.feature.impl2.generator.PriceGenerator;
import sweet.pbt.Pbt;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class PricePropertySteps {

    public enum Key {
        OrderLine,
        UnitPrice,
        Price
    }

    private final Pbt pbt;

    public PricePropertySteps(Pbt pbt) {
        this.pbt = pbt;
    }

    @Given("^a selection of a quantity of any sweet with a unit price$")
    public void a_selection_of_a_quantity_of_any_sweet_with_a_unit_price() throws Throwable {
        pbt.setSource(Key.OrderLine, OrderLineGenerator.source());
        pbt.setSource(Key.UnitPrice, PriceGenerator.source());
    }

    @When("^I ask for the price$")
    public void i_ask_for_the_price() throws Throwable {
        pbt.addInitializer(ctx -> {
            OrderLine orderLine = ctx.get(Key.OrderLine);

            Price unitPrice = ctx.get(Key.UnitPrice);
            Prices prices = mock(Prices.class);
            when(prices.priceOf(any(Sweet.class))).thenReturn(unitPrice);
            ctx.set(Key.Price, orderLine.total(prices));
        });
    }

    @Then("^the price is unit price \\* quantity$")
    public void the_price_is_unit_price_quantity() throws Throwable {
        pbt.addChecker(ctx -> {
            OrderLine orderLine = ctx.get(Key.OrderLine);
            Price unitPrice = ctx.get(Key.UnitPrice);
            Price price = ctx.get(Key.Price);
            assertThat(price).isEqualTo(unitPrice.mult(orderLine.quantity()));
        });
    }
}
