package com.example.docbao;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.docbao.adapter.BaiBaoAdapter;
import com.example.docbao.model.BaiBao;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ListView lvBaiBao;

    ArrayList<BaiBao> arrayBaiBao;
    ArrayList<String> arrayLink;
    BaiBaoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvBaiBao = findViewById(R.id.lvBaiBao);
        arrayLink = new ArrayList<>();
        arrayBaiBao = new ArrayList<>();
        adapter = new BaiBaoAdapter(this, R.layout.layout_item_bao, arrayBaiBao);
        lvBaiBao.setAdapter(adapter);

        new ReadRSS().execute("https://thanhnien.vn/rss/home.rss");

        lvBaiBao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                intent.putExtra("linkBao", arrayLink.get(position));
                startActivity(intent);
            }
        });
    }

    private class ReadRSS extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line ="";
                while ((line = bufferedReader.readLine()) !=null){
                    content.append(line);
                }

                bufferedReader.close();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList = document.getElementsByTagName("item");


            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                String tieuDe = parser.getValue(element, "title");
                String ngayDang = parser.getValue(element, "pubDate");
                String formatNgayDang = formatDate(ngayDang);
                String description = parser.getValue(element, "description");
                String imageURL = parser.getImageUrlFromDescription(description);

                arrayLink.add(parser.getValue(element, "link"));
                arrayBaiBao.add(new BaiBao(imageURL, tieuDe, formatNgayDang));
            }

            adapter.notifyDataSetChanged();
        }

        private String formatDate(String Ngay) {
            try {
                SimpleDateFormat inputFormat = new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss Z", Locale.US);
                Date date = inputFormat.parse(Ngay);

                SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("vi", "VN"));
                return outputFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return Ngay; // Trả lại nguyên bản nếu không thể định dạng
            }
        }
    }
}