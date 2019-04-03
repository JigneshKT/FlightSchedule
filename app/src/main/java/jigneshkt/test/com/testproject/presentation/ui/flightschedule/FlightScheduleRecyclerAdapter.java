package jigneshkt.test.com.testproject.presentation.ui.flightschedule;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import jigneshkt.test.com.testproject.R;
import jigneshkt.test.com.testproject.domain.model.Flight;
import jigneshkt.test.com.testproject.domain.model.FlightSchedule;
import jigneshkt.test.com.testproject.presentation.ui.base.BaseViewHolder;

public class FlightScheduleRecyclerAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final int VIEW_TYPE_LOADING = 0;
    private static final int VIEW_TYPE_NORMAL = 1;
    private boolean isLoaderVisible = false;
    private FlightScheduleSelectionListener flightScheduleSelectionListener;

    private List<FlightSchedule> mPostItems;

    public FlightScheduleRecyclerAdapter(List<FlightSchedule> postItems, FlightScheduleSelectionListener flightScheduleSelectionListener) {
        this.flightScheduleSelectionListener = flightScheduleSelectionListener;
        this.mPostItems = postItems;
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                return new FlightScheduleRecyclerAdapter.ViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(R.layout.list_flight_schedule, parent, false),flightScheduleSelectionListener, parent.getContext());
            case VIEW_TYPE_LOADING:
                return new FlightScheduleRecyclerAdapter.FooterHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false));
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

    public void add(FlightSchedule response) {
        mPostItems.add(response);
        notifyItemInserted(mPostItems.size() - 1);
    }

    public void addAll(List<FlightSchedule> postItems) {
        for (FlightSchedule response : postItems) {
            add(response);
        }
    }


    private void remove(FlightSchedule postItems) {
        int position = mPostItems.indexOf(postItems);
        if (position > -1) {
            mPostItems.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void addLoading() {
        isLoaderVisible = true;
        add(new FlightSchedule());
    }

    public void removeLoading() {
        isLoaderVisible = false;
        if(mPostItems.size()>0) {
            int position = mPostItems.size() - 1;
            FlightSchedule item = getItem(position);
            if (item != null) {
                mPostItems.remove(position);
                notifyItemRemoved(position);
            }
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    FlightSchedule getItem(int position) {
        return mPostItems.get(position);
    }


    public class ViewHolder extends BaseViewHolder implements View.OnClickListener {
        @BindView(R.id.flightScheduleContainer)
        LinearLayout flightScheduleContainer;

        @BindView(R.id.tv_duration)
        TextView duration;

        private FlightScheduleSelectionListener mListener;
        private  Context context;
        ViewHolder(View itemView, FlightScheduleSelectionListener listener, Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.mListener = listener;
            itemView.setOnClickListener(this);
            this.context = context;
        }

        protected void clear() {

        }

        public void onBind(int position) {
            super.onBind(position);
            FlightSchedule flightSchedule = mPostItems.get(position);
            duration.setText(flightSchedule.getTotalJourney());
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            for(Flight flight:flightSchedule.getFlightList()){
                View flightItem = layoutInflater.inflate(R.layout.item_schedule_flight, flightScheduleContainer, false);
                ((TextView)flightItem.findViewById(R.id.departureAirportCode)).setText(flight.getDepartureAirportCode());
                ((TextView)flightItem.findViewById(R.id.departureDate)).setText(flight.getDepartureTime());
                ((TextView)flightItem.findViewById(R.id.arrivalAirportCode)).setText(flight.getArrivalAirportCode());
                ((TextView)flightItem.findViewById(R.id.arrivalDate)).setText(flight.getArrivalTime());
                flightScheduleContainer.addView(flightItem);
            }
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

    public interface FlightScheduleSelectionListener {

        void onClick(View view, int position);
    }

}

