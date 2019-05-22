package kku.en.coe.extraproject;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

//    private List<ListItem> listItems;
    private Context context;
    private List<ObjectEvent> listItems;
    public MyAdapter(List<ObjectEvent> listItems, Context context) {
        this.listItems = listItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_recycler_view, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final ObjectEvent listItem = listItems.get(position);
        viewHolder.tvHead.setText(listItem.getEvName());
        viewHolder.tvDesc.setText(listItem.getTime());

//        String https_url = listItem.getImgUrl().replace("http","https");
//        Picasso.get()
//                .load(https_url)
//                .resize(50, 50)
//                .centerCrop()
//                .into(viewHolder.img);

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"clicked : " + listItem.getEmail(),Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvHead, tvDesc;
        public ImageView img;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvHead = itemView.findViewById(R.id.header);
            tvDesc = itemView.findViewById(R.id.desc);
            img = itemView.findViewById(R.id.imgView);
            linearLayout = itemView.findViewById(R.id.lnLayout);
        }
    }
}
