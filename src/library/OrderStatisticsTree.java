package library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderStatisticsTree {
    private Node<Integer> root;

    public OrderStatisticsTree() {
        root = null;
    }

    public Integer searchForIndex(Integer value) {
        return searchForIndexHelper(root, value);
    }

    public Integer searchForIndexHelper(Node<Integer> root, Integer value) {
        if (root == null)
            return -1;

        if (value.equals(root.value)) {
            return root.leftSubtreeSize;
        } else if (root.value > value) {
            return searchForIndexHelper(root.left, value);
        } else if (root.value < value) {
            int ind = searchForIndexHelper(root.right, value);

            if (ind != -1)
                return root.leftSubtreeSize + 1 + ind;
            else
                return -1;
        }

        return -1;
    }

    public Node<Integer> search(Integer value) {
        return searchHelper(root, value);
    }

    public Node<Integer> searchHelper(Node<Integer> root, Integer value) {
        if (root == null)
            return null;

        if (value.equals(root.value)) {
            return root;
        } else if (root.value > value) {
            return searchHelper(root.left, value);
        } else if (root.value < value) {
            return searchHelper(root.right, value);
        }

        // For compiler satisfaction.
        return null;
    }

    public Node<Integer> insert(Integer value) {
        return root = insertHelper(root, value);
    }

    // A utility function to get height of the tree
    int height(Node N) {
        if (N == null)
            return 0;

        return N.height;
    }

    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        y.leftSubtreeSize -= (x.leftSubtreeSize + 1);

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    Node<Integer> leftRotate(Node<Integer> x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        y.leftSubtreeSize += (x.leftSubtreeSize + 1);
        // Return new root
        return y;
    }

    // Get Balance factor of root N
    int getBalance(Node N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    public Node<Integer> insertHelper(Node<Integer> root, Integer value) {
        if (root == null) {
            Node<Integer> nn = new Node<Integer>();
            nn.value = value;
            nn.leftSubtreeSize = 0;
            nn.height = 1;

            return nn;
        } else if (value < root.value) {
            root.left = insertHelper(root.left, value);
            root.leftSubtreeSize++;
        } else if (value > root.value) {
            root.right = insertHelper(root.right, value);
        } else
            // Duplicate values NOT supported.
            return root;

        root.height = 1 + max(height(root.left), height(root.right));

        /*
         * 3. Get the balance factor of this ancestor root to check whether this root became
         * unbalanced
         */
        int balance = getBalance(root);

        // If this root becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && value < root.left.value)
            return rightRotate(root);

        // Right Right Case
        if (balance < -1 && value > root.right.value)
            return leftRotate(root);

        // Left Right Case
        if (balance > 1 && value > root.left.value) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Left Case
        if (balance < -1 && value < root.right.value) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    public void delete(Integer value) {
        root = deleteHelper(root, value);
    }

    private Node<Integer> getMinValueNode(Node<Integer> root) {
        Node<Integer> current = root;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    private Node<Integer> deleteHelper(Node<Integer> root, Integer value) {
        // Assuming a root is deleted always
        if (root == null)
            return null;

        if (value.equals(root.value)) {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left != null && root.right == null) {
                return root.left;
            } else if (root.left == null && root.right != null) {
                return root.right;
            } else {
                Node<Integer> temp = getMinValueNode(root.right);

                root.value = temp.value;

                root.right = deleteHelper(root.right, temp.value);
            }
        } else if (root.value > value) {
            root.left = deleteHelper(root.left, value);
            root.leftSubtreeSize--;
        } else if (root.value < value) {
            root.right = deleteHelper(root.right, value);
        }

        root.height = 1 + max(height(root.left), height(root.right));

        /*
         * 3. Get the balance factor of this ancestor root to check whether this root became
         * unbalanced
         */
        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    public Integer getNthElement(Integer n) {
        return getNthElementHelper(root, n);
    }

    public Integer getNthElementHelper(Node<Integer> root, Integer n) {
        if (root == null) {
            return null;
        }

        if (root.leftSubtreeSize + 1 == n) {
            return root.value;
        } else if (root.leftSubtreeSize + 1 < n) {
            return getNthElementHelper(root.right, n - root.leftSubtreeSize - 1);
        } else if (root.leftSubtreeSize + 1 > n) {
            return getNthElementHelper(root.left, n);
        }

        // For compiler satisfaction.
        return -1;
    }

    public Integer getIndGreaterThanOrEqualTo(Integer n) {
        return getIndGreaterThanOrEqualToHelper(root, n);
    }

    public Integer getIndGreaterThanOrEqualToHelper(Node<Integer> root, Integer n) {
        if (root == null) {
            return -1;
        }

        if (root.value.equals(n)) {
            return root.leftSubtreeSize;
        }

        if (root.value < n) {
            int ceil = getIndGreaterThanOrEqualToHelper(root.right, n);

            if (ceil != -1) {
                return root.leftSubtreeSize + 1 + ceil;
            } else {
                return -1;
            }
        } else {
            int ceil = getIndGreaterThanOrEqualToHelper(root.left, n);

            if (ceil != -1) {
                return ceil;
            } else {
                return root.leftSubtreeSize;
            }
        }
    }

    public Integer getNumGreaterThanOrEqualTo(Integer n) {
        return getNumGreaterThanOrEqualToHelper(root, n);
    }

    private Integer getNumGreaterThanOrEqualToHelper(Node<Integer> root, Integer n) {
        if (root == null) {
            return -1;
        }

        if (root.value.equals(n)) {
            return root.value;
        }

        if (root.value < n) {
            return getNumGreaterThanOrEqualToHelper(root.right, n);
        } else {
            int ceil = getNumGreaterThanOrEqualToHelper(root.left, n);

            if (ceil >= n) {
                return ceil;
            } else {
                return root.value;
            }
        }
    }

    public Integer getIndLessThanOrEqualTo(Integer n) {
        return getIndLessThanOrEqualToHelper(root, n);
    }

    public Integer getIndLessThanOrEqualToHelper(Node<Integer> root, Integer n) {
        if (root == null) {
            return -1;
        }

        if (root.value.equals(n)) {
            return root.leftSubtreeSize;
        }

        if (root.value > n) {
            return getIndLessThanOrEqualToHelper(root.left, n);
        } else {
            int floor = getIndLessThanOrEqualToHelper(root.right, n);
            if (floor != -1) {
                return root.leftSubtreeSize + 1 + floor;
            } else {
                return root.leftSubtreeSize;
            }
        }
    }

    public Integer getNumLessThanOrEqualTo(Integer n) {
        return getNumLessThanOrEqualToHelper(root, n);
    }

    private Integer getNumLessThanOrEqualToHelper(Node<Integer> root, Integer n) {
        if (root == null) {
            return -1;
        }

        if (root.value.equals(n)) {
            return root.value;
        }

        if (root.value > n) {
            return getNumLessThanOrEqualToHelper(root.left, n);
        } else {
            int floor = getNumLessThanOrEqualToHelper(root.right, n);
            if (floor != -1) {
                return floor;
            } else {
                return root.value;
            }
        }
    }

    int size(Node<Integer> root) {
        if (root == null)
            return 0;

        return root.leftSubtreeSize + 1 + size(root.right);
    }

    void print() {
        BTreePrinter.printNode(root);
    }

    static class Node<T extends Comparable<?>> {
        Node<T> left, right;
        T value;
        int leftSubtreeSize;
        int height;
    }

    static class BTreePrinter {

        public static <T extends Comparable<?>> void printNode(Node<T> root) {
            int maxLevel = BTreePrinter.maxLevel(root);

            printNodeInternal(Collections.singletonList(root), 1, maxLevel);
        }

        private static <T extends Comparable<?>> void printNodeInternal(List<Node<T>> roots,
                                                                        int level, int maxLevel) {
            if (roots.isEmpty() || BTreePrinter.isAllElementsNull(roots))
                return;

            int floor = maxLevel - level;
            int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
            int firstSpaces = (int) Math.pow(2, (floor)) - 1;
            int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

            BTreePrinter.printWhitespaces(firstSpaces);

            List<Node<T>> newNodes = new ArrayList<Node<T>>();
            for (Node<T> root : roots) {
                if (root != null) {
                    System.out.print(root.value);
                    newNodes.add(root.left);
                    newNodes.add(root.right);
                } else {
                    newNodes.add(null);
                    newNodes.add(null);
                    System.out.print(" ");
                }

                BTreePrinter.printWhitespaces(betweenSpaces);
            }
            System.out.println("");

            for (int i = 1; i <= endgeLines; i++) {
                for (int j = 0; j < roots.size(); j++) {
                    BTreePrinter.printWhitespaces(firstSpaces - i);
                    if (roots.get(j) == null) {
                        BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                        continue;
                    }

                    if (roots.get(j).left != null)
                        System.out.print("/");
                    else
                        BTreePrinter.printWhitespaces(1);

                    BTreePrinter.printWhitespaces(i + i - 1);

                    if (roots.get(j).right != null)
                        System.out.print("\\");
                    else
                        BTreePrinter.printWhitespaces(1);

                    BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
                }

                System.out.println("");
            }

            printNodeInternal(newNodes, level + 1, maxLevel);
        }

        private static void printWhitespaces(int count) {
            for (int i = 0; i < count; i++)
                System.out.print(" ");
        }

        private static <T extends Comparable<?>> int maxLevel(Node<T> root) {
            if (root == null)
                return 0;

            return Math.max(BTreePrinter.maxLevel(root.left), BTreePrinter.maxLevel(root.right)) + 1;
        }

        private static <T> boolean isAllElementsNull(List<T> list) {
            for (Object object : list) {
                if (object != null)
                    return false;
            }

            return true;
        }

    }
}