package com.example;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashTest {

    public static void main(String[] args) {
        final Set<Test> set = new HashSet<>();
        set.add(new Test("zhangsan"));
        set.add(new Test("zhangsan"));
        System.err.println(set.size());
    }

    static class Test{

        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Test{" +
                    "name='" + name + '\'' +
                    '}';
        }

        public Test(String name) {
            this.name = name;
        }

        @Override
        public int hashCode() {

            return Objects.hash(name);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Test test = (Test) o;
            return Objects.equals(name, test.name);
        }
    }
}
