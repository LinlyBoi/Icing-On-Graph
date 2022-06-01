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
        if(node == null)
        {
            System.out.println("Node not found");
            return;
        }
        if(node.getIngredient().equals(value))
        {
            System.out.println(node.getIngredient() + " " + node.getPortion());
        }
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
}
