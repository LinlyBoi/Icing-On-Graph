public class RecipeNode {
    private String ingredient;  // Ingredient's name
    private double portion;     // Portion size of ingredient
    RecipeNode pointer;         // To point at parent node

    // Accessors and Mutators for fields (Except pointer's)
    RecipeNode(String ingredient, double portion) {
        this.ingredient = ingredient;
        this.portion = portion;
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
}
