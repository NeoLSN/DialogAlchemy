DialogAlchemy
========

[![Release](https://jitpack.io/v/NeoLSN/DialogAlchemy.svg?style=flat)](https://jitpack.io/#NeoLSN/DialogAlchemy)

This is a dialog utility library. It provides a easy way to let developers deal with screen rotation issue.

<p align="center">
  <img src="https://github.com/NeoLSN/DialogAlchemy/blob/master/arts/device_portrait.png" height="300" alt="Portrait image" />
  <img src="https://github.com/NeoLSN/DialogAlchemy/blob/master/arts/rotate.png" width="50" alt="Rotate screen" />
  <img src="https://github.com/NeoLSN/DialogAlchemy/blob/master/arts/device_landscape.png" width="300" alt="Landscape image" />
</p>

Installation
--------
```gradle
repositories {
    ...
    maven { url "https://jitpack.io" }
}
dependencies {
    ...
    compile 'com.github.NeoLSN:DialogAlchemy:1.1.1'
}
```
API
--------
- Alchemist - Dialog fragment
- Material - Basic dialog model for most of Android Dialog library
- PhilosopherStone - There are two purpose for Philosopher Stone
  1. A interface for custom view (main purpose)
  2. Expand the Dialog library ability that Material didn't support
- TransmutationCircle - A interface for dialog creation factory. There are 2 notices.
  1. Should satisfy Material model requirement
  2. At least process PhilosopherStone as a custom view
- DialogAlchemy - A utility class to show a dialog

Usage
--------
#### Basic Usage
```Java
    Material material = new Material.Builder(getActivity())
        .setTitle("Dialog Title")
        .setMessage("Dialog message")
        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ... Do something ...
            }
        })
        .setNegativeButton(android.R.string.cancel, null)
        .build();

    Alchemist alchemist = DialogAlchemy.show(getFragmentManager(), material);
```

#### Advanced Usage
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
                ... Do something ...
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

            // reset listener or callback in here

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

            // reset listener or callback in here

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
#### Set default TransmutationCircle
```Java
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ...
        DialogAlchemy.setDefaultCircle(new MetalTransmutationCircle());
    }
}
```

License
--------

    Copyright (C) 2016 Jason Yang
    Copyright (C) 2007 The Android Open Source Project

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
