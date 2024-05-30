import java.util.Scanner;
public class Prog23 {
    public static int matrixChainOrder(int p[], int n) {
        int[][] m = new int[n][n];
        int[][] s = new int[n][n];

        for (int i = 1; i < n; i++) {
            m[i][i] = 0;
        }

        for (int l = 2; l < n; l++) {
            for (int i = 1; i < n - l + 1; i++) {
                int j = i + l - 1;
                m[i][j] = Integer.MAX_VALUE;
                for (int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q;
                        s[i][j] = k;
                    }
                }
            }
        }

        printOrder(s, 1, n - 1);
        System.out.println();

        return m[1][n - 1];
    }

    public static void printOrder(int s[][], int i, int j) {
        if (i == j) {
            System.out.print("A" + i);
        } else {
            System.out.print("(");
            printOrder(s, i, s[i][j]);
            printOrder(s, s[i][j] + 1, j);
            System.out.print(")");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Введите количество матриц: ");
        int n = sc.nextInt();

        int[] p = new int[n + 1];
        System.out.print("Введите размеры матриц: ");
        for (int i = 0; i < n + 1; i++) {
            p[i] = sc.nextInt();
        }

        System.out.println("Минимальное количество умножений: " + matrixChainOrder(p, n + 1));
    }
}
