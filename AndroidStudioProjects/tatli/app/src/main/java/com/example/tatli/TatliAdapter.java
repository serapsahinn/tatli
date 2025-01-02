package com.example.tatli;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.List;

public class TatliAdapter extends ArrayAdapter<Tatli> {
    private static final String TAG = "TatliAdapter";
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Tatli tatli, int position);
    }

    public TatliAdapter(Context context, List<Tatli> items, OnItemClickListener listener) {
        super(context, R.layout.tatli_item, items);
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tatli_item, parent, false);
            holder = new ViewHolder();
            holder.nameText = convertView.findViewById(R.id.textView);
            holder.descriptionText = convertView.findViewById(R.id.textView2);
            holder.imageView = convertView.findViewById(R.id.imageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Tatli tatli = getItem(position);
        if (tatli != null) {
            holder.nameText.setText(tatli.getName());
            holder.descriptionText.setText(tatli.getDescription());
            holder.descriptionText.setVisibility(View.VISIBLE); // Description'ı göster

            // Picasso ile resmi yükleme
            String imageUrl = tatli.getImageUrl();
            if (imageUrl != null && !imageUrl.isEmpty()) {
                Log.d(TAG, "Loading image for " + tatli.getName() + " from URL: " + imageUrl);
                
                try {
                    Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.error)
                        .fit()
                        .centerCrop()
                        .into(holder.imageView, new Callback() {
                            @Override
                            public void onSuccess() {
                                Log.d(TAG, "Image loaded successfully for " + tatli.getName());
                            }

                            @Override
                            public void onError(Exception e) {
                                Log.e(TAG, "Error loading image for " + tatli.getName() + ": " + e.getMessage());
                                holder.imageView.setImageResource(R.drawable.error);
                            }
                        });
                } catch (Exception e) {
                    Log.e(TAG, "Error in Picasso setup for " + tatli.getName() + ": " + e.getMessage());
                    holder.imageView.setImageResource(R.drawable.error);
                }
            } else {
                holder.imageView.setImageResource(R.drawable.placeholder);
            }

            convertView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onItemClick(tatli, position);
                }
            });
        }

        return convertView;
    }

    private static class ViewHolder {
        TextView nameText;
        TextView descriptionText;
        ImageView imageView;
    }
}
