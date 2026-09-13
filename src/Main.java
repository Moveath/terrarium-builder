public class Main 
{
        public static void main(String[] args) 
    {
        TerrariumDirector director = new TerrariumDirector();
        Terrarium desertTerrarium = director.makeDesertTerrarium(new Terrarium.Builder());
        Terrarium tropicalTerrarium = director.makeTropicalTerrarium(new Terrarium.Builder());
        Terrarium customTerrarium = new Terrarium.Builder()
                .size(ContainerSize.SMALL)
                .substrate(Substrate.GRAVEL)
                .addPlant("succulent")
                .build();
        System.out.println(desertTerrarium);
        System.out.println(tropicalTerrarium);
        System.out.println(customTerrarium);
    }
}