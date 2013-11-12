package org.makedream.app;

import ch.qos.logback.core.Context;
import ch.qos.logback.core.ContextBase;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.spi.ElementSelector;
import ch.qos.logback.core.joran.spi.JoranException;
import ch.qos.logback.core.util.StatusPrinter;
import org.makedream.configuration.SimpleConfigurator;
import org.makedream.action.AddAction;
import org.makedream.action.CalculationAction;
import org.makedream.action.MultiplyAction;
import org.makedream.action.VariableAction;

import java.util.HashMap;
import java.util.Map;

public class Calculator {

    public static void main(String[] args) throws Exception {
        Map<ElementSelector, Action> ruleMap = new HashMap<ElementSelector, Action>();

        ruleMap.put(new ElementSelector("*/calculation"), new CalculationAction());

        ruleMap.put(new ElementSelector("*/calculation/variable"), new VariableAction());
        ruleMap.put(new ElementSelector("*/calculation/add"), new AddAction());
        ruleMap.put(new ElementSelector("*/calculation/multiply"), new MultiplyAction());

        Context context = new ContextBase();
        SimpleConfigurator simpleConfigurator = new SimpleConfigurator(ruleMap);

        // link the configurator with its context
        simpleConfigurator.setContext(context);

        try {
            simpleConfigurator.doConfigure("calculation.xml");
        } catch (JoranException e) {
            // Print any errors that might have occured.
            StatusPrinter.print(context);
        }
    }
}
