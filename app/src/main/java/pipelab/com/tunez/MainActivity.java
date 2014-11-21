package pipelab.com.tunez;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import fm.feed.android.playersdk.Player;
import fm.feed.android.playersdk.PlayerError;
import fm.feed.android.playersdk.service.PlayInfo;


public class MainActivity extends Activity {

    private final static String TAG = MainActivity.class.getSimpleName();

    private Player player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button play = (Button) findViewById(R.id.playButton);
        Button pause = (Button) findViewById(R.id.pauseButton);
        Button skip = (Button) findViewById(R.id.skipButton);

        play.setOnClickListener(onClickPlay);
        pause.setOnClickListener(onClickPause);
        skip.setOnClickListener(onClickSkip);

        player = Player.getInstance(getApplicationContext(), playerListener, "fb9bade0c35af8494faa65d865595f2b8f67f00d", "41820ea45f18efceaeabbbfc2fa796468beeb00d");
    }

    private View.OnClickListener onClickPlay = new View.OnClickListener() {
        public void onClick(View v) {
            Log.d(TAG, "You clicked the play button");
            player.play();
        }
    };

    private View.OnClickListener onClickPause = new View.OnClickListener() {
        public void onClick(View v) {
            Log.d(TAG, "You clicked the pause button");
            player.pause();
        }
    };

    private View.OnClickListener onClickSkip = new View.OnClickListener() {
        public void onClick(View v) {
            Log.d(TAG, "You clicked the skip button");
            player.skip();
        }
    };

    private Player.PlayerListener playerListener = new Player.PlayerListener() {

        @Override
        public void onPlayerInitialized(PlayInfo playInfo) {
            Log.d(TAG, "player has been initialized");
        }

        @Override
        public Player.NotificationBuilder getNotificationBuilder() {
            return null;
        }

        @Override
        public void onPlaybackStateChanged(PlayInfo.State state) {
            Log.d(TAG, "playback state changed");
        }

        @Override
        public void onSkipStatusChange(boolean b) {
            Log.d(TAG, "skip status changed");
        }

        @Override
        public void onError(PlayerError playerError) {
            Log.d(TAG, "error!");
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
