package com.example.labwork8_1.Util;

import android.os.Build;
import androidx.annotation.RequiresApi;

import java.util.Random;

public class Randomize {
    private static final Random rand = new Random();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int[] getRandom(int size, int origin, int bound) {
            return rand.ints(size, origin, bound).toArray();
    }
}

