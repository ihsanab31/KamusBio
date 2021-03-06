package com.skripsi.dija.kamusbio.adapter;

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
import com.skripsi.dija.kamusbio.R;
import com.skripsi.dija.kamusbio.utils.listener.ItemClickListener;
import com.skripsi.dija.kamusbio.model.KamusItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


@SuppressLint({"InflateParams","SetTextI18n", "ResourceAsColor","SetTextI18n"})
public class IndonesiaAdapter extends  RecyclerView.Adapter<IndonesiaAdapter.ViewHolder> {
    private Context context;
    private List<KamusItem> kamusList;

    public IndonesiaAdapter(Context context, List<KamusItem> kamusList) {
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
        final KamusItem kamus = kamusList.get(position);
        holder.bindTo(kamus);
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void refresh(List<KamusItem> fill) {
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

        void bindTo(KamusItem kamus) {
            txtKegiatan.setText("Tumbuhan\t\t: "+kamus.getNamaKamus());
            textTempat.setText("Bahasa Latin\t: " + kamus.getLatin());
            txtTanggal.setText("Deskripsi\t\t\t:  " + kamus.getDeskripsi());
            Glide.with(context)
                    .load(kamus.getFoto())
                    .placeholder(R.drawable.ic_null)
                    .into(imgList);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v);
        }
    }

    public void addLaporan(List<KamusItem> laporanList){
        kamusList.addAll(laporanList);
        notifyDataSetChanged();
    }
}
