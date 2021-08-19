package com.example.common.provider;

import ohos.aafwk.ability.AbilitySlice;
import ohos.agp.components.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class BaseProvider<Data> extends BaseItemProvider
        implements ListContainer.ItemClickedListener, ListContainer.ItemLongClickedListener{
    protected AbilitySlice abilitySlice;
    protected List<Data> mDataList;
    protected ProviderListenerImpl<Data> listener;

    public BaseProvider(AbilitySlice abilitySlice) {
        this(abilitySlice, new ArrayList<>());
    }

    public BaseProvider(AbilitySlice abilitySlice, List<Data> mDataList) {
        this(abilitySlice, mDataList, new ProviderListenerImpl<Data>() {
            @Override
            public void onClicked(ComponentHolder<Data> holder) {
                super.onClicked(holder);
            }

            @Override
            public void onLongClicked(ComponentHolder<Data> holder) {
                super.onLongClicked(holder);
            }
        });
    }

    public BaseProvider(AbilitySlice abilitySlice, List<Data> mDataList, ProviderListenerImpl<Data> listener) {
        this.abilitySlice = abilitySlice;
        this.mDataList = mDataList;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return mDataList.size();
    }

    @Override
    public Data getItem(int i) {
        return mDataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    /**
     * 创建holder，可根据data创建不同的holder，以实现多种不同的item
     * @param component item的父component
     * @param data 该item容纳的数据对象
     * @return holder
     */
    protected abstract ComponentHolder<Data> componentHolder(Component component, Data data);

    /**
     * 返回相应item的layout的id
     * @param data 相应item容纳的数据对象
     * @param position item在list中的位置
     * @return layout的id
     */
    protected abstract int itemLayoutId(Data data, int position);

    @SuppressWarnings("unchecked")
    @Override
    public Component getComponent(int i, Component component, ComponentContainer componentContainer) {
        if(component == null) {
            int layoutId = this.itemLayoutId(this.getItem(i), i);
            component = LayoutScatter.getInstance(
                    abilitySlice.getContext()).parse(layoutId, null, false);
            component.setTag(componentHolder(component, this.getItem(i)));
        } else {
            ComponentHolder<Data> holder = (ComponentHolder<Data>) component.getTag();
            holder.bind(this.getItem(i));
        }

        return component;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onItemClicked(ListContainer listContainer, Component component, int i, long l) {
        ComponentHolder<Data> holder = (ComponentHolder<Data>) component.getTag();
        listener.onClicked(holder);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean onItemLongClicked(ListContainer listContainer, Component component, int i, long l) {
        ComponentHolder<Data> holder = (ComponentHolder<Data>) component.getTag();
        listener.onLongClicked(holder);
        return true;
    }

    /**
     * 添加数据
     * @param data 泛型数据
     */
    public void add(Data data) {
        mDataList.add(data);
        notifyDataSetItemInserted(mDataList.size()-1);
    }


    public void add(Data... dataList) {
        Collections.addAll(mDataList, dataList);
        notifyDataSetItemRangeInserted(mDataList.size()-dataList.length, mDataList.size()-1);
    }

    public void insert(Data data, int position) {
        mDataList.add(position, data);
        notifyDataSetItemRangeChanged(position, mDataList.size()-1);
    }

    public void remove(Data data) {
        mDataList.remove(data);
        notifyDataChanged();
    }

    public void remove(int position) {
        mDataList.remove(position);
        notifyDataSetItemRangeChanged(position, mDataList.size()-2);
    }

    public void replace(List<Data> dataList) {
        mDataList = dataList;
        notifyDataChanged();
    }

    /**
     * 该类与item的父component绑定，并且可以在子类中添加其他的控件。
     * @param <Data> 泛型参数
     */
    public static abstract class ComponentHolder<Data> {
        protected Data mData;

        public ComponentHolder(Component component, Data data) {
            bind(data);
        }

        public void bind(Data data) {
            mData = data;
            onBind(data);
        }

        /**
         * 在该方法里面，子类初始化控件
         * @param data
         */
        protected abstract void onBind(Data data);

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
        }
    }


    private interface ProviderListener<Data> {
        void onClicked(ComponentHolder<Data> holder);
        void onLongClicked(ComponentHolder<Data> holder);
    }

    /**
     * 用抽象类去先实现一次ProviderListener<Data>接口，那么子类就不需要同时实现两个接口方法，按需实现即可
     * @param <Data> 泛型参数，即List里的数据的类型
     */
    public static abstract class ProviderListenerImpl<Data> implements ProviderListener<Data> {
        @Override
        public void onClicked(ComponentHolder<Data> holder) {

        }

        @Override
        public void onLongClicked(ComponentHolder<Data> holder) {

        }
    }
}
