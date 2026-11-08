# Terrarium Visitor

Assignment 6 for Software Design Patterns.

## About

This Java console program uses the Visitor pattern to run different operations on terrarium elements without putting those operations into the element classes.

The object structure contains plants and equipment:

- `TerrariumPlant` stores a plant's name, daily water amount, and required light hours.
- `TerrariumEquipment` stores its name, power use, operating hours, and service schedule.
- `TerrariumElement` declares `accept()`, and each concrete element calls its matching method on `TerrariumVisitor`.

The program has two visitors. `CarePlanVisitor` prints watering, lighting, and maintenance instructions. `ResourceReportVisitor` totals the daily water and energy use. `Main` traverses the same list once for each visitor. To add another operation, create a visitor that implements `TerrariumVisitor`; the element classes and object structure do not need to change.

## Clean Code

1. **Meaningful names:** `TerrariumPlant`, `TerrariumVisitor`, and `visitEquipment()` distinguish elements from operations.
2. **Single responsibility:** each concrete visitor performs one operation across all terrarium element types.
3. **Double dispatch:** elements call `visitPlant()` or `visitEquipment()` from `accept()`. There are no `instanceof` checks or type-checking chains.
4. **Encapsulation:** element fields are private and final. Visitors read only the values they need through getters; this small public interface lets new operations use the data without exposing mutable state.
5. **Easy extensibility:** a third operation can be added as another `TerrariumVisitor` implementation without changing the elements or `Main`'s collection.

## How to run

Open a terminal in this folder and run with a Java JDK:

```sh
javac -d bin *.java
java -cp bin Main
```

Expected output:

```text
Terrarium care plan
Water Fern with 120 ml and provide 10 hours of light
Water Moss with 40 ml and provide 8 hours of light
LED lamp service is due in 8 months
Service Ventilation fan this month

Terrarium resource report
Daily water: 160 ml
Daily energy: 210 Wh
```
