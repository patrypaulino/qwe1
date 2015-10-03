package com.infodat.zbarscannerexample;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by intellisys on 9/25/15.
 */
public class ListScanAdapter extends BaseAdapter {
    public Context context;
    public ListScanAdapter(Context context) {
        this.context = context;
    }
    public ArrayList<String> productos = new ArrayList<String>();
    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public String getItem(int i) {
        return productos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void add(String producto){
        productos.add(producto);
        this.notifyDataSetChanged();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View row;
        row = inflater.inflate(R.layout.row_scanned, viewGroup, false);
        TextView title = (TextView) row.findViewById(R.id.rText);
        Button b = (Button) row.findViewById(R.id.bQuitar);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productos.remove(i);
                ListScanAdapter.this.notifyDataSetChanged();
            }
        });
        String string = getItem(i);
        if ( string.length() > 23 ) {
            string = string.substring(0, 20) + "...";
        }
        title.setText(string);

        return row;
    }
}
