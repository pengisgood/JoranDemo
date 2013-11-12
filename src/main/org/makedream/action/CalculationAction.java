package org.makedream.action;

import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.util.OptionHelper;
import org.xml.sax.Attributes;

import java.util.Stack;

public class CalculationAction extends Action {
    Stack<String> nameStack = new Stack<String>();

    @Override
    public void begin(InterpretationContext ic, String name, Attributes attributes) throws ActionException {
        String valueName = attributes.getValue("name");
        nameStack.push(valueName);
    }

    @Override
    public void end(InterpretationContext ic, String name) throws ActionException {
        String valueName = nameStack.pop();
        if (OptionHelper.isEmpty(valueName)) {

        } else {
            Integer result = (Integer) ic.peekObject();
            System.out.println("The calculation named [" + valueName + "] get the result " + result);
        }

    }
}
