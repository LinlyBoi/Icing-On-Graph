import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RecipeInstructions
{
    public static void main(String[] args)
    {
        int usrChoice;

        ArrayList<RecipeNode> ingredients = new ArrayList<>();
        RecipeTree fullInstructions = new RecipeTree();

        do
        {
            Scanner userOption = new Scanner(System.in);

            System.out.println("What would you like to do?\n1.Add Ingredient\n2.Mix");
            usrChoice = userOption.nextInt();

            // Switch case for user's choice
            switch (usrChoice)
            {
                case 1:
                    // Init 3 scanners because usually scanners crash on me with multiple input types lmao
                    Scanner ingredientAddAmount = new Scanner(System.in);      // Init Scanner for Added amount
                    Scanner ingredientAddName = new Scanner(System.in);      // Init Another Scanner for Name
                    Scanner ingredientAddPortion = new Scanner(System.in);      // Init Yet another Scanner for Portion

                    System.out.print("Enter how many ingredients you're adding: ");
                    int amountIngredient = ingredientAddAmount.nextInt();       // Defining how many ingredients will be added

                    for (int i = 0; i < amountIngredient; i++)
                    {
                        // Getting Ingredient Name
                        System.out.println("Enter ingredient Name");
                        String ingredientName = ingredientAddName.nextLine();

                        // Getting Ingredient Portion size
                        System.out.println("\nEnter ingredient portion");
                        double ingredientPortion = ingredientAddPortion.nextDouble();

                        // Creating the Ingredient's node and putting it into array list
                        // for safe keeping and later use
                        RecipeNode newIngredient = new RecipeNode(ingredientName, ingredientPortion);
//                        fullInstructions.addNode((RecipeNode) null, newIngredient);
                        ingredients.add(newIngredient);
                    }
                    break;
                case 2:
                    Scanner ingredientNames = new Scanner(System.in);   // Scanner for ingredients' names
                    String userIngredients;                             // String poo
                    ArrayList<RecipeNode> bowl = new ArrayList<>();     // Mixing bowl for ingredients obviously

                    do
                    {
                        System.out.println("Enter ingredient names to be mixed (type done to exit)");
                        userIngredients = ingredientNames.nextLine();

                        for (RecipeNode ingredient : ingredients)
                        {
                            if (userIngredients.equals(ingredient.getIngredient()))
                            {
                                bowl.add(ingredient);
                                break;
                            }
                        }
                    } while (!userIngredients.equalsIgnoreCase("done"));

                    RecipeNode mixture = new RecipeNode(bowl.get(0) + " mix", 69);
                    ingredients.add(mixture);

                    for (RecipeNode childIngredient : bowl)
                    {
                        mixture.addChild(childIngredient);
                        childIngredient.parent = mixture;
                    }

            }
        } while (usrChoice != -1);
    }

    //Magical Regex
    public static boolean patternMatch(String pattern, String input)
    {
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        return p.matcher(input).find();
    }

    // This creates an action + children
    public static void RecipeCreation()
    {
        //New tree who dis
        RecipeTree fullInstructions = new RecipeTree();
        //Stinky Scanner please remove or something idk
        //Maybe make the method take a string in or somethiong :D
        Scanner input = new Scanner(System.in);
        String userInput;
        //Need to not hardcode verbs maybe a global variable for it? etc etc
        String[] verbs = {"add", "mix"};
        //init this shit
        int currentNumber = 0;
        String currentIngredient = null;
        //Useless sout lol
        System.out.println("Enter instruction:  ");
//        userInput = input.nextLine();
        //this was simulating user input for the tests below
        userInput = "mix 3 eggs and  2 milk";
        //this is where it gets juicy
        //FIRST WE FIND A VERB FOR THE ACTION
        for (String verb : verbs)
        {
            //we regex the input for the thing
            if (patternMatch(verb, userInput))
            {
                //get Action
                RecipeNode newAction = new RecipeNode(verb, 0);
                //Split after verb
                //this is where we split the string
                String[] split = userInput.split(verb);
                //We get the words only POG
                String[] words = split[1].split(" ");
                construct_children(newAction, words);
                //we add the thing to the tree (this also needs to be checked for WHERE it gets added)
                fullInstructions.addNode((RecipeNode) null, newAction);
            }
            fullInstructions.printTree();
        }

    }

    //MMMMM children yes???
    //Seriously this takes the action node and the rest of the words in sentence and adds them as children
    public static void construct_children(RecipeNode node, String[] words)
    {
        int Number = 0;
        String Ingredient = null;
        for (String word : words)
        {
            if (word.matches("\\d+"))
            {
                Number = Integer.parseInt(word);
            } else if (word.matches("[a-zA-Z]+") && !word.equals("and"))
            {
                Ingredient = word;
            }
            if (Number != 0 && Ingredient != null)
            {
                RecipeNode newIngredient = new RecipeNode(Ingredient, Number);
                node.addChild(newIngredient);
                newIngredient.parent = node;
                Number = 0;
                Ingredient = null;
            }
        }

    }


    //Tests duh they work :DDDDDD
    @Test
    void testRecipeCreation()
    {
        RecipeCreation();
    }
    @Test
    void testconstruct_children()
    {
        RecipeNode node = new RecipeNode("", 0);
        String[] words = {"1", "eggs", "and", "2", "milk"};
        construct_children(node, words);
        System.out.println(node.getChildren());
    }


}
