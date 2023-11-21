package chapter2.item9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Item9 {
    // close() 해야할건 try with resources 를 사용하자.

    static String firstLineOfFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }
    public static void main(String[] args) throws IOException {
        firstLineOfFile("aa/aa");
    }
}
