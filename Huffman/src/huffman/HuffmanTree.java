package huffman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class HuffmanTree {
    // 알파벳 빈도수 저장하는 HashMap
    public static HashMap<Character, Integer> freq = new HashMap<Character, Integer>();
    public static Node huffmanTree=null;

    // 알파벳 빈도수 count하는 함수
    public static void countAlphabetFrequency(String src) {
        try {
            //파일 읽어오기
            BufferedReader in = new BufferedReader(new FileReader(src));
            String s;

            while ((s = in.readLine()) != null) {
                // 문자의 개수를 하나씩 셉니다.
                for(int i=0;i<s.length();i++)
                {
                    char c = s.charAt(i);
                    if(freq.containsKey(c)) freq.put(c, freq.get(c)+1);
                    else freq.put(c, 1);
                }
            }
            in.close();
        } catch (IOException e) {
            System.err.println(e); // 에러가 있다면 메시지 출력
            System.exit(1);
        }
    }
    public static void makeHuffmanTree() {
        MinHeap mh = new MinHeap();

        if(freq.isEmpty()) // 빈도 수 센 것이 없으면 null을 return
            return;

        // 최소 힙에 각각의 빈도 수 및 알파벳을 저장합니다.
        for(char key : freq.keySet())
            mh.insert(new Node(freq.get(key), key));

        while(true) {
            // 최소 노드 2개 추출
            Node leftChild = mh.extractMinNode();
            Node rightChild = mh.extractMinNode();

            // 새로운 부모 노드를 만듭니다. 부모 노드의 freq는 양쪽 자식의 freq의 합 입니다.
            huffmanTree = new Node(leftChild.freq+rightChild.freq, '.');

            huffmanTree.leftNode = leftChild;
            huffmanTree.rightNode = rightChild;

            if(mh.isEmpty()) return; // 힙이 비어있으면 huffman 트리의 완성.

            mh.insert(huffmanTree);
        }
    }
    // HuffmanTree의 root를 받으면 각각 문자의 코드를 출력해줍니다.
    // trace는 트리를 추적하기 위한 배열입니다.
    public static void printEachCharacterCode(Node htRoot, int []trace, int top) {
        // left를 탐색할 경우 0을 출력하고, right를 탐색할 경우 1을 출력을 합니다.
        // 단말 노드를 만났을 경우, 즉, left right 모두 null일 경우 단말 노드의 character를 출력합니다.
        if(htRoot.leftNode != null) {
            trace[top] = 0;
            printEachCharacterCode(htRoot.leftNode, trace, top+1);
        }
        if(htRoot.rightNode != null) {
            trace[top] = 1;
            printEachCharacterCode(htRoot.rightNode, trace, top+1);
        }

        if(htRoot.leftNode == null && htRoot.rightNode == null) { // 단말 노드를 만났을 경우
            System.out.print(htRoot.alphabet + "(빈도 수: " + htRoot.freq +"): ");
            printArr(trace, top);
        }
    }
    public static void printArr(int[] arr, int top) {
        for(int i=0;i<top;i++)
            System.out.print(arr[i]);
        System.out.println("");
    }

    public static void printFreq() {
        for(char key : freq.keySet())
            System.out.println("key: " + key + ", freq: " + freq.get(key));
    }
    public static void main(String[] args) {
        countAlphabetFrequency("C:\\Users\\changyeon\\IdeaProjects\\Algorithm_HW3\\src\\huffman\\huffman.txt"); // 테스트할 때마다 huffman1.txt, huffman2.txt, huffman3.txt 수정하여 테스트하였음
        makeHuffmanTree();

        int []arr = new int[freq.size()-1];
        System.out.println("각 문자의 빈도수 ------ ");
        printFreq();

        System.out.println("각 문자에 할당된 코드 -------- ");
        printEachCharacterCode(huffmanTree, arr, 0);
    }
}