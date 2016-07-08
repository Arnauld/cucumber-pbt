package sweet.feature.impl2.step;

import cucumber.api.java.After;
import sweet.pbt.Pbt;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class PbtHooks {

    private final Pbt pbt;

    public PbtHooks(Pbt pbt) {
        this.pbt = pbt;
    }

    @After
    public void checkProperties() {
        pbt.checkProperties();
    }
}
