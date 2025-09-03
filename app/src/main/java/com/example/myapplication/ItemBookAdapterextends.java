package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.databinding.ItemBookBinding;

import java.util.ArrayList;

public class ItemBookAdapterextends extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_LINEAR = 0;
    private static final int VIEW_TYPE_GRID = 1;

    private Context context;
    private ArrayList<ItemBook> itemBooks;
    private boolean isGrid; // يتحكم في نوع التصميم

    public ItemBookAdapterextends(Context context, ArrayList<ItemBook> itemBooks, boolean isGrid) {
        this.context = context;
        this.itemBooks = itemBooks;
        this.isGrid = isGrid;
    }

    // تغيير نوع العرض لاحقاً
    public void setLayoutType(boolean isGrid) {
        this.isGrid = isGrid;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return isGrid ? VIEW_TYPE_GRID : VIEW_TYPE_LINEAR;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_GRID) {
            return new GridViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_book_grid, parent, false)
            );
        } else {
            return new LinearViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.item_book, parent, false)
            );
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemBook item = itemBooks.get(position);

        if (holder instanceof GridViewHolder) {
            GridViewHolder h = (GridViewHolder) holder;
            h.imageBook.setImageResource(item.getImageBook());
            h.tvBookName.setText(item.getNameBook());
            h.tvBookAuthor.setText(item.getNameAuthor());

            h.btn_open.setOnClickListener(v -> openDetails(item));
        } else if (holder instanceof LinearViewHolder) {
            LinearViewHolder h = (LinearViewHolder) holder;
            h.imageBook.setImageResource(item.getImageBook());
            h.tvBookName.setText(item.getNameBook());
            h.tvBookAuthor.setText(item.getNameAuthor());

            h.btnOpen.setOnClickListener(v -> openDetails(item));
            h.itemView.setOnLongClickListener(v -> {
                itemBooks.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, itemBooks.size());
                Toast.makeText(context, "تم حذف الكتاب", Toast.LENGTH_SHORT).show();
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        return itemBooks.size();
    }

    private void openDetails(ItemBook item) {
        Intent intent = new Intent(context, ActivityShowItemBook.class);
        intent.putExtra("nameBook", item.getNameBook());
        intent.putExtra("nameAuthor", item.getNameAuthor());
        intent.putExtra("bakimage", item.getImageBook());
        intent.putExtra("dateShow", item.getDateShow());
        intent.putExtra("type", item.getType());
        intent.putExtra("Description", item.getDescription());
        intent.putExtra("Rating", item.getRating());
        intent.putExtra("URLBook", item.getURLBook());
        context.startActivity(intent);
    }

    // ViewHolder للـ Linear
    static class LinearViewHolder extends RecyclerView.ViewHolder {
        ImageView imageBook, btnOpen;
        TextView tvBookName, tvBookAuthor;

        public LinearViewHolder(@NonNull android.view.View itemView) {
            super(itemView);
            imageBook = itemView.findViewById(R.id.image_book);
            tvBookName = itemView.findViewById(R.id.tv_book_name);
            tvBookAuthor = itemView.findViewById(R.id.tv_book_author);
            btnOpen = itemView.findViewById(R.id.btn_open);
        }
    }

    // ViewHolder للـ Grid
    static class GridViewHolder extends RecyclerView.ViewHolder {
        ImageView imageBook ,btn_open;
        TextView tvBookName, tvBookAuthor;

        @SuppressLint("WrongViewCast")
        public GridViewHolder(@NonNull android.view.View itemView) {
            super(itemView);
            imageBook = itemView.findViewById(R.id.image_book);
            tvBookName = itemView.findViewById(R.id.tv_book_name);
            tvBookAuthor = itemView.findViewById(R.id.tv_book_author);
            btn_open = itemView.findViewById(R.id.btn_open_grid);
        }
    }
}



