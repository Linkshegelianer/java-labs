package exercise;

import java.util.Arrays;
import java.util.Objects;
import java.util.ArrayList;
import java.util.stream.Collectors;

class SafetyList {
    // BEGIN
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 16;
    private int elements[];

    public SafetyList() {
        elements = new int[DEFAULT_CAPACITY];
    }

    public synchronized void add(int number) {
        if (size == elements.length) {
            ensureCapacity();
        }
        elements[size++] = number;
    }

    public int get(int i) {
        if (i >= size || i < 0) {
            throw new IndexOutOfBoundsException("Index: " + i + ", Size " + i);
        }
        return (int) elements[i];
    }

    public int getSize() {
        return size;
    }

    private void ensureCapacity() {
        int newSize = elements.length * 2;
        elements = Arrays.copyOf(elements, newSize);
    }
    // END
}
