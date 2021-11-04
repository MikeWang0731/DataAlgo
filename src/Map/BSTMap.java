package Map;

import BST.BST;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    // 树的节点
    private class Node {
        public K key;
        public V value;
        public Node left, right;

        // 构造函数：当传来一个e的时候，就生成一个节点
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    // 向树中添加元素
    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    // 向树中添加元素 - 递归写法
    // 向以"root_node"为根的二分搜索树里插入元素e
    // 返回插入e之后的这颗二叉树(或二叉树子树)
    private Node add(Node root_node, K key, V value) {
        // 递归终止条件：为空，那么就生成新节点
        if (root_node == null) {
            return new Node(key, value);
        }

        // 递归 - 注意这里和上面的判断条件不一样
        if (key.compareTo(root_node.key) < 0) {
            // 如果e比当前node小，那么就往它的左方向继续遍历
            root_node.left = add(root_node.left, key, value);
        } else if (key.compareTo(root_node.key) > 0) {
            // 如果e比当前node大，那么就往它的右方向继续遍历
            root_node.right = add(root_node.right, key, value);
        } else {
            // 当key相同，但是用户传入的value和旧的value不一样的时候，我们认为用户想要更新
            root_node.value = value;
        }

        size++;

        // 将这棵树的根节点（连带着子树）返回
        return root_node;
    }

    @Override
    public boolean contains(K key) {
        // 有的话就是true，没有就是false
        return getNode(root, key) != null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        // 我们要找的key对应的node为空吗？如果是，那么返回null，如果不是，那就返回node的value
        return node == null ? null : node.value;
    }


    // 返回以node为根节点的二分搜索树中，某个key所在的节点
    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.compareTo(node.key) == 0) {
            // 如果key相等，那就是找到了
            return node;
        } else if (key.compareTo(node.key) < 0) {
            // 如果传入的key比当前节点小，那么根据二分搜索树性质，就要向左侧子树继续找
            return getNode(node.left, key);
        } else {
            // 大于的话就是右侧子树
            return getNode(node.right, key);
        }
    }

    @Override
    public void set(K key, V newValue) {
        // 1. 先取到这个节点
        Node node = getNode(root, key);
        // 2. 如果这个节点返回的null(不存在)，那就抛出异常
        if (node == null) {
            throw new IllegalArgumentException(key + "doesn't exist!");
        }
        // 3. 更新值
        node.value = newValue;
    }

    // 删除指定元素
    @Override
    public V remove(K key) {
        Node node = getNode(root, key);
        if (node != null) {
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
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


    // 删除以node为根的树中键为key的那个节点，递归算法，并返回新的树的根
    private Node remove(Node node, K key) {
        if (node == null) {
            return null;
        }
        // 如果e比当前值小，那么就找左侧
        if (key.compareTo(node.key) < 0) {
            // 尝试在左侧删除，并将删除后的结果重新给左侧节点
            node.left = remove(node.left, key);
            return node;
        } else if (key.compareTo(node.key) > 0) { // 找右侧
            node.right = remove(node.right, key);
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
}
