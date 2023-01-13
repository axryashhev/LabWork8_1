package com.example.labwork8_1.works;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.example.labwork8_1.Util.UtilArray;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

import java.io.Serializable;
import java.util.Arrays;

public class Work2 {
    public One workOne(int[] a, int[] b) {
        return new One(a, b);
    }
    public Two workTwo(String text) {
        return new Two(text);
    }
    public class One {
        private final int[] a;
        private final int[] b;
        public One(int[] a, int[] b) {
            this.a = a;
            this.b = b;
        }
        @RequiresApi(api = Build.VERSION_CODES.N)
        public @NonNull Observable<String> execute() {
            return Observable.create(s -> {
                s.onNext("Begin: \n" +
                        "a = " + Arrays.toString(a) + '\n' +
                        "b = " + Arrays.toString(b) + '\n');

                UtilArray.barrierElement(a, 4);
                UtilArray.insertionSort(b);

                s.onNext("Sort array: \n" +
                        "b = " + Arrays.toString(b) + '\n');

                final int findItem = Arrays.binarySearch(b, 4);
                final Serializable textFindItem = findItem < 0 ? "undenfided" : findItem;

                s.onNext("First: " + UtilArray.barrierElement(a, 4) + '\n');
                s.onNext("Second: " +  textFindItem + '\n');

                s.onNext("Unique: " + Arrays.toString(unionListUnique(a, b)) + '\n');
            });
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        private int[] unionListUnique(int[] a, int[] c) {
            int[] arr = unionList(a,c);
            return Arrays.stream(arr).distinct().toArray();
        }

        private int[] unionList(int[] a, int[] b) {
            int[]c = new int[a.length+b.length];
            int count = 0;
            for(int i = 0; i<a.length; i++) {
                c[i] = a[i];
                count++;
            }
            for(int j = 0;j<b.length;j++) {
                c[count++] = b[j];
            }
            return c;
        }
    }

    public class Two {
        private String word;

        public Two(String word) {
            this.word = word;
        }

        public Single<String> execute() {
            return Single.create(s -> {
                char[] mas = word.toCharArray();
                boolean isSorted = false;
                char buf;
                while(!isSorted) {
                    isSorted = true;
                    for (int i = 0; i < word.length()-1; i++) {
                        if(mas[i] > mas[i+1]){
                            isSorted = false;

                            buf = mas[i];
                            mas[i] = mas[i+1];
                            mas[i+1] = buf;
                        }
                    }
                }

                s.onSuccess(Arrays.toString(mas));
            });
        }
    }
}
