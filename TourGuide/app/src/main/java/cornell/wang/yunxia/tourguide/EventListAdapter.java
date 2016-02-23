package cornell.wang.yunxia.tourguide;

/**
 * Created by Lele on 2015/11/29.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.ArrayList;

public class EventListAdapter extends BaseAdapter implements Filterable {

        private Activity context;
        private EventFilter eventFilter = new EventFilter();
        private ArrayList<Event> eventList;
        private ArrayList<Event> filteredList;

        /** Initialize context variables         */
        public EventListAdapter(Activity context, ArrayList<Event> eventList) {
            this.context=context;
            this.eventList = eventList;
            this.filteredList = eventList;
            getFilter();
        }

        /**  Get size of event list         */
        @Override
        public int getCount() {
            return filteredList.size();
        }

        /** Get specific item from event list         */
        @Override
        public Object getItem(int i) {
            return filteredList.get(i);
        }

        /** Get event list item id         */
        @Override
        public long getItemId(int i) {
            return i;
        }


        /** Keep reference to children view to avoid unnecessary calls     */
        static class ViewHolder {
            TextView name;
            ImageView image;
            TextView startTime;
            TextView endTime;
            TextView location;
            TextView category;
            TextView description;
         }

        /** Create list row view         */
        @Override
        public View getView(int position, View view, ViewGroup parent) {
            // A ViewHolder keeps references to children views to avoid unnecessary calls to findViewById() on each row.
            ViewHolder holder;
            //final Event event = (Event) getItem(position);
            if (view == null) {
                //LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //view = layoutInflater.inflate(R.layout.mainlist, parent, false);
                LayoutInflater inflater=context.getLayoutInflater();
                view=inflater.inflate(R.layout.mainlist, null, true);
                holder = new ViewHolder();
                holder.name = (TextView) view.findViewById(R.id.main_name);
                holder.image = (ImageView) view.findViewById(R.id.main_image);
                holder.startTime = (TextView) view.findViewById(R.id.main_start_time);
                holder.endTime = (TextView) view.findViewById(R.id.main_end_time);
                holder.location = (TextView) view.findViewById(R.id.main_location);
                holder.category = (TextView) view.findViewById(R.id.main_category);
                holder.description = (TextView) view.findViewById(R.id.main_description);
                view.setTag(holder);
            }
            else {
                // get view holder back
                holder = (ViewHolder) view.getTag();
            }
            // set content for view
            holder.name.setText(filteredList.get(position).getName());
            holder.image.setImageResource(filteredList.get(position).getImageID());
            holder.startTime.setText(filteredList.get(position).getStartTime());
            holder.endTime.setText(filteredList.get(position).getEndTime());
            holder.location.setText(filteredList.get(position).getLocation());
            holder.category.setText(filteredList.get(position).getCategory());
            holder.description.setText(filteredList.get(position).getDescription());
            return view;
        }

        /**Get custom filter         */
        @Override
        public Filter getFilter() {
            return eventFilter;
        }

        /** Custom filter for event list
         * Filter content in event list according to the search text */
        private class EventFilter extends Filter {

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint!=null && constraint.length()>0) {
                    ArrayList<Event> tempList = new ArrayList<Event>();
                    // search content in event list
                    for (Event event : eventList) {
                        if (event.getName().toString().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            tempList.add(event);
                        }
                    }
                    filterResults.count = tempList.size();
                    filterResults.values = tempList;
                }
                else {
                    filterResults.count = eventList.size();
                    filterResults.values = eventList;
                }
                return filterResults;
            }

            /** Notify about filtered list to ui             */
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                filteredList = (ArrayList<Event>) results.values;
                notifyDataSetChanged();
            }
        }

}
