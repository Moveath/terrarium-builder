public final class ResourceReportVisitor implements TerrariumVisitor
{
    private int totalDailyWaterMl;
    private int totalDailyEnergyWh;

    @Override
    public void visitPlant(TerrariumPlant plant)
    {
        totalDailyWaterMl += plant.getDailyWaterMl();
    }

    @Override
    public void visitEquipment(TerrariumEquipment equipment)
    {
        totalDailyEnergyWh += equipment.getPowerWatts() * equipment.getDailyOperatingHours();
    }

    public void printReport()
    {
        System.out.println("Daily water: " + totalDailyWaterMl + " ml");
        System.out.println("Daily energy: " + totalDailyEnergyWh + " Wh");
    }
}
