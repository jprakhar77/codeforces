package library;

//For square non-singular matrices
public class GaussianElimination {
    int n;
    double[][] mat;

    boolean isSingular;

    public GaussianElimination(int n, double[][] mat) {
        this.n = n;
        this.mat = new double[n][n + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n; j++) {
                this.mat[i][j] = mat[i][j];
            }
        }
    }

    double[] compute() {
        rowEchelonForm();

        if (isSingular) {
            return null;
        }

        return backSubstitution();
    }

    double[][] rowEchelonForm() {
        for (int i = 0; i < n; i++) {
            swapForNumericalStability(i);

            if (mat[i][i] == 0) {
                isSingular = true;
                return mat;
            }

            for (int j = i + 1; j < n; j++) {
                double factor = mat[j][i] / mat[i][i];

                for (int k = i; k <= n; k++) {
                    mat[j][k] -= factor * mat[i][k];
                }

                mat[j][i] = 0;
            }
        }

        return mat;
    }

    void swapForNumericalStability(int row) {
        double valMax = mat[row][row];
        int indMax = row;
        for (int i = row + 1; i < n; i++) {
            if (mat[i][row] > valMax) {
                valMax = mat[i][row];
                indMax = i;
            }
        }
        if (indMax != row) {
            swapRows(row, indMax);
        }
    }

    void swapRows(int row1, int row2) {
        for (int i = 0; i <= n; i++) {
            double temp = mat[row1][i];
            mat[row1][i] = mat[row2][i];
            mat[row2][i] = temp;
        }
    }

    double[] backSubstitution() {
        double[] x = new double[n];

        x[n - 1] = mat[n - 1][n] / mat[n - 1][n - 1];
        for (int i = n - 2; i >= 0; i--) {
            double val = mat[i][n];
            for (int j = i + 1; j < n; j++) {
                val -= x[j] * mat[i][j];
            }
            x[i] = val / mat[i][i];
        }

        return x;
    }
}