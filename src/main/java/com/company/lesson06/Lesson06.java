package com.company.lesson06;

import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;

@Data
public class Lesson06 {

    private static final Logger log = LoggerFactory.getILoggerFactory(Lesson06.class);

    public static void main(String[] args) {

        int[] arr = {1,8,6,3,9,9,8,4,3,1,7};

        System.out.println(Arrays.toString(arr));

        System.out.println(Arrays.toString(afterLastFour(arr)));

        //-------------------------------------------------------

        int[] arr1 = {4,1,4};

        System.out.println("atLeastOneFourAndOne - " + atLeastOneFourAndOne(arr1));
    }

    public static int[] afterLastFour(int[] arr) {

        int searchNum = 4;

        boolean noFours = true;

        int[] res = null;

        if (arr == null) {
            try {
                throw new ArrayIsNotInitialized();
            } catch (ArrayIsNotInitialized arrayIsNotInitialized) {
                log.error("ArrayIsNotInitialized");
            }
        } else {
            if (arr.length > 0) {
                mark:
                if (arr[arr.length - 1] != searchNum) {
                    for (int i = arr.length - 1; i > -1; i--) {
                        if (arr[i] == searchNum) {
                            noFours = false;
                            res = new int[arr.length - i - 1];
                            i = i + 1;

                            for (int j = 0; i < arr.length; i++, j++) res[j] = arr[i];

                            break mark;
                        }
                    }
                } else noFours = false;

                if(noFours) {
                    try {
                        throw new Number4IsNotFound();
                    } catch (Number4IsNotFound number4IsNotFound) {
                        log.error("Number4IsNotFound");
                    }
                }


            } else {
                try {
                    throw new ArrayEmptyEception();
                } catch (ArrayEmptyEception arrayEmptyEception) {
                    log.error("ArrayEmptyEception");
                }
            }
        }
        return res;
    }

    public static boolean atLeastOneFourAndOne(int[] arr) {
        boolean res = false;
        int[] requiredNumbers = {1,4};
        for (int i = 0; i < requiredNumbers.length; i++) {
            res = false;
            for (int j = 0; j < arr.length; j++) {
                if (requiredNumbers[i] == arr[j]) {
                    res = true;
                    break;
                }
            }
            if (!res) break;
        }
        return res;
    }
}

class ArrayEmptyEception extends Throwable {
    public ArrayEmptyEception() {
        System.err.println("Array is empty");
    }
}

class ArrayIsNotInitialized extends Throwable {
    public ArrayIsNotInitialized() {
        System.err.println("Array is not initialized");
    }
}

class Number4IsNotFound extends Throwable {
    public Number4IsNotFound() {
        System.err.println("The number 4 is not found");
    }
}