package com.rubio.haro.digital.linearity.underground;

/*
 * Linearity
 */
import com.rubio.haro.digital.matrix.Matrix;
import com.rubio.haro.digital.matrix.Transformer;
import com.rubio.haro.digital.matrix.resultset;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author rodrigo_rubio
 */
@Path("/undergroundStation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UndergroundStation {

    @GET
    public Response ping() {
        //CASE 3:3X3 MATRIX
        Double[][] c = new Double[3][4];
        Matrix g = new Matrix(c);

        g.setValue(0, 0, 2.0);
        g.setValue(0, 1, -3.0);
        g.setValue(0, 2, 0.0);
        g.setValue(0, 3, 8.0);

        g.setValue(1, 0, 4.0);
        g.setValue(1, 1, -5.0);
        g.setValue(1, 2, 1.0);
        g.setValue(1, 3, 15.0);

        g.setValue(2, 0, 2.0);
        g.setValue(2, 1, 0.0);
        g.setValue(2, 2, 4.0);
        g.setValue(2, 3, 1.0);
        g.logMatrix();

        Transformer t = new Transformer();
        resultset r = t.getGaussMatrix(g);
        Matrix mr;
        if (r.hasError()) {
            System.out.println(r.getMessage());
            return Response
                    .ok("ping")
                    .build();
        } else {
            mr = r.getResult();

            final String ANSI_RESET = "\u001B[0m";
            final String ANSI_YELLOW = "\u001B[33m";

            System.out.println(ANSI_YELLOW + "------------------------------" + ANSI_RESET);
            mr.logMatrix();
            return Response
                    .ok(mr)
                    .build();
        }

    }

    /**
     *
     * @param matrix
     * @return matrix
     */
    @POST
    @Path("/getGauss")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GaussMatrix(Matrix matrix) {
        System.out.println("Starting getter");
        try {
            matrix.logMatrix();
            Transformer t = new Transformer();
            resultset r = t.getGaussMatrix(matrix);
            if (r.hasError()) {
                System.out.println("murio:" + r.getMessage());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(matrix).build();
            } else {
                //r.getResult().logMatrix();
                return Response.status(Response.Status.CREATED).entity(r.getResult()).build();
            }

        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(matrix).build();
        }
        /*
        
        Matrix mr;
        if (r.hasError()) {
            System.out.println("murio:" + r.getMessage());
            Double[][] c = new Double[1][1];
            c[0][0] = 0.0;
            mr = new Matrix(c);
        } else {
            mr = r.getResult();

            final String ANSI_RESET = "\u001B[0m";
            final String ANSI_YELLOW = "\u001B[33m";

            System.out.println(ANSI_YELLOW + "------------------------------" + ANSI_RESET);
            mr.logMatrix();

        }
        return mr.getMatrix();
         */
    }
    
    
    /**
     *
     * @param matrix
     * @return Matrix
     */
    @POST
    @Path("/getGaussJordan")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response GaussJordanMatrix(Matrix matrix) {
        System.out.println("Starting getter");
        try {
            matrix.logMatrix();
            Transformer t = new Transformer();
            resultset r = t.getGaussJordanMatrix(matrix);
            if (r.hasError()) {
                System.out.println("murio:" + r.getMessage());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(matrix).build();
            } else {
                //r.getResult().logMatrix();
                return Response.status(Response.Status.CREATED).entity(r.getResult()).build();
            }

        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
            return Response.status(Response.Status.UNAUTHORIZED).entity(matrix).build();
        }
    }
}
