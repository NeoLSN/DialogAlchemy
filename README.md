DialogAlchemy
========

This is a dialog utility library. It provides a easy way to let developers deal with screen rotation issue.

Installation
--------
```gradle
repositories {
    ...
    maven { url "https://jitpack.io" }
}
dependencies {
    ...
    compile 'com.github.NeoLSN:DialogAlchemy:1.0.0'
}
```
API
--------
- Alchemist - Dialog fragment
- Material - Basic dialog model
- PhilosopherStone - A interface for custom view
- TransmutationCircle - A interface for dialog creation factory
- DialogAlchemy - A utility class to show a dialog

Usage
--------
##### Basic Usage
```Java
    Material material = new Material.Builder(getActivity())
        .setTitle("Dialog Title")
        .setMessage("Dialog message")
        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ... do something ...
            }
        })
        .setNegativeButton(android.R.string.cancel, null)
        .build();

    Alchemist alchemist = DialogAlchemy.show(getFragmentManager(), material);
```

##### Advanced Usage
```Java
    //Create a custom view
    PhilosopherStone stone = new EditTextStone.Builder()
        .setText(...)
        .setOnTextAcceptedListener(listener)
        .build();

    Material material = new Material.Builder(getActivity())
        .setTitle("Dialog Title")
        .setMessage("Dialog message")
        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ... do something ...
            }
        })
        .setNegativeButton(android.R.string.cancel, null)
        .setPhilosopherStone(stone) // incre
        .build();

    Alchemist alchemist = DialogAlchemy.show(getFragmentManager(), material);
```

#### Reset listener or callback

When ```DialogAlchemy.show()``` called, it will set a tag to ```Alchemist``` automatically. You can use this tag to find this fragment after screen rotated.

##### For Activity
```Java
    private Alchemist alchemist;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ...
        if (savedInstanceState != null) {
            String tag = savedInstanceState.getString(TAG_KEY);
            alchemist = (Alchemist) getSupportFragmentManager().findFragmentByTag(tag);
            Material material = alchemist.getMaterial();
            Material.Builder builder = material.rebuild(this);

            // reset listner or callback in here

            Material newMaterial = builder.build();
            alchemist.setMaterial(newMaterial);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(TAG_KEY, alchemist.getTag());

        super.onSaveInstanceState(outState);
    }
```
##### For fragment
```Java
    private Alchemist alchemist;

    public void onViewCreated(View view, Bundle savedInstanceState) {
        ...
        if (savedInstanceState != null) {
            String tag = savedInstanceState.getString(TAG_KEY);
            alchemist = (Alchemist) getFragmentManager().findFragmentByTag(tag);
            Material material = alchemist.getMaterial();
            Material.Builder builder = material.rebuild(this);

            // reset listner or callback in here

            Material newMaterial = builder.build();
            alchemist.setMaterial(newMaterial);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(TAG_KEY, alchemist.getTag());

        super.onSaveInstanceState(outState);
    }
```