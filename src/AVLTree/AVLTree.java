package AVLTree;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> {

    // 树的节点
    private class Node {
        public K key;
        public V value;
        public Node left, right;
        public int height;

        // 构造函数：当传来一个e的时候，就生成一个节点
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            height = 1;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }


    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 判断该树是不是二分搜索树
    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            // 判断一下之前一个和现在这个相比是不是要小(prev<current)，如果小则返回true
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                // 这里是用"是否大于"进行比较
                return false;
            }
        }
        return true;
    }

    // 是不是一颗平衡二叉树
    public boolean isBalanced() {
        return isBalanced(root);
    }

    // 左右子树的高度差不超过1
    private boolean isBalanced(Node node) {
        // 空树一定是平衡二叉树
        if (node == null) {
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    private void inOrder(Node node, ArrayList<K> keys) {
        if (node == null) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    // 获取节点node的高度
    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // 获取节点node的平衡因子
    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    // 向树中添加元素
    public void add(K key, V value) {
        root = add(root, key, value);
    }

    // 向树中添加元素 - 递归写法
    // 向以"root_node"为根的二分搜索树里插入元素e
    // 返回插入e之后的这颗二叉树(或二叉树子树)
    private Node add(Node root_node, K key, V value) {
        // 递归终止条件：为空，那么就生成新节点
        if (root_node == null) {
            size++;
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

        // ======== 插入动作结束，开始检查平衡性 =========
        // 更新height: 找到左侧和右侧当中最高的那个，然后+1
        root_node.height = 1 + Math.max(getHeight(root_node.left), getHeight(root_node.right));
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(root_node);
//        if (Math.abs(balanceFactor) > 1) {
//            System.out.println("Unbalanced:" + balanceFactor);
//        }
        // 平衡维护 - LL
        if (balanceFactor > 1 && getBalanceFactor(root_node.left) >= 0) {
            // 第一个条件代表不平衡，第二个条件代表插入的节点在不平衡节点左侧的左侧
            return rightRotate(root_node); // 返回自平衡后的新的子树的root返回给递归的上一层
        }
        // 平衡维护 - RR
        if (balanceFactor < -1 && getBalanceFactor(root_node.right) <= 0) {
            // 第一个条件代表不平衡，第二个条件代表插入的节点在不平衡节点右侧的右侧
            return leftRotate(root_node);
        }
        // 平衡维护 - LR
        if (balanceFactor > 1 && getBalanceFactor(root_node.right) < 0) {
            // 不平衡在左侧子树，但是是在左侧子树中的右侧部分
            root_node.left = leftRotate(root_node.left);
            return rightRotate(root_node);
        }
        // 平衡维护 - RL
        if (balanceFactor < -1 && getBalanceFactor(root_node.right) > 0) {
            // 不平衡在右侧子树，但是是在右侧子树中的左侧部分
            root_node.right = rightRotate(root_node.right);
            return leftRotate(root_node);
        }

        // 将这棵树的根节点（连带着子树）返回
        return root_node;
    }

    // 右旋转操作(左侧加入节点导致的不平衡), y为不平衡的那个节点
    //  y - T4              x - y - T4
    //  |                   |   |
    //  x - T3              |   T3
    //  |           ---->   z - T2
    //  z - T2              |
    //  |                   T1
    //  T1
    private Node rightRotate(Node y) {
        Node x = y.left;  // 用x承接所有y的左侧子树
        Node T3 = x.right; // 用T3承接所有y的右侧子树
        x.right = y; // 旋转后y在x的右侧
        y.left = T3; // 旋转后T3在y的左侧

        // 更新height
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        // 因为x是旋转之后的"顶层"节点，所以返回x
        return x;
    }

    // 左旋转，右侧加入节点导致的不平衡
    private Node leftRotate(Node y) {
        Node x = y.right;
        Node T2 = x.left;
        x.left = y;
        y.right = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1; // 先
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1; // 后

        return x;
    }

    public boolean contains(K key) {
        // 有的话就是true，没有就是false
        return getNode(root, key) != null;
    }

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
        Node retNode;
        // 如果e比当前值小，那么就找左侧
        if (key.compareTo(node.key) < 0) {
            // 尝试在左侧删除，并将删除后的结果重新给左侧节点
            node.left = remove(node.left, key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0) { // 找右侧
            node.right = remove(node.right, key);
            retNode = node;
        } else { // e == node.e -> 找到了！
            // 左侧为空
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            }
            // 右侧为空
            else if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {
                // 两侧都不为空！
                // 找到比待删除节点大的最小的那个节点(e.g. 删59找60)，即待删除节点右侧子树的最小节点
                // 用这个节点来顶替位置

                // 找到右侧的最小值，successor指向这个最小值
                Node successor = minimum(node.right);
                // 将原来node右侧的树删除最小值之后，剩下的部分连在那个最小值上(这是算法步骤)
                successor.right = remove(node.right, successor.key);
                // 将左侧子树原封不动的链接在最小值上
                successor.left = node.left;
                // 到这里，这一小部分的树我们就完全拷贝并操作完成了。将原来的节点置null，删除。
                node.left = node.right = null;
                // 返回新的最小值组成的树
                retNode = successor; // successor由原来大树的"递归层上面那一层的"left或right接着
            }
        }
        if (retNode == null) {
            return null;
        }
        // ======== 动作结束，开始检查平衡性 =========
        // 更新height: 找到左侧和右侧当中最高的那个，然后+1
        retNode.height = 1 + Math.max(getHeight(retNode), getHeight(retNode));
        // 计算平衡因子
        int balanceFactor = getBalanceFactor(retNode);

        // 平衡维护 - LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            // 第一个条件代表不平衡，第二个条件代表插入的节点在不平衡节点左侧的左侧
            return rightRotate(retNode); // 返回自平衡后的新的子树的root返回给递归的上一层
        }
        // 平衡维护 - RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            // 第一个条件代表不平衡，第二个条件代表插入的节点在不平衡节点右侧的右侧
            return leftRotate(retNode);
        }
        // 平衡维护 - LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.right) < 0) {
            // 不平衡在左侧子树，但是是在左侧子树中的右侧部分
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        // 平衡维护 - RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            // 不平衡在右侧子树，但是是在右侧子树中的左侧部分
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        // 将这棵树的根节点（连带着子树）返回
        return retNode;
    }

    public static void main(String[] args) {

    }
}

