import java.util.ArrayDeque;
public class TaskA4 {
    public static void main(String[] args) {
        TreeNode tree = TreeNode.generateTestTree();
        // Проверка поиска в ширину
        System.out.println(findNodeBFS(tree, 11));
        System.out.println(findNodeBFS(null, 11));
        System.out.println(findNodeBFS(tree, 7));

        // Проверка поиска по уровням
        System.out.println(findNodeLevelOrder(tree, 11));
        System.out.println(findNodeLevelOrder(null, 11));
        System.out.println(findNodeLevelOrder(tree, 7));
    }
    // Поиск в ширину с очередью
    private static TreeNode findNodeBFS(TreeNode root, int target) {
        if (root == null) return null;
        ArrayDeque<TreeNode> nodesQueue = new ArrayDeque<>();
        nodesQueue.add(root);
        while (!nodesQueue.isEmpty()) {
            TreeNode current = nodesQueue.poll();
            if (current.val == target) return current;
            if (current.left != null) nodesQueue.add(current.left);
            if (current.right != null) nodesQueue.add(current.right);
        }
        return null;
    }
    // Поиск по уровням с рекурсией
    private static TreeNode findNodeLevelOrder(TreeNode root, int target) {
        if (root == null) return null;
        ArrayDeque<TreeNode> currentLevel = new ArrayDeque<>();
        currentLevel.add(root);
        return searchLevel(currentLevel, target);
    }
    // Поиск на текущем уровне
    private static TreeNode searchLevel(ArrayDeque<TreeNode> level, int target) {
        if (level.isEmpty()) return null;
        ArrayDeque<TreeNode> nextLevel = new ArrayDeque<>();
        for (TreeNode node : level) {
            if (node.val == target) return node;
            if (node.left != null) nextLevel.add(node.left);
            if (node.right != null) nextLevel.add(node.right);
        }
        return searchLevel(nextLevel, target);
    }
}