package cn.aprain.numberkeyboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;

import cn.aprain.numberkeyboard.view.MoneyEditText;
import cn.aprain.numberkeyboard.view.NumberKeyboardView;

public class MainActivity extends AppCompatActivity {


    private NumberKeyboardView nkvView;
    private MoneyEditText metMoney;
    private String mMonneyStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nkvView=findViewById(R.id.nkv_view);
        metMoney=findViewById(R.id.met_money);

        nkvView.setOnNumberClickListener(new NumberKeyboardView.OnNumberClickListener() {
            @Override
            public void onNumberReturn(String number) {
                String amount = metMoney.getText().toString().trim();
                if (number.equals("0") && amount.equals("0")) {
                    return;
                }
                if (amount.equals("0") && !number.equals(".")) {
                    metMoney.setText("0." + number);
                    return;
                }
                if (!number.equals(".") || !amount.contains(".")) {
                    amount = amount + number;
                    metMoney.setText(amount);
                }
                Editable ea = metMoney.getText();
                metMoney.setSelection(ea.length());
            }

            @Override
            public void onNumberDelete() {
                String amount = metMoney.getText().toString().trim();
                if (amount.length() <= 1) {
                    mMonneyStr = "";
                } else {
                    mMonneyStr = amount.substring(0, amount.length() - 1);
                }
                metMoney.setText(mMonneyStr);
            }
        });
    }
}
