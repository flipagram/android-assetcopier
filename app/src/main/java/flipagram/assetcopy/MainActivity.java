/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package flipagram.assetcopy;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import flipagram.assetcopylib.AssetCopier;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    0);
        }

        findViewById(R.id.copyAll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            }
        });
    }
}
