package org.makedream.action;

import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import org.xml.sax.Attributes;

import java.util.EmptyStackException;

public class MultiplyAction extends Action {
    @Override
    public void begin(InterpretationContext ic, String name, Attributes attributes) throws ActionException {
        int first = fetchInteger(ic);
        int second = fetchInteger(ic);
        ic.pushObject(first + second);

    }


    private int fetchInteger(InterpretationContext ic) {
        int result = 0;
        Object obj = ic.popObject();
        try {
            if (obj instanceof Integer) {
                result = ((Integer) obj).intValue();
            } else {
                String errorMessage = "Object [" + obj + "] currently at the top of the stack is not an integer.";
                ic.addError(errorMessage);
                throw new IllegalArgumentException(errorMessage);
            }
        } catch (EmptyStackException e) {
            ic.addError("Expect an integer on the execution stack.");
            throw e;
        }
        return result;
    }

    @Override
    public void end(InterpretationContext ic, String name) throws ActionException {

    }
}
