package com.csci5115.group8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.csci5115.group8.data.apartment.Apartment;
import com.csci5115.group8.data.apartment.ApartmentSRL;
import com.csci5115.group8.data.apartment.ApartmentUnit;

import java.util.List;

public class ApartmentSearchResultsAdapter extends RecyclerView.Adapter<ApartmentSearchResultsAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView name;
        TextView address;
        TextView perUnitAmenities;
        TextView perUnitAmenitiesHint;
        TextView commonAmenities;
        TextView commonAmenitiesHint;
        TextView securityFeatures;
        TextView securityFeaturesHint;
        TextView numAvailableByNumTotalUnits;
        TextView numAvailableByNumTotalUnitsHint;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameHolder);
            address = itemView.findViewById(R.id.addressHolder);
            perUnitAmenitiesHint = itemView.findViewById(R.id.perUnitAmenitiesHintHolder);
            perUnitAmenities = itemView.findViewById(R.id.perUnitAmenitiesHolder);
            commonAmenities = itemView.findViewById(R.id.commonAmenitiesHolder);
            commonAmenitiesHint = itemView.findViewById(R.id.commonAmenitiesHintHolder);
            securityFeatures = itemView.findViewById(R.id.securityFeaturesHolder);
            securityFeaturesHint = itemView.findViewById(R.id.securityFeaturesHintHolder);
            numAvailableByNumTotalUnits = itemView.findViewById(R.id.numAvailableByNumTotalUnitsHolder);
            numAvailableByNumTotalUnitsHint = itemView.findViewById(R.id.numAvailableByNumTotalUnitsHintHolder);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null)
                itemClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    private List<Apartment> data;
    private LayoutInflater inflater;
    private ItemClickListener itemClickListener;

    public ApartmentSearchResultsAdapter(Context context, List<Apartment> data, ItemClickListener itemClickListener) {
        this.inflater = LayoutInflater.from(context);
        this.data = data;
        this.itemClickListener = itemClickListener;
    }

    Apartment getItem(int id) {
        return data.get(id);
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_view_apartment_search_result, parent, false);
        return new ViewHolder(view);
    }

    private String truncate(String str, int maxLen) {
        if (str.length() > maxLen) {
            return str.substring(0, maxLen) + "...";
        } else {
            return str;
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Apartment apartment = data.get(position);
        holder.name.setText(truncate(apartment.name, 15));
        holder.name.setVisibility(ApartmentSRL.nameVisible ? View.VISIBLE : View.GONE);
        holder.address.setText(truncate(apartment.address, 20));
        holder.address.setVisibility(ApartmentSRL.addressVisible ? View.VISIBLE : View.GONE);
        holder.perUnitAmenities.setText(truncate(apartment.perUnitAmenities.toString(), 50));
        holder.perUnitAmenities.setVisibility(ApartmentSRL.perUnitAmenitiesVisible ? View.VISIBLE : View.GONE);
        holder.perUnitAmenitiesHint.setVisibility(ApartmentSRL.perUnitAmenitiesVisible ? View.VISIBLE : View.GONE);
        holder.commonAmenities.setText(truncate(apartment.commonAmenities.toString(), 50));
        holder.commonAmenities.setVisibility(ApartmentSRL.commonAmenitiesVisible ? View.VISIBLE : View.GONE);
        holder.commonAmenitiesHint.setVisibility(ApartmentSRL.commonAmenitiesVisible ? View.VISIBLE : View.GONE);
        holder.securityFeatures.setText(truncate(apartment.securityFeatures.toString(), 50));
        holder.securityFeatures.setVisibility(ApartmentSRL.securityFeaturesVisible ? View.VISIBLE : View.GONE);
        holder.securityFeaturesHint.setVisibility(ApartmentSRL.securityFeaturesVisible ? View.VISIBLE : View.GONE);
        int numAvailable = 0;
        for (ApartmentUnit unit : apartment.units) {
            if (!unit.isLeased) {
                numAvailable++;
            }
        }
        holder.numAvailableByNumTotalUnits.setText(truncate(numAvailable + " / " + apartment.units.size(), 10));
        holder.numAvailableByNumTotalUnits.setVisibility(ApartmentSRL.numAvailableByNumTotalUnitsVisible ? View.VISIBLE : View.GONE);
        holder.numAvailableByNumTotalUnitsHint.setVisibility(ApartmentSRL.numAvailableByNumTotalUnitsVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}