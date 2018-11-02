package com.yurentsy.watchingyou.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yurentsy.watchingyou.R;
import com.yurentsy.watchingyou.mvp.model.entity.Person;
import com.yurentsy.watchingyou.mvp.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> implements Filterable {
    private MainPresenter presenter;

    public MainAdapter(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_main_item, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        presenter.bindViewHolder(holder, position);
        holder.itemView.setOnClickListener(item->{
            presenter.onClickPerson(position);
        });
    }

    @Override
    public int getItemCount() {
        return presenter.getPersoneSize();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Person> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(presenter.getPeople());
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Person person : presenter.getPeople()) {
                    if (person.getName().toLowerCase().contains(filterPattern) || person.getSurname().toLowerCase().contains(filterPattern)) {
                        filteredList.add(person);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            presenter.getPeople().clear();
            presenter.setPeople((List) results.values);
            notifyDataSetChanged();
            presenter.updatePersons();
        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPersonPhoto;
        private TextView tvName;
        private TextView tvPosition;

        MyViewHolder(final View itemView) {
            super(itemView);
            ivPersonPhoto = itemView.findViewById(R.id.iv_photo_small);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPosition = itemView.findViewById(R.id.tv_position);
        }

        public void bind(Person person) {
            Picasso.get()
                    .load(person.getUrlPhoto())
                    .placeholder(R.drawable.ic_autorenew_black_24dp)
                    .error(R.drawable.ic_crop_original_black_24dp)
                    .into(ivPersonPhoto);
            tvName.setText(person.getName() + " " + person.getSurname());
            tvPosition.setText(person.getPosition());
        }

    }
}

