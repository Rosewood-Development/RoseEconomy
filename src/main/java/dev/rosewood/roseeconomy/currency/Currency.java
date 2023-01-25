package dev.rosewood.roseeconomy.currency;

import java.util.ArrayList;
import java.util.List;

public class Currency {

    private final String id; // Unique ID
    private boolean enabled; // Whether this currency is enabled
    private String name; // Display name (this will also be the command name)
    private List<String> aliases; // Command aliases
    private double startBalance; // Starting amount when a player joins
    private double maxAmount; // Maximum amount a player can have
    private double minAmount; // Minimum amount a player can have
    private double dailyAmount; // Daily amount
    private double dailyInterval; // Interval between daily amounts
    private double dailyMax; // Maximum amount a player can have from daily amounts (random if higher than daily amount)
    private boolean defaultCurrency; // Whether this is the default currency
    private double globalMultiplier; // Global multiplier for all players (stacks with player multipliers)
    private double maxMultiplier; // Maximum multiplier a player can have
    private String singular; // Name of the currency when there is only 1
    private String plural; // Name of the currency when there is more than 1
    private boolean payCommand; // Whether this currency has a pay command

    public Currency(String id) {
        this.id = id.toLowerCase(); // Make sure the ID is lowercase
        this.enabled = true;
        this.name = id;
        this.aliases = new ArrayList<>();
        this.startBalance = -1;
        this.maxAmount = Double.MAX_VALUE;
        this.minAmount = Double.MIN_VALUE;
        this.dailyAmount = 0;
        this.dailyInterval = 0;
        this.dailyMax = 0;
        this.defaultCurrency = false;
        this.globalMultiplier = 1;
        this.maxMultiplier = 1;
        this.singular = name;
        this.plural = name;
        this.payCommand = true;
    }

    /**
     * Format the amount based on the singular/plural settings
     *
     * @param amount the amount
     * @return the formatted amount
     */
    public String getFormat(double amount) {
        if (amount == 1 || amount == -1) {
            return singular;
        } else {
            return plural;
        }
    }

    public String getId() {
        return id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }

    public double getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(double startBalance) {
        this.startBalance = startBalance;
    }

    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
    }

    public double getDailyAmount() {
        return dailyAmount;
    }

    public void setDailyAmount(double dailyAmount) {
        this.dailyAmount = dailyAmount;
    }

    public double getDailyInterval() {
        return dailyInterval;
    }

    public void setDailyInterval(double dailyInterval) {
        this.dailyInterval = dailyInterval;
    }

    public double getDailyMax() {
        return dailyMax;
    }

    public void setDailyMax(double dailyMax) {
        this.dailyMax = dailyMax;
    }

    public boolean isDefaultCurrency() {
        return defaultCurrency;
    }

    public void setDefaultCurrency(boolean defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    public double getGlobalMultiplier() {
        return globalMultiplier;
    }

    public void setGlobalMultiplier(double globalMultiplier) {
        this.globalMultiplier = globalMultiplier;
    }

    public double getMaxMultiplier() {
        return maxMultiplier;
    }

    public void setMaxMultiplier(double maxMultiplier) {
        this.maxMultiplier = maxMultiplier;
    }

    public String getSingular() {
        return singular;
    }

    public void setSingular(String singular) {
        this.singular = singular;
    }

    public String getPlural() {
        return plural;
    }

    public void setPlural(String plural) {
        this.plural = plural;
    }

    public boolean isPayCommand() {
        return payCommand;
    }

    public void setPayCommand(boolean payCommand) {
        this.payCommand = payCommand;
    }

}
