package com.example.balderasx;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Unidades3Adapter extends RecyclerView.Adapter<Unidades3Adapter.Unidad3viewHolder> {

    private Context mContext;
    private Cursor mCursor;

    public Unidades3Adapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }


    @NonNull
    @Override
    public Unidad3viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.unidades3_list, parent, false);
        return new Unidad3viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Unidad3viewHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        String ruta = mCursor.getString(mCursor.getColumnIndex(UnidadesContract.UnidadesEntry3.COLUMN_RUTA));
            String amount = mCursor.getString(mCursor.getColumnIndex(UnidadesContract.UnidadesEntry3.COLUMN_UNIDAD));
            String name = mCursor.getString(mCursor.getColumnIndex(UnidadesContract.UnidadesEntry3.COLUMN_HORA));
            long id = mCursor.getLong(mCursor.getColumnIndex(UnidadesContract.UnidadesEntry3._ID));

            holder.rutaText.setText(ruta);
        holder.nameText.setText(name);
        holder.countText.setText(amount);
        holder.itemView.setTag(id);
    }



    public class Unidad3viewHolder extends RecyclerView.ViewHolder {

        public TextView nameText;
        public TextView countText,rutaText;
        public Unidad3viewHolder(@NonNull View itemView) {
            super(itemView);

            nameText = itemView.findViewById(R.id.textview_name_item);
            countText = itemView.findViewById(R.id.textview_amount_item);
            rutaText = itemView.findViewById(R.id.textview_ruta_item);

        }
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
