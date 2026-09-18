package com.rpam.rd.autotasks;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CatchEmAll {

    // You may set another exception in this field;
    static Exception exception = new FileNotFoundException();

    public static void riskyMethod() throws Exception {
        throw exception;
    }

    public static void main(String[] args) throws Exception {

        try {
            riskyMethod();
        }
        catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("Resource is missing", ex);
        }
        catch (IOException ex) {
            throw new IllegalArgumentException("Resource error", ex);
        }
        catch (ArithmeticException | NumberFormatException ex) {
            System.err.println(ex.getMessage());
        }
    }
}