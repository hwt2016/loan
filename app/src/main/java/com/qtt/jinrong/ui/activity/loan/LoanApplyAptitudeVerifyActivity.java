package com.qtt.jinrong.ui.activity.loan;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.qtt.framework.util.GeneratedClassUtils;
import com.qtt.jinrong.R;
import com.qtt.jinrong.bean.Response;
import com.qtt.jinrong.bean.loan.LoanApplyRequest;
import com.qtt.jinrong.enums.CarLinscePositionEnum;
import com.qtt.jinrong.enums.CarPropertyEnum;
import com.qtt.jinrong.enums.CompanyPositionEnum;
import com.qtt.jinrong.enums.CreditOverdueEnum;
import com.qtt.jinrong.enums.CreditSituationEnum;
import com.qtt.jinrong.enums.CreditTotalLimitEnum;
import com.qtt.jinrong.enums.CreditUsedLimitEnum;
import com.qtt.jinrong.enums.HousePropertyEnum;
import com.qtt.jinrong.enums.HousePropertyPositionEnum;
import com.qtt.jinrong.enums.HousePropertyMortgageSituationEnum;
import com.qtt.jinrong.enums.IdentityEnum;
import com.qtt.jinrong.enums.IncomePayMethodEnum;
import com.qtt.jinrong.enums.LegalPersonEnum;
import com.qtt.jinrong.enums.OperatorYearsEnum;
import com.qtt.jinrong.enums.SocialFundEnum;
import com.qtt.jinrong.enums.WorkYearsEnum;
import com.qtt.jinrong.presenter.ILoanApplyPresenter;
import com.qtt.jinrong.presenter.impl.LoanApplyPresenterImpl;
import com.qtt.jinrong.ui.activity.common.BaseSelectActivity;
import com.qtt.jinrong.ui.widget.CommonTitleBar;
import com.qtt.jinrong.ui.widget.SelectPopView;
import com.qtt.jinrong.ui.widget.text.InputEditText;
import com.qtt.jinrong.util.ToastUtil;
import com.qtt.jinrong.view.ILoanApplyView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 贷款资质审核
 * Created by yanxin on 16/3/9.
 */
@EActivity(R.layout.activity_loan_aptitude_verify)
public class LoanApplyAptitudeVerifyActivity extends BaseSelectActivity implements ILoanApplyView {

    public static final String INTENT_PRODUCT_ID = "INTENT_PRODUCT_ID";
    public static final String INTENT_RESPONSE_TERM = "INTENT_RESPONSE_TERM";
    public static final String INTENT_RESPONSE_AMOUNT = "INTENT_RESPONSE_AMOUNT";

    @ViewById(R.id.titleBar)
    CommonTitleBar mTitleBar;

    @ViewById(R.id.identity)
    TextView mIdentifyText;

    @ViewById(R.id.enterprisePersonalMore)
    View enterprisePersonalMore;
    @ViewById(R.id.legalPerson)
    TextView legalPersonText;
    @ViewById(R.id.companyPosition)
    TextView companyPositionText;
    @ViewById(R.id.operationPeriod)
    TextView operationPeriodText;

    @ViewById(R.id.workerOtherMore)
    View workerOtherMore;
    @ViewById(R.id.companyWorkYears)
    TextView companyWorkYearsText;
    @ViewById(R.id.incomePayWay)
    TextView incomePayWayText;
    @ViewById(R.id.monthSalary)
    InputEditText monthSalaryEdit;
    @ViewById(R.id.socialFund)
    TextView socialFundText;

