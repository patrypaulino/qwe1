package com.infodat.zbarscannerexample;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
/*
Esta es el activity que se ejecuta luego de iniciar sesion. Es utilizado
para administrar la lista de los productos escaneados.
*/
public class ScannedList extends ActionBarActivity implements View.OnClickListener {

    public Button bAgregar; // Botton de agregar un nuevo codigo
    public ListView lProductos; // Lista de los productos.
    static final int SCANNING_CODE_REQUEST = 999;
    ListScanAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanned_list);

        // Inicializamos todas las variables que vamos a utilizar
        // como los botones y las listas.
        adapter = new ListScanAdapter(this);

        bAgregar = (Button) findViewById(R.id.bAgregar);
        lProductos = (ListView) findViewById(R.id.lProductos);
        bAgregar.setOnClickListener(this);
        lProductos.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_scanned_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        // Cuando hacemos click al boton de agregar, ejecutamos la pantalla/activity
        // ScannerActivity el cual nos ofrece la capacidad de tomar un codigo.
        if (view.getId() == R.id.bAgregar) {
            Intent scanningImage = new Intent(this, ScannerActivity.class);
            startActivityForResult(scanningImage, SCANNING_CODE_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Cuando escaneamos un codigo desde el Activity de escaneo, el resultado
        // es enviado aca y lo tomamos desde la variable "data".
        // Luego ese valos es agregado en el adapter, el cual es el manejador de la lista.
        if (requestCode == SCANNING_CODE_REQUEST){
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("scannedCode");
                adapter.add(result);
            }
        }
    }
}
