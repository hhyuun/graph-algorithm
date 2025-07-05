package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon_24480 {

    static int N, M, R;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int[] answer;
    static int order;

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i=1; i<N+1; i++){
            graph[i] = new ArrayList<>();
        }

        visited = new boolean[N+1];
        answer = new int[N+1];

        order = 1;

        for(int i = 1; i < M+1; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            graph[x].add(y);
            graph[y].add(x);
        }

        for(int i = 1; i < N+1; i++){
            Collections.sort(graph[i],Collections.reverseOrder());
        }

        // dfs 호출
        dfs(R);

        // 출력
        for(int i=1; i<N+1; i++){
            System.out.println(answer[i]);
        }  
    }

    public static void dfs(int k){
        visited[k] = true;
        answer[k] = order;
        order++;

        for(int i=0; i<graph[k].size(); i++){
            if(!visited[graph[k].get(i)]){
                dfs((graph[k].get(i)));
            }
        }
    }
    
}
