import java.util.ArrayList;

public class RecipeNode {
    private String ingredient;  // Ingredient's name
    private double portion;     // Portion size of ingredient
    RecipeNode parent;         // To point at parent node
    ArrayList<RecipeNode> children; // To point at children nodes

    // Accessors and Mutators for fields (Except pointer's)
    RecipeNode(String ingredient, double portion) {
        this.ingredient = ingredient;
        this.portion = portion;
    }
    public RecipeNode(String name)
    {
        this.ingredient = name;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setPortion(double portion) {
        this.portion = portion;
    }

    public double getPortion() {
        return portion;
    }
    public void setParent(RecipeNode parent) {
        this.parent = parent;
    }
    public RecipeNode getParent() {
        return parent;
    }
    //Children
    public void addChild(RecipeNode child) {
        if (children == null) {
            children = new ArrayList<RecipeNode>();
        }
        child.setParent(this);
        children.add(child);
    }
    public ArrayList<RecipeNode> getChildren() {
        return children;
    }
    public void printNode() {
        System.out.println(ingredient + " " + portion);
        if (children != null) {
            for (RecipeNode child : children) {
                child.printNode();
            }
        }
    }
    @Override
    public String toString() {
        if(portion != 0) {
            return "[Ingredient: " + ingredient + ", portion = " + portion + " units]";
        } else {
            return "[Ingredient: " + ingredient + "]";
        }
    }
}
