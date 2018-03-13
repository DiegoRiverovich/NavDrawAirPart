package com.example.andrey.navdrawairpart;


import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.andrey.navdrawairpart.adapter.MyAdapter;

/**
 * Created by Andrey on 07.03.2018.
 */

public class ProductFragment extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    //GridView gridView;

    String[] values = {
            "Компрессоры",
            "Осушители",
            "Генераторы газов",

            "Вакуумные насосы",
            "Циклонные сепараторы",
            "Магистральные фильтры и элементы",

            "Допоборудование",
            "Масло",
            "Гидравлические фильтры и элементы",

            "Конденсатоотводчики",
            "Подобрать по габаритам",
            "Абсорбент для осушителей",

            "Фильтрация Parket",
            "Разделение\n конденсата",
            "Турбокомпрессоры",

            "Дизельные генераторы",
            "Угольные колонны",
            "Полезная информация",

            "Спецтехника",
            "Сервис 24"
    };

    int[] images = {
            R.drawable.kompressor,
            R.drawable.osushiteli,
            R.drawable.generatorigazov,

            R.drawable.vakuumnyenasosy,
            R.drawable.ciklonnyeseparatory,
            R.drawable.magistralniefiltri,

            R.drawable.oborudovanie,
            R.drawable.maslo,
            R.drawable.gidravlika,

            R.drawable.kondensatootvodchiki,
            R.drawable.podobratfiltr,
            R.drawable.adsorbent,

            R.drawable.filtracijaparker,
            R.drawable.razdeleniekondensata,
            R.drawable.turbokompressory,

            R.drawable.dizelnyegeneratory,
            R.drawable.ugolnyekolonny,
            R.drawable.inform,

            R.drawable.spectehnika,
            R.drawable.servis,
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_product, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        recyclerView = view.findViewById(R.id.recycleview);
        if (width > 1090) {
            layoutManager = new GridLayoutManager(getActivity(), 4);
        } else {
            layoutManager = new GridLayoutManager(getActivity(), 3);
        }


        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()
        {
            @Override
            public int getSpanSize(int position)
            {
                if (position == 18) {
                    return 3;
                }
                return 1;
            }
        });


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        MyAdapter myAdapter = new MyAdapter(getActivity(), images, values);
        recyclerView.setAdapter(myAdapter);


        /*
        gridView = (GridView) view.findViewById(R.id.griview);

        GridAdapter gridAdapter = new GridAdapter(getActivity(), values, images);
        gridView.setAdapter(gridAdapter);
        */

        /*
        TextView textview = (TextView)view.findViewById(R.id.helloworld);
        textview.setText("yo");
        */

        //GridLayout productFragmentLayout = view.findViewById(R.id.productLayout);
        /*
        View child = view.findViewById(R.id.productLayout);
        GridLayout.LayoutParams params =
                new GridLayout.LayoutParams(child.getLayoutParams());

        params.rowSpec = GridLayout.spec(5, 3);    // First cell in first row use rowSpan 2.
        //params.columnSpec = GridLayout.spec(0, 2); // First cell in first column use columnSpan 2.
        child.setLayoutParams(params);
        */



        /*

        addProduct("Компрессоры", R.drawable.kompressor, productFragmentLayout);
        addProduct("Осушители", R.drawable.osushiteli, productFragmentLayout);
        addProduct("Генераторы\n газов", R.drawable.generatorigazov, productFragmentLayout);

        addProduct("Вакуумные\n насосы", R.drawable.vakuumnyenasosy, productFragmentLayout);
        addProduct("Циклонные\n сепараторы", R.drawable.ciklonnyeseparatory, productFragmentLayout);
        addProduct("Магистральные\n фильтры\n и элементы", R.drawable.magistralniefiltri, productFragmentLayout);

        addProduct("Доп\nоборудование", R.drawable.oborudovanie, productFragmentLayout);
        addProduct("Масло", R.drawable.maslo, productFragmentLayout);
        addProduct("Гидравлические\n фильтры\n и элементы", R.drawable.gidravlika, productFragmentLayout);

        addProduct("Конденсато\nотводчики", R.drawable.kondensatootvodchiki, productFragmentLayout);
        addProduct("Подобрать \nпо габаритам", R.drawable.podobratfiltr, productFragmentLayout);
        addProduct("Абсорбент \nдля осушителей", R.drawable.adsorbent, productFragmentLayout);

        addProduct("Фильтрация\n Parket", R.drawable.filtracijaparker, productFragmentLayout);
        addProduct("Разделение\n конденсата", R.drawable.razdeleniekondensata, productFragmentLayout);
        addProduct("Турбокомпрессоры", R.drawable.turbokompressory, productFragmentLayout);

        addProduct("Дизельные\n генераторы", R.drawable.dizelnyegeneratory, productFragmentLayout);
        addProduct("Угольные\n колонны", R.drawable.ugolnyekolonny1, productFragmentLayout);
        addProduct("Полезная\n информация", R.drawable.inform, productFragmentLayout);

        addProduct("Спецтехника", R.drawable.servis, productFragmentLayout);
        addProduct("Сервис 24", R.drawable.servis, productFragmentLayout);

        View child = view.findViewById(R.id.product_list);
        View child1 = view.findViewById(R.id.productLayout);

        */


    /*
        GridLayout gridLayout = (GridLayout)view.findViewById(R.id.productLayout);

        gridLayout.removeAllViews();

        int total = 20;
        int column = 3;
        int row = total / column;
        gridLayout.setColumnCount(column);
        gridLayout.setRowCount(row + 1);
        for(int i =0, c = 0, r = 0; i < total; i++, c++)
        {
            if(c == column)
            {
                c = 0;
                r++;
            }
            GridLayout.LayoutParams param = new GridLayout.LayoutParams();
            param.height = GridLayout.LayoutParams.WRAP_CONTENT;
            param.width = GridLayout.LayoutParams.WRAP_CONTENT;
            param.rightMargin = 5;
            param.topMargin = 5;
            param.setGravity(Gravity.CENTER);
            param.columnSpec = GridLayout.spec(c);
            param.rowSpec = GridLayout.spec(r);
            child.setLayoutParams (param);
            //gridLayout.addView(oImageView);
        }
    */



    /*
        GridLayout.LayoutParams itemLP = (GridLayout.LayoutParams)productFragmentLayout.getLayoutParams();
        //productFragmentLayout.setColumnCount(3);
        //productFragmentLayout.setRowCount(7);
        //itemLP.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, productFragmentLayout.getColumnCount());
        itemLP.height = GridLayout.LayoutParams.WRAP_CONTENT;
        itemLP.width = GridLayout.LayoutParams.WRAP_CONTENT;
        itemLP.setGravity(Gravity.CENTER);

        //itemLP.rowSpec = GridLayout.spec(2, 4);
        //itemLP.columnSpec = GridLayout.spec(0, 3);
        child.setLayoutParams(itemLP);



        GridLayout.LayoutParams itemLP1 = new GridLayout.LayoutParams();
        //productFragmentLayout.setColumnCount(3);
        //productFragmentLayout.setRowCount(7);
        //itemLP.columnSpec = GridLayout.spec(GridLayout.UNDEFINED, productFragmentLayout.getColumnCount());
        itemLP1.height = GridLayout.LayoutParams.MATCH_PARENT;
        itemLP1.width = GridLayout.LayoutParams.MATCH_PARENT;
        itemLP1.setGravity(Gravity.CENTER);

        //itemLP.rowSpec = GridLayout.spec(2, 4);
        //itemLP.columnSpec = GridLayout.spec(0, 3);
        child1.setLayoutParams(itemLP1);
        */




        /*
        android:orientation="horizontal"
    android:layout_gravity="center"
    android:rowCount="8"
    android:columnCount="3"
    android:id="@+id/productLayout"
    android:gravity="center"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:paddingBottom="5dp"
    android:paddingLeft="5dp"
    android:paddingTop="5dp"
    android:paddingRight="5dp"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
        */


    }

    /*
    private void addProduct(String name, int imageId, GridLayout parenet) {
        View product = getLayoutInflater().inflate(R.layout.productslist,  null);

        TextView productName = product.findViewById(R.id.product_name);
        productName.setText(name);

        ImageView productImage = product.findViewById(R.id.product_image);
        productImage.setImageResource(imageId);


        parenet.addView(product);


    }
    */
}
