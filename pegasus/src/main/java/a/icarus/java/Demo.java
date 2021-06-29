package a.icarus.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo {
    private String it = "string";
    private String word = "word";
    private boolean b = true;
    private int i;
    private Integer in;
    private ArrayList<Demo2> list = new ArrayList<>();
    private Demo2 d = new Demo2();
    private Map<String, String> map = new HashMap<>();

    public Demo() {
        list.add(new Demo2());
        list.add(new Demo2());
        list.add(new Demo2());
        map.put("aaa", "bbb");
    }
}
