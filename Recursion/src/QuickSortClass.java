import java.util.Stack;

public class QuickSortClass {

    private	int a[];
    private int aSize ;

    public QuickSortClass(int arr[], int n){  //생성자 함수, 정렬할 데이터를 배열 a 에 받음
        a = arr;                              //임시 배열 b 를 동적으로 생성
        aSize = n;
        a[n+1] = Integer.MAX_VALUE ;          // a[n+1] is set to infinity.

    }

    public int[] QuickSortCall2() {  // MergeSort가 재귀함수 이므로, 처음 호출함
        QuickSort2(1, aSize);
        return a ;
    }

    void QuickSort2(int p, int q)
    // Sorts the elements in a[p:q].
    {
        Stack<Integer> stack = new Stack<Integer>();  // 자바의 시스템 스택 클래스의 객체를 생성
        do {
            while (p < q) {
                int j = Partition(a, p, q+1);
                if ((j-p) < (q-j)) {
                    stack.push(new Integer(j+1));
                    stack.push(new Integer(q)); q = j-1;
                }
                else {
                    stack.push(new Integer(p));
                    stack.push(new Integer(j-1)); p = j+1;
                    //또는                stack.push(p);
                    //          stack.push(j-1); p = j+1;

                }
            }; // Sort the smaller subfile.
            if (stack.isEmpty()) return;
            q = (Integer)stack.pop().intValue();
            p = (Integer)stack.pop().intValue();
            // 또는     q = stack.pop();
            //       p = stack.pop();
        } while (true);
    }

    int Partition(int a[], int m, int p)
    // Within a[m], a[m+1],..., a[p-1] the elements
    // are rearranged in such a manner that if
    // initially t==a[m], then after completion
    // a[q]==t for some q between m and p-1, a[k]<=t
    // for m<=k<q, and a[k]>=t for q<k<p. q is returned.
    {
        int v=a[m]; int i=m, j=p;
        do {
            do i++;
            while (a[i] < v);
            do j--;
            while (a[j] > v);
            if (i < j) Interchange(a, i, j);
        } while (i < j);
        a[m] = a[j]; a[j] = v; return(j);
    }

    void Interchange(int a[], int i, int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
