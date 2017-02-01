# android-assetcopier
AssetCopier copies assets to real files then optionally uses MediaScannerConnection
to scan them for media. The end result is real files in the file system all ready for
consumption. It's especially useful for providing an app with test data (images/videos)
for UI testing.

## Binaries

First, add the following to root build.gradle at the end of repositories:

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }

Then, add this to the dependencies:

    compile 'com.github.flipagram:android-assetcopier:0.1.1'

## Build

    $ git clone git@github.com:flipagram/android-assetcopier.git
    $ cd android-assetcopier
    $ ./gradlew build

## Sample usage

A sample project which provides a runnable code examples that demonstrates
the uses of the class in this project is available in the app/ folder.

    int count = -1;
    try {
        // This will fail if the user didn't allow the permissions
        File destDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        destDir.mkdirs();
        count = new AssetCopier(MainActivity.this)
                .withFileScanning()
                .copy("tocopy", destDir);
    } catch (IOException e) {
        e.printStackTrace();
    }
    Toast.makeText(
            MainActivity.this,
            (count==-1 ? "There was an error copying" : "files copied " + count),
            Toast.LENGTH_LONG
    ).show();

## Bugs and Feedback

For bugs, feature requests, and discussion please use GitHub Issues.

## LICENSE

    Copyright 2017 Flipagram

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
