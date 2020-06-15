package com.example.wanandroid.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wanandroid.R;
import com.example.wanandroid.constant.Constant;
import com.just.agentweb.AgentWeb;

public class WebViewActivity extends AppCompatActivity {
    Unbinder mBinder;

    @BindView(R.id.web_content)
    LinearLayout mWebContent;

    protected AgentWeb mAgentWeb;
    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.toolBar)

    Toolbar mToolBar;

    public static void actionStart(Context context, String title, String url) {
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.putExtra(Constant.ARTICLE_TITLE, title);
        intent.putExtra(Constant.ARTICLE_URL,url);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mBinder = ButterKnife.bind(this);
        Intent intent = getIntent();
        String title = intent.getStringExtra(Constant.ARTICLE_TITLE);
        String url = intent.getStringExtra(Constant.ARTICLE_URL);
        setUpActionBar(title);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(mWebContent, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(url);

    }

    /**
     * <p>设置actionbar的title</p>
     *
     * @param title title
     */
    private void setUpActionBar(String title) {

        setSupportActionBar(mToolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.back_selector);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            mTitle.setText(title);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mAgentWeb.getWebLifeCycle().onDestroy();
        super.onDestroy();
        mBinder.unbind();
    }
}

