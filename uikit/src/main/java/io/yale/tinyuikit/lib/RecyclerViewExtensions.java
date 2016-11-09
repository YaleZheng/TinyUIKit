package io.yale.tinyuikit.lib;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.LinkedList;
import java.util.List;

import rx.functions.Action3;
import rx.functions.Action4;
import rx.functions.Func2;

/**
 * Created by yalez on 2016/11/8.
 */

public class RecyclerViewExtensions {
    private RecyclerViewExtensions() {
    }

    public static <T> AdapterBuilder<T> newAdapter(List<T> items) {
        return new AdapterBuilder<>(items);
    }

    public static class AdapterBuilder<T> {
        private List<T> items;

        private AdapterBuilder(List<T> items) {
            this.items = items;
        }

        private Func2<ViewGroup, Integer, View> onCreateVH;
        private Action3<SimpleAdapter<T>, Integer, SimpleVH> onVHCreated;
        private Action3<SimpleAdapter<T>, SimpleVH, Integer> onBindVH;

        public AdapterBuilder<T> onCreateVH(final int layoutID) {
            return onCreateVH(new Func2<ViewGroup, Integer, View>() {
                @Override
                public View call(ViewGroup parent, Integer viewType) {
                    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                    return inflater.inflate(layoutID, parent, false);
                }
            });
        }

        public AdapterBuilder<T> onCreateVH(Func2<ViewGroup, Integer, View> handler) {
            this.onCreateVH = handler;
            return this;
        }

        public AdapterBuilder<T> onVHCreated(final Action4<SimpleAdapter<T>, Integer, SimpleVH, ViewBinder> handler) {
            this.onVHCreated = new Action3<SimpleAdapter<T>, Integer, SimpleVH>() {
                @Override
                public void call(SimpleAdapter<T> ad, Integer type, SimpleVH vh) {
                    handler.call(ad, type, vh, vh.binder);
                }
            };
            return this;
        }

        public AdapterBuilder<T> onBindVH(final Action4<SimpleAdapter<T>, SimpleVH, ViewBinder, Integer> handler) {
            this.onBindVH = new Action3<SimpleAdapter<T>, SimpleVH, Integer>() {
                @Override
                public void call(SimpleAdapter<T> ad, SimpleVH vh, Integer pos) {
                    handler.call(ad, vh, vh.binder, pos);
                }
            };
            return this;
        }

        public SimpleAdapter<T> build() {
            return new SimpleAdapter<T>(items) {

                @Override
                public SimpleVH onCreateViewHolder(ViewGroup parent, int viewType) {
                    if (viewType == VIEW_TYPE_HEADER || viewType == VIEW_TYPE_FOOTER) {
                        Context ctx = parent.getContext();
                        FrameLayout itemView = new FrameLayout(ctx);
                        itemView.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
                        return new SimpleVH(itemView);
                    }

                    View itemView = onCreateVH.call(parent, viewType);
                    SimpleVH vh = new SimpleVH(itemView);
                    if (onVHCreated != null) {
                        onVHCreated.call(this, viewType, vh);
                    }
                    return vh;
                }

                @Override
                public void onBindViewHolder(SimpleVH vh, int position) {
                    int viewType = getItemViewType(position);
                    if (viewType == VIEW_TYPE_HEADER || viewType == VIEW_TYPE_FOOTER) {
                        FrameLayout itemView = (FrameLayout) vh.itemView;
                        View contentView = viewType == VIEW_TYPE_HEADER ? getHeader(position) : getFooter(position);
                        View oldContentView = itemView.getChildCount() > 0 ? itemView.getChildAt(0) : null;
                        if (oldContentView == null || oldContentView != contentView) {
                            itemView.removeAllViewsInLayout();
                            itemView.addView(contentView);
                        } else {
                            itemView.requestLayout();
                        }
                    } else {
                        onBindVH.call(this, vh, position);
                    }
                }
            };
        }
    }

    public static abstract class SimpleAdapter<T> extends RecyclerView.Adapter<SimpleVH> {
        public static final int VIEW_TYPE_HEADER = 10086;
        public static final int VIEW_TYPE_FOOTER = 10087;

        protected LinkedList<View> headers = new LinkedList<>();
        protected LinkedList<View> footers = new LinkedList<>();
        protected LinkedList<T> items = new LinkedList<>();
        protected int currentHeaderCount = 0;
        protected int currentFooterCount = 0;
        protected int currentItemCount = 0;

        public SimpleAdapter(List<T> items) {
            addItems(items);
        }

        @Override
        public int getItemViewType(int position) {
            if (position < currentHeaderCount) {
                return VIEW_TYPE_HEADER;
            } else if (position >= currentHeaderCount + currentItemCount) {
                return VIEW_TYPE_FOOTER;
            }
            return super.getItemViewType(position);
        }

        @Override
        public final int getItemCount() {
            return currentHeaderCount + currentItemCount + currentFooterCount;
        }


        public T getItem(int adapterPosition) {
            int position = adapterPosition - this.currentHeaderCount;
            if (position >= 0 && position < this.currentItemCount) {
                return items.get(position);
            }
            return null;
        }

        public void addItem(T item) {
            if (item != null && !this.items.contains(item)) {
                this.items.add(item);
                computeItemCount();
            }
        }

        public void addItems(List<T> items) {
            if (items != null && !items.isEmpty()) {
                this.items.addAll(items);
                computeItemCount();
            }
        }

        public void removeItem(T item) {
            if (item != null && this.items.contains(item)) {
                this.items.remove(item);
                computeItemCount();
            }
        }

        @SuppressWarnings("UnnecessaryLocalVariable")
        public View getHeader(int adapterPosition) {
            int position = adapterPosition;
            if (position >= 0 && position < this.currentHeaderCount) {
                return this.headers.get(position);
            }

            return null;
        }

        public void addHeader(View header) {
            if (header != null && !headers.contains(header)) {
                this.headers.add(header);
                computeHeaderCount();
            }
        }

        public void removeHeader(View header) {
            if (header != null && headers.contains(header)) {
                headers.remove(header);
                computeHeaderCount();
            }
        }

        public View getFooter(int adapterPosition) {
            int position = adapterPosition - this.currentHeaderCount - this.currentItemCount;
            if (position >= 0 && position <= this.currentFooterCount) {
                return this.footers.get(position);
            }

            return null;
        }

        public void addFooter(View footer) {
            if (footer != null && !footers.contains(footer)) {
                this.footers.add(footer);
                computeFooterCount();
            }
        }

        public void removeFooter(View footer) {
            if (footer != null && footers.contains(footer)) {
                this.footers.remove(footer);
                computeFooterCount();
            }
        }

        private void computeHeaderCount() {
            this.currentHeaderCount = this.headers.size();
        }

        private void computeFooterCount() {
            this.currentFooterCount = this.footers.size();
        }

        private void computeItemCount() {
            this.currentItemCount = this.items.size();
        }
    }

    public static class SimpleVH extends RecyclerView.ViewHolder {
        private ViewBinder binder = new ViewBinder();

        public SimpleVH(View itemView) {
            super(itemView);
        }
    }
}
