package dev.rosewood.roseeconomy;

import dev.rosewood.roseeconomy.manager.CommandManager;
import dev.rosewood.roseeconomy.manager.ConfigurationManager;
import dev.rosewood.roseeconomy.manager.CurrencyManager;
import dev.rosewood.roseeconomy.manager.DataManager;
import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.manager.*;

import java.util.Collections;
import java.util.List;

public class RoseEconomy extends RosePlugin {

    private static RoseEconomy instance;

    public RoseEconomy() {
        super(-1, -1, ConfigurationManager.class, DataManager.class, null, CommandManager.class);

        instance = this;
    }

    @Override
    protected void enable() {

    }

    @Override
    protected void disable() {

    }

    @Override
    protected List<Class<? extends Manager>> getManagerLoadPriority() {
        return Collections.singletonList(CurrencyManager.class);
    }

    public static RoseEconomy getInstance() {
        return instance;
    }

}
