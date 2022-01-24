# Waltz of the Flowers

## Introduction

The Garden category on Amazon allows a customer to purchase and search for flowers. Customers may search for flowers
that are of a certain kind, color, and stage in planting. They can even search for flowers that have symbolic meaning.
(Some flowers are considered to carry a meaning; for instance, in American culture, red roses are exchanged to
express true love.) Flowers may be purchased at various stages of growth, called "plantings", such as "seed" (an
unsprouted seed) or "graft" (part of a mature plant that gets added to another mature plant). With each purchase, 
Amazon sends care instructions to the customer based on the planting.

Take a few minutes to read the `GardenCategory`, `Flower`, and `SymbolicFlower` classes in this package.

## Phase 0 - Stop and smell the roses

Run the phase 0 tests. You'll purchase a couple flowers here, please enjoy, but this is just to make sure things 
are up and running! 

GOAL: Have the code built for the rest of the activity.

Phase 0 is complete when:
- `./gradlew -q clean :test --tests Phase0Test` completes successfully.

## Phase 1 - Searching for meaningless flowers

We've got a problem. Searching doesn't work. Examine the `searchCatalog()` method in the `GardenCategory` class to see
if you can identify the problem. You'll want to dig into the [Javadoc of the contains method](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html#contains-java.lang.Object-)
to get a better idea of what might be going on.

**Hint:** [I'm not fully understanding the Javadoc.](src/com/amazon/ata/inheritance/day2/garden/hints/hint01.md)

When you've identified the issue, make changes so that we can find a Flower. (Let's not worry about 
`SymbolicFlower`s just yet.)

GOAL: Fix searching for `Flower`s, so that `searchCatatlog()` returns true for `Flower`s in the catalog.

Phase 1 is complete when:
- You have updated `Flower` to ensure `ArrayList::contains` can determine if a `Flower` is present in the list
- `./gradlew -q clean :test --tests Phase1Test` completes successfully.

## Phase 2 - Find the symbolic ones too!

Beware the dangers of inheritance. Up until now no two `SymbolicFlower`s were equal unless they were the same object.
The owners of the superclass (also you!) have just updated the `Flower` class to implement `equals`. Lilacs can 
symbolize both passion and tranquility. We do not want a purple lilac seed that has the significance of passion to be
equal to a purple lilac seed that has the significance tranquility. Could be some embarrassing purchases being made!!

Notice that nothing stopped compiling when this change was made, but behavior did change! We might not have known it 
until a customer called and complained!

Implement `equals` in the `SymbolicFlower` class to ensure that two `SymbolicFlower`s  are equal only if kind, coloration, 
planting, and significance all match.

Try to implement this method without using any getter methods from `Flower`.

**Hint:** [How can I do this without getters?](src/com/amazon/ata/inheritance/day2/garden/hints/hint02.md)
**Hint:** [I'm still not sure!](src/com/amazon/ata/inheritance/day2/garden/hints/hint03.md)

GOAL: Implement `equals` in the `SymbolicFlower` class to ensure that two `SymbolicFlower`s  are equal only if kind, 
coloration, planting, and significance all match.

Phase 2 is complete when:
- You have implemented `equals` in `SymbolicFlower`
- `./gradlew -q clean :test --tests Phase2Test` completes successfully.
