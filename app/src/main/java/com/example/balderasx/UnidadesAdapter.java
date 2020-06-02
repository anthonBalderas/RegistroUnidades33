package com.example.balderasx;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class UnidadesAdapter extends RecyclerView.Adapter<UnidadesAdapter.GroceryViewHolder> {
    private Context mContext;
    private Cursor mCursor;

        public UnidadesAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public class GroceryViewHolder extends RecyclerView.ViewHolder {
        public TextView nameText;
        public TextView countText;
        public GroceryViewHolder(View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.textview_name_item);
            countText = itemView.findViewById(R.id.textview_amount_item);
        }
    }
    @Override
    public GroceryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
            View view = inflater.inflate(R.layout.unidades_list, parent, false);
        return new GroceryViewHolder(view);

    }

    @Override
    public void onBindViewHolder(GroceryViewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        String amount = mCursor.getString(mCursor.getColumnIndex(UnidadesContract.UnidadesEntry.COLUMN_UNIDAD));
        String name = mCursor.getString(mCursor.getColumnIndex(UnidadesContract.UnidadesEntry.COLUMN_HORA));
        long id = mCursor.getLong(mCursor.getColumnIndex(UnidadesContract.UnidadesEntry._ID));

        holder.nameText.setText(name);
        holder.countText.setText(amount);
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = newCursor;
        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }
}