package BST;

import java.util.Stack;

public class BST<E extends Comparable<E>> {
    // 树的节点
    private class Node {
        public E e;
        public Node left, right;

        // 构造函数：当传来一个e的时候，就生成一个节点
        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    // 初始化一个树：无根无节点
    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 向树中添加元素
    public void add(E e) {
        root = add(root, e);
    }

    // 向树中添加元素 - 递归写法
    // 向以"root_node"为根的二分搜索树里插入元素e
    // 返回插入e之后的这颗二叉树(或二叉树子树)
    private Node add(Node root_node, E e) {
        // 递归终止条件：为空，那么就生成新节点
        if (root_node == null) {
            return new Node(e);
        }

        // 递归 - 注意这里和上面的判断条件不一样
        if (e.compareTo(root_node.e) < 0) {
            // 如果e比当前node小，那么就往它的左方向继续遍历
            root_node.left = add(root_node.left, e);
        } else {
            // 如果e比当前node大，那么就往它的右方向继续遍历
            root_node.right = add(root_node.right, e);
        }

        // 将这棵树的根节点（连带着子树）返回
        return root_node;
    }

    // 是否包含元素e
    public boolean contains(E e) {
        return contains(root, e);
    }

    // 递归写法：在根为node的树里有没有元素e
    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        if (e.compareTo(node.e) == 0) {
            // e和这个节点一样，就找到了
            return true;
        } else if (e.compareTo(node.e) < 0) {
            // e比这个节点小，按照特征，找当前节点的左侧
            return contains(node.left, e);
        } else {
            // 比它大，就找右侧
            return contains(node.right, e);
        }
    }
    // ***************** 深度优先遍历：前中后序 *******************
    // 前序遍历整个树：先访问节点，后访问子树
    public void preOrder() {
        preOrder(root);
    }

    // 前序遍历以node为根的整个二分搜索树
    private void preOrder(Node node) {
        // 没东西了，我们就撤了
        if (node == null) {
            return;
        }
        // 打印当前节点
        System.out.println(node.e);
        // 继续遍历这个节点的左侧
        preOrder(node.left);
        // 继续遍历这个节点的右侧
        preOrder(node.right);
    }


    // 前序遍历 - 非递归写法
    public void preOrderNonRecursive() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.println(current.e);

            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

    // 中序遍历：先左，再当前节点，再右
    // 中序遍历的结果就应该是元素排序后的结果
    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    // 后序遍历：先左，再右, 再当前节点
    // 释放内存的情况：先释放子进程，再结束主进程
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }
    // ***************** 广度有限遍历：层序遍历 *******************


    // ***************** 格式化输出 ********************
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res) {
        // 没东西可遍历，我们就撤
        if (node == null) {
            res.append(generateDepthString(depth)).append("null\n");
            return;
        }

        // 当前节点：它的深度信息 + 它的数值 + 换行符
        res.append(generateDepthString(depth)).append(node.e).append("\n");
        // 继续向它的左侧和右侧遍历
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            res.append("--");
        }
        return res.toString();
    }
}
