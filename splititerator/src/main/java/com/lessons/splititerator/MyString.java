package com.lessons.splititerator;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;


public class MyString {
    private String[] data;

    Stream<String> stream() {
        return StreamSupport.stream(new MySpliterator(), false);
    }

    Stream<String> parallelStream() {
        return StreamSupport.stream(new MySpliterator(), true);
    }

    public MyString(String[] data) {
        this.data = data;
    }

    public class MySpliterator implements Spliterator<String> {

        private int start;
        private int finish;

        public MySpliterator(int finish, int start) {
            this.finish = finish;
            this.start = start;
        }

        public MySpliterator() {
            this.start = 0;
            this.finish = data.length - 1;
        }

        @Override
        public boolean tryAdvance(Consumer<? super String> action) {
            if (start + 1 > finish) {
                start++;
                action.accept(data[start]);
                return true;
            }
            return false;
        }

        @Override
        public Spliterator<String> trySplit() {
            int middle = (finish - start)/2;
            if (middle <= 1) {
                return null;
            }
            int newFin = start;
            int newStart = start + middle;

            start = start + middle +1;
            return new MySpliterator(  newFin, newStart);
        }

        @Override
        public long getExactSizeIfKnown() {
            return estimateSize();
        }

        @Override
        public void forEachRemaining(Consumer<? super String> action) {
            while (start <= finish) {
                action.accept(data[start]);
                start++;
            }
        }

        @Override
        public long estimateSize() {
            return finish - start;
        }

        @Override
        public int characteristics() {
            return IMMUTABLE | SIZED | SUBSIZED;
        }
    }
}
