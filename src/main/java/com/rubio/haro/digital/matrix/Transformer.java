/*
 * Linearity
 */
package com.rubio.haro.digital.matrix;

import java.util.Objects;

/**
 * @objetive: transform matrix into Gauss matrix or Gauss-Jordan matrix
 * @author rodrigo_rubio
 */
public class Transformer {

    resultset r;

    public resultset getDeterminant(Matrix m) {
        System.out.println("Starting Determinant...");
        r = new resultset();
        r = getGaussMatrix(m);
        if (!r.hasError()) {
            m.logMatrix();
            m = r.getResult();
            Double values[] = new Double[m.getRows()];
            Double Determinant = 1.0;

            if (m.getCols() == m.getRows()) {
                System.out.println("Is a nxn matrix");
                for (int i = 0; i < m.getRows(); i++) {
                    values[i] = m.getValue(i, i);
                    Determinant *= values[i];
                    System.out.print(values[i]);
                }
                System.out.println(" ");
                System.out.println("determinant:" + Determinant);
                Double[][] det = new Double[1][1];
                det[0][0] = Determinant;
                r.setMatrix(new Matrix(det));
            } else {
                r.addError("We can't get the determinant with GaussJordan because is not nxn");
            }
        }
        return r;
    }

    public resultset getGaussMatrixWithElemental(Matrix m, Matrix elemental) {
        System.out.println("Starting Gauss...");
        r = new resultset();
        int col = 1;

        try {
            if (m.getRows() != 0) {
                for (int i = 1; i < m.getRows(); i++) {
                    for (int j = 0; j < col; j++) {
                        r = makeZerosWithElemental(j, i, m, elemental);
                        m = r.getResult();
                        elemental = r.getElementalMatrix();

                        final String ANSI_YELLOW = "\u001B[33m";
                        System.out.println(ANSI_YELLOW + "------- J:" + j + " I:" + i);
                        m.logMatrix();
                        System.out.println("Elemental" + "------- J:" + j + " I:" + i);
                        elemental.logMatrix();
                    }
                    col++;
                }
                r.setMatrix(m);
            } else {
                System.out.println("Error index");
                r.addError("array index out of bounds");
            }
        } catch (Exception e) {
            System.out.println("Other error:" + e.getMessage());
            r.addError(e.getMessage());
        }

        return r;
    }

    public resultset getGaussMatrix(Matrix m) {
        System.out.println("Starting Gauss...");
        r = new resultset();
        int col = 1;

        try {
            if (m.getRows() != 0) {
                for (int i = 1; i < m.getRows(); i++) {
                    for (int j = 0; j < col; j++) {
                        m.setMatrix(makeZeros(j, i, m));
                        final String ANSI_YELLOW = "\u001B[33m";
                        System.out.println(ANSI_YELLOW + "------- J:" + j + " I:" + i);
                        m.logMatrix();
                    }
                    col++;
                }
                r.setMatrix(m);
            } else {
                System.out.println("Error index");
                r.addError("array index out of bounds");
            }
        } catch (Exception e) {
            System.out.println("Other error:" + e.getMessage());
            r.addError(e.getMessage());
        }

        return r;
    }

    /**
     * Transform matrix into Gauss-Jordan matrix. Author: rodrigo_rubio
     *
     * @param m you just send the matrix! this use the Gauss Method 2 times
     * @return we return a resultset Object. This have a error-message and the
     * result atributes.
     */
    public resultset getGaussJordanMatrix(Matrix m) {
        r = new resultset();
        int col = 1;

        r = getGaussMatrix(m);
        if (!r.hasError()) {

            Matrix gaussMatrix = r.getResult();
            r = new resultset();
            try {
                if (gaussMatrix.getRows() != 0) {
                    System.out.println("Starting Gauss-Jordan...");
                    for (int i = (gaussMatrix.getRows() - 2); i >= 0; i--) {
                        int cont = 0;
                        for (int j = (gaussMatrix.getCols() - 2); cont != col; j--) {
                            gaussMatrix.setMatrix(makeZeros(j, i, gaussMatrix));

                            System.out.println("------- J:" + j + " I:" + i);
                            final String ANSI_YELLOW = "\u001B[33m";
                            System.out.println(ANSI_YELLOW + "------- J:" + j + " I:" + i);
                            gaussMatrix.logMatrix();
                            cont++;
                        }
                        col++;
                    }
                    r.setMatrix(gaussMatrix);
                } else {
                    r.addError("array index out of bounds");
                }
            } catch (Exception e) {
                System.out.println(e);
                r.addError(e.getMessage());
            }

        } else {

        }

        return r;
    }

