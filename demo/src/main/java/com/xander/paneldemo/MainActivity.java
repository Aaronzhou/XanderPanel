package com.xander.paneldemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.xander.panel.PanelInterface;
import com.xander.panel.XanderPanel;

public class MainActivity extends AppCompatActivity {

    private int mButtonsId[] = {
            R.id.top_with_controller,
            R.id.bottom_with_controller,
            R.id.sheet,
            R.id.top_without_controller,
            R.id.bottom_without_controller,
            R.id.top_custom_view,
            R.id.bottom_custom_view
    };

    private Context mContext;
    private LayoutInflater mInflater;

    private static Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        mInflater = LayoutInflater.from(mContext);

        for (int i = 0; i < mButtonsId.length; i++) {
            View v = findViewById(mButtonsId[i]);
            v.setOnClickListener(mOnClickListener);
        }
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            XanderPanel.Builder mBuilder = new XanderPanel.Builder(MainActivity.this);
            switch (v.getId()) {
                case R.id.top_with_controller:
                    mBuilder.setTitle("Title")
                            .setIcon(R.mipmap.ic_launcher)
                            .setMessage("I am Message!!!")
                            .setGravity(Gravity.TOP)
                            .setController("Cancel", "Sure", new PanelInterface.PanelControllerListener() {
                                @Override
                                public void onPanelNagetiiveClick(XanderPanel panel) {
                                    toast("onPanelNagetiiveClick");
                                }

                                @Override
                                public void onPanelPositiveClick(XanderPanel panel) {
                                    toast("onPanelPositiveClick");
                                }
                            })
                            .setCanceledOnTouchOutside(false);
                    break;
                case R.id.bottom_with_controller:
                    mBuilder.setTitle("Title")
                            .setIcon(R.mipmap.ic_launcher)
                            .setMessage("I am Message!!!")
                            .setGravity(Gravity.BOTTOM)
                            .setController("Cancel", "Sure", new PanelInterface.PanelControllerListener() {
                                @Override
                                public void onPanelNagetiiveClick(XanderPanel panel) {
                                    toast("onPanelNagetiiveClick");
                                }

                                @Override
                                public void onPanelPositiveClick(XanderPanel panel) {
                                    toast("onPanelPositiveClick");
                                }
                            })
                            .setCanceledOnTouchOutside(false);
                    break;
                case R.id.sheet:
                    mBuilder.setSheet(
                            new String[]{"I","am","sheet","item"},
                            true,
                            new PanelInterface.SheetListener() {
                                @Override
                                public void onSheetItemClick(int position) {
                                    toast("you click sheet item " + position);
                                }

                                @Override
                                public void onSheetCancelClick() {
                                    toast("sheet cancel");
                                }
                            }
                    );
                    break;
                case R.id.top_without_controller:
                    mBuilder.setTitle("Title")
                            .setIcon(R.mipmap.ic_launcher)
                            .setMessage("I am Message!!!")
                            .setGravity(Gravity.TOP)
                            .setCanceledOnTouchOutside(true);
                    break;
                case R.id.bottom_without_controller:
                    mBuilder.setTitle("Title")
                            .setIcon(R.mipmap.ic_launcher)
                            .setMessage("I am Message!!!")
                            .setGravity(Gravity.BOTTOM)
                            .setCanceledOnTouchOutside(true);
                    break;
                case R.id.top_custom_view:
                    mBuilder.setGravity(Gravity.TOP);
                    mBuilder.setCanceledOnTouchOutside(true);
                    View mCustomView = mInflater.inflate(R.layout.custom_layout, null);
                    mBuilder.setView(mCustomView);
                    break;
                case R.id.bottom_custom_view:
                    mBuilder.setCanceledOnTouchOutside(true);
                    mBuilder.setGravity(Gravity.BOTTOM);
                    View mCustomViewBottom = mInflater.inflate(R.layout.custom_layout, null);
                    mBuilder.setView(mCustomViewBottom);
                    break;
            }
            XanderPanel xanderPanel = mBuilder.create();
            xanderPanel.show();
        }
    };

    private void toast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

}
