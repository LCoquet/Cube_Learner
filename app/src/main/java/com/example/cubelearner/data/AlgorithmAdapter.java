package com.example.cubelearner.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cubelearner.R;

import java.util.List;

/*
 * Permits the creation of algorithm views in the ListView of AlgorithmActivity.
 */

public class AlgorithmAdapter extends ArrayAdapter<Algorithm> {

    public AlgorithmAdapter(Context context, List<Algorithm> algorithms){
        super(context, 0, algorithms);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.algorithm, parent, false);

        AlgorithmViewHolder avh = (AlgorithmViewHolder) convertView.getTag();
        if(avh == null){
            avh = new AlgorithmViewHolder();
            avh.id = (TextView) convertView.findViewById(R.id.algorithmId);
            avh.algorithm = (TextView) convertView.findViewById(R.id.algorithm);
            avh.image = (ImageView) convertView.findViewById(R.id.algorithmImage);
            convertView.setTag(avh);
        } //Find the views
        Algorithm algorithm = getItem(position);
        //Set the text or image on the different views.
        avh.id.setText(algorithm.getId());
        avh.algorithm.setText(algorithm.getAlgorithm());
        avh.image.setImageResource(getContext().getResources().getIdentifier("ic_" + algorithm.getId().toLowerCase(), "drawable", getContext().getPackageName()));
        return convertView;
    }

    //Stores the different sub-views of algorithm view.
    private class AlgorithmViewHolder {
        public TextView id;
        public TextView algorithm;
        public ImageView image;
    }

}
