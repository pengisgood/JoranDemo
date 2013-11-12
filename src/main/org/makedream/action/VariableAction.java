package org.makedream.action;

import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.spi.ActionException;
import ch.qos.logback.core.joran.spi.InterpretationContext;
import ch.qos.logback.core.util.OptionHelper;
import org.xml.sax.Attributes;

public class VariableAction extends Action {
    @Override
    public void begin(InterpretationContext ic, String name, Attributes attributes) throws ActionException {
        String variable = attributes.getValue("value");
        if (OptionHelper.isEmpty(variable)) {
            ic.addError("The variable action requires a value attribute");
            return;
        }
        try {
            Integer v = Integer.valueOf(variable);
            ic.pushObject(v);

        } catch (NumberFormatException e) {
            ic.addError("The value [" + variable + "] could not be converted to an integer", e);
            throw e;
        }
    }

    @Override
    public void end(InterpretationContext ic, String name) throws ActionException {

    }
}
