package demo;

import demo.model.Greeting;
import demo.model.Value;

public class TestData {

    public static Value newValue() {
        return new Value(1, "Hello, World!");
    }

    public static Greeting newGreeting() {
        return new Greeting("greeting", newValue());
    }
}
