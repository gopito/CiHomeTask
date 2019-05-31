package ru.android2019.cihometask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.android.testing.espresso.RecyclerViewSample.R;

import java.util.List;

/**
 * Provides views to {@link RecyclerView} with data from a data set.
 */
    public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private final List<String> mDataSet;

    private final Context mContext;

    /**
     * Provide a reference to the type of views that you are using
     * (custom {@link RecyclerView.ViewHolder}).
     */
    static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final Button button;

        // We'll use this field to showcase matching the holder from the test.
        private boolean mIsInTheMiddle = false;
        private boolean isClicked = false;

        ViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textView);
            button = (Button) v.findViewById(R.id.button);
        }

        public Button getButton() {
            return button;
        }

        TextView getTextView() {
            return textView;
        }

        public boolean isClicked() {
            return isClicked;
        }

        public void setClicked(boolean clicked) {
            isClicked = clicked;
        }

        boolean getIsInTheMiddle() {
            return mIsInTheMiddle;
        }

        void setIsInTheMiddle(boolean isInTheMiddle) {
            mIsInTheMiddle = isInTheMiddle;
        }


    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    CustomAdapter(List<String> dataSet, Context context) {
        mDataSet = dataSet;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.text_row_item, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        if (position == getMiddleIndex() /* calculate middle element position */) {
            viewHolder.setIsInTheMiddle(true);
            viewHolder.getTextView().setText(mContext.getResources().getString(R.string.middle));
        } else {
            viewHolder.setIsInTheMiddle(false);
            viewHolder.getTextView().setText(mDataSet.get(position));
        }
        viewHolder.getButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.isClicked) {
                    viewHolder.getTextView().setText(mDataSet.get(position));
                    viewHolder.setClicked(false);
                } else {
                    viewHolder.getTextView().setText(String.format("%s %s", mDataSet.get(position), "Clicked"));
                    viewHolder.setClicked(true);
                }
            }
        });

    }

    int getMiddleIndex() {
        return mDataSet.size() / 2;
    }

    // Return the size of your data set (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}
