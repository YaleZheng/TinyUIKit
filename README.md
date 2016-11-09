# TinyUIKit
provide some helper function to simple your programing on ui

##USAGE

1.provide a simple way to find view
```java

    Activity act;
    Fragment frag;
    Dialog dialog;
    View parent;

    TextView labelA;
    \\  common way
    labelA = (TextView)act.findViewById(R.id.label);
    labelA = (TextView)frag.getView().findViewById(R.id.label);
    labelA = (TextView)dialog.getWindow().getDecorView().findViewById(R.id.label);
    labelA = (TextView)parent.findViewById(R.id.label);

    \\ now
    labelA = v_findView(act, R.id.label);
    labelA = v_findView(frag, R.id.label);
    labelA = v_findView(dialog, R.id.label);
    labelA = v_findView(parent, R.id.label);
```

2.provide a simple way to update view
 ```java
    TextView nameLabel;
    ViewGroup group;
    \\ common way 
    nameLabel = act.findViewById(R.id.label);
    nameLabel.setText("Hello");
    
    group = (ViewGroup)act.findViewById(R.id.group);
    int childCount = group.getChildCount();
    for(int = 0 ;i < childCount; i++) {
        View child = group.getChildAt(i);
        // operate on child...
    }
    
    \\ now
    v_setText(act, R.id.label, "hello");
    v_forEach(act, R.id.group, (child) -> {
        // operate on child...
    };
 ```
 
 3.provide a simple way to create recyclerView adapter
 ```java
    \\ common way
    
    \\ define class which extend from RecyclerView.VH
    public static class MyVH extends RecyclerView.VH {
        ...
    }
    \\ define class which extend from RecyclerView.Adapter
    public static class MyAdapter extends RecyclerView.Adapter<MyVH> {
        ...
    }
    
    RecyclerView rv;
    rv.setAdapter(new MyAdapter());
    
    \\ now
    SimpleAdapter<T> adapter = RecyclerViewExtension.newAdapter();
    rv.setAdapter(RecyclerViewExtension.newAdapter()
        .oncreateVH((adapter, parent,viewType) -> {
            ...
        })
        .onVHCreated((adapter,vh,binder) -> {
            ...
        })
        .onBindVH((adapter,vh,binder) -> {
            ...
        }
        .build())
 ```
