package com.example.cubelearner.data;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cubelearner.R;

import org.w3c.dom.Text;

import java.util.List;

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
        }
        Algorithm algorithm = getItem(position);
        avh.id.setText(algorithm.getId());
        avh.algorithm.setText(algorithm.getAlgorithm());
        avh.image.setImageResource(R.drawable.sourire);

        return convertView;
    }

    private class AlgorithmViewHolder{
        public TextView id;
        public TextView algorithm;
        public ImageView image;
    }

}
