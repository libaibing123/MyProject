package com.example.myjingdong2.ui.shopcart.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.myjingdong2.R;
import com.example.myjingdong2.bean.GetCartsBean;
import com.example.myjingdong2.bean.SellerBean;
import com.example.myjingdong2.ui.shopcart.presenter.ShopCartPresenter;
import com.example.myjingdong2.ui.widget.AddSubView;
import com.example.myjingdong2.utils.SharedPreferencesUtils;

import java.util.List;

import retrofit2.http.POST;

public class ElvShopcartAdapter2 extends BaseExpandableListAdapter{

    private Context context;
    private List<SellerBean> groupList;
    private List<List<GetCartsBean.DataBean.ListBean>> childList;
    private LayoutInflater inflater;
    private ProgressDialog progressDialog;
    private ShopCartPresenter shopCartPresenter;
    private final String uid;
    private final String token;
    private static  final  int CLICK_PRODUCT=0;
    private static  final int CLICK_SELLECT=1;
    private static int state=0;
    private int cIndex=0;//商家下的商品下标
    private int gIndex=1;//点击商家的下标
    private int sellected;//点击商家，该商品checkbox的选中状态。
    private int selected;


    public ElvShopcartAdapter2(Context context, List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList, ProgressDialog progressDialog, ShopCartPresenter shopCartPresenter) {
        this.context = context;
        this.groupList = groupList;
        this.childList = childList;
        this.progressDialog = progressDialog;
        this.shopCartPresenter = shopCartPresenter;
        uid = (String) SharedPreferencesUtils.getParam(context, "uid", "");
        token = (String) SharedPreferencesUtils.getParam(context, "token", "");



    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childList.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childList.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final GroupViewHolder groupViewHolder;
        if(convertView==null){
            groupViewHolder=new GroupViewHolder();
            convertView=inflater.inflate(R.layout.shopcart_seller_item,null);
            groupViewHolder.cbSeller=convertView.findViewById(R.id.cbSeller);
            groupViewHolder.tvSeller=convertView.findViewById(R.id.tvSeller);
            convertView.setTag(groupViewHolder);

        }else{

            groupViewHolder= (GroupViewHolder) convertView.getTag();


        }

        final SellerBean sellerBean = groupList.get(groupPosition);
        groupViewHolder.cbSeller.setChecked(sellerBean.isSelected());
        groupViewHolder.tvSeller.setText(sellerBean.getSellerName());


        groupViewHolder.cbSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state=CLICK_SELLECT;
                gIndex=groupPosition;

                //显示进度条
                progressDialog.show();
                GetCartsBean.DataBean.ListBean listBean = childList.get(groupPosition).get(cIndex);
                int pid = listBean.getPid();
                int num = listBean.getNum();
                selected = groupViewHolder.cbSeller.isChecked() ? 1 : 0;
                shopCartPresenter.updateCarts(uid,sellerBean.getSellerid()+"",pid+"",num+"",selected+"",token);





            }
        });
        return convertView;


    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ChildViewHolder childViewHolder;
        if(convertView==null){
            childViewHolder=new ChildViewHolder();
            convertView=inflater.inflate(R.layout.shopcart_seller_product_item,null);
           childViewHolder.cbProduct=convertView.findViewById(R.id.cbSeller);
           childViewHolder.iv=convertView.findViewById(R.id.iv);
           childViewHolder.tvTitle=convertView.findViewById(R.id.tvTitle);
           childViewHolder.tvPrice=convertView.findViewById(R.id.tvPrice);
           childViewHolder.tvDel=convertView.findViewById(R.id.tvDel);
           childViewHolder.addSubView=convertView.findViewById(R.id.addSubCard);
           convertView.setTag(childViewHolder);

        }else{

            childViewHolder= (ChildViewHolder) convertView.getTag();


        }
        final GetCartsBean.DataBean.ListBean listBean = childList.get(groupPosition).get(childPosition);
        childViewHolder.cbProduct.setChecked(listBean.getSelected()==1?true:false);
        childViewHolder.tvTitle.setText(listBean.getTitle());
        childViewHolder.tvPrice.setText(listBean.getPrice()+"");
        Glide.with(context).load(listBean.getImages().split("\\|")[0]).into(childViewHolder.iv);
        childViewHolder.addSubView.setNum(listBean.getNum()+"");
        //给二级列表的checkboc设置点击事件
        childViewHolder.cbProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state=CLICK_PRODUCT;
                //更新购物车
                SellerBean sellerBean = groupList.get(groupPosition);
                String sellerid = sellerBean.getSellerid();
                int pid = listBean.getPid();
                int num = listBean.getNum();
                int seleterd = childViewHolder.cbProduct.isChecked() ? 1 : 0;

                progressDialog.show();
                shopCartPresenter.updateCarts(uid,sellerid,pid+"",num+"",sellected+"",token);








            }
        });






        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
    class GroupViewHolder {
        CheckBox cbSeller;
        TextView tvSeller;
    }

    class ChildViewHolder {
        CheckBox cbProduct;
        ImageView iv;
        TextView tvTitle;
        TextView tvPrice;
        TextView tvDel;
        AddSubView addSubView;
    }

    /**
     * 更新购物车成功后走的方法
     *
     */
    public void updateSuccess(){
        progressDialog.dismiss();
        if(state==CLICK_PRODUCT){
            shopCartPresenter.getCarts(uid,token);


        }else if(state==CLICK_SELLECT){
            //判断该下商品下还有别的商品吗 如果有继续更新
            cIndex++;
            //获取该商品下的列表个数
            int size = childList.get(gIndex).size();
            if(cIndex>=gIndex){
                //该商品下的都已经遍历完成
                cIndex=0;
                shopCartPresenter.getCarts(uid,token);



            }else{
                //接着更新该商家下的其他商品
                SellerBean sellerBean = groupList.get(gIndex);
                GetCartsBean.DataBean.ListBean listBean = childList.get(gIndex).get(cIndex);
                int pid = listBean.getPid();
                int num = listBean.getNum();
                shopCartPresenter.updateCarts(uid, selected + "", pid + "", num + "", selected + "", token);

            }


        }

    }

}


