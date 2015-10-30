package com.example.administrator.heightcalucate;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    //计算按钮
    private Button calculatorButton;
    //体重输入框
    private EditText weightEditText;
    //男性选择框
    private CheckBox manCheckBox;
    //女性选择框
    private CheckBox womanCheckBox;
    //显示结果
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //从main.xml页面布局中获得对应的UI控件
        calculatorButton = (Button)findViewById(R.id.calculator);
        weightEditText = (EditText)findViewById(R.id.weight);
        manCheckBox = (CheckBox)findViewById(R.id.man);
        womanCheckBox = (CheckBox)findViewById(R.id.woman);
        resultTextView = (TextView)findViewById(R.id.result);

    }
    @Override
    public void  onStart(){
        super.onStart();
        //注册事件
        registerEvent();
    }
    private void registerEvent(){
        //注册按钮事件
        calculatorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!weightEditText.getText().toString().trim().equals("")) {
                    //判断是否已选择性别
                    if (manCheckBox.isChecked() || womanCheckBox.isChecked()) {
                        Double weight = Double.parseDouble(weightEditText.getText().toString());
                        StringBuffer sb = new StringBuffer();
                        sb.append("-------评估结果-------\n");
                        if (manCheckBox.isChecked()) {
                            sb.append("男性标准身高：");
                            //执行运算
                            double result = evaluateHeight(weight, "男");
                            sb.append((int) result + "(厘米)\n");
                        }

                        if (womanCheckBox.isChecked()) {
                            sb.append("女性标准身高：");
                            //执行运算
                            double result = evaluateHeight(weight, "女");
                            sb.append((int) result + "(厘米)");
                        }
                        //输出页面结果
                        resultTextView.setText(sb.toString());
                    } else {
                        //请选择性别！
                        showMessage("请选择性别！");
                    }
                } else {
                    //请输入体重
                    showMessage("请输入体重！");
                }


            }
        });
    }
    private double evaluateHeight(double weight,String sex) {
        double height = 0;
        if(sex=="男"){

            height = 170-(62-weight)/0.6;
        }else {
            height = 158-(52-weight)/0.5;
        }
        return height;

    }
    private void showMessage(String message){
        //提示框
        AlertDialog alert = new AlertDialog.Builder(this).create();
        alert.setTitle("系统信息");
        alert.setMessage(message);
        alert.setButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });
        alert.show();  //显示窗口

    }

    /**
     *
     * 创建菜单
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.menu_main, menu);
        // return true;
        menu.add(Menu.NONE,1,Menu.NONE,"退出");
        return super.onCreateOptionsMenu(menu);
    }

    /**
     *
     * 菜单事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //   int id = item.getItemId();

        //noinspection SimplifiableIfStatement
     /*   if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);*/
        switch(item.getItemId()){
            case 1://退出
                finish();
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}

