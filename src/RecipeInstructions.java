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
        Scanner userInput = new Scanner(System.in);

        do
        {
            System.out.println("Enter instruction:  ");
            userInstruction = userInput.nextLine();

            RecipeCreation(userInstruction,fullInstructions);
            fullInstructions.printTree();

        } while(!userInstruction.equalsIgnoreCase("done"));
    }

    //Magical Regex
    public static boolean patternMatch(String pattern, String input)
    {
        Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        return p.matcher(input).find();
    }

    // This creates an action + children
    public static void RecipeCreation(String instructionIn, RecipeTree fullInstructions)
    {
        //Need to not hardcode verbs maybe a global variable for it? etc etc
        String[] verbs = {"add", "mix", "stir", "wait", "bake", "heat", "sift", "boil", "melt", "fry", "cool",
                          "remove", "eat", "cut"};
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
                fullInstructions.actionAdd(newAction);
            }
        }

    }

    //MMMMM children yes??
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
