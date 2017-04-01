package administrator.example.com.testxutilsexpandable;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import administrator.example.com.testxutilsexpandable.bean.Info;


public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.pu_listView)
    private PullToRefreshExpandableListView pu_listView;
    private List<Info.DataBean> list_data;
    private List<Info.DataBean> list = new ArrayList<>();
    @ViewInject(R.id.text_money)
    private TextView text_money;
    @ViewInject(R.id.bu_gc)
    private Button bu_gc;
    @ViewInject(R.id.text_count)
    private TextView text_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化控件
        x.view().inject(this);

        getServerData();

        //监听
        pu_listView.setMode(PullToRefreshBase.Mode.BOTH);
        pu_listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ExpandableListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
                pu_listView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pu_listView.onRefreshComplete();
                    }
                }, 2000);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ExpandableListView> refreshView) {
                pu_listView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pu_listView.onRefreshComplete();
                    }
                }, 2000);
            }
        });
    }

    //解析数据
    private void getServerData() {
        String url = "https://mock.eolinker.com/success/rq7m6GNqurs93zYkEANkY8Z4358Aihf1";
        RequestParams params = new RequestParams(url);
        params.setCacheMaxAge(1000 * 60);
        /*x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson = new Gson();
                Info info = gson.fromJson(result, Info.class);
                list_data = info.getData();
                for (int i = 0; i < list_data.size(); i++) {
                    Info.DataBean dataBean = list_data.get(i);
                    list.add(dataBean);
                }
                pu_listView.getRefreshableView().setAdapter(new MyAdapter());
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {


            }

            @Override
            public void onFinished() {

            }
        });*/

        x.http().get(params, new Callback.CacheCallback<String>() {
            private String result = null;

            @Override
            public void onSuccess(String result) {
                if (result != null) {
                    Gson gson = new Gson();
                    Info info = gson.fromJson(result, Info.class);
                    list_data = info.getData();
                    for (int i = 0; i < list_data.size(); i++) {
                        Info.DataBean dataBean = list_data.get(i);
                        list.add(dataBean);
                    }
                    pu_listView.getRefreshableView().setAdapter(new MyAdapter());
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

            @Override
            public boolean onCache(String result) {
                this.result = result;
                return true;
            }
        });
    }

    private class MyAdapter implements ExpandableListAdapter {
        private int money = 0;
        private int count = 0;

        @Override
        public void registerDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public void unregisterDataSetObserver(DataSetObserver observer) {

        }

        @Override
        public int getGroupCount() {
            return list.size();
        }

        @Override
        public int getChildrenCount(int grosition) {
            return list.get(grosition).getDatas().size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return list.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return list_data.get(groupPosition).getDatas().get(childPosition);
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
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.group_layout, null);
                holder = new ViewHolder();
                holder.text_title = (TextView) convertView.findViewById(R.id.text_title);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.text_title.setText(list.get(groupPosition).getTitle());
            return convertView;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            final ViewHolder holder;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.child_laout, null);
                holder = new ViewHolder();
                holder.type_name = (TextView) convertView.findViewById(R.id.type_name);
                holder.type_msg = (TextView) convertView.findViewById(R.id.type_msg);
                holder.text_price = (TextView) convertView.findViewById(R.id.text_price);
                holder.child_cb = (CheckBox) convertView.findViewById(R.id.child_cb);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.type_name.setText(list.get(groupPosition).getDatas().get(childPosition).getType_name());
            holder.type_msg.setText(list.get(groupPosition).getDatas().get(childPosition).getMsg());
            holder.text_price.setText("￥" + list.get(groupPosition).getDatas().get(childPosition).getPrice());
            holder.child_cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.child_cb.isChecked()) {
                        money = money + list.get(groupPosition).getDatas().get(childPosition).getPrice();
                        count++;
                        text_money.setText("合计：￥" + money + ".00");
                        text_count.setText(count + "台旧机");
                    } else {
                        money = money - list.get(groupPosition).getDatas().get(childPosition).getPrice();
                        count--;
                        text_money.setText("合计：￥" + money + ".00");
                        text_count.setText(count + "台旧机");
                    }

                }
            });
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public void onGroupExpanded(int groupPosition) {

        }

        @Override
        public void onGroupCollapsed(int groupPosition) {

        }

        @Override
        public long getCombinedChildId(long groupId, long childId) {
            return 0;
        }

        @Override
        public long getCombinedGroupId(long groupId) {
            return 0;
        }

        class ViewHolder {
            TextView text_title;
            TextView type_name;
            TextView type_msg;
            TextView text_price;
            // CheckBox cb;
            CheckBox child_cb;
        }
    }
}
