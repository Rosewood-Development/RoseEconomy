package dev.rosewood.roseeconomy.manager;

import dev.rosewood.roseeconomy.RoseEconomy;
import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.config.CommentedFileConfiguration;
import dev.rosewood.rosegarden.config.RoseSetting;
import dev.rosewood.rosegarden.manager.AbstractConfigurationManager;

public class ConfigurationManager extends AbstractConfigurationManager {

    public enum Setting implements RoseSetting {
        ;

        private final String key;
        private final Object defaultValue;
        private final String[] comments;
        private Object value = null;

        Setting(String key, Object defaultValue, String... comments) {
            this.key = key;
            this.defaultValue = defaultValue;
            this.comments = comments != null ? comments : new String[0];
        }

        @Override
        public String getKey() {
            return this.key;
        }

        @Override
        public Object getDefaultValue() {
            return this.defaultValue;
        }

        @Override
        public String[] getComments() {
            return this.comments;
        }

        @Override
        public Object getCachedValue() {
            return this.value;
        }

        @Override
        public void setCachedValue(Object value) {
            this.value = value;
        }

        @Override
        public CommentedFileConfiguration getBaseConfig() {
            return RoseEconomy.getInstance().getManager(ConfigurationManager.class).getConfig();
        }
    }

    public ConfigurationManager(RosePlugin rosePlugin) {
        super(rosePlugin, Setting.class);
    }

    @Override
    protected String[] getHeader() {
        return new String[]{
                "\n" +
                        "__________                    ___________                                         ",
                        "\\______   \\ ____  ______ ____ \\_   _____/ ____  ____   ____   ____   _____ ___.__.",
                        " |       _//  _ \\/  ___// __ \\ |    __)__/ ___\\/  _ \\ /    \\ /  _ \\ /     <   |  |",
                        " |    |   (  <_> )___ \\\\  ___/ |        \\  \\__(  <_> )   |  (  <_> )  Y Y  \\___  |",
                        " |____|_  /\\____/____  >\\___  >_______  /\\___  >____/|___|  /\\____/|__|_|  / ____|",
                        "        \\/           \\/     \\/        \\/     \\/           \\/             \\/\\/     "
        };
    }

}
