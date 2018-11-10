package com.yurentsy.watchingyou.ui.adapter;

import android.annotation.SuppressLint;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Flowable;

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

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Person> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(presenter.getPeople());
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Person person : presenter.getPeople()) {
                    if ((person.getName() + person.getSurname()).toLowerCase().startsWith(filterPattern) ||
                            (person.getName() + " " + person.getSurname()).toLowerCase().startsWith(filterPattern)
                            || person.getSurname().toLowerCase().startsWith(filterPattern)) {
                        filteredList.add(person);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @SuppressLint("CheckResult")
        @Override
        @SuppressWarnings("unchecked")
        protected void publishResults(CharSequence constraint, FilterResults results) {
            Flowable.just((List<Person>) results.values)
                    .flatMap(Flowable::fromIterable)
                    .sorted((p1, p2) -> (p1.getSurname() + p1.getName()).compareTo((p2.getSurname() + p2.getName())))
                    .compose(obs -> Flowable.concat(
                            obs.filter(Person::isWorking),
                            obs.filter(p -> !p.isWorking())
                    ))
                    .toList()
                    .subscribe(pList -> {
                        presenter.setDisplayedPeople(pList);
                        presenter.updateViews();
                    });
        }
    };

    @Override
    public int getItemCount() {
        return presenter.getDisplayedPersoneSize();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.bind(presenter.getDisplayedPeople().get(position));
        holder.setOnClickListener(item -> presenter.onClickPerson(position));
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_photo_small)
        ImageView photo;
        @BindView(R.id.tv_name)
        TextView name;
        @BindView(R.id.tv_position)
        TextView position;
        @BindView(R.id.iv_status)
        ImageView status;
        @BindView(R.id.tv_status)
        TextView textStatus;

        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Person person) {
            Picasso.get()
                    .load(person.getUrlPhoto())
                    .placeholder(R.drawable.ic_autorenew_black_24dp)
                    .error(R.drawable.ic_crop_original_black_24dp)
                    .into(photo);
            if (person.isWorking()) {
                status.setBackgroundResource(R.color.colorGreen);
                textStatus.setText(R.string.tv_status_title_working);
            } else {
                status.setBackgroundResource(R.color.colorRed);
                textStatus.setText(R.string.tv_status_title_absent);
            }

            name.setText(String.format("%s %s", person.getName(), person.getSurname()));
            position.setText(person.getPosition());
        }

        public void setOnClickListener(View.OnClickListener listener) {
            itemView.setOnClickListener(listener);
        }
    }
}

