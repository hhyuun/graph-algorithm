package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Baekjoon_1260 {

    static int N, M, V;
    static boolean[][] graph;
    static boolean[] visited;
    static int[] dfsAnswer;
    static ArrayList<Integer> queue;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 정점
        M = Integer.parseInt(st.nextToken()); // 간선
        V = Integer.parseInt(st.nextToken()); // 시작 노드

        graph = new boolean[N+1][N+1];
        visited = new boolean[N+1];
        dfsAnswer = new int[N+1];

        for(int i=1; i<M+1; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x][y] = true;
            graph[y][x] = true;
        }

        dfs(V);

        // 출력
        for(int i=1; i<dfsCount; i++){
            System.out.print(dfsAnswer[i] + " "); 
        }
        System.out.println();

        bfs();
    }

    static int dfsCount = 1;

    public static void dfs(int k){
        visited[k] = true;
        dfsAnswer[dfsCount] = k;
        dfsCount++;

        for(int i=1; i<N+1; i++){
            if(graph[k][i] == true && visited[i] ==false){
                dfs(i);
            }
        }
    }

    public static void bfs(){
        queue = new ArrayList<>();
        visited = new boolean[N+1];

        queue.add(V);
        visited[V] = true;

        while(!queue.isEmpty()){
            int idx = queue.remove(0);
            System.out.print(idx + " ");

            for(int i=1; i<N+1; i++){
                if(!visited[i] && graph[idx][i]){
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }

    }
}