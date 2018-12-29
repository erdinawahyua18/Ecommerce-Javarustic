package com.example.user.javarustic;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.javarustic.adapter.ProductAdapter;
import com.example.user.javarustic.modal.ProductDomain;

import java.util.ArrayList;

public class ProductActivity extends Activity {

    GridView gridView;
    TextView txtItemCount;
    int itemCount;

    ArrayList<ProductDomain> productList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        productList = new ArrayList<>();

        gridView = findViewById(R.id.grid_product);

        int position = getIntent().getIntExtra("position", 0);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.description_layout, null));
        builder.create();

        Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_LONG).show();

        switch (position) {
            case 0:
                String[] clothList = {"CHAIR 1","CHAIR 2","CHAIR 3","CHAIR 4","CHAIR 5"};
                int[] clothimageList = {R.drawable.chair1, R.drawable.chair2, R.drawable.chair3, R.drawable.chair4, R.drawable.chair5};
                String[] clothpriceList = {"IDR 250K", "IDR 275K", "IDR 250K","IDR 290K","IDR 270K"};


                ProductDomain productDomain;
                for (int i = 0; i < clothList.length; i++) {
                    productDomain = new ProductDomain(clothList[i], clothimageList[i], clothpriceList[i]);
                    productList.add(productDomain);
                }

                gridView.setAdapter(new ProductAdapter(productList, getApplicationContext()));


            case 1:
                String[] electronicList = {"TABLE 1","TABLE 2","TABLE 3","TABLE 4","TABLE 5"};
                int[] electroimageList = {R.drawable.table1, R.drawable.table2, R.drawable.table3,R.drawable.table4,R.drawable.table5};

                String[] electropriceList = {"IDR 400K","IDR 410K","IDR 440K","IDR 400K","IDR 440K"};


                for (int i = 0; i < electronicList.length; i++) {
                    productDomain = new ProductDomain(electronicList[i], electroimageList[i], electropriceList[i]);
                    productList.add(productDomain);
                }

                gridView.setAdapter(new ProductAdapter(productList, getApplicationContext()));

                break;

            case 2:

                String[] softwareList = {"COFFEE TABLE 1","COFFEE TABLE 2","COFFEE TABLE 3","COFFEE TABLE 4","COFFEE TABLE 5"};
                int[] softwareimageList = {R.drawable.ct1, R.drawable.ct2, R.drawable.ct3, R.drawable.ct4, R.drawable.ct5};

                String[] softwarepriceList = {"IDR 240K", "IDR 275K", "IDR 290K","IDR 250K","IDR 250K"};

                for (int i = 0; i < softwareList.length; i++) {
                    productDomain = new ProductDomain(softwareList[i], softwareimageList[i], softwarepriceList[i]);
                    productList.add(productDomain);
                }

                gridView.setAdapter(new ProductAdapter(productList, getApplicationContext()));

                break;

            case 3:

                String[] cellList = {"WARDROBE 1","WARDROBE 2","WARDROBE 3","WARDROBE 4","WARDROBE 5"};
                int[] cellimageList = {R.drawable.wd1, R.drawable.wd2, R.drawable.wd3, R.drawable.wd4,  R.drawable.wd5};

                String[] cellpriceList = {"IDR 3700K","IDR 4000K","IDR 4200K","IDR 4000K","IDR 4200K"};

                for (int i = 0; i < cellList.length; i++) {
                    productDomain = new ProductDomain(cellList[i], cellimageList[i], cellpriceList[i]);
                    productList.add(productDomain);
                }
                gridView.setAdapter(new ProductAdapter(productList, getApplicationContext()));

                break;

            case 4:

                String[] autoList = {"COUNTER 1","COUNTER 2","COUNTER 3","COUNTER 4","COUNTER 5"};
                int[] autoimageList = {R.drawable.co1, R.drawable.co2, R.drawable.co3,R.drawable.co4, R.drawable.co5};

                String[] autopriceList = {"IDR 11000K","IDR 9000K","IDR 10500K","IDR 9900K","IDR 6900K"};

                for (int i = 0; i < autoList.length; i++) {
                    productDomain = new ProductDomain(autoList[i], autoimageList[i], autopriceList[i]);
                    productList.add(productDomain);
                }
                gridView.setAdapter(new ProductAdapter(productList, getApplicationContext()));

                break;
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_LONG).show();
                productDetails(productList.get(i).getProductName(), productList.get(i).getProductPrice(), productList.get(i).getImageId(), i);
            }
        });
    }

    public void productDetails(final String productName, String productPrice, int imgId, final int position) {
        final AlertDialog alert;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Get the layout inflater
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        //Inflate and set the layout for the dialog
        //Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.description_layout, null);
        builder.setView(view);
        alert = builder.create();
        alert.show();

        TextView txtProduct = view.findViewById(R.id.txt_product_name);
        TextView txtPrice = view.findViewById(R.id.txt_price);
        ImageView imageView = view.findViewById(R.id.img_product);
        Button btnCart = view.findViewById(R.id.button);

        txtProduct.setText(productName);
        txtPrice.setText(productPrice);
        imageView.setImageResource(imgId);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //cartArray.add(productList.get(position));
                itemCount++;
                updateHotCount(itemCount);
                alert.dismiss();

                ;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cart_menu,menu);

        final View notifications = menu.findItem(R.id.cart_item).getActionView();

        txtItemCount = (TextView) notifications.findViewById(R.id.cart_badge);
        updateHotCount(itemCount++);
        txtItemCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateHotCount(itemCount++);
            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle item selection
        switch (item.getItemId()) {
            case R.id.cart_item:
                //newGame();
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }
    }


    public void updateHotCount(final int new_number) {

        itemCount = new_number;
        if (itemCount <0 ) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (itemCount ==0)
                    txtItemCount.setVisibility(View.GONE);
                else {
                    txtItemCount.setVisibility(View.VISIBLE);
                    txtItemCount.setText(Integer.toString(itemCount));
                    // supportInvalidateOptionsMenu();
                }
            }
        });

    }
    }

