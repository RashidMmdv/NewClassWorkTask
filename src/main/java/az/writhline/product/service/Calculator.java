package az.writhline.product.service;

public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }
    public int divide(int a, int b) {
        if (b == 0){
            throw new IllegalArgumentException("Divide by zero is prohibited");
        }

        return a / b;
    }

}
