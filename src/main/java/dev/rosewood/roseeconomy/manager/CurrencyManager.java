package dev.rosewood.roseeconomy.manager;

import dev.rosewood.roseeconomy.currency.Currency;
import dev.rosewood.rosegarden.RosePlugin;
import dev.rosewood.rosegarden.config.CommentedConfigurationSection;
import dev.rosewood.rosegarden.config.CommentedFileConfiguration;
import dev.rosewood.rosegarden.manager.Manager;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class CurrencyManager extends Manager {

    private final Map<String, Currency> currencies;
    private CommentedFileConfiguration currencyConfig;

    public CurrencyManager(RosePlugin rosePlugin) {
        super(rosePlugin);

        this.currencies = new HashMap<>();
    }

    @Override
    public void reload() {
        this.currencies.clear();
        File file = new File(this.rosePlugin.getDataFolder(), "currencies.yml");
        try {
            if (!file.exists())
                file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load the default config if the file is empty
        CommentedFileConfiguration config = CommentedFileConfiguration.loadConfiguration(file);
        if (config.getKeys(false).isEmpty()) {
            this.getDefaultCurrencyConfig().forEach(config::set);
            config.save();
        }

        // Disable plugin if no currencies are found
        CommentedConfigurationSection section = config.getConfigurationSection("currencies");
        if (section == null) {
            this.rosePlugin.getLogger().severe("No currencies found in currencies.yml!");
            Bukkit.getPluginManager().disablePlugin(this.rosePlugin);
            return;
        }

        // Load currencies from config
        List<Currency> loaded = section.getKeys(false).stream()
                .map(id -> this.loadCurrency(id, section))
                .collect(Collectors.toList());

        // TODO: Load all the currencies from the database
        this.rosePlugin.getLogger().info("Loaded " + loaded.size() + " currencies. (" + loaded.stream().map(Currency::getId).collect(Collectors.joining(", ")) + ")");

        this.currencyConfig = config;
    }

    /**
     * Get a currency by its ID
     *
     * @param id The ID of the currency
     * @return The currency or null if it doesn't exist
     */
    @Nullable
    public Currency getCurrency(@NotNull String id) {
        if (this.currencies.containsKey(id.toLowerCase()))
            return this.currencies.get(id.toLowerCase());

        return null;
    }

    public Currency loadCurrency(@NotNull String id, @NotNull CommentedConfigurationSection section) {

        this.rosePlugin.getLogger().info("Loading currency " + id);

        Currency currency = new Currency(id);
        currency.setName(section.getString(id + ".name"));
        currency.setEnabled(section.getBoolean(id + ".enabled"));
        currency.setSingular(section.getString(id + ".singular"));
        currency.setPlural(section.getString(id + ".plural"));
        currency.setAliases(section.getStringList(id + ".aliases"));
        currency.setStartBalance(section.getDouble(id + ".starting-amount"));
        currency.setMaxAmount(section.getDouble(id + ".max-amount"));
        currency.setMinAmount(section.getDouble(id + ".min-amount"));
        currency.setDailyAmount(section.getDouble(id + ".daily-amount"));
        currency.setDailyInterval(section.getInt(id + ".daily-interval"));
        currency.setDailyMax(section.getDouble(id + ".daily-max"));
        currency.setGlobalMultiplier(section.getDouble(id + ".global-multiplier"));
        currency.setMaxMultiplier(section.getInt(id + ".max-multiplier"));
        this.currencies.put(id.toLowerCase(), currency);

        // TODO: Register individual currency commands
        // TODO: Load the

        return currency;
    }

    /**
     * @return The config defined default currency
     */
    public Currency getDefaultCurrency() {
        // TODO: Load the default currency setting.
        Currency currency = this.getCurrency("coins");
        if (currency != null)
            return currency;

        // Use the first currency if the default currency is not found
        Currency randomCurrency = this.currencies.values().iterator().next();
        // todo: replace coins with the default currency setting
        this.rosePlugin.getLogger().warning("Default currency " + "coins" + " not found! Using '" + randomCurrency.getId() + "' instead.");

        // there should always be at least one currency
        return randomCurrency;
    }

    /**
     * Get the default currency configuration values
     * TODO: Change into a RoseSetting for comments to be added
     *
     * @return The default values
     */
    private Map<String, Object> getDefaultCurrencyConfig() {
        return new LinkedHashMap<String, Object>() {{
            this.put("currencies.coins.name", "Coins");
            this.put("currencies.coins.enabled", true);
            this.put("currencies.coins.singular", "Coin");
            this.put("currencies.coins.plural", "Coins");
            this.put("currencies.coins.aliases", new ArrayList<>(Arrays.asList("coin", "coins")));
            this.put("currencies.coins.starting-amount", -1);
            this.put("currencies.coins.max-amount", 0);
            this.put("currencies.coins.min-amount", 0);
            this.put("currencies.coins.daily-amount", 0);
            this.put("currencies.coins.daily-interval", 0);
            this.put("currencies.coins.daily-max", 0);
            this.put("currencies.coins.global-multiplier", 1.0);
            this.put("currencies.coins.max-multiplier", 1.0);
        }};
    }

    @Override
    public void disable() {
        // TODO
    }

    public Map<String, Currency> getCurrencies() {
        return currencies;
    }

}
