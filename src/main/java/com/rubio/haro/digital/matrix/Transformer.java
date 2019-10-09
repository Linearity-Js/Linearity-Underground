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
     * @param m you just send the matrix! this use the Gauss Method 2 times
     * @return we return a resultset Object. This have a error-message and the result atributes.
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

    /**
     * Make Zero for matrix. Author: anonymous
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
}
