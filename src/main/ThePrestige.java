package main;

import fastio.InputReader;
import fastio.OutputWriter;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ThePrestige {
    class ImplicitTreap {

        class Node {
            //int index; //BST component -- Implicit
            int priority; //Heap component
            int value;
            int revValue;

            public Node(int priority, int value, int revValue) {
                this.priority = priority;
                this.value = value;
                this.revValue = revValue;
                this.size = 1;
                this.sum = value;
                this.revSum = revValue;
                this.actualValue = value;
                this.actualSum = value;
            }

            int size;

            Node left;
            Node right;

            boolean reverse; //Updated lazily
            long sum;
            long revSum;

            int actualValue;
            long actualSum;
        }

        class Split {
            Node left;
            Node right;

            public Split(Node left, Node right) {
                this.left = left;
                this.right = right;
            }
        }

        Node root = null;

        Split NULL_SPLIT = new Split(null, null);

        int size(Node node) {
            if (node == null)
                return 0;
            else
                return node.size;
        }

        int value(Node node) {
            if (node == null)
                return 0;
            else
                return node.value;
        }

        int actualValue(Node node) {
            if (node == null)
                return 0;
            else
                return node.actualValue;
        }

        long actualSum(Node node) {
            if (node == null)
                return 0;
            else
                return node.actualSum;
        }

        int revValue(Node node) {
            if (node == null)
                return 0;
            else
                return node.revValue;
        }

        long sum(Node node) {
            if (node == null)
                return 0;
            else
                return node.sum;
        }

        long revSum(Node node) {
            if (node == null)
                return 0;
            else
                return node.revSum;
        }

        void updateSize(Node node) {
            node.size = size(node.left) + 1 + size(node.right);
        }

        void updateSum(Node node) {
            node.sum = sum(node.left) + node.value + sum(node.right);
        }

        void updateRevSum(Node node) {
            node.revSum = revSum(node.left) + node.revValue + revSum(node.right);
        }

        void updateActualSum(Node node) {
            lazyPush(node.left);
            lazyPush(node.right);
            node.actualSum = actualSum(node.left) + node.actualValue + actualSum(node.right);
        }

        void lazyPush(Node node) {
            if (node == null)
                return;
            if (node.reverse) {
                swapLeftAndRight(node);
                if (node.left != null) {
                    node.left.reverse ^= true;
                }
                if (node.right != null) {
                    node.right.reverse ^= true;
                }
                node.reverse ^= true;
                node.actualValue = (node.revValue + node.value - node.actualValue);
                node.actualSum = (node.revSum + node.sum - node.actualSum);
            }
        }

        void swapLeftAndRight(Node node) {
            Node temp = node.right;
            node.right = node.left;
            node.left = temp;
        }

        //@leftSize specifies the number of elements that have already been incorporated in the left split
        //@position specifies the last index (0-indexed) that should belong to the left split
        private Split split(Node node, int leftSize, int position) {
            if (node == null) {
                return NULL_SPLIT;
            }

            lazyPush(node);

            int currentPosition = leftSize + size(node.left);

            Split resultSplit = null;

            if (currentPosition == position) {
                Node rightSplit = node.right;
                node.right = null;
                resultSplit = new Split(node, rightSplit);
            } else if (currentPosition < position) {
                Split split = split(node.right, leftSize + size(node.left) + 1, position);
                node.right = split.left;
                resultSplit = new Split(node, split.right);
            } else {
                Split split = split(node.left, leftSize, position);
                node.left = split.right;
                resultSplit = new Split(split.left, node);
            }

            updateSize(node);
            updateSum(node);
            updateRevSum(node);
            updateActualSum(node);

            return resultSplit;
        }

        //Follows Max Heap property
        //Ensure random priority values assigned are all distinct
        private Node merge(Node leftNode, Node rightNode) {
            if (leftNode == null && rightNode == null)
                return null;

            lazyPush(leftNode);
            lazyPush(rightNode);

            if (leftNode == null)
                return rightNode;
            if (rightNode == null)
                return leftNode;

            Node resultNode;
            if (leftNode.priority > rightNode.priority) {
                Node mergedNode = merge(leftNode.right, rightNode);
                leftNode.right = mergedNode;
                resultNode = leftNode;
            } else {
                Node mergedNode = merge(leftNode, rightNode.left);
                rightNode.left = mergedNode;
                resultNode = rightNode;
            }

            updateSize(resultNode);
            updateSum(resultNode);
            updateRevSum(resultNode);
            updateActualSum(resultNode);

            return resultNode;
        }

        public void reverseSegment(Node node, int l, int r) {
            int n = node.size;

            Node leftSplit = null;
            Node rightSplit = null;

            if (l > 0) {
                Split split = split(node, 0, l - 1);
                leftSplit = split.left;
                node = split.right;
            }

            if (r < n - 1) {
                Split split = split(node, 0, r - l);
                rightSplit = split.right;
                node = split.left;
            }

            node.reverse ^= true;
            lazyPush(node);

            node = merge(leftSplit, node);

            node = merge(node, rightSplit);

            root = node;
        }

        public long getSegmentValue(Node node, int l, int r) {
            int n = node.size;

            Node leftSplit = null;
            Node rightSplit = null;

            if (l > 0) {
                Split split = split(node, 0, l - 1);
                leftSplit = split.left;
                node = split.right;
            }

            if (r < n - 1) {
                Split split = split(node, 0, r - l);
                rightSplit = split.right;
                node = split.left;
            }

            lazyPush(node);
            long value = actualSum(node);

            node = merge(leftSplit, node);

            node = merge(node, rightSplit);

            root = node;

            return value;
        }

        void insert(Node node, int value, int revValue, int priority, int position) {

            Node newNode = new Node(priority, value, revValue);

            if (node == null) {
                root = newNode;
                return;
            }

            Split split = null;
            if (position > 0) {
                split = split(node, 0, position - 1);
                node = merge(split.left, newNode);
                node = merge(node, split.right);
            } else {
                node = merge(newNode, node);
            }

            root = node;
        }
    }

    public void solve(int testNumber, InputReader in, OutputWriter out) {
        int n = in.nextInt();
        int m = in.nextInt();

        int[] a = in.nextIntArray(n);
        int[] b = in.nextIntArray(n);

        Set<Integer> usedPriors = new HashSet<>();

        ImplicitTreap treap = new ImplicitTreap();

        Random random = new Random(System.nanoTime());

        for (int i = 0; i < n; i++) {
            int prior = -1;
            while (usedPriors.contains(prior = random.nextInt(2000000000)))
                ;

            treap.insert(treap.root, a[i], b[i], prior, i);

            usedPriors.add(prior);
        }

        int lmo = -1;

        while (m-- > 0) {
            int t = in.nextInt();

            if (t == 1) {
                int l = in.nextInt();
                int r = in.nextInt();

                l--;
                r--;

                treap.reverseSegment(treap.root, l, r);
            } else if (t == 2) {
                int k = in.nextInt();

                k--;

                lmo = k - lmo - 1;
            } else {
                int x1 = in.nextInt();
                int y1 = in.nextInt();

                int x2 = in.nextInt();
                int y2 = in.nextInt();

                x1--;
                y1--;
                x2--;
                y2--;

                if (lmo < x2) {
                    long value = treap.getSegmentValue(treap.root, x1, y1);

                    out.println(value);
                } else if (lmo >= x2 && lmo <= y2) {
                    long value1 = treap.getSegmentValue(treap.root, x1, x1 + (lmo - x2 + 1) - 1);
                    long value2 = treap.getSegmentValue(treap.root, x1 + (lmo - x2 + 1), y1);

                    out.println(value2 - value1);
                } else {
                    long value = treap.getSegmentValue(treap.root, x1, y1);

                    out.println(-value);
                }
            }
        }
    }
}
