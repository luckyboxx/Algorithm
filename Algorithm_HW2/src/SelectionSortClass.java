public class SelectionSortClass {
    public static int[] SelectionSort(int a[], int n)
    // Sort the array a[1:n] into nondecreasing order.
    {
        for (int i=1; i<n-2; i++) {
            // arrSize는 공용으로 사용되기 때문에 선택정렬에 쓰일 수 있게 시작 값 및 범위 수정
            int j = i;
            for (int k=i+1; k<n; k++)
                if (a[k]<a[j]) j=k;
            int t = a[i]; a[i] = a[j]; a[j] = t;
        }

        return a;
    }
}
