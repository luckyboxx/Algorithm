import java.util.Scanner;	// 표준 입력 클래스
public class Select_Sort {
    public static void main(String [] args) {
        int n, temp;	// 정수의 개수 n과 임시 변수 temp 선언
        Scanner scan = new Scanner(System.in);	// 표준 입력 클래스 생성
        System.out.print("n 값 입력: ");
        n = scan.nextInt();		// n 값 입력
        int[] num = new int[n];	//길이가 n인 배열 num 생성
        System.out.print(n + "개의 정수 입력: ");
        for(int i = 0; i < num.length; i++)	// 배열 num에 정수 입력
            num[i] = scan.nextInt();
        for(int i = 0; i < num.length; i++) {
            for(int j = i + 1; j < num.length; j++) {
                // num[i - 1]을 제외하고 num[i]가 최솟값이 되도록 반복 수행
                if(num[i] > num[j]) {	//num[i]가 num[j]보다 크면 둘의 위치를 바꿈
                    temp = num[i];
                    num[i] = num[j];
                    num[j] = temp;
                }
            }
        }
        System.out.print("정렬 결과: ");
        for(int i = 0; i < num.length; i++)		// num 출력
            System.out.print(num[i] + " ");
    }
}
