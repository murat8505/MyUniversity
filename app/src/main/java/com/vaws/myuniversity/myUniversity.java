package com.vaws.myuniversity;

import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class myUniversity extends ActionBarActivity {

    private String[] opcionesMenu;
    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private ActionBarDrawerToggle toggle; // Menu Toggle para el ListView en el action Bar
    private WebView webView;

    // nav drawer title
    private CharSequence mDrawerOpen;
    private CharSequence mDrawerClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_university);
        // Web View
        String webPage_es = "<html><head>\n" +
                "        <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />\n" +
                "    </head><body>" +
                "<div style=text-align:justify><b>"+this.getString(R.string.text_calculadora_notas)+"</b> </div></br>" +
                "<div style=text-align:justify>"+this.getString(R.string.text1_calculadora_notas)+" </div></br>" +
                "</body></html>";

        webView  = (WebView) findViewById(R.id.webview);
        webView.setVerticalScrollBarEnabled(true);
        webView.setVerticalFadingEdgeEnabled(true);
        webView.setVerticalScrollbarOverlay(true);
        webView.loadData(webPage_es, "text/html", "utf-8");
        // Navigation Drawer
        mDrawerOpen = "Menu";
        mDrawerClose = "Calculadora Universitaria";
        getSupportActionBar().setTitle(mDrawerClose);

        opcionesMenu = new String[] {"Calculadora Universitaria", "Opciones"};
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);


        drawerList.setAdapter(new ArrayAdapter(this, R.layout.listlayout, R.id.listTextView, opcionesMenu));
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                //Toast.makeText(getApplicationContext(), "Ha pulsado el item " + position+1, Toast.LENGTH_SHORT).show();
                /*switch (position) {
                    case 0:
                        break;
                    case 1:
                        //Creamos el Intent
                        Intent intent2 = new Intent(NotasPrincipal.this, PruebaTabs2.class);

                        //Iniciamos la nueva actividad
                        startActivity(intent2);

                        break;
                    case 2:
                        //Creamos el Intent
                        Intent intent = new Intent(NotasPrincipal.this, AgregarMaterias.class);
                        //Creamos la informacion a pasar entre actividades
                        Bundle b = new Bundle();
                        //Anadimos la informacion al intent
                        intent.putExtras(b);
                        //Iniciamos la nueva actividad
                        startActivity(intent);
                        break;
                    case 3:

                        break;
                }*/

            }

        });
        toggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.drawer_open, R.string.Calcular_Notas){

            public void onDrawerClosed(View view)
            {
                getSupportActionBar().setTitle(mDrawerClose);
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
                syncState();
            }

            public void onDrawerOpened(View drawerView)
            {
                getSupportActionBar().setTitle(mDrawerOpen);
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
                syncState();
            }

        };
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        // Fin Navigation Drawer
    }
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        toggle.syncState();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my_university, menu);
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
        //noinspection SimplifiableIfStatement
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }


        return super.onOptionsItemSelected(item);
    }
}
