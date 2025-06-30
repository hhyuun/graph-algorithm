package dfs;

import java.util.*;

class Solution {
    
    static boolean[] visited;
    static ArrayList<String> result = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        // 일반적인 dfs 문제에 사전순 처리가 추가된 문제구나
        
        visited = new boolean[tickets.length];
        
        dfs(0, "ICN", "ICN", tickets);
        
        Collections.sort(result);
        
        // 정답 출력
        String[] answer = result.get(0).split(" ");
        return answer;
    }
    
    public static void dfs(int idx, String start, String route, String[][] tickets){
        
        // 탈출 조건
        if (idx == tickets.length) {
            result.add(route);
            return;
        }

        // 2. 백트래킹 알고리즘 사용
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i][0].equals(start) && !visited[i]) {
                visited[i] = true;
                dfs(idx + 1, tickets[i][1], route + " " + tickets[i][1], tickets);
                visited[i] = false;
            }
        }
 
    }
}