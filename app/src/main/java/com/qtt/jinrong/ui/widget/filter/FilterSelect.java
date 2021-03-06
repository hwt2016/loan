package com.qtt.jinrong.ui.widget.filter;

/**
 * Created by yanxin on 16/2/29.
 */
public interface FilterSelect {

    /**
     * 数据适配器
     * @param baseFilterSelectAdapter
     */
    void setAdapter(BaseFilterAdapter baseFilterSelectAdapter);

    /**
     * 设置选中项
     * @param position   第几个选项卡
     * @param index      选项卡下的第几个选项
     * @param defaultStr 默认填写在筛选项的值
     */
    void setSelect(int position,int index,String defaultStr);

    void setSelectLisenter(SelectLisenter mSelectLisenter);

    interface SelectLisenter {
        void onSelect(int position,int index,String val);
    }
}
