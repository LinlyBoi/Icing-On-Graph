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

    public RecipeNode DFS(String value)
    {
        return DFS(root, value);
    }

    public RecipeNode DFS(RecipeNode node, String value)
    {
        if (node == null)
        {
            return null;
        }
        if (node.getIngredient().equals(value))
        {
            return node;
        }
        if (node.getChildren() != null)
        {
            for (RecipeNode child : node.getChildren())
            {
                RecipeNode result = DFS(child, value);
                if (result != null)
                {
                    return result;
                }
            }
        }


        return null;
    }

    public void addNode(RecipeNode parent, RecipeNode child)
    {
        if (parent == null)
        {
            root = child;
        } else
        {
            parent.addChild(child);
        }
    }

    public void addNode(String parent, RecipeNode child)
    {
        RecipeNode parentNode = findNode(root, parent);
        if (parentNode == null)
        {
            System.out.println("Parent node not found");
        } else
        {
            addNode(parentNode, child);
        }
    }

    private RecipeNode findNode(RecipeNode root, String parent)
    {
        if (root == null)
        {
            return null;
        }
        if (root.getIngredient().equals(parent))
        {
            return root;
        }
        for (RecipeNode child : root.getChildren())
        {
            RecipeNode found = findNode(child, parent);
            if (found != null)
            {
                return found;
            }
        }
        return null;
    }

    public void sortTopology(RecipeNode root, Stack<RecipeNode> stack)
    {
        if (root.getChildren() != null)
        {
            for (RecipeNode child : root.getChildren())
            {
                stack.push(child);
            }


            for (RecipeNode child : root.getChildren())
            {
                sortTopology(child, stack);
            }
        }

    }

    public Stack<RecipeNode> sortTopology()
    {
        Stack<RecipeNode> stack = new Stack<RecipeNode>();
        stack.push(root);
        sortTopology(root, stack);
        return stack;
    }


    public void printTree()
    {
        if (root != null)
        {
            root.printNode();
        }
    }


}
