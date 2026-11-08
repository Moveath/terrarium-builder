public final class TerrariumPlant implements TerrariumElement
{
    private final String name;
    private final int dailyWaterMl;
    private final int requiredLightHours;

    public TerrariumPlant(String name, int dailyWaterMl, int requiredLightHours)
    {
        if (name == null || name.isBlank())
        {
            throw new IllegalArgumentException("Plant name is required");
        }

        if (dailyWaterMl < 0 || requiredLightHours < 0 || requiredLightHours > 24)
        {
            throw new IllegalArgumentException("Plant care values are invalid");
        }

        this.name = name;
        this.dailyWaterMl = dailyWaterMl;
        this.requiredLightHours = requiredLightHours;
    }

    @Override
    public void accept(TerrariumVisitor visitor)
    {
        if (visitor == null)
        {
            throw new IllegalArgumentException("Visitor is required");
        }

        visitor.visitPlant(this);
    }

    public String getName()
    {
        return name;
    }

    public int getDailyWaterMl()
    {
        return dailyWaterMl;
    }

    public int getRequiredLightHours()
    {
        return requiredLightHours;
    }
}
