package com.lw;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;

import com.lw.adapter.EmojiAdapter;
import com.lw.emoji.AndroidEmojiPackage;
import com.lw.emoji.Emoji;
import com.lw.emoji.EmojiManager;
import com.lw.view.EmojiEditText;

public class MainActivity extends AppCompatActivity {

    private EmojiEditText   mEmojiEdit;

    private GridView        mEmojiView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        initView();
    }

    private void initView(){

        AndroidEmojiPackage androidEmojiPackage = new AndroidEmojiPackage();
        EmojiManager.getInstance().addEmoji(androidEmojiPackage);

        mEmojiEdit = (EmojiEditText) findViewById(R.id.emoji_edit);
        mEmojiView = (GridView) findViewById(R.id.emoji_list);

        EmojiAdapter emojiAdapter = new EmojiAdapter(this, androidEmojiPackage);
        mEmojiView.setAdapter(emojiAdapter);
        mEmojiView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Emoji emoji = (Emoji) parent.getAdapter().getItem(position);
                mEmojiEdit.getText().insert(mEmojiEdit.getSelectionStart(), emoji.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
