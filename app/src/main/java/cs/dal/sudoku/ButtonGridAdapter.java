package cs.dal.sudoku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by duncanpulsifer on 2017-02-09.
 */

public class ButtonGridAdapter extends BaseAdapter{

    private Context context;
    private String[] buttons;
    LayoutInflater inflater;

    public ButtonGridAdapter(Context context, String[] buttons) {
        this.context = context;
        this.buttons = buttons;
        inflater = (LayoutInflater) this.context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView ==null) {
            convertView = inflater.inflate(R.layout.button_cell, null);
        }
        TextView b = (TextView) convertView.findViewById(R.id.button_item);
        b.setText(buttons[position]);

        return convertView;

    }

    @Override
    public int getCount() { return buttons.length; }

    @Override public Object getItem(int position) { return buttons[position]; }

    @Override public long getItemId(int position) { return position; }
}