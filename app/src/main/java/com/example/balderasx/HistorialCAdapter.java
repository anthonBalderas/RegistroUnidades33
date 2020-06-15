package com.example.balderasx;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistorialCAdapter extends RecyclerView.Adapter<HistorialCAdapter.HistorialCViewHolder> {

    private Context mContext;
    private Cursor mCursor;

    public HistorialCAdapter (Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    public class HistorialCViewHolder extends RecyclerView.ViewHolder{
        public TextView nameText;
        public TextView countText,rutaText;

        public HistorialCViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.textview_name_item);
            countText = itemView.findViewById(R.id.textview_amount_item);
            rutaText = itemView.findViewById(R.id.textview_ruta_item);



        }
    }
    @NonNull
    @Override
    public HistorialCViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.unidades_hist3_list, parent, false);
            return new HistorialCViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HistorialCAdapter.HistorialCViewHolder holder, int position) {

        if (!mCursor.moveToPosition(position)) {
            return;
        }

            String ruta = mCursor.getString(mCursor.getColumnIndex(UnidadesContract.UnidadesEntry6.COLUMN_RUTA));
            String amount = mCursor.getString(mCursor.getColumnIndex(UnidadesContract.UnidadesEntry6.COLUMN_UNIDAD));
            String name = mCursor.getString(mCursor.getColumnIndex(UnidadesContract.UnidadesEntry6.COLUMN_HORA));
            long id = mCursor.getLong(mCursor.getColumnIndex(UnidadesContract.UnidadesEntry6._ID));

        holder.rutaText.setText(ruta);
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
