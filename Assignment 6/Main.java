import java.util.List;

public class Main
{
    public static void main(String[] args)
    {
        List<TerrariumElement> terrarium = List.of(
            new TerrariumPlant("Fern", 120, 10),
            new TerrariumPlant("Moss", 40, 8),
            new TerrariumEquipment("LED lamp", 18, 10, 12, 4),
            new TerrariumEquipment("Ventilation fan", 5, 6, 3, 3));

        System.out.println("Terrarium care plan");
        CarePlanVisitor carePlan = new CarePlanVisitor();
        for (TerrariumElement element : terrarium)
        {
            element.accept(carePlan);
        }

        System.out.println();
        System.out.println("Terrarium resource report");
        ResourceReportVisitor resourceReport = new ResourceReportVisitor();
        for (TerrariumElement element : terrarium)
        {
            element.accept(resourceReport);
        }
        resourceReport.printReport();
    }
}
