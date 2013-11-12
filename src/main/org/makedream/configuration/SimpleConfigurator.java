package org.makedream.configuration;

import ch.qos.logback.core.joran.GenericConfigurator;
import ch.qos.logback.core.joran.action.Action;
import ch.qos.logback.core.joran.action.ImplicitAction;
import ch.qos.logback.core.joran.spi.ElementSelector;
import ch.qos.logback.core.joran.spi.Interpreter;
import ch.qos.logback.core.joran.spi.RuleStore;

import java.util.List;
import java.util.Map;

public class SimpleConfigurator extends GenericConfigurator {

    final Map<ElementSelector, Action> ruleMap;
    final List<ImplicitAction> iaList;

    public SimpleConfigurator(Map<ElementSelector, Action> ruleMap) {
        this(ruleMap, null);
    }

    public SimpleConfigurator(Map<ElementSelector, Action> ruleMap, List<ImplicitAction> iaList) {
        this.ruleMap = ruleMap;
        this.iaList = iaList;
    }

    @Override
    protected void addInstanceRules(RuleStore rs) {
        for (ElementSelector elementSelector : ruleMap.keySet()) {
            Action action = ruleMap.get(elementSelector);
            rs.addRule(elementSelector, action);
        }
    }

    @Override
    protected void addImplicitRules(Interpreter interpreter) {
        if (iaList == null) {
            return;
        }
        for (ImplicitAction ia : iaList) {
            interpreter.addImplicitAction(ia);
        }
    }
}
