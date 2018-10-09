package in.cognitia.cognitia18;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by devansh on 9/9/18.
 */

public class EventViewHolder extends RecyclerView.ViewHolder {
    public TextView name, description;
    public ImageView image;
    public Context mContext;

    public EventViewHolder(View view) {
        super(view);

        mContext = view.getContext();

        name = view.findViewById(R.id.card_name);
        description = view.findViewById(R.id.card_description);
        image = view.findViewById(R.id.card_image);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EventDetailActivity.class);
                intent.putExtra(EventDetailActivity.IMAGE_ID, getDrawableId(image));

                CognitiaEvent event = EventsCategoryRecyclerViewAdapter.getEventsMap().get(name.getText());

                Bundle eventBundle = new Bundle();
                eventBundle.putString(EventDetailActivity.EVENT_NAME, event.getName());
                Log.v("Event name", event.getName());
                eventBundle.putString(EventDetailActivity.DESCRIPTION, event.getDescription());
                eventBundle.putString(EventDetailActivity.OBJECTIVE, event.getShortDescription());
                eventBundle.putString(EventDetailActivity.RULES, event.getRules());
                eventBundle.putString(EventDetailActivity.AIM, event.getAim());
                eventBundle.putString(EventDetailActivity.PARENT, event.getParent());
                if (event.getRobotSpecs() != null)
                    eventBundle.putString(EventDetailActivity.ROBOT_SPECS, event.getRobotSpecs());
                eventBundle.putInt(EventDetailActivity.IMAGE_ID, getDrawableId(image));

                intent.putExtras(eventBundle);

                //Implementing multiple transitions
                Pair<View, String> p1 = Pair.create((View)image, mContext.getString(R.string.event_image_trans));

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) mContext, p1);
                view.getContext().startActivity(intent, options.toBundle());
            }
        });
    }

    //Adding this because it is not possible to get image ID from an ImageView
    private int getDrawableId(ImageView imageView) {
        return (Integer) imageView.getTag(R.string.image_tag);
    }
}