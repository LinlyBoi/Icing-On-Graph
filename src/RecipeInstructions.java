import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Scanner;

public class RecipeInstructions {
    public static void main(String[] args) {
        int usrChoice;

        ArrayList<RecipeNode> ingredients = new ArrayList<>();
        RecipeTree fullInstructions = new RecipeTree();

        do {
            Scanner userOption = new Scanner(System.in);

            System.out.println("What would you like to do?\n1.Add Ingredient\n2.Mix");
            usrChoice = userOption.nextInt();

            // Switch case for user's choice
            switch (usrChoice) {
                case 1:
                    // Init 3 scanners because usually scanners crash on me with multiple input types lmao
                    Scanner ingredientAddAmount  = new Scanner(System.in);      // Init Scanner for Added amount
                    Scanner ingredientAddName    = new Scanner(System.in);      // Init Another Scanner for Name
                    Scanner ingredientAddPortion = new Scanner(System.in);      // Init Yet another Scanner for Portion

                    System.out.print("Enter how many ingredients you're adding: ");
                    int amountIngredient = ingredientAddAmount.nextInt();       // Defining how many ingredients will be added

                    for(int i = 0; i < amountIngredient; i++) {
                        // Getting Ingredient Name
                        System.out.println("Enter ingredient Name");
                        String ingredientName = ingredientAddName.nextLine();

                        // Getting Ingredient Portion size
                        System.out.println("\nEnter ingredient portion");
                        double ingredientPortion = ingredientAddPortion.nextDouble();

                        // Creating the Ingredient's node and putting it into array list
                        // for safe keeping and later use
                        RecipeNode newIngredient = new RecipeNode(ingredientName, ingredientPortion);
                        fullInstructions.addNode((RecipeNode) null, newIngredient);
                    }
                    break;
                case 2:
                    Scanner ingredientNames = new Scanner(System.in);   // Scanner for ingredients' names
                    String userIngredients;                             // String poo
                    ArrayList<RecipeNode> bowl = new ArrayList<>();     // Mixing bowl for ingredients obviously

                    do {
                        System.out.println("Enter ingredient names to be mixed (type done to exit)");
                        userIngredients = ingredientNames.nextLine();

                        for(RecipeNode ingredient : ingredients) {
                            if(userIngredients.equals(ingredient.getIngredient())) {
                                bowl.add(ingredient);
                                break;
                            }
                        }
                    } while (!userIngredients.equalsIgnoreCase("done"));

                    RecipeNode mixture = new RecipeNode(bowl.get(0) + " mix", 69);
                    ingredients.add(mixture);

                    for(RecipeNode childIngredient : bowl) {
                        mixture.addChild(childIngredient);
                        childIngredient.parent = mixture;
                    }

            }
        } while(usrChoice != -1);
    }
}
