import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 두 개의 문자열 길이 입력 받음
        System.out.print("Input Two String's lengths : ");
        Scanner length = new Scanner(System.in);
        int n = length.nextInt();
        int m = length.nextInt();

        // 연산 비용을 저장할 2차원 배열 선언
        // 입력받은 배열들의 크기로 행, 열의 수를 정의
        int cost[][] = new int[n+1][m+1];

        // 최적 연산을 저장할 2차원 배열 선언
        // 입력받은 배열들의 크기로 행, 열의 수를 정의
        char operation[][] = new char[n+1][m+1];

        // 최적의 연산만 저장할 2차원 배열 선언
        char opt[][] = new char[n+1][m+1];

        // 두 문자열 입력 받음
        System.out.print("Input Two Strings : ");
        Scanner string = new Scanner(System.in);
        String x = string.next();
        String y = string.next();

        // 입력받은 문자열 배열에 저장
        String[] X, Y;
        X = x.split("");
        Y = y.split("");

        // 문자열 편집 함수들이 있는 Edit 클래스 선언
        Edit ed = new Edit();

        // X->Y 바꿀때 드는 비용과 최적의 연산을 구하는 함수 실행
        ed.char_edit(cost,operation, X, Y, n, m);
        System.out.println(" ");

        // cost table 출력
        System.out.println("-- cost(i,j) --");
        // 정수로 이루어진 2차원 배열을 출력하는 함수 실행
        ed.IntArrayPrint(cost, n+1, m+1);
        System.out.println(" ");

        // edit table 출력
        System.out.println("-- edit table --");
        // string 타입으로 이루어진 2차원 배열을 출력하는 함수 실행
        ed.StringArrayPrint(operation, n+1, m+1);
        System.out.println(" ");

        // 최적의 편집 순서열 출력
        System.out.println("--- optimal edit sequence ---");
        // 최적의 편집 순서열을 출력하는 함수 실행
        ed.optimal_seq(operation, opt, n, m);
        System.out.println(" ");
        System.out.println(" ");

        // 문자열 편집과정 출력
        System.out.println("-- convert X to Y --");
        System.out.print("X :");
        ed.array2string(X);
        System.out.print(", Y : ");
        ed.array2string(Y);
        System.out.println(" ");
        // 문자열을 최적의 편집 순서열로 편집하는 함수 실행
        ed.char_change(opt, X, Y, n, m, X);
        // Scanner 사용 종료
        length.close();
        string.close();
    }
}
