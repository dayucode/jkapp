package com.jk.dayu.jkapp.BaseModule;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jk.dayu.jkapp.MineModule.MineFragment;
import com.jk.dayu.jkapp.TipsModule.TipsFragment;
import com.jk.dayu.jkapp.R;
import com.jk.dayu.jkapp.untils.StatusBarUtil;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup rg_tab_bar;
    private RadioButton rb_channel;

    //Fragment Object
    private HomeFragment homeFragment;
    private TipsFragment tipsFragment;
    private MineFragment mineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rg_tab_bar.setOnCheckedChangeListener(this);
        //获取第一个单选按钮，并设置其为选中状态
        rb_channel = (RadioButton) findViewById(R.id.rb_home);
        rb_channel.setChecked(true);
        DbUtils.createDb(this);

    }

    @Override
    protected void onResume() {
        if (DataManager.isLogin(this)){
            homeFragment.initData();
            homeFragment.loginBtn.setVisibility(View.INVISIBLE);
        }else {
            homeFragment.loginBtn.setVisibility(View.VISIBLE);
        }
        super.onResume();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction fTransaction = getSupportFragmentManager().beginTransaction();

        hideAllFragment(fTransaction);
        switch (checkedId){
            case R.id.rb_home:
                if(homeFragment == null){
                    homeFragment = new HomeFragment();
                    fTransaction.add(R.id.ly_content,homeFragment);
                }else{
                    fTransaction.show(homeFragment);
                }
                break;
            case R.id.rb_tips:
                if(tipsFragment == null){
                    tipsFragment = new TipsFragment();
                    fTransaction.add(R.id.ly_content,tipsFragment);
                }else{
                    fTransaction.show(tipsFragment);
                }
                break;
            case R.id.rb_mine:
                if(mineFragment == null){
                    mineFragment = new MineFragment();
                    fTransaction.add(R.id.ly_content,mineFragment);
                }else{
                    fTransaction.show(mineFragment);
                }
                break;
        }
        fTransaction.commit();
    }

    //隐藏所有Fragment
    private void hideAllFragment(FragmentTransaction fragmentTransaction){
        if(homeFragment != null)fragmentTransaction.hide(homeFragment);
        if(tipsFragment != null)fragmentTransaction.hide(tipsFragment);
        if(mineFragment != null)fragmentTransaction.hide(mineFragment);
    }
}
