package com.android.finance.ui.activity.credit;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.finance.R;
import com.android.finance.ui.activity.common.BaseActivity;
import com.android.finance.ui.widget.CommonTitleBar;
import com.android.finance.ui.widget.ImgText;
import com.android.finance.ui.widget.TabIndictor;
import com.android.finance.ui.widget.dialog.AlertDialogUtils;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by yanxin on 16/2/25.
 */
@EActivity(R.layout.activity_credit_detail)
public class CreditDetailActivity extends BaseActivity {

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.img)
    ImageView mIcon;

    @ViewById(R.id.name)
    TextView mCreditName;

    @ViewById(R.id.free)
    TextView mFreeDays;

    @ViewById(R.id.level)
    TextView mLevelText;

    @ViewById(R.id.cashPerscent)
    TextView mCashPerscent;

    @ViewById(R.id.currency)
    TextView mCurrency;

    @ViewById(R.id.info)
    TextView mInfo;

    @ViewById(R.id.material)
    TabIndictor mTabIndictor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    public void initView() {
        mTitleBar.setTitle(getString(R.string.credit_detail_title));
        mTitleBar.setActivity(this);

        mTabIndictor.setAdapter(new TabIndictor.BaseAdapter() {
            @Override
            public String getString(int position) {
                return "";
            }
        });
    }

    @Click(R.id.btnSubmit)
    void clickBtnSubmit() {

    }

}
