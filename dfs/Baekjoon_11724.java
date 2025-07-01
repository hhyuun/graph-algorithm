package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon_11724{

    static int N, M;
    static boolean[][] graph;
    static boolean[] visited;
    static int answer; // 연결 요소의 갯수

    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정점 
        M = Integer.parseInt(st.nextToken()); // 간선

        graph = new boolean[N+1][N+1];
        visited = new boolean[N+1];

        int x, y;
        for(int i = 1; i < M+1; i++){
            StringTokenizer s = new StringTokenizer(br.readLine());
            x = Integer.parseInt(s.nextToken());
            y = Integer.parseInt(s.nextToken());

            graph[x][y] = true;
            graph[y][x] = true;
        }

        for(int i = 1; i < N+1; i++){
            if(visited[i] == false){
                dfs(i);
                answer++;
            }
        }

        System.out.println(answer);

    }

    public static void dfs(int k){
        visited[k] = true;
        for(int i=1; i<N+1; i++){
            if(visited[i] == false && graph[k][i] == true){
                dfs(i);
            }
        }
    }
}