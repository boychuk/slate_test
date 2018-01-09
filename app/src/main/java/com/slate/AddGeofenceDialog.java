package com.slate;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.slate.model.CustomGeofence;

public class AddGeofenceDialog extends DialogFragment {

    private ViewHolder viewHolder;
    private CustomGeofence customGeofence;

    private ViewHolder getViewHolder() {
        return viewHolder;
    }

    private AddGeofenceListener listener;


    public void setListener(AddGeofenceListener listener) {
        this.listener = listener;
    }

    public void setCustomGeofence(CustomGeofence customGeofence) {
        this.customGeofence = customGeofence;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_geofence, null);

        viewHolder = new ViewHolder();
        viewHolder.populate(view);

        if (customGeofence != null)
            initData();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(view)
                .setPositiveButton(R.string.Add, null)
                .setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        AddGeofenceDialog.this.getDialog().cancel();

                        if (listener != null) {
                            listener.onDialogNegativeClick(AddGeofenceDialog.this);
                        }
                    }
                });


        final AlertDialog dialog = builder.create();

        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        if (dataIsValid()) {
                            CustomGeofence geofence = new CustomGeofence();
                            geofence.setName(getViewHolder().nameEditText.getText().toString());
                            geofence.setLatitude(Double.parseDouble(getViewHolder().latitudeEditText.getText().toString()));
                            geofence.setLongitude(Double.parseDouble(getViewHolder().longitudeEditText.getText().toString()));
                            geofence.setRadius(Float.parseFloat(getViewHolder().radiusEditText.getText().toString()));

                            if (listener != null) {
                                listener.onDialogPositiveClick(AddGeofenceDialog.this, geofence);
                                dialog.dismiss();
                            }
                        } else {
                            showValidationErrorToast();
                        }
                    }

                });

            }
        });

        return dialog;
    }

    private void initData() {
        getViewHolder().nameEditText.setText(customGeofence.getName());
        getViewHolder().latitudeEditText.setText(String.valueOf(customGeofence.getLatitude()));
        getViewHolder().longitudeEditText.setText(String.valueOf(customGeofence.getLongitude()));
        getViewHolder().radiusEditText.setText(String.valueOf(customGeofence.getRadius()));
    }

    private boolean dataIsValid() {
        boolean validData = true;

        String name = getViewHolder().nameEditText.getText().toString();
        String latitudeString = getViewHolder().latitudeEditText.getText().toString();
        String longitudeString = getViewHolder().longitudeEditText.getText().toString();
        String radiusString = getViewHolder().radiusEditText.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(latitudeString)
                || TextUtils.isEmpty(longitudeString) || TextUtils.isEmpty(radiusString)) {
            validData = false;
        } else {
            double latitude = Double.parseDouble(latitudeString);
            double longitude = Double.parseDouble(longitudeString);
            float radius = Float.parseFloat(radiusString);
            if ((latitude < Constants.Geometry.MinLatitude || latitude > Constants.Geometry.MaxLatitude)
                    || (longitude < Constants.Geometry.MinLongitude || longitude > Constants.Geometry.MaxLongitude)
                    || (radius < Constants.Geometry.MinRadius || radius > Constants.Geometry.MaxRadius)) {
                validData = false;
            }
        }

        return validData;
    }

    private void showValidationErrorToast() {
        Toast.makeText(getActivity(), getActivity().getString(R.string.Toast_Validation), Toast.LENGTH_SHORT).show();
    }


    public interface AddGeofenceListener {
        void onDialogPositiveClick(DialogFragment dialog, CustomGeofence geofence);

        void onDialogNegativeClick(DialogFragment dialog);
    }

    static class ViewHolder {
        EditText nameEditText;
        EditText latitudeEditText;
        EditText longitudeEditText;
        EditText radiusEditText;

        public void populate(View v) {
            nameEditText = v.findViewById(R.id.fragment_add_geofence_name);
            latitudeEditText = v.findViewById(R.id.fragment_add_geofence_latitude);
            longitudeEditText = v.findViewById(R.id.fragment_add_geofence_longitude);
            radiusEditText = v.findViewById(R.id.fragment_add_geofence_radius);

            latitudeEditText.setHint(String.format(v.getResources().getString(R.string.Hint_Latitude), Constants.Geometry.MinLatitude, Constants.Geometry.MaxLatitude));
            longitudeEditText.setHint(String.format(v.getResources().getString(R.string.Hint_Longitude), Constants.Geometry.MinLongitude, Constants.Geometry.MaxLongitude));
            radiusEditText.setHint(String.format(v.getResources().getString(R.string.Hint_Radius), Constants.Geometry.MinRadius, Constants.Geometry.MaxRadius));
        }
    }
}