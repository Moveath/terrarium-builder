import java.util.ArrayList;
import java.util.List;

public class Terrarium
{
    private final ContainerSize size;
    private final Substrate substrate;
    private final HumidityLevel humidity;
    private final boolean hasLighting;
    private final List<String> plants;
    private final List<String> decorations;

    private Terrarium(Builder builder)
    {
        this.size = builder.size;
        this.substrate = builder.substrate;
        this.humidity = builder.humidity;
        this.hasLighting = builder.hasLighting;
        this.plants = builder.plants;
        this.decorations = builder.decorations;
    }


    @Override
    public String toString()
    {
        return "Terrarium{"+
                "size=" + size +
                ", substrate=" + substrate +
                ", humidity=" + humidity +
                ", hasLighting=" + hasLighting +
                ", plants=" + plants +
                ", decorations=" + decorations +
                '}';
    }

    public static class Builder
    {
        private static final HumidityLevel DEFAULT_HUMIDITY = HumidityLevel.MEDIUM;
        private ContainerSize size;
        private Substrate substrate;
        private HumidityLevel humidity = DEFAULT_HUMIDITY;
        private boolean hasLighting = false;
        private final List<String> plants = new ArrayList<>();
        private final List<String> decorations = new ArrayList<>();

        public Builder size(ContainerSize size)
        {
            this.size = size;
            return this;
        }
        public Builder substrate(Substrate substrate)
        {
            this.substrate = substrate;
            return this;
        }

        public Builder humidity(HumidityLevel humidity)
        {
            this.humidity = humidity;
            return this;
        }

        public Builder withLighting()
        {
            this.hasLighting = true;
            return this;
        }

        public Builder addPlant(String plant)
        {
            plants.add(plant);
            return this;
        }
        public Terrarium build()
        {
            validateState();
            return new Terrarium(this);
        }

        public Builder addDecoration(String decoration)
        {
            decorations.add(decoration);
            return this;
        }

        private void validateState()
        {
            if (size == null)
            {
                throw new IllegalStateException("Container size must be set before calling build()");
            }
            if (substrate == null)
            {
                throw new IllegalStateException("Substrate must be set before calling build()");
            }
        }
    }
}
