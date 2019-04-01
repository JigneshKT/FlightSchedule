package jigneshkt.test.com.testproject.presentation.ui.airportlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jigneshkt.test.com.testproject.R;
import jigneshkt.test.com.testproject.domain.model.Airport;
import jigneshkt.test.com.testproject.presentation.ui.base.BaseViewHolder;

public class AirportsRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;
    private AirportSelectionListener airportSelectionListener;

    private List<Airport> mPostItems;

    public AirportsRecyclerAdapter(List<Airport> postItems, AirportSelectionListener airportSelectionListener) {
        this.airportSelectionListener = airportSelectionListener;
        this.mPostItems = postItems;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_airport, parent, false),airportSelectionListener);
            case VIEW_TYPE_LOADING:
                return new FooterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoaderVisible) {
            return position == mPostItems.size() - 1 ? VIEW_TYPE_LOADING : VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_NORMAL;
        }
    }

    @Override
    public int getItemCount() {
        return mPostItems == null ? 0 : mPostItems.size();
    }

    public void add(Airport response) {
        mPostItems.add(response);
        notifyItemInserted(mPostItems.size() - 1);
    }

    public void addAll(List<Airport> postItems) {
        for (Airport response : postItems) {
            add(response);
        }
    }


    private void remove(Airport postItems) {
        int position = mPostItems.indexOf(postItems);
        if (position > -1) {
            mPostItems.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void addLoading() {
        isLoaderVisible = true;
        add(new Airport());
    }

    public void removeLoading() {
        isLoaderVisible = false;
        int position = mPostItems.size() - 1;
        Airport item = getItem(position);
        if (item != null) {
            mPostItems.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    Airport getItem(int position) {
        return mPostItems.get(position);
    }


    public class ViewHolder extends BaseViewHolder implements View.OnClickListener {
        @BindView(R.id.tv_name)
        TextView textViewName;
        @BindView(R.id.tv_country)
        TextView textViewCountry;
        @BindView(R.id.tv_code)
        TextView textViewCode;
        private AirportSelectionListener mListener;


        ViewHolder(View itemView, AirportSelectionListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mListener = listener;
            itemView.setOnClickListener(this);
        }

        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);
            Airport item = mPostItems.get(position);

            textViewName.setText(item.getName());
            textViewCountry.setText(item.getCountryCode());
            textViewCode.setText(item.getAirportCode());
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }

    public class FooterHolder extends BaseViewHolder {

        @BindView(R.id.progressBar)
        ProgressBar mProgressBar;


        FooterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        protected void clear() {

        }

    }

    public interface AirportSelectionListener {

        void onClick(View view, int position);
    }

}
