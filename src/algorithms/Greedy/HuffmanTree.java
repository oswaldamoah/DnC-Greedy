package algorithms.Greedy;

import java.util.*;

public class HuffmanTree {
    private HuffmanNode root;

    public HuffmanTree() {
        this.root = null;
    }

    public HuffmanNode buildHuffmanTree(Map<Character, Integer> frequencyMap) {
        PriorityQueue<HuffmanNode> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(node -> node.data));

        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            priorityQueue.offer(new HuffmanNode(entry.getValue(), entry.getKey()));
        }

        while (priorityQueue.size() > 1) {
            HuffmanNode left = priorityQueue.poll();
            HuffmanNode right = priorityQueue.poll();

            HuffmanNode parent = new HuffmanNode(left.data + right.data, '-');
            parent.left = left;
            parent.right = right;

            priorityQueue.offer(parent);
        }

        this.root = priorityQueue.poll();
        return this.root;
    }

    public Map<Character, String> buildHuffmanCodes() {
        if (root == null) {
            throw new IllegalStateException("Huffman tree has not been built.");
        }

        Map<Character, String> huffmanCodes = new HashMap<>();
        buildCodesRecursive(root, "", huffmanCodes);
        return huffmanCodes;
    }

    private void buildCodesRecursive(HuffmanNode node, String code, Map<Character, String> huffmanCodes) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            huffmanCodes.put(node.character, code);
        }

        buildCodesRecursive(node.left, code + "0", huffmanCodes);
        buildCodesRecursive(node.right, code + "1", huffmanCodes);
    }

    public String encode(String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }

        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : text.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        buildHuffmanTree(frequencyMap);
        Map<Character, String> huffmanCodes = buildHuffmanCodes();

        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toCharArray()) {
            encodedText.append(huffmanCodes.get(c));
        }

        return encodedText.toString();
    }

    public String decode(String encodedText) {
        if (encodedText == null || encodedText.isEmpty()) {
            return "";
        }

        StringBuilder decodedText = new StringBuilder();
        HuffmanNode current = root;
        for (char bit : encodedText.toCharArray()) {
            if (bit == '0') {
                current = current.left;
            } else if (bit == '1') {
                current = current.right;
            }

            if (current.left == null && current.right == null) {
                decodedText.append(current.character);
                current = root;
            }
        }

        return decodedText.toString();
    }

    private static class HuffmanNode implements Comparable<HuffmanNode> {
        private int data;
        private char character;
        private HuffmanNode left;
        private HuffmanNode right;

        public HuffmanNode(int data, char character) {
            this.data = data;
            this.character = character;
            this.left = null;
            this.right = null;
        }

        @Override
        public int compareTo(HuffmanNode other) {
            return this.data - other.data;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Step 1: Read input text from the user
        System.out.print("Enter the text to encode: ");
        String text = scanner.nextLine();

        // Step 2: Create HuffmanTree instance and encode the text
        HuffmanTree huffmanTree = new HuffmanTree();
        String encodedText = huffmanTree.encode(text);
        System.out.println("Encoded text: " + encodedText);

        // Step 3: Decode the encoded text
        String decodedText = huffmanTree.decode(encodedText);
        System.out.println("Decoded text: " + decodedText);

        scanner.close();
    }
}
