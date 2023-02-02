package de.symeda.sormas.api.person;


public enum TimeUnit {
    YEAR("Year"),
    MONTH("Month"),
    DAYS("Day");

    final String value;
     TimeUnit(String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return value;
    }
}
