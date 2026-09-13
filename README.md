# Terrarium Builder

Implementation of the **Builder** design pattern using a terrarium constructor as an example.

## What is being built

`Terrarium` is a complex object with required parameters (`size`, `substrate`) and optional ones (`humidity`, `hasLighting`, `plants`, `decorations`). Instead of telescoping constructors, a fluent builder (`Terrarium.Builder`) with method chaining is used. `TerrariumDirector` stores ready-made "recipes" (`makeDesertTerrarium`, `makeTropicalTerrarium`), but the client can still build a custom configuration directly through the builder.

## Structure

- `Terrarium` the product (immutable).
- `Terrarium.Builder` builder with setter methods and `build()`.
- `TerrariumDirector` director with predefined configurations.
- `ContainerSize`, `Substrate`, `HumidityLevel` enums instead of raw strings/numbers.
- `Main` client code.

## Clean Code principles

### 1. Meaningful, intention-revealing names
Method names describe the action itself instead of being abstract setters.
```java
// Before (bad):
builder.set(0, true);

// After:
builder.withLighting();
builder.addPlant("cactus");
```

### 2. Small methods, each doing one thing
Every builder method is responsible for exactly one field and has no extra side logic.
```java
public Builder substrate(Substrate substrate) {
    this.substrate = substrate;
    return this;
}
```

### 3. Validated construction
`build()` doesn't return a broken object it checks the required fields first and throws a clear exception if something is missing.
```java
public Terrarium build() {
    validateState();
    return new Terrarium(this);
}

private void validateState() {
    if (size == null) {
        throw new IllegalStateException("Container size must be set before calling build()");
    }
    if (substrate == null) {
        throw new IllegalStateException("Substrate must be set before calling build()");
    }
}
```

### 4. No magic numbers/strings
Instead of raw strings like `"sand"` or `"low"`, type-safe enums are used, so a typo is caught by the compiler instead of at runtime.
```java
// Before (bad):
builder.substrate("sand");
builder.humidity("low");

// After:
builder.substrate(Substrate.SAND);
builder.humidity(HumidityLevel.LOW);
```

### 5. Single Responsibility across small classes
The product, builder and director are three separate classes, each with one job: `Terrarium` holds the data, `Builder` assembles it step by step, and `TerrariumDirector` only knows the *order* of calls for common configurations — it doesn't know the assembly details.
```java
public Terrarium makeDesertTerrarium(Terrarium.Builder builder) {
    return builder
            .size(ContainerSize.MEDIUM)
            .substrate(Substrate.SAND)
            .humidity(HumidityLevel.LOW)
            .withLighting()
            .addPlant("cactus")
            .addDecoration("rocks")
            .build();
}
```

## Usage example

```java
Terrarium customTerrarium = new Terrarium.Builder()
        .size(ContainerSize.SMALL)
        .substrate(Substrate.GRAVEL)
        .addPlant("succulent")
        .build();
```