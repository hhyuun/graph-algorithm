package dfs;

import java.util.Scanner;

public class Programmers_2573 {

    static int[][] ice; // 첫 입력받을 빙산 상태
    static int[][] change; // 변화하는 빙산 상태를 저장할 배열
    static int year; // 출력값
    static int n;
    static int m;
    static boolean[][] visited;
    
    int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}}; // 위, 아래, 왼쪽, 오른쪽

    public static void changeIce(int[][] ice){

        // 일단 빙하상태를 그대로 복사해놨음.초기상태
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                change[i][j] = ice[i][j];
            }
        }

        while(true){
            if(noIce(change)){
            year = 0;
            break;
            }

            // 빙하가 두개 이상인지 확인하기
            if(isOverTwo(change)){
                break;
            }

            // 1. 매년 변화하는 빙하 상태를 갱신해서 change에 저장
            for(int i=1; i<n-1; i++){
                for(int j=1; j<m-1; j++){
                    if(ice[i][j] == 0){ // 애초부터 0인 빙산은 계속 0이니깐
                        change[i][j] = 0;
                        continue;
                    }
                    // 나머지 빙산 계산
                    int count = 0;

                    if(ice[i-1][j] == 0) count++;
                    if(ice[i+1][j] == 0) count++;
                    if(ice[i][j-1] == 0) count++;
                    if(ice[i][j+1] == 0) count++;

                    change[i][j] = ice[i][j] - count;

                    if(change[i][j]<0){
                        change[i][j] = 0;
                    }

                }
            }

            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    ice[i][j] = change[i][j];
                }
            }

            year++;
        }
        
    }

    // 빙산이 다 녹았음
    public static boolean noIce(int[][] change){

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(change[i][j] != 0){
                    return false;
                }
            }
        }
        return true;
    }

    // 빙하가 두개 이상으로 쪼개졌는지 확인하는 로직, 2개 이상으로 쪼개졌으면 true 반환
    public static boolean isOverTwo(int[][] change){

        // 빙산이 2개 이상인지 확인
        // 전체 배열을 돌면서 아직 방문하지 않은 빙산(0이 아닌 값)을 발견하면 DFS를 시작해서 그 덩어리를 한 번에 탐색하고, 덩어리 수를 증가

        int iceCount = 0;
        visited = new boolean[n][m];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(change[i][j] > 0 && !visited[i][j]){
                    dfs(i, j, change);
                    iceCount ++;
                }  
            }
        }

        if(iceCount>=2){
            return true;
        }
        
        return false;
    }

    public static void dfs(int x, int y, int[][] change){
        visited[x][y] = true;
        
        if(x-1>=0 && change[x-1][y] > 0 && !visited[x-1][y]){
            dfs(x-1, y, change);
        }
        if(x+1 < n && change[x+1][y] > 0 && !visited[x+1][y]){
            dfs(x+1, y, change);
        }
        if(y-1>=0 && change[x][y-1] > 0 && !visited[x][y-1]){
            dfs(x, y-1, change);
        }
        if(y+1 < m && change[x][y+1] > 0 && !visited[x][y+1]){
            dfs(x, y+1, change);
        }
    }
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();

        ice = new int[n][m];
        change = new int[n][m];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                ice[i][j] = sc.nextInt();
            }
        }

        year = 0; // 초기 상태

        changeIce(ice);

        System.out.println(year);

        sc.close();
    }
}