package ua.edu.ucu.smartarr;

import java.util.Arrays;

// Remove duplicates from SmartArray. Use method equals() to compare objects
public class DistinctDecorator extends SmartArrayDecorator{

    public DistinctDecorator(SmartArray smartArray) {
        super(smartArray);
    }

    @Override
    public Object[] toArray() {
        Object[] smArray = smartArray.toArray();
        Object[] tmp = new Object[smArray.length];
        int size = 0;
        for (int i = 0; i < smArray.length - 1; ++i) {
            if (!smArray[i].equals(smArray[i + 1])) {
                tmp[size] = smArray[i];
                ++size;
            } else break;
        }
        Object[] finalArray = Arrays.copyOf(tmp, size);
        return finalArray;
    }

    @Override
    public String operationDescription() {
        return "Distinct Decorator";
    }

    @Override
    public int size() {
        return toArray().length;
    }

}
