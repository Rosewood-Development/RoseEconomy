package dev.rosewood.roseeconomy.manager;

import dev.rosewood.roseeconomy.database.migrations._1_Create_Tables;
import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.database.DataMigration;
import dev.rosewood.rosegarden.manager.AbstractDataManager;

import java.util.Collections;
import java.util.List;

public class DataManager extends AbstractDataManager {

    public DataManager(RosePlugin rosePlugin) {
        super(rosePlugin);
    }

    @Override
    public List<Class<? extends DataMigration>> getDataMigrations() {
        return Collections.singletonList(_1_Create_Tables.class);
    }

}
