/*
 * Linearity
 */
package com.rubio.haro.digital.matrix;

/**
 *
 * @author rodrigo_rubio
 */
public class resultset {

    private int error;
    private String message;
    private Matrix matrix;
    private Matrix aux_matrix;

    public resultset() {
        this.error = 0;
    }

    public boolean hasError() {
        return (error >= 1);
    }

    public void addError(String message) {
        ++error;
        this.message = this.message + error + " " + message + "\n";
    }

    public Matrix getResult() {
        return this.matrix;
    }

    public void setMatrix(Matrix m) {
        this.matrix = m;
    }

    public void setElementalMatrix(Matrix m) {
        this.aux_matrix = m;
    }
    
    public Matrix getElementalMatrix() {
        return aux_matrix;
    }

    public String getMessage() {
        return message;
    }

}
