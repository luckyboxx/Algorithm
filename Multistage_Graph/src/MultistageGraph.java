public class MultistageGraph {
    static final int INF = 9999; //무한대
    int cost[]; //정점의 cost 배열
    int c[][]; //간선 <i,j>의 비용
    int d[]; //최소비용으로 갈수 있는 정점

    int edges[][]; //간선 배열
    int adjMatrix[][]; //인접 행렬
    int sV, lV; //처음, 마지막 정점
    int numE, numS; //간선, 스테이지 수
    int[] vLv; //정점의 스테이지 값 배열
    int path[]; //경로

    public MultistageGraph(int[][] v,int nV, int nE) {
        edges=v; //인자로 받은 배열 복사
        sV=1; //시작정점=1
        lV=nV; //정점의 갯수=마지막 정점 숫자
        numE=nE; // 인자로 받은 간선 수 복사


        vLv=new int[nV+1];
        cost=new int[nV+1];
        d= new int[nV+1];
        adjMatrix= new int[nV+1][nV+1];
        cost[lV]=0; //마지막 정점 cost=0

        //인접행렬 생성/출력-------------------------------------------------
        for (int i = 0; i < nV + 1; i++) {
            for (int j = 0; j < nV + 1; j++) {
                adjMatrix[i][j] = INF;
            }
        } //모두 무한대로 초기화
        for(int i=0; i<nE;i++) {
            if (adjMatrix[edges[i][0]][edges[i][1]] > edges[i][2]) {
                adjMatrix[edges[i][0]][edges[i][1]] = edges[i][2];
            }
        } //값이 들어갈 부분 변경

        for (int i = 0; i < nV + 1; i++) {
            for (int j = 0; j < nV + 1; j++) {
                System.out.print(adjMatrix[i][j] + "\t");
            }
            System.out.println("");
        } //인접행렬 출력
        //----------------------------------------------------------

        //스테이지 값 (verStage) 설정------------------------------------------
        vLv[sV]=1;
        for(int i=0; i<numE;i++) {
            vLv[edges[i][1]]=vLv[edges[i][0]]+1;
        } //시작정점 스테이지 +1 = 도착정점 스테이지
        numS=vLv[lV];
        //---------------------------------------------------------
    }

    public void forwardMethod() {
        for(int i=lV-1; i>=1; i--) {
            if(vLv[i]==numS-1) { //마지막 직전 정점
                cost[i]=adjMatrix[i][lV];
                d[i]=lV; //다음 정점으로 가는 경로가 하나 존재
            }
            else {
                int min=INF; //최소값에 우선 무한대 삽입
                for(int j=0; j<numE; j++) {
                    if(edges[j][0]==i) {
                        if(adjMatrix[i][edges[j][1]]+cost[edges[j][1]]<min) {
                            min=adjMatrix[i][edges[j][1]]+cost[edges[j][1]]; //최솟값 최신화
                            d[i]=edges[j][1]; //d배열에 삽입
                        }
                    }
                }
                cost[i]=min; //최소비용을 cost배열에 저장
            }
        }

        for(int i=1;i<lV;i++) //cost와 d 출력
            System.out.println("<정점 " + i + "> cost : " + cost[i] + "  d : " + d[i]);

        path=new int[numS+1]; //path배열 생성
        path[1]=sV;
        path[numS]=lV;
        for(int i=2; i<numS; i++)
            path[i]=d[path[i-1]]; //d에 저장된 값을 이용해서 p 생성

        for(int i=1; i<numS;i++) //최소비용 출력
            System.out.print(path[i]+"->");
        System.out.println(path[numS]);
    }
}
