public class MergeSortClass {
    private	int a[], link[] ;
    private int aSize ;

    public MergeSortClass(int arr[], int n){  //생성자 함수, 정렬할 데이터를 배열 a 에 받음
        a = arr;                              //임시 배열 b 를 동적으로 생성
        aSize = n;
        link = new int[aSize+1];
        for(int i=0; i<= aSize; i++) link[i] = 0;
    }

    public int[] MergeSortCall() {  // MergeSort가 재귀함수 이므로, 처음 호출함
        int i, j, k, p ;
        int Temp1, Temp2 ;
        p = MergeSort1(1, aSize);    // 정렬 후, p 가 가장 작은 값의 index 이다.

        /*System.out.println("정렬 중간: 링크를 나타내는 link[]");
        for(i=1; i<= aSize; i++)
            System.out.print("  " + link[i]);	// 정렬 후의  배열 link 의 값들
        System.out.println();*/

        j = 1 ;         // 정렬 데이터의 순서에 해당
        while (p != 0) {    // 정렬 데이터의 제 위치를 결정
            int q = p ;
            p = link[p];
            link[q]= j ;
            j++;
        }

       /* System.out.println("정렬 중간: 제 위치를 나타내는 link[]");
        for(i=1; i<= aSize; i++)
            System.out.print("  " + link[i]);	// 정렬 후의  배열 link 의 값들
        System.out.println();*/

        for(i=1; i <= aSize; i++) {   // 정렬 데이터를 정렬된 위치로 이동
            if (link[i] != i) {
                j = link[i] ;
                Temp1 = a[i] ;
                while (j != i) {      // 하나의 permutation 사이클을 돌며, 데이터를 제 위치에 저장
                    Temp2 = a[j];
                    p = link[j];
                    a[j] = Temp1 ;
                    link[j] = j ;
                    Temp1 = Temp2;
                    j = p ;
                }
                a[j] = Temp1 ;
                link[j] = j ;
            }
        }
        return a ;
    }

    int MergeSort1(int low, int high)
    // The global array a[low : high] is sorted in
    // nondecreasing order using the auxiliary array
    // link[low:high]. The values in link  will
    // represent a list of the indices low through
    // high giving a[] in sorted order. A pointer
    // to the beginning of the list is returned.
    {
/* 작은 리스트에 삽입정렬 적용시 사용
        if ((high-low+1)<16)    // n < 16 인 작은 리스트에 삽입정렬 적용
          return InsertionSort1(a, link, low, high);
        else {
           int mid = (low + high)/2;
           int q = MergeSort1(low, mid);
           int r = MergeSort1(mid+1, high);
           return(Merge1(q,r));
        }
*/
// 삽입정렬 적용하지 않음
        if (!(low < high))
            return low;
        else {
            int mid = (low + high)/2;
            int q = MergeSort1(low, mid);
            int r = MergeSort1(mid+1, high);
            return(Merge1(q,r));
        }
    }

    int Merge1(int q, int r)
    // The lists q and r are merged and
    // a pointer to the beginning of the merged list is returned.
    {
        int i=q, j=r, k=0;
        // The new list starts at link[0].    p 에 해당함.
        while (i!=0 && j!=0) { // While both lists are nonempty do
            if (a[i] <= a[j]) { // Find the smaller key.
                link[k] = i; k = i; i = link[i]; // Add a new key to the list.
            }
            else {
                link[k] = j; k = j; j = link[j];
            }
        }
        if (i == 0) link[k] = j;
        else link[k] = i;
        return(link[0]);
    }


}
