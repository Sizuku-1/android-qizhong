package cn.edu.sicnu.txp.bookmanager;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Comparator;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Book[] books = {
            new Book("代码大全（第2版）", "史蒂夫·迈克康奈尔", "电子工业出版社", 128.00),
            new Book("计算机是怎样跑起来的", "矢泽久雄", "人民邮电出版社", 49.00),
            new Book("算法图解", "阿迪提亚·巴尔加瓦", "人民邮电出版社", 69.00),
            new Book("深入理解Java虚拟机（第3版）", "周志明", "机械工业出版社", 129.00),
            new Book("程序是怎样跑起来的", "矢泽久雄", "人民邮电出版社", 59.00),
            new Book("Java核心技术 卷I", "凯·S. 霍斯特曼", "机械工业出版社", 119.00)
        };
        Arrays.sort(books, Comparator.comparingDouble(Book::getPrice));

        LayoutInflater inflater = getLayoutInflater();
        ArrayAdapter<Book> adapter = new ArrayAdapter<>(this, R.layout.item_book, books) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View row = convertView;
                if (row == null) {
                    row = inflater.inflate(R.layout.item_book, parent, false);
                }

                Book book = getItem(position);
                TextView title = row.findViewById(R.id.row_title);
                TextView author = row.findViewById(R.id.row_author);
                TextView price = row.findViewById(R.id.row_price);
                title.setText(book.getTitle());
                author.setText(book.getAuthor());
                price.setText(getString(R.string.price_format, book.getPrice()));
                return row;
            }
        };

        ListView bookList = findViewById(R.id.book_list);
        bookList.setAdapter(adapter);
        bookList.setOnItemClickListener((parent, row, position, id) -> {
            Book selectedBook = adapter.getItem(position);
            startActivity(BookDetailActivity.createIntent(this, selectedBook));
        });
    }
}
