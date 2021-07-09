import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MultistageGraph_Main {
    static int nV, nE; //정점 수, 간선 수
    static int[][] myGraph; //[출발점][도착점][가중치] 정점 및 간선 배열


    public static void main(String[] args) throws IOException {
        @SuppressWarnings("resource")
        Scanner scan = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("정점의 수: ");
        nV = scan.nextInt();

        System.out.print("간선의 수: ");
        nE = scan.nextInt();

        int myGraph[][] =new int[nE][3];


        for (int i = 0; i < nE; i++) { //myGraph 입력받기

            System.out.print((i+1) + "번째 간선 : ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            myGraph[i][0] = Integer.parseInt(st.nextToken());
            myGraph[i][1] = Integer.parseInt(st.nextToken());
            myGraph[i][2] = Integer.parseInt(st.nextToken()); //weight
        }

        MultistageGraph x=new MultistageGraph(myGraph,nV,nE);
        x.forwardMethod(); //최단경로 찾기 실행
    }
}