    public Matrix getIdentity(int rows, int cols) {

        System.out.println("getting identity...");
        Matrix matrix = new Matrix(new Double[rows][cols]);
        int position = 0;
        for (int i = 0; i < rows; i++) {
            for (int e = 0; e < cols; e++) {
                if (e == position) {
                    matrix.setValue(i, e, 1.0);
                } else {
                    matrix.setValue(i, e, 0.0);
                }
            }
            position++;
        }
        return matrix;
    }

    /**
     * Transform matrix into Gauss-Jordan matrix. Author: rodrigo_rubio
     *
     * @param m you just send the matrix! this use the Gauss Method 2 times
     * @return we return a resultset Object. This have a error-message and the
     * result atributes.
     */
    public resultset getInverse(Matrix m) {
        Matrix elemental = getIdentity(m.getRows(), m.getCols());
        elemental.logMatrix();

        r = new resultset();
        int col = 1;

        r = getGaussMatrixWithElemental(m, elemental);
        if (!r.hasError()) {
            System.out.println("results from gauss:");
            System.out.println("---original----");
            Matrix gaussMatrix = r.getResult();
            gaussMatrix.logMatrix();
            System.out.println("---elemental----");
            elemental = r.getElementalMatrix();
            elemental.logMatrix();
            r = new resultset();
            try {

                if (gaussMatrix.getRows() != 0) {
                    System.out.println("Starting Gauss-Jordan...");

                    for (int i = (gaussMatrix.getRows() - 2); i >= 0; i--) {
                        int cont = 0;
                        for (int j = (gaussMatrix.getCols() - 1); cont != col; j--) {

                            r = makeZerosWithElemental(j, i, gaussMatrix, elemental);
                            gaussMatrix = r.getResult();
                            elemental = r.getElementalMatrix();

                            final String ANSI_YELLOW = "\u001B[33m";
                            System.out.println(ANSI_YELLOW + "------- J:" + j + " I:" + i);
                            gaussMatrix.logMatrix();
                            System.out.println("elemental" + "------- J:" + j + " I:" + i);
                            elemental.logMatrix();
                            cont++;
                        }
                        col++;
                    }
                    r.setMatrix(gaussMatrix);
                } else {
                    r.addError("array index out of bounds");
                }
            } catch (Exception e) {
                System.out.println(e);
                r.addError(e.getMessage());
            }

        }

        return r;
    }

