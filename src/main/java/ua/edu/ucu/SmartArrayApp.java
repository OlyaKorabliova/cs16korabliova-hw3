package ua.edu.ucu;

import java.util.Arrays;
import ua.edu.ucu.functions.MyComparator;
import ua.edu.ucu.functions.MyFunction;
import ua.edu.ucu.functions.MyPredicate;
import ua.edu.ucu.smartarr.*;

public class SmartArrayApp {

    public static Integer[]
            filterPositiveIntegersSortAndMultiplyBy2(Integer[] integers) {

        MyPredicate pr = new MyPredicate() {
            @Override
            public boolean test(Object t) {
                return ((Integer) t) > 0;
            }
        };

        MyComparator cmp = new MyComparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Integer) o1) - ((Integer) o2);
            }
        };

        MyFunction func = new MyFunction() {
            @Override
            public Object apply(Object t) {
                return 2 * ((Integer) t);
            }
        };

        // Input: [-1, 2, 0, 1, -5, 3]
        SmartArray sa = new BaseArray(integers);

        sa = new FilterDecorator(sa, pr); // Result: [2, 1, 3];
        sa = new SortDecorator(sa, cmp); // Result: [1, 2, 3]
        sa = new MapDecorator(sa, func); // Result: [2, 4, 6]

        // Alternative
//        sa = new MapDecorator(
//                    new SortDecorator(
//                        new FilterDecorator(sa, pr),
//                    cmp),
//                func);
        Object[] result = sa.toArray();
        return Arrays.copyOf(result, result.length, Integer[].class);
    }

    public static String[]
            findDistinctStudentNamesFrom2ndYearWithGPAgt4AndOrderedBySurname(Student[] students) {

        MyPredicate GPA = new MyPredicate() {
            @Override
            public boolean test(Object t) {
                return (((Student)t).getGPA() >= 4);
            }
        };

        MyPredicate year = new MyPredicate() {
            @Override
            public boolean test(Object t) {
                return (((Student)t).getYear() == 2);
            }
        };

        MyComparator surname = new MyComparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Student e1 = (Student)o1;
                Student e2 = (Student)o2;
                return (e1.getSurname().compareTo(e2.getSurname()));
            }
        };

        MyFunction func = new MyFunction() {
            @Override
            public Object apply(Object t) {
                String s;
                s = String.format("%s %s", ((Student)t).getSurname(), ((Student)t).getName());
                return s;
            }
        };


        BaseArray studentSmartArray= new BaseArray(students);
        DistinctDecorator distDecor = new DistinctDecorator(studentSmartArray);
        studentSmartArray = new BaseArray(distDecor.toArray());

        FilterDecorator filtDecor1 = new FilterDecorator(studentSmartArray, year);
        studentSmartArray = new BaseArray(filtDecor1.toArray());

        FilterDecorator filtDecor2 = new FilterDecorator(studentSmartArray, GPA);
        studentSmartArray = new BaseArray(filtDecor2.toArray());

        SortDecorator sortDecor = new SortDecorator(studentSmartArray, surname);
        studentSmartArray = new BaseArray(sortDecor.toArray());

        MapDecorator mapDecor = new MapDecorator(studentSmartArray, func);
        studentSmartArray = new BaseArray(mapDecor.toArray());

        Object[] result = studentSmartArray.toArray();

        return Arrays.copyOf(result, result.length, String[].class);
    }
}
