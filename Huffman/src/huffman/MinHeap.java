package huffman;

import java.util.ArrayList;
import java.util.Collections;

public class MinHeap { // 최소 힙
    // 최소힙에 들어갈 수 있는 문자의 최대 갯수는 소문자 26개 + 대문자 26개 + 스페이스 1개 + 여유공간 1 = 54
    private ArrayList<Node> tree = new ArrayList<Node>(54);

    public MinHeap() {
        tree.add(null); // 첫 번쨰 비우기
    }
    public void insert(Node n) {
        tree.add(n);

        int childPos = tree.size()-1;
        int parentPos = childPos/2;

        // freq를 기준으로 heap 구성
        while(parentPos >= 1 && tree.get(childPos).freq < tree.get(parentPos).freq) {
            Collections.swap(tree, childPos, parentPos);

            childPos = parentPos;
            parentPos = childPos/2;
        }
    }
    // heap이 비어있으면 true
    public boolean isEmpty() {
        return (tree.size() <= 1);
    }
    // 최소 노드 반환.
    public Node extractMinNode() {
        if(isEmpty()) return null; // heap이 비어있을 경우

        Node min = tree.get(1);

        int top = tree.size()-1;

        tree.set(1, tree.get(top));
        tree.remove(top); // 맨마지막 원소로 대체

        int parentPos = 1;
        int leftPos = parentPos*2;
        int rightPos = parentPos*2 + 1;

        // 왼쪽 자식이 있는 경우에만 균형을 맞춘다. 힙은 완전 이진 트리이기 때문에
        // 왼쪽 자식이 없으면 오른쪽 자식도 없다는 뜻이기 때문이다.
        while(leftPos <= tree.size()-1) {
            int targetPos;
            if(rightPos > tree.size()-1) { // 오른쪽 자식이 없는 경우
                if(tree.get(leftPos).freq >= tree.get(parentPos).freq) // 왼쪽 자식이 더 크면 for 종료
                    break;
                targetPos = leftPos;
            }
            else { // 왼쪽 오른쪽 전부 있는 경우
                if(tree.get(leftPos).freq >= tree.get(parentPos).freq &&
                        tree.get(rightPos).freq >= tree.get(parentPos).freq)
                    break; // 두 자식 노드가 더 크거나 같으면 while문 종료

                // 더 작은 노드로 swap
                targetPos = (tree.get(leftPos).freq < tree.get(rightPos).freq) ? leftPos : rightPos;
            }

            Collections.swap(tree, targetPos, parentPos);

            // top-down 순회
            parentPos = targetPos;
            leftPos = parentPos*2;
            rightPos = parentPos*2 + 1;
        }
        return min;
    }
    public void printTree() {
        for(Node n : tree)
            if(n != null)
                System.out.print(n.freq+" ");
        System.out.println("");
    }
}

