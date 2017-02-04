package com.bluelead.popularmovies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MoviePosterAdapter extends RecyclerView.Adapter<MoviePosterAdapter.MoviePosterViewHolder> {

    private int mNumberItems, mViewHolderCount;
    final private ListItemClickListener mOnClickListener;
    private static final String TAG = MoviePosterAdapter.class.getSimpleName();
    private static ArrayList<Movie> mMovieList;
    private Context mContext;

    public MoviePosterAdapter(Context context, int numberOfItems, ListItemClickListener listener, ArrayList<Movie> moviesList) {
        mNumberItems = numberOfItems;
        mOnClickListener = listener;
        mViewHolderCount = 0;
        mMovieList = moviesList;
        mContext = context;
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

            Picasso.with(mContext).load(mMovieList.get(0).getPosterPath()).into(listItemImageView);

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
