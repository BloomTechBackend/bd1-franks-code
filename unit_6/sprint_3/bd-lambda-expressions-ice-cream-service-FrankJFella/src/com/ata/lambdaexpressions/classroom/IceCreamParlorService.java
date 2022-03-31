package com.ata.lambdaexpressions.classroom;

import com.ata.lambdaexpressions.classroom.converter.RecipeConverter;
import com.ata.lambdaexpressions.classroom.dao.CartonDao;
import com.ata.lambdaexpressions.classroom.dao.RecipeDao;
import com.ata.lambdaexpressions.classroom.exception.CartonCreationFailedException;
import com.ata.lambdaexpressions.classroom.exception.RecipeNotFoundException;
import com.ata.lambdaexpressions.classroom.model.Carton;
import com.ata.lambdaexpressions.classroom.model.Ingredient;
import com.ata.lambdaexpressions.classroom.model.Recipe;
import com.ata.lambdaexpressions.classroom.model.Sundae;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.inject.Inject;

/**
 * Provides Ice Cream Parlor functionality.
 */
public class IceCreamParlorService {
    private final RecipeDao recipeDao;
    private final CartonDao cartonDao;
    private final IceCreamMaker iceCreamMaker;

    /**
     * Constructs service with the provided DAOs.
     * @param recipeDao the RecipeDao to use for accessing recipes
     * @param cartonDao the CartonDao to use for accessing ice cream cartons
     */
    @Inject
    public IceCreamParlorService(RecipeDao recipeDao, CartonDao cartonDao, IceCreamMaker iceCreamMaker) {
        this.recipeDao = recipeDao;
        this.cartonDao = cartonDao;
        this.iceCreamMaker = iceCreamMaker;
    }

    /**
     * Creates and returns the sundae defined by the given ice cream flavors.
     * If a flavor is not found or we have none of that flavor left, the sundae
     * is returned, but without that flavor. (We'll only charge the customer for
     * the scoops they are returned)
     * @param flavorNames List of flavors to include in the sundae
     * @return The newly created Sundae
     */
    public Sundae getSundae(List<String> flavorNames) {
        // This does the filtering out of any unknown flavors, so only
        // Cartons of known flavors will be returned.
        List<Carton> cartons = cartonDao.getCartonsByFlavorNames(flavorNames);

        // PHASE 1: Use removeIf() to remove any empty cartons from cartons
        // ListObject.removeIf(condition) - remove the element if condition is true

        // cartons.removeIf((carton) -> carton.isEmpty());
        // cartons.removeIf( carton  -> carton.isEmpty()); // no () on Lambda parameter of there is only one
          cartons.removeIf(Carton::isEmpty);               // Use of Method reference since Lambda expression
                                                           //   only called a named function
        return buildSundae(cartons);
    }

    @VisibleForTesting
    Sundae buildSundae(List<Carton> cartons) {
        Sundae sundae = new Sundae();

        // PHASE 2: Use forEach() to add one scoop of each flavor
        // remaining in cartons
        // call the Sundae.addScoop() for each carton left after the removeIf()

        // go through the list of cartons and get one scoop from each for the sundae
        // Since cartons is a List we will use teh List forEach rather then stream().forEach()
        //       because its safer
        //
        // .forEach() uses an implied for loop to go through each element
        //  same as:
        //        for(Carton matthew : cartons) {
        //            sundae.addScoop(matthew.getFlavor());
        //        }
        // cartons.forEach((matthew) -> sundae.addScoop((matthew.getFlavor())));
        cartons.forEach(matthew -> sundae.addScoop((matthew.getFlavor())));

        return sundae;
    }

    /**
     * Prepares the specified flavors, creating 1 carton of each provided
     * flavor.
     *
     * A flavor name that doesn't correspond
     * to a known recipe will result in CartonCreationFailedException, and
     * no Cartons will be created.
     *
     * @param flavorNames List of names of flavors to create new batches of
     * @return the number of cartons produced by the ice cream maker
     */
    public int prepareFlavors(List<String> flavorNames) {
        List<Recipe> recipes = map(  // Call the map() helper method for this class (see end of code)
            flavorNames,             // 1st parameter - list of flavor names
            (flavorName) -> {        // 2nd parameter - Lambda expression to be applied to 1st parameter
                // trap the checked exception, RecipeNotFoundException, and
                // wrap in a runtime exception because our lambda can't throw
                // checked exceptions
                try {
                    return recipeDao.getRecipe(flavorName);
                } catch (RecipeNotFoundException e) {
                    throw new CartonCreationFailedException("Could not find recipe for " + flavorName, e);
                }
            }  // end of Lambda expressions passed as 2nd parameter
        );

        // PHASE 3: Replace right hand side: use map() to convert List<Recipe> to List<Queue<Ingredient>>
        // List<Queue<Ingredient>> ingredientQueues = new ArrayList<>();
        // Use map() to create the new ArrayList using recipes
        /*******************************************************************************************************
         * Solution using class helper method (see bottom of code) and Method Reference
         *****************************************************************************************************/
        List<Queue<Ingredient>> ingredientQueues = map(recipes, RecipeConverter::fromRecipeToIngredientQueue);

        return makeIceCreamCartons(ingredientQueues);
    }

    @VisibleForTesting
    int makeIceCreamCartons(List<Queue<Ingredient>> ingredientQueues) {
        // don't change any of the lines that touch cartonsCreated.
        int cartonsCreated = 0;
        for (Queue<Ingredient> ingredients : ingredientQueues) {

            // PHASE 4: provide Supplier to prepareIceCream()
            if (iceCreamMaker.prepareIceCreamCarton(() -> ingredients.poll())) {
                cartonsCreated++;
            }
        }

        return cartonsCreated;
    }

    /**
     * Converts input list of type T to a List of type R, where each entry in the return
     * value is the output of converter applied to each entry in input.
     *
     * (We will get to Java streams in a later lesson, at which point we won't need a helper method
     * like this.)
     */
    // This is a method only availble to this class to create a map from parameters passed
    //   parameter:  a-List-to-be-processed , function-to-be-given to stream().map()
    private <T, R> List<R> map(List<T> input, Function<T, R> converter) {
        //     the list with teh elements tp process
        return input.stream()              // parameter with the list with the elements to process
                .map(converter)            // parameter with the function to be used with stream().map()
            .collect(Collectors.toList());
    }
}
