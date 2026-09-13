# Terrarium Builder

Реализация паттерна **Builder** на примере конструктора террариумов для рептилий/растений.

## Что строим

`Terrarium` — сложный объект с обязательными (`size`, `substrate`) и опциональными (`humidity`, `hasLighting`, `plants`, `decorations`) параметрами. Вместо телескопических конструкторов используется fluent-билдер (`Terrarium.Builder`) с method chaining, а `TerrariumDirector` инкапсулирует готовые "рецепты" (`makeDesertTerrarium`, `makeTropicalTerrarium`), не мешая клиенту собирать кастомные конфигурации напрямую через билдер.

## Структура

- `Terrarium` — продукт (immutable).
- `Terrarium.Builder` — билдер с методами-сеттерами и `build()`.
- `TerrariumDirector` — director с предустановленными конфигурациями.
- `ContainerSize`, `Substrate`, `HumidityLevel` — enum'ы вместо строк/чисел.
- `Main` — клиентский код.

## Принципы Clean Code

### 1. Осмысленные имена (Meaningful, intention-revealing names)
Методы называются по смыслу действия, а не абстрактно (`set(1, "x")`).
```java
// Было:
builder.set(0, true);

// Стало:
builder.withLighting();
builder.addPlant("cactus");
```

### 2. Небольшие методы, каждый делает одну вещь
Каждый метод билдера отвечает ровно за одно поле и не содержит побочной логики.
```java
public Builder substrate(Substrate substrate) {
    this.substrate = substrate;
    return this;
}
```

### 3. Валидация состояния при конструировании
`build()` не возвращает "битый" объект — сначала проверяет обязательные поля и явно бросает исключение.
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

### 4. Отсутствие магических чисел/строк
Вместо строк `"sand"`, `"low"` и т.п. используются типобезопасные enum'ы — ошибка опечатки ловится компилятором, а не в рантайме.
```java
// Было:
builder.substrate("sand");
builder.humidity("low");


builder.substrate(Substrate.SAND);
builder.humidity(HumidityLevel.LOW);
```

### 5. Разделение ответственности между небольшими классами (SRP)
Продукт, билдер и director — три разных класса с одной обязанностью каждый: `Terrarium` хранит данные, `Builder` собирает объект пошагово, `TerrariumDirector` знает только *порядок* вызовов для типовых конфигураций, но не детали сборки.
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

## Пример использования

```java
Terrarium customTerrarium = new Terrarium.Builder()
        .size(ContainerSize.SMALL)
        .substrate(Substrate.GRAVEL)
        .addPlant("succulent")
        .build();
```