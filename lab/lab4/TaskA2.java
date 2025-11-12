// Реализовать поиск в дереве (Iterative deepening depth-first search)
public class TaskA2 {
    public static void main(String[] args) {
        TreeNode tree = TreeNode.generateTestTree();
        System.out.println(iddfs(tree, 11));
        System.out.println(iddfs(null, 11));
        System.out.println(iddfs(tree, 7));
        System.out.println(iddfs(tree, 4));
    }
    private static TreeNode iddfs(TreeNode root, int target) {
        if (root == null) return null;
        int depth = 0;
        TreeNode result;
        while ((result = depthLimitedSearch(root, target, depth)) == null) {
            depth++;
        }
        return result;
    }
    private static TreeNode depthLimitedSearch(TreeNode node, int target, int depth) {
        if (node == null) return null;
        if (node.val == target) return node;
        if (depth == 0) return null;
        TreeNode leftResult = depthLimitedSearch(node.left, target, depth - 1);
        if (leftResult != null) return leftResult;
        TreeNode rightResult = depthLimitedSearch(node.right, target, depth - 1);
        return rightResult;
    }
}