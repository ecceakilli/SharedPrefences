package com.eceakilli.sharedprefences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    SharedPreferences sharedPreferences;
    private Button btndelete;
    private  TextView lblAdet;
    private  int adet=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText=findViewById(R.id.editTextNumber);
        textView=findViewById(R.id.textView);
        btndelete=(Button)findViewById(R.id.btndelete);
        lblAdet=(TextView)findViewById(R.id.textView2);
        deleteButonClickEvent();
        //SharedPreferences objesi telefonun kendi hafızasını kullanarak verileri saklamamıza yarar.
        //mode_private sadece benim uygulamamdan ulaşılsın demek
        sharedPreferences=this.getSharedPreferences("com.eceakilli.sharedprefences", Context.MODE_PRIVATE);
        //kaydedilmiş mi kontrol edelim
        //key hepsinde aynı olmalı(kayitliYas)
        int kayitliYas=sharedPreferences.getInt("kayitliYas",0);
        if (kayitliYas==0){
            textView.setText("Yaşınız: ");
        }
        else{
            textView.setText("kayıtlı yasınız:"+kayitliYas);
        }



    }
    public void save(View view){
        if (!editText.getText().toString().matches("")){
            int age=Integer.parseInt(editText.getText().toString());
            textView.setText("Yaşınız:"+age);

            //Bu şekilde kayıtta kalıyor.
            sharedPreferences.edit().putInt("kayitliYas",age).apply();


        }

    }
    public void delete(View view){
        //eger xml tarafından click metoduna adını verirsen burda da olusturup işlem yapabilirsin
        //deleteButonClilEvent mehhodunun olmasının nedeni burdan da clik işlemi nasılyazılır onu incele.

    }
    private void deleteButonClickEvent(){
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kayitliVeri=sharedPreferences.getInt("kayitliYas",0);
                if (kayitliVeri!=0){
                    sharedPreferences.edit().remove("kayitliYas").apply();
                    textView.setText("Yaşınız:");
                }
                adet++;
                lblAdet.setText(adet +"defa tıklandı");

            }
        });
    }


}