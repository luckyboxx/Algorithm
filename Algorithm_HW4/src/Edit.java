public class Edit {
    String text[]; // 최적 편집 순서로 편집되는 중간 문자열이 저장되는 배열

    // 정수로 이루어진 2차원 배열을 출력하는 함수
    public void IntArrayPrint(int[][] arr, int row, int col) {
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++)
                System.out.print(arr[i][j]+" ");
            System.out.println("");
        }
        System.out.println(" ");
    }

    // string 타입으로 이루어진 2차원 배열을 출력하는 함수
    public void StringArrayPrint(char[][] arr, int row, int col) {
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++)
                System.out.print(arr[i][j]+" ");
            System.out.println("");
        }
        System.out.println(" ");
    }

    // 배열 속 원소들을 합쳐서 하나의 문자열로 출력해주는 함수
    public void array2string(String[] x) {
        for(int i = 0; i < x.length; i++)
            System.out.print(x[i]);
    }

    // 최적의 편집 비용과 치적의 연산을 구하는 함수
    public void char_edit(int cost[][], char operation[][], String[] X, String[] Y, int leng1, int leng2) {

        // 비용 정의
        int cost_I = 1; // 삽입(I)
        int cost_D = 1; // 삭제(D)
        int cost_C = 2; // 교체(C)

        int I, D, C = 0; // 각 원소들의 삽입, 삭제, 교체 연산을 할 때 비용 넣는 변수 초기화

        // 행이 0 또는 열이 0일 때 각 원소에 해당하는 비용과 최적의 연산을 cost, operation 배열에 저장
        for(int row = 0; row < leng1 + 1; row++) {
            for(int col = 0; col < leng2 + 1; col++) {
                if(row == 0 && col == 0) {
                    cost[row][col] = 0;
                    operation[0][0] = '-';
                }else if(row == 0 && col != 0) {
                    cost[row][col] = cost[0][col - 1] + cost_I;
                    operation[0][col] = 'I';
                }else if(row != 0 && col == 0) {
                    cost[row][col] = cost[row - 1][col] + cost_D;
                    operation[row][0] = 'D';
                }
            }
        }
        // 행이 1 이상 또는 열이 1이상일 때 각 원소에 해당하는 비용과 최적의 연산을 cost, operation 배열에 저장
        // 비용이 들어있는 cost함수를 2중 for문으로 돌면서 각 원소의 I, D, C 연산을 했을 때 가장 작은 비용과 그 때의 연산을 cost, operation 배열에 저장
        for(int row = 1; row < leng1 + 1; row++) {
            for(int col = 1; col < leng2 + 1; col++) {

                // 교체의 경우 원소가 같을 때와 아닐 때를 나눠서 각 연산을 할 때 비용 계산
                // X[i]와 Y[j]가 같다면 교체 비용은 0
                if(X[row - 1].equals(Y[col - 1])) { // 같을 경우
                    I = cost[row][col - 1] + cost_I;
                    D = cost[row - 1][col] + cost_D;
                    C = cost[row - 1][col - 1];
                }
                else { // 다를 경우
                    I = cost[row][col - 1] + cost_I;
                    D = cost[row - 1][col] + cost_D;
                    C = cost[row - 1][col - 1] + cost_C;
                }
                // arr배열에 D, I, C 연산을 했을 때 비용 저장
                int [] arr = new int[] {D, I, C};
                // I, D, C 연산 중 가장 비용이 적은 연산과 비용을 저장
                for(int k = 0; k < 3; k++){
                    int min = arr[0];
                    if(arr[1] < min) { // I 연산 비용이 가장 작을 때
                        cost[row][col] = I;
                        operation[row][col] = 'D';
                        break;
                    }
                    if(arr[2] < min) { // C 연산 비용이 가장 작을 때
                        cost[row][col] = C;
                        operation[row][col] = 'C';
                        break;
                    }
                    // D 연산 비용이 가장 작을 때
                    cost[row][col] = D;
                    operation[row][col] = 'I';
                }
            }
        }
    }

    // 최적의 연산들이 담겨있는 operation 배열에서 최종 목적지에서 역추적하여 최적의 연산 순서를 opt 2차원 배열에 저장하는 재귀 함수
    public void optimal_seq(char operation[][], char opt[][], int leng1, int leng2){
        int x = leng1, y = leng2;
        // operation의 가장 마지막 원소부터 되추적하기 위해서 재귀 함수 사용
        switch(operation[x][y]) {
            case 'I':
                optimal_seq(operation, opt, x, y - 1); // 삽입 연산 이전은 x, y-1
                opt[x][y] = 'I';
                System.out.print("I" + "<" + "y" + y + "> ");
                break;

            case 'D':
                optimal_seq(operation, opt, x - 1, y); // 삭제 연산 이전은 x-1, y
                opt[x][y] = 'D';
                System.out.print("D" + "<" + "x" + x + "> ");
                break;

            case 'C':
                optimal_seq(operation, opt, x - 1, y - 1); // 교체 연산 이전은 x-1, y-1
                opt[x][y] = 'C';
                System.out.print("C" + "<" + "x" + x + "," + "y" + y + "> ");
                break;
        }
    }

    // 최적의 편집 순서열이 담긴 opt를 적용하여 문자열 변환하여 배열 temp에 저장하는 재귀 함수
    public void char_change(char opt[][], String[] X, String[] Y, int i, int j, String[] temp) {
        String copy[]; // 변환해주기 전에 원래의 문자열을 담는 배열
        text = temp; // 배열 temp를 받아와서 text에 저장하여 text로 처리
        int before = 0; // text가 변환되기 전에 길이가 담기는 변수
        int index = 0; // 삽입, 삭제 연산에서 사용되는 인덱스를 나타내는 변수
        int x = 0; // 삭제 연산에서 사용되는 인덱스를 나타내는 변수
        // 각 연산마다의 연산하는 과정
        switch(opt[i][j]) {
            case 'I':
                // 최적 연산 순서대로 하기 위해 이전의 연산을 하도록하는 재귀함수
                char_change(opt, X, Y, i, j - 1, text); // 삽입이므로 이전의 위치는 (i, j-1)
                copy = text.clone(); // 삽입되기 전 문자를 copy에 복사
                before = text.length; // 변환 되기 전 문자 길이를 before에 저장
                // 삽입 연산이므로 변환 후 배열의 크기는 1이 증가됨.
                text = new String[before + 1];
                // Y 문자열의 j-1 번째 문자를 X 문자열의 j-1번째에 삽입
                text[j - 1] = Y[j - 1];
                // j-1번째를 제외한 text에 변환되기 전의 문자를 차례대로 넣어준다.
                for(int k = 0; k < text.length; k++) {
                    if(k == j - 1) // j-1번째는 넘기고 다음 for문으로 들어감.
                        continue;
                    text[k] = copy[index];
                    index++;
                // k는 변환된 문자열의 인덱스이고, index는 변환되기 전 문자열의 인덱스를 의미함.
                }
                // 삽입 연산 결과 출력
                System.out.print("I" + "<y" + j + "> :");
                array2string(text); // 배열로 담긴 문자열들을 하나의 문자로 만듦.
                System.out.println("");
                break;

            case 'D':
                // 최적 연산 순서대로 하기 위해 이전의 연산을 하도록하는 재귀함수
                char_change(opt, X, Y, i - 1, j, text); // 삭제이므로 이전의 위치는 (i-1, j)
                // 변환되기 전 문자를 복사
                copy = text.clone();
                // text 문자열에서 제일 앞 부분부터 삭제할 문자 일치 여부 확인
                for(int k = 0; k < text.length; k++) {
                    if(text[k] == X[i - 1]) // X 문자열의 i-1번째와 text 의 문자열이 같을 때
                        index = k; // 그때의 인덱스 값을 index에 저장
                }
                // 삭제 연산이기 때문에 배열의 길이가 1 감소되므로 text 배열 새로 정의
                text = new String[text.length - 1];
                // 새로 정의된 text에 변환된 문자열 저장
                // text의 원소가삭제될 원소의 인덱스일때 넘어가고 다음 for문으로 돌아감.
                for(int k = 0; k < text.length + 1; k++) {
                    if(k == index)
                        continue;
                    // 삭제될 원소 제외하고 변환되기 전 문자를 text에 저장
                    text[x] = copy[k];
                    x++;  // text의 인덱스를 나타내는 변수
                }
                // 삭제 연산 결과 출력
                System.out.print("D" + "<x" + i + "> : ");
                array2string(text);
                System.out.println("");
                break;

            case 'C':
                // 최적 연산 순서대로 하기 위해 이전의 연산을 하도록하는 재귀함수
                char_change(opt, X, Y, i - 1, j - 1, text); // 교체이므로 이전의 위치 (i-1, j-1)
                // X[i-1] = Y[j-1] 이면 편집 필요없이 출력
                if(X[i - 1].equals(Y[j - 1])) {
                    // 같을 경우에 바로 교체 연산 결과 출력
                    System.out.print("C" + "<x" + i + ", y" + j + "> : ");
                    array2string(text);
                    System.out.println("");
                } else { // 같지 않을 경우 교체 연산
                    // 변환되기 전 문자열 copy에 복사
                    copy = text.clone();
                    String tmp = X[i - 1]; // 교체될 원소를 tmp에 저장
                    // text의 원소 중 tmp와 같은 원소의 인덱스 저장
                    for(int k = 0; k < text.length; k++) {
                        if(text[k] == tmp) {
                            text[k] = Y[j - 1];
                            index = k;
                            break;
                        }
                    }
                    // 교체 연산이 저장될 새로운 text 배열 선언
                    text = new String[text.length];
                    // 교체될 원소인 인덱스를 제외하고는 변환되기 전 copy를 넣어준다.
                    for(int k = 0; k < text.length; k++) {
                        if(k == index)
                            continue;
                        text[x] = copy[k];
                        x++;
                    }
                    // 교체 연산 결과 출력
                    System.out.print("C" + "<x" + i + ", y" + j + "> : ");
                    array2string(text);
                    System.out.println("");
                    break;
                }
        }
    }
} // Edit Class 끝