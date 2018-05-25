package com.example.myrikao0517.ui.classfiy;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.example.myrikao0517.R;
import com.example.myrikao0517.base.BaseFragment;
import com.example.myrikao0517.bean.CatagoryBean;
import com.example.myrikao0517.bean.ProductCatagoryBean;
import com.example.myrikao0517.inter.OnItemClickListener;
import com.example.myrikao0517.ui.classfiy.adapter.ElvAdapter;
import com.example.myrikao0517.ui.classfiy.adapter.RvLeftAdapter;
import com.example.myrikao0517.ui.classfiy.content.ClassifyConntet;
import com.example.myrikao0517.ui.classfiy.presenter.ClassifyPresenter;

import java.util.ArrayList;
import java.util.List;


public class ClassifyFragment extends BaseFragment<ClassifyPresenter> implements ClassifyConntet.View {


    private View view;
    private ImageView mIvZxing;
    private RecyclerView mRvLeft;
    private ImageView mIv;
    private ExpandableListView mElv;

    @Override
    public int getContentLayout() {
        return R.layout.fragment_class;
    }

    @Override
    public void inject() {

    }

    @Override
    public void initView(View view) {

        mIvZxing = (ImageView) view.findViewById(R.id.ivZxing);
        mRvLeft = (RecyclerView) view.findViewById(R.id.rvLeft);
        mIv = (ImageView) view.findViewById(R.id.iv);
        mElv = (ExpandableListView) view.findViewById(R.id.elv);
        mIv.setBackgroundResource(R.drawable.timg);

        mPresenter.getCatagory();

    }

    @Override
    public void getProductCatagorySuccess(ProductCatagoryBean productCatagoryBean) {
        //定义一个集合用于存放title
        List<String> groupList = new ArrayList<>();
        //定义一个集合用于存放title对应的内容
        List<List<ProductCatagoryBean.DataBean.ListBean>> childList = new ArrayList<>();
        List<ProductCatagoryBean.DataBean> data = productCatagoryBean.getData();
        for (int i = 0; i < data.size(); i++) {
            groupList.add(data.get(i).getName());
            List<ProductCatagoryBean.DataBean.ListBean> list = data.get(i).getList();
            childList.add(list);
        }
        //使用ExpandableListView展示数据
        ElvAdapter elvAdapter = new ElvAdapter(getContext(), groupList, childList);
        mElv.setAdapter(elvAdapter);
        //默认展开列表
        for (int i = 0; i < groupList.size(); i++) {
            mElv.expandGroup(i);
        }

        productCatagoryBean.getMsg();


    }

    @Override
    public void getCatagorySuccess(final CatagoryBean catagoryBean) {

        final List<CatagoryBean.DataBean> data = catagoryBean.getData();
        //设置布局管理器
        mRvLeft.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvLeft.addItemDecoration(new DividerItemDecoration(getContext(), RecyclerView.VERTICAL));
        //创建适配器
        final RvLeftAdapter adapter = new RvLeftAdapter(getContext(), data);
        //显示左侧列表数据
        mRvLeft.setAdapter(adapter);

        int cid = data.get(0).getCid();
        mPresenter.getProductCatagory(cid + "");

        //设置第一个为默认选中
        adapter.changeCheck(0, true);

        //设置点击事件
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //改变DataBean里面check的属性值
                adapter.changeCheck(position, true);
                //请求右侧对应的数据
                mPresenter.getProductCatagory(catagoryBean.getData().get(position).getCid() + "");
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });
    }




}
