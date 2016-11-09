# TinyUIKit
provide some helper function to simple your programing on ui

##USAGE
1.provide a better way to find view
```java

    Activity act;
    Fragment frag;
    Dialog dialog;
    View parent;

    TextView labelA;
    \\  common way to find view
    labelA = (TextView)act.findViewById(R.id.label);
    labelA = (TextView)frag.getView().findViewById(R.id.label);
    labelA = (TextView)dialog.getWindow().getDecorView().findViewById(R.id.label);
    labelA = (TextView)parent.findViewById(R.id.label);

    \\ you could do it now
    labelA = v_findView(act, R.id.label);
    labelA = v_findView(frag, R.id.label);
    labelA = v_findView(dialog, R.id.label);
    labelA = v_findView(parent, R.id.label);
```
