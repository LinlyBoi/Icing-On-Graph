import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class RecipeInstructions
{
    public static void main(String[] args)
    {
        String userInstruction;

        ArrayList<RecipeNode> ingredients = new ArrayList<>();
        RecipeTree fullInstructions = new RecipeTree();

        do
        {
            System.out.println("Enter instruction:  ");
            Scanner userInput = new Scanner(System.in);
            userInstruction = userInput.nextLine();

            RecipeCreation(userInstruction);

        } while (userInstruction.equalsIgnoreCase("done"));
    }

    //Magical Regex
    public static boolean patternMatch(String pattern, String input)
    {
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        return p.matcher(input).find();
    }

    // This creates an action + children
    public static void RecipeCreation(String instructionIn)
    {
        //New tree who dis
        RecipeTree fullInstructions = new RecipeTree();
        //Stinky Scanner please remove or something idk
        //Maybe make the method take a string in or somethiong :D
        // Scanner input = new Scanner(System.in); <-- Commented remove for testing
        String userInput;
        //Need to not hardcode verbs maybe a global variable for it? etc etc
        String[] verbs = {"add", "mix", "stir", "wait", "bake", "heat", "sift", "boil", "melt", "fry", "cool",
                          "remove", "eat", "cut"};
        //init this shit
        int currentNumber = 0;
        String currentIngredient = null;
        //Useless sout lol <deleted dw lmao>
//        userInput = input.nextLine();
        //this was simulating user input for the tests below
        // userInput = "mix 3 eggs and  2 milk"; <-- Commented remove for testing
        //this is where it gets juicy
        //FIRST WE FIND A VERB FOR THE ACTION
        for (String verb : verbs)
        {
            //we regex the input for the thing
            if (patternMatch(verb, instructionIn))
            {
                //get Action
                RecipeNode newAction = new RecipeNode(verb, 0);
                //Split after verb
                //this is where we split the string
                String[] split = instructionIn.split(verb);
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
        RecipeCreation("disNotRealOnlyToAvoidErrorTrustDuud");
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
