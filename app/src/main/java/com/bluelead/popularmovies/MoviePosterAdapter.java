package com.bluelead.popularmovies;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.MoviePosterViewHolder> {

    private int mNumberItems, mViewHolderCount;
    final private ListItemClickListener mOnClickListener;
    private static final String TAG = MoviePosterAdapter.class.getSimpleName();

    public MoviePosterAdapter(int numberOfItems, ListItemClickListener listener) {
        mNumberItems = numberOfItems;
        mOnClickListener = listener;
        mViewHolderCount = 0;
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }

    @Override
    public MoviePosterAdapter.MoviePosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.grid_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        MoviePosterViewHolder viewHolder = new MoviePosterViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MoviePosterAdapter.MoviePosterViewHolder holder, int position) {
        Log.d(TAG, "#" + position);
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mNumberItems;
    }

    class MoviePosterViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        ImageView listItemImageView;

        public MoviePosterViewHolder(View itemView) {
            super(itemView);

            listItemImageView = (ImageView) itemView.findViewById(R.id.iv_poster);

            listItemImageView.setImageResource(R.drawable.image1); //uhmmmmmmmmmm
            itemView.setOnClickListener(this);
        }

        void bind(int listIndex) {
            listItemImageView.setContentDescription("Moview Poster: " + listIndex);
        }

        @Override
        public void onClick(View v) {
            int clickedPosition = getAdapterPosition();
            mOnClickListener.onListItemClick(clickedPosition);
        }
    }
}
