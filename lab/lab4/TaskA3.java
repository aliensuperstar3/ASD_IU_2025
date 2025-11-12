import java.util.ArrayDeque;
public class TaskA3 {
    public static void main(String[] args) {
        TreeNode tree = TreeNode.generateTestTree();
        // Проверка итеративного поиска в глубину
        System.out.println(dfsIterative(tree, 11));
        System.out.println(dfsIterative(null, 11));
        System.out.println(dfsIterative(tree, 7));
        // Проверка рекурсивного поиска в глубину
        System.out.println(dfsRecursive(tree, 11));
        System.out.println(dfsRecursive(null, 11));
        System.out.println(dfsRecursive(tree, 7));
    }
    // Итеративный поиск в глубину с использованием стека
    private static TreeNode dfsIterative(TreeNode root, int target) {
        if (root == null) return null;
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (node.val == target) return node;

            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        return null;
    }
    // Рекурсивный поиск в глубину
    private static TreeNode dfsRecursive(TreeNode root, int target) {
        if (root == null) return null;
        if (root.val == target) return root;
        TreeNode leftResult = dfsRecursive(root.left, target);
        if (leftResult != null) return leftResult;
        TreeNode rightResult = dfsRecursive(root.right, target);
        return rightResult;
    }
}