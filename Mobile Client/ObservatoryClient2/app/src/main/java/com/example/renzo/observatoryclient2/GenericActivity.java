package com.example.renzo.observatoryclient2;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GenericActivity extends Activity {

    TextView laboratoryView;
    ListView genericsList;

    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter<String> adapter;
    List<Medicine> medicines;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic);
        setup();
    }


    private void setup(){
        laboratoryView = (TextView)findViewById(R.id.laboratory);
        genericsList = (ListView)findViewById(R.id.generics_list);

        SpannableStringBuilder builder = new SpannableStringBuilder();
        String actComp = LogicCaller.laboratory.getName();
        SpannableString nameSpannable= new SpannableString(actComp);
        nameSpannable.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, actComp.length(), 0);
        builder.append(laboratoryView.getText());
        builder.append(nameSpannable);
        laboratoryView.setText(builder, TextView.BufferType.SPANNABLE);

        medicines = LogicCaller.laboratory.getMedicines();
        for (Medicine medicine : medicines){
            listItems.add(medicine.getName()+'\n'+medicine.getManufacturer());
        }
        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        genericsList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}