    public resultset getTransposeMatrix(Matrix mat) {
        r = new resultset();
        System.out.println("starting transpose matrix..");

        try {
            int rows = mat.getRows();
            int cols = mat.getRows();

            Matrix d = new Matrix(new Double[rows][cols]);

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    d.setValue(j, i, mat.getValue(i, j));
                    System.out.println("t[" + j + "][" + i + "] = m [" + i + "][" + j + "]");
                    System.out.println(d.getValue(j, i) + " = " + d.getValue(i, j));
                }
            }
            System.out.println("--------------");
            d.logMatrix();
            System.out.println("--------------");
            mat.logMatrix();
            r.setMatrix(d);
        } catch (Exception e) {
            System.out.println(e);
            r.addError(e.getMessage());
        }

        return r;
    }

    /**
     * Make Zero for matrix. Author: anonymous
     *
     * @param pivot, mat, destinationRow
     * @return Return a Double with the zeros changes.
     */
    private Double[][] makeZeros(int pivot, int destinationRow, Matrix mat) {
        Double[] auxiliarRow1 = new Double[mat.getCols()];
        Double[] auxiliarRow2 = new Double[mat.getCols()];
        int columnZero = pivot; //Normally, the Pivot row is the same number of the column we want to make zero.
        Double[][] m = mat.getMatrix();
        if (Objects.equals(m[pivot][columnZero], m[destinationRow][columnZero])) { //If they are the same number
            for (int i = 0; i < mat.getCols(); i++) {
                auxiliarRow1[i] = m[pivot][i] * -1;
                m[destinationRow][i] = auxiliarRow1[i] + m[destinationRow][i]; //-R1+R2 -> DestinyRow
            }
        } else if ((m[pivot][columnZero] > 0 && m[destinationRow][columnZero] > 0)
                || (m[pivot][columnZero] < 0 && m[destinationRow][columnZero] < 0)) { //If they have the same sign
            Double multiplier1 = m[destinationRow][columnZero] * -1;
            Double multiplier2 = m[pivot][columnZero];
            for (int i = 0; i < mat.getCols(); i++) {
                auxiliarRow1[i] = multiplier1 * m[pivot][i];
                auxiliarRow2[i] = multiplier2 * m[destinationRow][i];
                m[destinationRow][i] = auxiliarRow1[i] + auxiliarRow2[i]; // -(multiplier)R1+(multiplier)R2 -> DestinyRow 
            }
        } else { //They have different signs
            Double multiplier1 = m[destinationRow][columnZero];
            Double multiplier2 = m[pivot][columnZero];
            if (m[pivot][columnZero] < 0) {//Changing the negative multiplier to positive
                multiplier2 = multiplier2 * -1;
            } else {
                multiplier1 = multiplier1 * -1;
            }
            for (int i = 0; i < mat.getCols(); i++) {
                auxiliarRow1[i] = multiplier1 * m[pivot][i];
                auxiliarRow2[i] = multiplier2 * m[destinationRow][i];
                m[destinationRow][i] = auxiliarRow1[i] + auxiliarRow2[i]; // (multiplier)R1+(multiplier)R2 -> DestinyRow 
            }
        }

        return m;
    }

    /**
     * Make Zero for matrix. Author: rubio rodrigo
     *
     * @param pivot, mat, destinationRow
     * @return Return a Double with the zeros changes.
     */
    private resultset makeZerosWithElemental(int pivot, int destinationRow, Matrix mat, Matrix elemental) {

        Double[] auxiliarRow1 = new Double[mat.getCols()];
        Double[] auxiliarRow2 = new Double[mat.getCols()];

        Double[] e_auxiliarRow1 = new Double[mat.getCols()];
        Double[] e_auxiliarRow2 = new Double[mat.getCols()];

        int columnZero = pivot; //Normally, the Pivot row is the same number of the column we want to make zero.
        Double[][] m = mat.getMatrix();
        Double[][] n = elemental.getMatrix();
        if (Objects.equals(m[pivot][columnZero], m[destinationRow][columnZero])) { //If they are the same number
            for (int i = 0; i < mat.getCols(); i++) {
                auxiliarRow1[i] = m[pivot][i] * -1;
                e_auxiliarRow1[i] = n[pivot][i] * -1;

                m[destinationRow][i] = auxiliarRow1[i] + m[destinationRow][i]; //-R1+R2 -> DestinyRow
                n[destinationRow][i] = e_auxiliarRow1[i] + n[destinationRow][i]; //-R1+R2 -> DestinyRow
            }
        } else if ((m[pivot][columnZero] > 0 && m[destinationRow][columnZero] > 0)
                || (m[pivot][columnZero] < 0 && m[destinationRow][columnZero] < 0)) { //If they have the same sign
            Double multiplier1 = m[destinationRow][columnZero] * -1;
            Double multiplier2 = m[pivot][columnZero];
            for (int i = 0; i < mat.getCols(); i++) {
                auxiliarRow1[i] = multiplier1 * m[pivot][i];
                auxiliarRow2[i] = multiplier2 * m[destinationRow][i];

                e_auxiliarRow1[i] = multiplier1 * n[pivot][i];
                e_auxiliarRow2[i] = multiplier2 * n[destinationRow][i];

                m[destinationRow][i] = auxiliarRow1[i] + auxiliarRow2[i]; // -(multiplier)R1+(multiplier)R2 -> DestinyRow 
                n[destinationRow][i] = e_auxiliarRow1[i] + e_auxiliarRow2[i]; // -(multiplier)R1+(multiplier)R2 -> DestinyRow 
            }
        } else { //They have different signs
            Double multiplier1 = m[destinationRow][columnZero];
            Double multiplier2 = m[pivot][columnZero];
            if (m[pivot][columnZero] < 0) {//Changing the negative multiplier to positive
                multiplier2 = multiplier2 * -1;
            } else {
                multiplier1 = multiplier1 * -1;
            }
            for (int i = 0; i < mat.getCols(); i++) {
                auxiliarRow1[i] = multiplier1 * m[pivot][i];
                auxiliarRow2[i] = multiplier2 * m[destinationRow][i];

                e_auxiliarRow1[i] = multiplier1 * n[pivot][i];
                e_auxiliarRow2[i] = multiplier2 * n[destinationRow][i];

                m[destinationRow][i] = auxiliarRow1[i] + auxiliarRow2[i]; // (multiplier)R1+(multiplier)R2 -> DestinyRow 
                n[destinationRow][i] = e_auxiliarRow1[i] + e_auxiliarRow2[i]; // (multiplier)R1+(multiplier)R2 -> DestinyRow 
            }
        }
        mat.setMatrix(m);
        r.setMatrix(mat);
        elemental.setMatrix(n);
        r.setElementalMatrix(elemental);
        return r;
    }
}
