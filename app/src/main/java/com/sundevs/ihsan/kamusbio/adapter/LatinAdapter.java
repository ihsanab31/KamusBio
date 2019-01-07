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
import com.sundevs.ihsan.kamusbio.model.Latin;
import com.sundevs.ihsan.kamusbio.utils.Constant;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by
 * Name     : Ihsan Abdurahman
 * Email    : ihsanab31@gmail.com
 * WA       : 085749729115
 * on       : 07, January, 2019
 * ------------------------------
 * This class for
 */

@SuppressLint({"InflateParams","SetTextI18n", "ResourceAsColor","SetTextI18n"})
public class LatinAdapter extends RecyclerView.Adapter<LatinAdapter.ViewHolder> {
    private Context context;
    private List<Latin> latinList;

    public LatinAdapter(Context context, List<Latin> latinList) {
        this.context = context;
        this.latinList = latinList;
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
        return latinList.size();
    }


    @Override
    public void onBindViewHolder(@NonNull final LatinAdapter.ViewHolder holder, int position) {
        final Latin latin = latinList.get(position);
        holder.bindTo(latin);
        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


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

        void bindTo(Latin latin) {
            textTempat.setText("Hewan\t\t\t\t : "+latin.getNamaIndo());
            txtKegiatan.setText("Bahasa Latin : " + latin.getNamaLatin());
            txtTanggal.setText("Deskripsi\t\t\t :  " + latin.getDeskripsiLatin());
            Glide.with(context)
                    .load(Constant.IMAGE_URL+latin.getFoto())
                    .placeholder(R.drawable.ic_null)
                    .into(imgList);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v);
        }
    }
}