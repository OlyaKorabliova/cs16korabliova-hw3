package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyFunction;

import java.util.Arrays;

// Map every element to another object using MyFunction
public class MapDecorator extends SmartArrayDecorator{

    MyFunction function;

    public MapDecorator(SmartArray smartArray, MyFunction function) {
        super(smartArray);
        this.function = function;
    }

    @Override
    public Object[] toArray() {
        Object[] smArray = smartArray.toArray();
        for (int i = 0; i < smArray.length; ++i) {
            smArray[i] = function.apply(smArray[i]);
        }
        return smArray;
    }

    @Override
    public String operationDescription() {
        return "Map Decorator";
    }

    @Override
    public int size() {
        return toArray().length;
    }
}
