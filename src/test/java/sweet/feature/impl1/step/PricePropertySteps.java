package sweet.feature.impl1.step;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.quicktheories.quicktheories.generators.SourceDSL;
import sweet.pbt.Pbt;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class PricePropertySteps {

    public enum Key {
        QUANTITY,
        UNIT_PRICE,
        PRICE
    }

    private final Pbt pbt;

    public PricePropertySteps(Pbt pbt) {
        this.pbt = pbt;
    }

    @Given("^a selection of a quantity of any sweet with a unit price$")
    public void a_selection_of_a_quantity_of_any_sweet_with_a_unit_price() throws Throwable {
        pbt.setSource(Key.QUANTITY, SourceDSL.integers().all());
        pbt.setSource(Key.UNIT_PRICE, SourceDSL.doubles().fromZeroToDoubleMax());
    }

    @When("^I ask for the price$")
    public void i_ask_for_the_price() throws Throwable {
        pbt.addChecker(ctx -> {
            double unitPrice = ctx.get(Key.UNIT_PRICE);
            int quantity = ctx.get(Key.QUANTITY);
            double price = calculatePrice(unitPrice, quantity);
            ctx.set(Key.PRICE, price);
        });
    }

    private double calculatePrice(double unitPrice, int quantity) {
        return unitPrice * quantity;
    }

    @Then("^the price is unit price \\* quantity$")
    public void the_price_is_unit_price_quantity() throws Throwable {
        pbt.addChecker(ctx -> {
            double unitPrice = ctx.get(Key.UNIT_PRICE);
            int quantity = ctx.get(Key.QUANTITY);
            double price = ctx.get(Key.PRICE);
            assertThat(price).isEqualTo(unitPrice * quantity);
        });
    }
}
