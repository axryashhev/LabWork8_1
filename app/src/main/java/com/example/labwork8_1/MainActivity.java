package com.example.labwork8_1;

import android.annotation.SuppressLint;
import android.os.Build;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.labwork8_1.Util.Randomize;
import com.example.labwork8_1.works.Work2;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    BehaviorSubject<String> behaviorSubject = BehaviorSubject.create();
    Disposable disposable;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        generateData();
    }

    private void generateData() {
        behaviorSubject.onNext("1 Work");

        Work2 work2 = new Work2();

        Work2.One one = work2.workOne(Randomize.getRandom(5, 1, 5),
                Randomize.getRandom(5, 1, 5));
        Disposable disposable1 = one.execute().subscribe(behaviorSubject::onNext);
        disposable1.dispose();

        behaviorSubject.onNext("\n2 Work");
        Work2.Two two = work2.workTwo("REST14342");
        Disposable disposable2 = two.execute().subscribe(behaviorSubject::onNext);
        disposable2.dispose();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onStart() {
        super.onStart();
        disposable = behaviorSubject.subscribe(data -> textView.setText(textView.getText() + data + '\n'));
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(!disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void onUpdate(View view) {
        textView.setText("");
        this.generateData();
    }
}