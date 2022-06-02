import java.util.Arrays;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTreeTest
{

    @org.junit.jupiter.api.Test
    void setRoot()
    {
        RecipeTree tree = new RecipeTree();
        RecipeNode root = new RecipeNode("root", 1);
        tree.setRoot(root);
        assertEquals(root, tree.getRoot());
    }

    @org.junit.jupiter.api.Test
    void DFS()
    {
        RecipeTree tree = new RecipeTree();
        RecipeNode root = new RecipeNode("root", 1);
        RecipeNode child1 = new RecipeNode("child1", 1);
        RecipeNode child2 = new RecipeNode("child2", 1);
        RecipeNode child3 = new RecipeNode("child3", 1);
        RecipeNode child4 = new RecipeNode("child4", 1);
        RecipeNode child5 = new RecipeNode("child5", 1);
        RecipeNode child6 = new RecipeNode("child6", 1);
        RecipeNode child7 = new RecipeNode("child7", 1);
        RecipeNode child8 = new RecipeNode("child8", 1);
        tree.setRoot(root);
        tree.addNode(root, child1);
        tree.addNode(root, child2);
        tree.addNode(root, child3);
        tree.addNode(child1, child4);
        tree.addNode(child1, child5);
        tree.addNode(child2, child6);
        tree.addNode(child3, child7);
        tree.addNode(child3, child8);
        assertEquals(child1, tree.DFS("child1"));
        assertEquals(child2, tree.DFS("child2"));
        assertEquals(child3, tree.DFS("child3"));
        assertEquals(child4, tree.DFS("child4"));
        assertEquals(child5, tree.DFS("child5"));
        assertEquals(child6, tree.DFS("child6"));
        assertEquals(child7, tree.DFS("child7"));
        assertEquals(child8, tree.DFS("child8"));


    }

    @org.junit.jupiter.api.Test
    void sortTopologically()
    {
        RecipeTree tree = new RecipeTree();
        RecipeNode root = new RecipeNode("root", 1);
        RecipeNode child1 = new RecipeNode("child1", 1);
        RecipeNode child2 = new RecipeNode("child2", 1);
        RecipeNode child3 = new RecipeNode("child3", 1);
        RecipeNode child4 = new RecipeNode("child4", 1);
        RecipeNode child5 = new RecipeNode("child5", 1);
        RecipeNode child6 = new RecipeNode("child6", 1);
        RecipeNode child7 = new RecipeNode("child7", 1);
        RecipeNode child8 = new RecipeNode("child8", 1);
        tree.setRoot(root);
        tree.addNode(root, child1);
        tree.addNode(root, child2);
        tree.addNode(root, child3);
        tree.addNode(child1, child4);
        tree.addNode(child1, child5);
        tree.addNode(child2, child6);
        tree.addNode(child3, child7);
        tree.addNode(child3, child8);
        Stack<RecipeNode> stack = tree.sortTopology();
        while(!stack.isEmpty())
        {
            System.out.println(stack.pop().getIngredient());
        }

    }

    @org.junit.jupiter.api.Test
    void addNode()
    {
    }

    @org.junit.jupiter.api.Test
    void printTree()
    {
        RecipeTree tree = new RecipeTree();
        RecipeNode root = new RecipeNode("root", 1);
        RecipeNode child1 = new RecipeNode("child1", 1);
        RecipeNode child2 = new RecipeNode("child2", 1);
        RecipeNode child3 = new RecipeNode("child3", 1);
        RecipeNode child4 = new RecipeNode("child4", 1);
        RecipeNode child5 = new RecipeNode("child5", 1);
        RecipeNode child6 = new RecipeNode("child6", 1);
        tree.setRoot(root);
        tree.addNode(root, child1);
        tree.addNode(root, child2);
        tree.addNode(root, child3);
        tree.addNode(child1, child4);
        tree.addNode(child2, child5);
        tree.addNode(child3, child6);
        tree.printTree();

    }
}