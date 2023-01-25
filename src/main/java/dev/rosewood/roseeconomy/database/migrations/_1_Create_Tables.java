package dev.rosewood.roseeconomy.database.migrations;

import dev.rosewood.rosegarden.database.DataMigration;
import dev.rosewood.rosegarden.database.DatabaseConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class _1_Create_Tables extends DataMigration {

    public _1_Create_Tables() {
        super(1);
    }

    @Override
    public void migrate(DatabaseConnector connector, Connection connection, String tablePrefix) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + tablePrefix + "balances (" +
                    "uuid VARCHAR(36) NOT NULL, " +
                    "currency TEXT NOT NULL, " +
                    "balance DECIMAL NOT NULL, " +
                    "UNIQUE (uuid, currency))"
            );
        }
    }

}
