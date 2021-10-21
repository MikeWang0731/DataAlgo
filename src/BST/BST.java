package BST;

import java.util.LinkedList;
import java.util.Queue;
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

        size++;

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
    public void levelOrder() {
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node current = q.remove();
            System.out.println(current.e);

            if (current.left != null) {
                q.add(current.left);
            }
            if (current.right != null) {
                q.add(current.right);
            }
        }
    }

    // ***************** 删除元素 *******************
    // 寻找二分搜索树的最小元素
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("Tree is Empty.");
        }
        return minimum(root).e;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    // 寻找二分搜索树的最大元素
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("Tree is Empty.");
        }
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    // 删除二分搜索树中的最小节点，并返回它
    public E removeMin() {
        E ret = minimum();
        // 将删除后的新树重新赋值给root
        root = removeMin(root);
        return ret;
    }

    // 删除以node为根的二分搜索树的最小节点，并返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node) {
        // 如果没有左侧了，就说明这个点就是最小的
        if (node.left == null) {
            // 但这个点可能有右侧子树，所以我们保存一下
            Node rightNode = node.right;
            // 右侧子树置null
            node.right = null;
            size--;
            // 将右侧子树的头节点作为新root返回（当然也可能没有子树）
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    // 删除二分搜索树中的最小节点，并返回它
    public E removeMax() {
        E ret = maximum();
        // 将删除后的新树重新赋值给root
        root = removeMax(root);
        return ret;
    }

    // 删除以node为根的二分搜索树的最小节点，并返回删除节点后新的二分搜索树的根
    private Node removeMax(Node node) {
        // 如果没有左侧了，就说明这个点就是最小的
        if (node.right == null) {
            // 但这个点可能有右侧子树，所以我们保存一下
            Node leftNode = node.left;
            // 右侧子树置null
            node.left = null;
            size--;
            // 将右侧子树的头节点作为新root返回（当然也可能没有子树）
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    // 删除指定元素
    public void remove(E e) {
        root = remove(root, e);
    }
    // 删除以node为根的树中值为e的那个节点，递归算法，并返回新的树的根
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        // 如果e比当前值小，那么就找左侧
        if (e.compareTo(node.e) < 0) {
            // 尝试在左侧删除，并将删除后的结果重新给左侧节点
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) { // 找右侧
            node.right = remove(node.right, e);
            return node;
        } else { // e == node.e -> 找到了！
            // 左侧为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            // 右侧为空
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            // 两侧都不为空！
            // 找到比待删除节点大的最小的那个节点(e.g. 删59找60)，即待删除节点右侧子树的最小节点
            // 用这个节点来顶替位置

            // 找到右侧的最小值，successor指向这个最小值
            Node successor = minimum(node.right);
            // 注意这是removeMin(Node node),不是removeMin()!!!这里会返回删除后新的二叉树的根！
            // 将原来node右侧的树删除最小值之后，剩下的部分连在那个最小值上(这是算法步骤)
            successor.right = removeMin(node.right);
            // 将左侧子树原封不动的链接在最小值上
            successor.left = node.left;
            // 到这里，这一小部分的树我们就完全拷贝并操作完成了。将原来的节点置null，删除。
            node.left = node.right = null;
            // 返回新的最小值组成的树
            return successor; // successor由原来大树的"递归层上面那一层的"left或right接着
        }
    }


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
