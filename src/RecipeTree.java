import java.util.Stack;

public class RecipeTree

{
    private RecipeNode root;

    public RecipeTree()
    {
        root = null;
    }

    public RecipeNode getRoot()
    {
        return root;
    }

    public void setRoot(RecipeNode root)
    {
        this.root = root;
    }

    public void DFS(String value)
    {
        DFS(root, value);
    }
    public void DFS(RecipeNode node, String value)
    {
        //Base case where we stop recursion
        if(node == null)
        {
            System.out.println("Node not found, going back up");
            return;
        }
        //Case where its actually found !!!!
        if(node.getIngredient().equals(value))
        {
            System.out.println(node.getIngredient() + " " + node.getPortion());
        }
        //Depth first search we take first child then we take the first child in the first child then we take the first child in the first child in the first child
        for(RecipeNode child : node.getChildren())
        {
            DFS(child, value);
        }
    }


    public void addNode(RecipeNode parent, RecipeNode child)
    {
        if (parent == null)
        {
            root = child;
        }
        else
        {
            parent.addChild(child);
        }
    }

    public void printTree()
    {
        if (root != null)
        {
            root.printNode();
        }
    }

    public Stack<RecipeNode> sortTopologically(RecipeNode currentNode) {
        Stack<RecipeNode> recipeStack = new Stack<>();

        if(currentNode.getChildren() == null) {
            recipeStack.push(currentNode);
            return recipeStack;
        } else {
            for (RecipeNode childNode : currentNode.children) {
                recipeStack.push(currentNode);
                sortTopologically(childNode);
            }
        }
        return recipeStack;
    }
}
