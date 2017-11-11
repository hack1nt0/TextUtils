package jni.cuda;

/**
 * Created by dy on 17-10-25.
 */
public class HelloWorld {
    static {
        System.loadLibrary("hello");
    }

    private native void hello();

    // Test Driver
    public static void main(String[] args) {
        new HelloWorld().hello();
    }
}
