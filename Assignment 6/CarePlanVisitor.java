public final class CarePlanVisitor implements TerrariumVisitor
{
    @Override
    public void visitPlant(TerrariumPlant plant)
    {
        System.out.println(
            "Water " + plant.getName() + " with " + plant.getDailyWaterMl()
                + " ml and provide " + plant.getRequiredLightHours() + " hours of light");
    }

    @Override
    public void visitEquipment(TerrariumEquipment equipment)
    {
        if (equipment.getMonthsSinceService() >= equipment.getServiceIntervalMonths())
        {
            System.out.println("Service " + equipment.getName() + " this month");
            return;
        }

        int monthsUntilService = equipment.getServiceIntervalMonths()
            - equipment.getMonthsSinceService();
        System.out.println(
            equipment.getName() + " service is due in " + monthsUntilService + " months");
    }
}
