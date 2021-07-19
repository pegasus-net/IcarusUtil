package a.icarus.test;

import a.icarus.utils.Maths;

public class JavaTest {
    public static void main(String[] args) throws Exception {
        Integer a = Maths.interval(22, 20, 10);
        System.out.println(a);

    }
}

class A implements Comparable<A> {

    @Override
    public int compareTo(A o) {
        return 0;
    }
}
