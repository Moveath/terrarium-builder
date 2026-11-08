public interface TerrariumVisitor
{
    void visitPlant(TerrariumPlant plant);

    void visitEquipment(TerrariumEquipment equipment);
}
