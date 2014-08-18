package com.jason.calc;

import android.R.integer;
import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import java.lang.Math.*;
import java.math.*;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final EditText editText=(EditText)findViewById(R.id.edit_text);  
        editText.setOnEditorActionListener(new OnEditorActionListener() {  
            @Override  
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {  
                Toast.makeText(MainActivity.this, v.getText(), Toast.LENGTH_SHORT).show();  
                return false;  
            }


        });  
        
        Button getValue=(Button)findViewById(R.id.btn_select);  
        getValue.setOnClickListener(new OnClickListener() {  
            @Override  
            public void onClick(View v) {  
            	BigDecimal bdAllNum = new BigDecimal(""+(Integer.parseInt(editText.getText().toString())*10000));
            	EditText eMonths=(EditText)findViewById(R.id.edit_months);  
            	BigDecimal bdMonths = new BigDecimal(""+(Integer.parseInt(eMonths.getText().toString())*12));
            	EditText eRate=(EditText)findViewById(R.id.edit_rate);  
            	BigDecimal bdRate = new BigDecimal(eRate.getText().toString()).divide(new BigDecimal("1200"),10,BigDecimal.ROUND_HALF_EVEN);
            	//〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕
            	BigDecimal bdTemp = new BigDecimal("1").add(bdRate).pow(bdMonths.intValue());

            	BigDecimal bdT1 = bdAllNum.multiply(bdRate).multiply(bdTemp);
            	BigDecimal bdT2 = bdTemp.subtract(new BigDecimal("1"));
            	//BigDecimal bdEveryMonth = bdAllNum.multiply(bdRate).multiply(bdTemp).divide(bdTemp.subtract(new BigDecimal("1")),10,BigDecimal.ROUND_HALF_EVEN);
                BigDecimal bdEveryMonth = bdT1.divide(bdT2,2,BigDecimal.ROUND_HALF_EVEN);
                BigDecimal bdEqualMonth = bdAllNum.multiply(bdRate);
            	Toast.makeText(MainActivity.this, ""+bdT1.intValue()+"/"+bdT2.intValue(), Toast.LENGTH_SHORT).show();  
            	EditText eRet = (EditText)findViewById(R.id.edit_ret);
            	eRet.setText(bdEveryMonth.toString());
            	
            	EditText eRet2 = (EditText)findViewById(R.id.edit_ret2);
            	eRet2.setText(bdEqualMonth.toString());
            }  
        });  
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
