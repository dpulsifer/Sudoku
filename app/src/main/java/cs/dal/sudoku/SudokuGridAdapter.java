package cs.dal.sudoku;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by duncanpulsifer on 2017-02-01.
 */

public class SudokuGridAdapter extends BaseAdapter
{
    private Context context;
    private String[] items;
    LayoutInflater inflater;

    public SudokuGridAdapter(Context context, String[] items) {
        this.context = context;
        this.items = items;
        inflater = (LayoutInflater) this.context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        //int color = 0xFFFFFF;
        //if (!items[position].equals(" ")) { color = 0xd3d3d3;}

        if(convertView ==null) {
            convertView = inflater.inflate(R.layout.sudoku_cell, null);
        }
        TextView t = (TextView) convertView.findViewById(R.id.grid_item);
        t.setText(items[position]);
        return convertView;
    }



    @Override
    public int getCount() { return items.length; }

    @Override public Object getItem(int position) { return items[position]; }

    @Override public long getItemId(int position) { return position; }
}
