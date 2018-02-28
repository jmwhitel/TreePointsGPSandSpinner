package com.example.josephwhiteley.treepointsgpsandspinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ThreeColumn_ListAdapter extends ArrayAdapter<User> {

    private LayoutInflater mInflater;
    private ArrayList<User> users;
    private int mViewResourceId;

    public ThreeColumn_ListAdapter(Context context, int textViewResourceId, ArrayList<User> users) {
        super(context, textViewResourceId, users);
        this.users = users;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        User user = users.get(position);

        if (user != null) {
            TextView northing = (TextView) convertView.findViewById(R.id.textNorthing);
            TextView easting = (TextView) convertView.findViewById(R.id.textEasting);
            TextView elevation = (TextView) convertView.findViewById(R.id.textElevation);
            if (northing != null) {
                northing.setText(user.getNorthing());
            }
            if (easting != null) {
                easting.setText((user.getEasting()));
            }
            if (elevation != null) {
                elevation.setText((user.getElevation()));
            }
        }


        return convertView;
    }
}
