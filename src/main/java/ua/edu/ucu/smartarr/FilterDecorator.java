package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyPredicate;

import java.util.Arrays;

// Tests every element and removes it if it doesn't satisfy MyPredicate
public class FilterDecorator extends SmartArrayDecorator{

    MyPredicate predicate;

    public FilterDecorator(SmartArray smartArray, MyPredicate predicate) {
        super(smartArray);
        this.predicate = predicate;
    }

    @Override
    public Object[] toArray() {
        Object[] smArray = smartArray.toArray();
        Object[] tmp = new Object[smArray.length];
        int size = 0;
        for (int i = 0; i < smArray.length; ++i) {
            if (predicate.test(smArray[i])) {
                tmp[size] = smArray[i];
                ++size;
            }
        }
        Object[] finalArray = Arrays.copyOf(tmp, size);

        return finalArray;
    }

    @Override
    public String operationDescription() {
        return "Filter Decorator";
    }

    @Override
    public int size() {
        return toArray().length;
    }
}
