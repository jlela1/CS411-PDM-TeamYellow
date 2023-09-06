package frontend;

import backend.HelloWorldBackend;
 
public class HelloWorldFrontend {

    public void outputHelloWorld() { //Recieve message from backend, print out to display
        String outputMsg = HelloWorldBackend.getHelloMessage();

        System.out.println(outputMsg);
    }

}
