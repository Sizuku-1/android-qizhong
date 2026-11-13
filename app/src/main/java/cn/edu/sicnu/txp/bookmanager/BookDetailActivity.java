package cn.edu.sicnu.txp.bookmanager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class BookDetailActivity extends Activity {
    private static final String EXTRA_TITLE = "book_title";
    private static final String EXTRA_AUTHOR = "book_author";
    private static final String EXTRA_PUBLISHER = "book_publisher";
    private static final String EXTRA_PRICE = "book_price";

    static Intent createIntent(Context context, Book book) {
        return new Intent(context, BookDetailActivity.class)
                .putExtra(EXTRA_TITLE, book.getTitle())
                .putExtra(EXTRA_AUTHOR, book.getAuthor())
                .putExtra(EXTRA_PUBLISHER, book.getPublisher())
                .putExtra(EXTRA_PRICE, book.getPrice());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Intent selectedBook = getIntent();
        TextView title = findViewById(R.id.detail_book_title);
        TextView author = findViewById(R.id.detail_book_author);
        TextView publisher = findViewById(R.id.detail_book_publisher);
        TextView price = findViewById(R.id.detail_book_price);

        title.setText(selectedBook.getStringExtra(EXTRA_TITLE));
        author.setText(selectedBook.getStringExtra(EXTRA_AUTHOR));
        publisher.setText(selectedBook.getStringExtra(EXTRA_PUBLISHER));
        price.setText(getString(R.string.price_format, selectedBook.getDoubleExtra(EXTRA_PRICE, 0)));

        findViewById(R.id.back_to_list).setOnClickListener(view -> finish());
    }
}
