package axisimski.colazconj;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.content.SharedPreferences;


public class MainActivity extends AppCompatActivity {

    Button exec;
    Button settings;
    static  EditText input;
    static TextView output;
    static Switch showSteps;
    Button addOne;

    static String MaxValue, TotalSteps, AtStep;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //-------------------------------------------------------------------------------------------
        TotalSteps=getResources().getString(R.string.total_steps);
        AtStep=getResources().getString(R.string.at_step);
        MaxValue=getResources().getString(R.string.max_value);
        //--------------------------------------------------------------------------------------------
        showSteps=(Switch)findViewById(R.id.showSteps);
        exec=(Button)findViewById(R.id.button);
        settings=(Button)findViewById(R.id.settingsButton);
        addOne=(Button) findViewById(R.id.addOne);
        input=(EditText)findViewById(R.id.input);
        output=(TextView)findViewById(R.id.output);
        output.setMovementMethod(ScrollingMovementMethod.getInstance());
        output.setTextIsSelectable(true);

        //Hide the main screen switch statement.
        showSteps.setVisibility(View.GONE);
        //--------------------------------------------------------------------------------------------

        settings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent myIntent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(myIntent);

            }
        });

        SharedPreferences SharedPref=getSharedPreferences("userInput", Context.MODE_PRIVATE);
        Boolean FC=SharedPref.getBoolean("showSteps", false);
        showSteps.setChecked(FC);


        addOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long num;

                if(input.getText().toString().isEmpty()){
                    num=1;
                }
                else{
                    num=Long.valueOf (input.getText().toString());
                }
                num++;
                input.setText(Long.toString(num));

                NoSteps ns=new NoSteps();
                withSteps ws =new withSteps();

                if(showSteps.isChecked()){
                    ws.execute();
                }

                else{
                    ns.execute();
                }

            }
        });
    }
//==================================================================================================

    public void execute(View v){

       NoSteps ns1=new NoSteps();
       withSteps ws1=new withSteps();

        if(showSteps.isChecked()){
            ws1.execute();
        }

        else{
            ns1.execute();
        }

//==================================================================================================

        showSteps.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                NoSteps ns=new NoSteps();
                withSteps ws =new withSteps();

                if(showSteps.isChecked()){
                    ws.execute();
                }

                else{
                    ns.execute();
                }

            }
        });

//==================================================================================================

    }



}
