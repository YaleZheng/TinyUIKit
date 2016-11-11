package io.yale.tinyuikit.lib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.LinkedList;
import java.util.List;

import io.yale.rxfunction.lib.func.Action3;
import io.yale.rxfunction.lib.func.Action4;
import io.yale.rxfunction.lib.func.Func2;
import io.yale.rxfunction.lib.func.SafeAction1;


/**
 * Created by yalez on 2016/11/8.
 */

public class RecyclerViewExtensions {
    private RecyclerViewExtensions() {
    }

    public static class DecorationParams {
        private int fillColor = 0;
        private int height = 0;
        private int marginTop = 0;
        private int marginBottom = 0;
        private int marginStart = 0;
        private int marginEnd = 0;
        private int headerCount = 0;
        private int footerCount = 0;

        public DecorationParams setFillColor(int fillColor) {
            this.fillColor = fillColor;
            return this;
        }

        public DecorationParams setHeight(int height) {
            this.height = height;
            return this;
        }

        public DecorationParams setMargin(int margin) {
            return setMargin(margin, margin, margin, margin);
        }

        public DecorationParams setMargin(int marginH, int marginV) {
            return setMargin(marginH, marginV, marginH, marginV);
        }

        public DecorationParams setMargin(int marginStart, int marginTop, int marginEnd, int marginBottom) {
            this.marginStart = marginStart;
            this.marginTop = marginTop;
            this.marginEnd = marginEnd;
            this.marginBottom = marginBottom;
            return this;
        }

        public DecorationParams setHeaderCount(int headerCount) {
            this.headerCount = headerCount;
            return this;
        }

        public DecorationParams setFooterCount(int footerCount) {
            this.footerCount = footerCount;
            return this;
        }
    }

    public static void addDividerHorizontal(final RecyclerView recyclerView, final DecorationParams params) {
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            Rect rect = new Rect();
            Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

            @Override
            public void onDrawOver(final Canvas c, final RecyclerView rv, RecyclerView.State state) {
                super.onDrawOver(c, rv, state);
                ViewExtension.v_forEach(rv, new SafeAction1<View>() {
                    @Override
                    public void call(View child) throws Exception {
                        int adapterPosition = rv.getChildAdapterPosition(child);
                        boolean isHeader = adapterPosition < params.headerCount;
                        int itemCount = rv.getAdapter() != null ? rv.getAdapter().getItemCount() : 0;
                        boolean isFooter = adapterPosition > params.headerCount + itemCount;
                        boolean isItem = !isHeader && !isFooter;
                        boolean isLastItem = isItem && (adapterPosition == params.headerCount + itemCount);

                        if (isItem && !isLastItem) {
                            rect.set(child.getLeft() + params.marginStart,
                                    child.getBottom() + params.marginTop,
                                    child.getRight() - params.marginEnd,
                                    child.getBottom() + params.height - params.marginBottom);
                            paint.setStyle(Paint.Style.FILL);
                            paint.setColor(params.fillColor);
                            c.drawRect(rect, paint);
                        }
                    }
                });
            }
        });
    }

    public static void addItemSpacing(final RecyclerView recyclerView, final DecorationParams params) {
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int adapterPosition = parent.getChildAdapterPosition(view);
                boolean isHeader = adapterPosition < params.headerCount;
                int itemCount = parent.getAdapter() != null ? parent.getAdapter().getItemCount() : 0;
                boolean isFooter = adapterPosition > params.headerCount + itemCount;
                boolean isItem = !isHeader && !isFooter;
                if (isItem) {
                    outRect.setEmpty();
                    outRect.set(params.marginStart, params.marginTop, params.marginEnd, params.marginBottom);
                }
            }
        });
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
                    vh.binder.attachRoot(vh.itemView);
                    handler.call(ad, type, vh, vh.binder);
                    vh.binder.detachRoot();
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

        public void resetItems(List<T> items) {
            this.items.clear();
            if (items != null) {
                this.items.addAll(items);
            }
            computeItemCount();
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
