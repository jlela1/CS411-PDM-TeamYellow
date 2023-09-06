package frontend;

import backend.HelloWorldBackend;
 
public class HelloWorldFrontend {   
public static void main (String[] args) {
    String helloMessage = HelloWorldBackend.getHelloMessage();
    System.out.println(helloMessage);
    }
}
