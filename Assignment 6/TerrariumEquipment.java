public final class TerrariumEquipment implements TerrariumElement
{
    private final String name;
    private final int powerWatts;
    private final int dailyOperatingHours;
    private final int serviceIntervalMonths;
    private final int monthsSinceService;

    public TerrariumEquipment(
        String name,
        int powerWatts,
        int dailyOperatingHours,
        int serviceIntervalMonths,
        int monthsSinceService)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Equipment name is required");
        }

        if (powerWatts < 0
            || dailyOperatingHours < 0
            || dailyOperatingHours > 24
            || serviceIntervalMonths <= 0
            || monthsSinceService < 0)
        {
            throw new IllegalArgumentException("Equipment values are invalid");
        }

        this.name = name;
        this.powerWatts = powerWatts;
        this.dailyOperatingHours = dailyOperatingHours;
        this.serviceIntervalMonths = serviceIntervalMonths;
        this.monthsSinceService = monthsSinceService;
    }

    @Override
    public void accept(TerrariumVisitor visitor)
    {
        if (visitor == null)
        {
            throw new IllegalArgumentException("Visitor is required");
        }

        visitor.visitEquipment(this);
    }

    public String getName()
    {
        return name;
    }

    public int getPowerWatts()
    {
        return powerWatts;
    }

    public int getDailyOperatingHours()
    {
        return dailyOperatingHours;
    }

    public int getServiceIntervalMonths()
    {
        return serviceIntervalMonths;
    }

    public int getMonthsSinceService()
    {
        return monthsSinceService;
    }
}
