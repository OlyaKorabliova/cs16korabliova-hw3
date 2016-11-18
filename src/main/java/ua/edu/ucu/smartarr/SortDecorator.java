package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyComparator;

import java.util.Arrays;

// Sorts elements using MyComparator to compare them
public class SortDecorator extends SmartArrayDecorator{

    MyComparator comparator;

    public SortDecorator(SmartArray smartArray, MyComparator comparator) {
        super(smartArray);
        this.comparator = comparator;
    }

    @Override
    public Object[] toArray() {
        Object[] smArray = smartArray.toArray();
        Arrays.sort(smArray, comparator);
        return smArray;
    }

    @Override
    public String operationDescription() {
        return "Sort Decorator";
    }

    @Override
    public int size() {
        return toArray().length;
    }
}
