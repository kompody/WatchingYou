package silantyevmn.ru.materialdesign.model.photo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import silantyevmn.ru.materialdesign.R;


public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.MyViewHolder> {
    private final int PHOTO_WIDTH = 0; //длина фото 0 значит, что может растягиваться по горизонтали
    private final int PHOTO_HEIGHT = 150; //высота фото
    private final OnClickAdapter listener;
    private List<Photo> photos;

    public interface OnClickAdapter {
        void onClickPhoto(int position);

        void onClickMenuDelete(int position);

        void onClickMenuFavorite(int position);
    }

    public PhotoAdapter(List<Photo> photos, OnClickAdapter listener) {
        this.photos = photos;
        this.listener = listener;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main_item, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.bind(photos.get(position));
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ImageView imagePhoto;
        private ImageView imageFavorite;
        private TextView textName;

        MyViewHolder(final View itemView) {
            super(itemView);
            imagePhoto = itemView.findViewById(R.id.card_image_photo);
            imageFavorite = itemView.findViewById(R.id.cart_item_favorite);
            textName = itemView.findViewById(R.id.card_item_text);

            imagePhoto.setOnClickListener(this);
            imagePhoto.setOnLongClickListener(this);
            imageFavorite.setOnClickListener(this);

        }

        void bind(Photo photo) {
            Picasso.get()
                    .load(photo.getUri())
                    .placeholder(R.drawable.ic_autorenew_black)
                    .error(R.drawable.ic_crop_original_black)
                    .resize(PHOTO_WIDTH, PHOTO_HEIGHT)
                    .centerCrop()
                    .into(imagePhoto);
            if (photo.isFavorite()) {
                imageFavorite.setImageResource(R.drawable.ic_favorite_white_24dp);
            } else {
                imageFavorite.setImageResource(R.drawable.ic_favorite_border_white);
            }
            textName.setText(photo.getName());
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.card_image_photo: {
                    listener.onClickPhoto(getAdapterPosition());
                    break;
                }
                case R.id.cart_item_favorite: {
                    listener.onClickMenuFavorite(getAdapterPosition());
                    break;
                }
                default:
                    break;
            }

        }

        @Override
        public boolean onLongClick(View view) {
            listener.onClickMenuDelete(getAdapterPosition());
            return true;
        }
    }
}

