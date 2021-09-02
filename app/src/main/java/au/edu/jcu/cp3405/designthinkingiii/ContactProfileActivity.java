package au.edu.jcu.cp3405.designthinkingiii;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContactProfileActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public Adapter mAdapter;
    public static final int CONTACTLOADER = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_profile);

        FloatingActionButton  fab = findViewById(R.id.fab);
        fab.setOnClickListener(v -> {
            Intent intent = new Intent(ContactProfileActivity.this, ContactProfileEditorActivity.class);
            startActivity(intent);
        });

        ListView listView = findViewById(R.id.list);
        mAdapter = new Adapter(this, null);
        listView.setAdapter(mAdapter);

        // whenever we press a listview for updating

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(ContactProfileActivity.this, ContactProfileEditorActivity.class);
            Uri newUri = ContentUris.withAppendedId(Contract.ContactEntry.CONTENT_URI, id);
            intent.setData(newUri);
            startActivity(intent);

        });

        // get the loader running
        LoaderManager.getInstance(this).initLoader(CONTACTLOADER, null, this);


    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection = {Contract.ContactEntry._ID,
                Contract.ContactEntry.COLUMN_NAME,
                Contract.ContactEntry.COLUMN_EMAIL,
                Contract.ContactEntry.COLUMN_PICTURE,
                Contract.ContactEntry.COLUMN_PHONENUMBER,
                Contract.ContactEntry.COLUMN_TYPEOFCONTACT
        };

        return new CursorLoader(this, Contract.ContactEntry.CONTENT_URI,
                projection, null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        mAdapter.swapCursor(null);

    }
}