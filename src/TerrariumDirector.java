public class TerrariumDirector
{
    public Terrarium makeDesertTerrarium(Terrarium.Builder builder)
    {
        return builder
                .size(ContainerSize.MEDIUM)
                .substrate(Substrate.SAND)
                .humidity(HumidityLevel.LOW)
                .withLighting()
                .addPlant("cactus")
                .addDecoration("rocks")
                .build();
    }

    public Terrarium  makeTropicalTerrarium(Terrarium.Builder builder)
    {
        return builder
                .size(ContainerSize.LARGE)
                .substrate(Substrate.SOIL)
                .humidity(HumidityLevel.HIGH)
                .withLighting()
                .addPlant("fern")
                .addDecoration("figurine")
                .build();
    }
}
