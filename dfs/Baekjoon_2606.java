package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_2606 {

    static int N, M;
    static boolean[][] graph;
    static boolean[] visited;
    static int answer;

    
    public static void main(String[] args) throws IOException {

        // 입력받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine()); // 컴퓨터대수
        M = Integer.parseInt(br.readLine()); // 연결

        // connect 입력
        graph = new boolean[N+1][N+1];

        // visited 배열로써 재방문하는 경우를 방지할 수 있음
        visited = new boolean[N+1];

        int x, y;
        for (int i=0; i< M; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            x = Integer.parseInt(tokenizer.nextToken());
            y = Integer.parseInt(tokenizer.nextToken());
            graph[x][y] = true;
            graph[y][x] = true;
        }

        dfs(1);

        // 출력
        System.out.println(answer-1);
    }

    public static void dfs(int k){
        visited[k] = true;
        answer++;

        for(int i=1; i<N+1; i++){

            if(visited[i] == false && graph[k][i] == true){
                dfs(i);
            }
        }
    }
}
