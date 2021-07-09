import java.util.ArrayList; // ArrayList 클래스 import
import java.util.Scanner; // 입력을 위한 Scanner import

public class Recursion_Algorithm {

    private int data; //데이터가 몇 개 들어갔는지 알기 위한 값
    private float select_runtime; // Recursion_Algorithm 이 가지는 선택 정렬의 평균값
    private float merge_runtime; // 합병 정렬의 평균값
    private float quick_runtime; // 퀵 정렬의 평균값
    public Recursion_Algorithm() { // default 생성자
        this.data = 0;
        this.select_runtime = 0;
        this.merge_runtime = 0;
        this.quick_runtime = 0;
    }
    public Recursion_Algorithm(int data, float select_runtime, float merge_runtime, float quick_runtime) {// 매개변수가 있을 때의 생성자.
        this.data = data;
        this.select_runtime = select_runtime;
        this.merge_runtime = merge_runtime;
        this.quick_runtime = quick_runtime;
    }

    // getter, setter
    public int getData() {return data;}
    public float getSelect_runtime() {return select_runtime;}
    public void setSelect_runtime(float select_runtime) {
        this.select_runtime = select_runtime;
    }
    public float getMerge_runtime() {return merge_runtime;}
    public void setMerge_runtime(float merge_runtime) {
        this.merge_runtime = merge_runtime;
    }
    public float getQuick_runtime() {return quick_runtime;}
    public void setQuick_runtime(float quick_runtime) {
        this.quick_runtime = quick_runtime;
    }
    // main 부
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ArrayList<Recursion_Algorithm> runtime_table = new ArrayList<>();
// 평균값 리스트
        MergeSortClass m; QuickSortClass q; // 합병정렬과 퀵정렬은 재귀를 이용하므로 처음으로 호출하기 위해 객체를 만들어주어야 합니다.
        long start_time = 0, end_time = 0; // 시작하는 시간과 끝나는 시간 초기화.
        int count = 0; // 반복 횟수 체크
        while (true) { // 별도의 break가 없으면 무한 반복합니다
            // 입력은 1000, 5000, 10000, 20000, 50000, 100000. 총 6번 실행.
            count++;

            System.out.print("Input Data Count(input data <= 0 is end): ");
            int arrSize = scan.nextInt();
            int arr[] = new int[arrSize + 2];
            for (int i = 1; i <= arrSize; i++)
                arr[i] = (int) (Math.random() * 99999);
            // 정렬에 쓰이는 공용 데이터. arr[0]은 아무 것도 안들어가고, a[1]부터 a[arrSize] 까지 값이 들어갑니다.
            // a[arrSize+1]에는 Integer.MAXVALUE가 들어갑니다.
            float sum_select = 0, sum_merge = 0, sum_quick = 0;
            //평균값을 구하기 위한 합계
            for (int i = 0; i < 100; i++) { // 10번 반복
                //SelectionSort
                start_time = System.currentTimeMillis();// sort start
                SelectionSortClass.SelectionSort(arr.clone(), arrSize);
//arr.clone()를 쓰는 이유: 그냥 arr을 넣으면 다른 정렬을 할 때 이미 정렬된 값이 들어가므로 arr의 복제인 arr.clone()을 넣어서 arr의 값이 바뀌지않게 하기 위해서 입니다.
                end_time = System.currentTimeMillis(); // sort end
                sum_select += (float) (end_time - start_time);
                // runtime=end-start 값들을 float 형변환해서 sum_select에 더해줍니다..

                //MergeSort
                m = new MergeSortClass(arr.clone(), arrSize);
                start_time = System.currentTimeMillis();
                m.MergeSortCall();
                end_time = System.currentTimeMillis();
                sum_merge += (float) (end_time - start_time);

                //QuickSort
                q = new QuickSortClass(arr.clone(), arrSize);
                start_time = System.currentTimeMillis();
                q.QuickSortCall2();
                end_time = System.currentTimeMillis();
                sum_quick += (float) (end_time - start_time);
            }
            sum_select /= 100;
            sum_merge /= 100;
            sum_quick /= 100;
            // 반복횟수 만큼 나눠서 평균값 구하기
            Recursion_Algorithm runtime_object = new Recursion_Algorithm(arrSize, sum_select, sum_merge, sum_quick); // Recursion_Algorithm 객체를 만들어서 data 개수와 각 정렬의 평균을 넣어줍니다.
            runtime_table.add(runtime_object); // list에 객체 삽입.
            if (count == 6)// 6번쨰 반복이 끝난 후 결과창.
            {
                System.out.println("Data\t\tSelect\t\tMerge\t\tQuick");
                for (int i = 0; i < runtime_table.size(); i++) {
                    System.out.println(runtime_table.get(i).getData() + "\t\t" + runtime_table.get(i).getSelect_runtime()+ "\t\t" + runtime_table.get(i).getMerge_runtime() + "\t\t"+ runtime_table.get(i).getQuick_runtime());
                }
                break;
            }
        }
    }
}