    @ViewById(R.id.age)
    InputEditText mAgeEdit;
    @ViewById(R.id.creditStation)
    TextView mCreditText;
    @ViewById(R.id.creditOverdueMore)
    View creditOverdueMore;
    @ViewById(R.id.creditOverdueSituation)
    TextView overdueText;
    @ViewById(R.id.creditLimit)
    TextView mCreditLimitText;
    @ViewById(R.id.creditMore)
    View creditMore;
    @ViewById(R.id.creditUsed)
    TextView creditUsedText;
    @ViewById(R.id.houseproperty)
    TextView mHousePropertyText;
    @ViewById(R.id.housePropertyMore)
    View housePropertyMore;
    @ViewById(R.id.housePropertyPosition)
    TextView housePropertyPositionText;
    @ViewById(R.id.housePropertySituaion)
    TextView housePropertySituaionText;
    @ViewById(R.id.carproperty)
    TextView mCarPropertyText;
    @ViewById(R.id.carPropertyMore)
    View carPropertyMore;
    @ViewById(R.id.carLicense)
    TextView carLicenseText;

    String productId;
    int term,amount;
    ILoanApplyPresenter mPresenter;
    LoanApplyRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        productId = mIntent.getStringExtra(INTENT_PRODUCT_ID);
        term = mIntent.getIntExtra(INTENT_RESPONSE_TERM, 0);
        amount = mIntent.getIntExtra(INTENT_RESPONSE_AMOUNT,0);
        mPresenter = new LoanApplyPresenterImpl(this);
        request = new LoanApplyRequest();
    }

    @AfterViews
    void initViews() {
        mTitleBar.setTitle(getString(R.string.loan_vertify_title));
        mTitleBar.setActivity(this);
    }

    @Click(R.id.identity)
    void clickIdentity() {
        mSelectView.setData(IdentityEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                IdentityEnum mEnums = IdentityEnum.values()[position];
                mIdentifyText.setText(val);
                request.setCapacity(mEnums.getCode());
                if (mEnums.equals(IdentityEnum.企业户) || mEnums.equals(IdentityEnum.个体户)) {
                    enterprisePersonalMore.setVisibility(View.VISIBLE);
                    workerOtherMore.setVisibility(View.GONE);
                } else if (mEnums.equals(IdentityEnum.工薪族) || mEnums.equals(IdentityEnum.其他)) {
                    enterprisePersonalMore.setVisibility(View.GONE);
                    workerOtherMore.setVisibility(View.VISIBLE);
                }
            }
        });
        show();
    }

    //企业主 个体户
    @Click(R.id.legalPerson)
    void clickLegalPerson() {
        mSelectView.setData(LegalPersonEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                LegalPersonEnum mEnum = LegalPersonEnum.values()[position];
                request.setLegal(mEnum.getCode());
                legalPersonText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.companyPosition)
    void clickCompanyPosition() {
        mSelectView.setData(CompanyPositionEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CompanyPositionEnum mEnums = CompanyPositionEnum.values()[position];
                request.setCompanyPosition(mEnums.getCode());
                companyPositionText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.operationPeriod)
    void clickOperationPeriod() {
        mSelectView.setData(OperatorYearsEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                OperatorYearsEnum mEnums = OperatorYearsEnum.values()[position];
                request.setOperatorYear(mEnums.getCode());
                operationPeriodText.setText(val);
            }
        });
        show();
    }

    //工薪族 其他
    @Click(R.id.companyWorkYears)
    void clickCompanyWorkYears() {
        mSelectView.setData(WorkYearsEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                WorkYearsEnum mEnums = WorkYearsEnum.values()[position];
                request.setCurrSeniority(mEnums.getCode());
                companyWorkYearsText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.incomePayWay)
    void clickIncomePayWay() {
        mSelectView.setData(IncomePayMethodEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                IncomePayMethodEnum mEnums = IncomePayMethodEnum.values()[position];
                request.setPayWay(mEnums.getCode());
                incomePayWayText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.socialFund)
    void clickSocialFund() {
        mSelectView.setData(SocialFundEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                SocialFundEnum mEnums = SocialFundEnum.values()[position];
                request.setSocialSecurity(mEnums.getCode());
                socialFundText.setText(val);
            }
        });
        show();
    }
    //公共属性
    @Click(R.id.creditStation)
    void clickCreditStation() {
        mSelectView.setData(CreditSituationEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CreditSituationEnum mEnum = CreditSituationEnum.values()[position];
                if(mEnum.equals(CreditSituationEnum.有逾期)) {
                    creditOverdueMore.setVisibility(View.VISIBLE);
                } else {
                    creditOverdueMore.setVisibility(View.GONE);
                }
                request.setCredit(mEnum.getCode());
                mCreditText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.creditOverdueSituation)
    void clickOverdue() {
        mSelectView.setData(CreditOverdueEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CreditOverdueEnum mEnum = CreditOverdueEnum.values()[position];
                request.setOverdue(mEnum.getCode());
                overdueText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.creditLimit)
    void clickCreditLimit() {
        mSelectView.setData(CreditTotalLimitEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CreditTotalLimitEnum mEnum = CreditTotalLimitEnum.values()[position];
                if (mEnum.equals(CreditTotalLimitEnum.无信用卡)) creditMore.setVisibility(View.GONE);
                else creditMore.setVisibility(View.VISIBLE);
                request.setCreMoney(mEnum.getCode());
                mCreditLimitText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.creditUsed)
    void clickCreditUsed() {
        mSelectView.setData(CreditUsedLimitEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CreditUsedLimitEnum mEnum = CreditUsedLimitEnum.values()[position];
                request.setCreUsed(mEnum.getCode());
                creditUsedText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.houseproperty)
    void clickHuseProperty() {
        mSelectView.setData(HousePropertyEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                HousePropertyEnum mEnum = HousePropertyEnum.values()[position];
                if (mEnum.equals(HousePropertyEnum.无房产)) housePropertyMore.setVisibility(View.GONE);
                else housePropertyMore.setVisibility(View.VISIBLE);
                request.setHouse(mEnum.getCode());
                mHousePropertyText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.housePropertyPosition)
    void clickHousePropertyPosition() {
        mSelectView.setData(HousePropertyPositionEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                HousePropertyPositionEnum mEnum = HousePropertyPositionEnum.values()[position];
                request.setDistrict(mEnum.getCode());
                housePropertyPositionText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.housePropertySituaion)
    void clickHousePropertySituaion() {
        mSelectView.setData(HousePropertyMortgageSituationEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                HousePropertyMortgageSituationEnum mEnum = HousePropertyMortgageSituationEnum.values()[position];
                request.setMortgage(mEnum.getCode());
                housePropertySituaionText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.carproperty)
    void clickCarProperty() {
        mSelectView.setData(CarPropertyEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CarPropertyEnum mEnum = CarPropertyEnum.values()[position];
                if (mEnum.equals(CarPropertyEnum.无车产)) carPropertyMore.setVisibility(View.GONE);
                else carPropertyMore.setVisibility(View.VISIBLE);
                request.setCar(mEnum.getCode());
                mCarPropertyText.setText(val);
            }
        });
        show();
    }
    @Click(R.id.carLicense)
    void clickCarLicense() {
        mSelectView.setData(CarLinscePositionEnum.getValues());
        mSelectView.setSelectCallback(new SelectPopView.SelectCallback() {
            @Override
            public void onItemSelect(int position, String val) {
                CarLinscePositionEnum mEnum = CarLinscePositionEnum.values()[position];
                request.setLinscebelong(mEnum.getCode());
                carLicenseText.setText(val);
            }
        });
        show();
    }

    @Click(R.id.btnNext)
    void clickBtnNext() {
        IdentityEnum mEnums = IdentityEnum.find(request.getCapacity());
        if(mEnums == null) {
            ToastUtil.showShortToast("请选择身份");
            return;
        }
        if(mEnums.equals(IdentityEnum.企业户) || mEnums.equals(IdentityEnum.个体户)) {
            if(request.getLegal() == null) {
                ToastUtil.showShortToast("请选择法人或股东");
                return;
            }
            if(request.getCompanyPosition() == null) {
                ToastUtil.showShortToast("请选择企业经营地");
                return;
            }
            if(request.getOperatorYear() == null) {
                ToastUtil.showShortToast("请选择经营年限");
                return;
            }
        } else {
            if(request.getCurrSeniority() == null) {
                ToastUtil.showShortToast("请选择现单位工龄");
                return;
            }
            if(request.getPayWay() == null) {
                ToastUtil.showShortToast("请选择收入发放方式");
                return;
            }
            if(TextUtils.isEmpty(monthSalaryEdit.getString())) {
                ToastUtil.showShortToast("请填写月均总收入");
                return;
            }
            if(request.getSocialSecurity() == null) {
                ToastUtil.showShortToast("请选择社保和公积金");
                return;
            }
        }

        Integer age = 0;
        if(!TextUtils.isEmpty(mAgeEdit.getString())) {
            age = Integer.valueOf(mAgeEdit.getString());
        }
        if(age.intValue() == 0) {
            ToastUtil.showShortToast("请填写年龄");
            return;
        } else {
            request.setAge(age);
        }

        CreditSituationEnum scEnum = CreditSituationEnum.find(request.getCredit());
        if(scEnum == null) {
            ToastUtil.showShortToast("请选择信用情况");
            return;
        }
        if(scEnum.equals(CreditSituationEnum.有逾期)) {
            if(request.getOverdue() == null) {
                ToastUtil.showShortToast("请选择逾期情况");
                return;
            }
        }

        CreditTotalLimitEnum ctlEnum = CreditTotalLimitEnum.find(request.getCreMoney());
        if(ctlEnum == null) {
            ToastUtil.showShortToast("请选择信用卡总额度");
            return;
        }
        if(!ctlEnum.equals(CreditTotalLimitEnum.无信用卡)) {
            if(request.getCreUsed() == null) {
                ToastUtil.showShortToast("请选择已使用额度");
                return;
            }
        }

        HousePropertyEnum hpEnum = HousePropertyEnum.find(request.getHouse());
        if(hpEnum == null) {
            ToastUtil.showShortToast("请选择房产信息");
            return;
        }
        if(!hpEnum.equals(HousePropertyEnum.无房产)) {
            if(request.getDistrict() == null) {
                ToastUtil.showShortToast("请选择房产位置");
                return;
            }
            if(request.getMortgage() == null) {
                ToastUtil.showShortToast("请选择房产抵押/按揭情况");
                return;
            }
        }

        CarPropertyEnum cpEnum = CarPropertyEnum.find(request.getCar());
        if(cpEnum == null) {
            ToastUtil.showShortToast("请选择车产信息");
            return;
        }
        if(cpEnum.equals(CarPropertyEnum.有车产)) {
            if(request.getLinscebelong() == null) {
                ToastUtil.showShortToast("请选择牌照归属地");
                return;
            }
        }

        mPresenter.apply();
    }

    /*** ILoanApplyView ***/
    @Override
    public void onRequestVerify() {

    }

    @Override
    public LoanApplyRequest getReauest() {
        if(!TextUtils.isEmpty(monthSalaryEdit.getString()))
            request.setSalary(Integer.valueOf(monthSalaryEdit.getString()));
        if(!TextUtils.isEmpty(mAgeEdit.getString()))
            request.setAge(Integer.valueOf(mAgeEdit.getString()));

        return request;
    }

    @Override
    public void onApply(Response response) {
        Intent intent = new Intent(this, GeneratedClassUtils.get(LoanApplyResultActivity.class));
        intent.putExtra(LoanApplyResultActivity.INTENT_RESPONSE_SUCCESS,response.isSuccess());
        intent.putExtra(LoanApplyResultActivity.INTENT_RESPONSE_MESSAGE,response.getMessage());
        intent.putExtra(LoanApplyResultActivity.INTENT_RESPONSE_AMOUNT,amount);
        intent.putExtra(LoanApplyResultActivity.INTENT_RESPONSE_TERM,term);
        startActivity(intent);
        finish();
    }
    /*** ILoanApplyView ***/
}