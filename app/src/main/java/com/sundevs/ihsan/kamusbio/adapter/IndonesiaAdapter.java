package com.sundevs.ihsan.kamusbio.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sundevs.ihsan.kamusbio.R;
import com.sundevs.ihsan.kamusbio.adapter.listener.ItemClickListener;
import com.sundevs.ihsan.kamusbio.model.Kamus;
import com.sundevs.ihsan.kamusbio.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 04, January, 2019
 * ------------------------------
 * This class for
 */
@SuppressLint({"InflateParams","SetTextI18n", "ResourceAsColor","SetTextI18n"})
public class IndonesiaAdapter extends  RecyclerView.Adapter<IndonesiaAdapter.ViewHolder> {
    private Context context;
    private List<Kamus> kamusList;

    public IndonesiaAdapter(Context context, List<Kamus> kamusList) {
        this.context = context;
        this.kamusList = kamusList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(layoutParams);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return kamusList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final Kamus kamus = kamusList.get(position);
        holder.bindTo(kamus);
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void refresh(List<Kamus> fill) {
        kamusList = new ArrayList<>();
        kamusList.addAll(fill);
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ItemClickListener itemClickListener;
        @BindView(R.id.img_list)
        ImageView imgList;
        @BindView(R.id.txt_kegiatan)
        TextView txtKegiatan;
        @BindView(R.id.text_tempat)
        TextView textTempat;
        @BindView(R.id.txt_tanggal)
        TextView txtTanggal;
        @BindView(R.id.card_view)
        CardView cardView;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }


        void setClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        void bindTo(Kamus kamus) {
            txtKegiatan.setText("Hewan\t\t\t\t : "+kamus.getNamaKamus());
            textTempat.setText("Bahasa Latin : " + kamus.getLatin());
            txtTanggal.setText("Deskripsi\t\t\t :  " + kamus.getDeskripsi());
            Glide.with(context)
                    .load(Constant.IMAGE_URL+kamus.getFoto())
                    .placeholder(R.drawable.ic_null)
                    .into(imgList);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v);
        }
    }
}
