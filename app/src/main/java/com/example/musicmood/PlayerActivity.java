package com.example.musicmood;

import static android.os.Build.VERSION_CODES.S;

import android.media.AudioDeviceInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.musicmood.databinding.ActivityPlayerBinding;
import com.google.android.exoplayer2.DeviceInfo;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.MediaMetadata;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.PlayerMessage;
import com.google.android.exoplayer2.Renderer;
import com.google.android.exoplayer2.SeekParameters;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Tracks;
import com.google.android.exoplayer2.analytics.AnalyticsCollector;
import com.google.android.exoplayer2.analytics.AnalyticsListener;
import com.google.android.exoplayer2.audio.AudioAttributes;
import com.google.android.exoplayer2.audio.AuxEffectInfo;
import com.google.android.exoplayer2.decoder.DecoderCounters;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ShuffleOrder;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.text.CueGroup;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionParameters;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.util.Clock;
import com.google.android.exoplayer2.util.Effect;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.util.Size;
import com.google.android.exoplayer2.video.VideoFrameMetadataListener;
import com.google.android.exoplayer2.video.VideoSize;
import com.google.android.exoplayer2.video.spherical.CameraMotionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PlayerActivity extends AppCompatActivity {
    private ActivityPlayerBinding binding;
    private ExoPlayer player;
    private Handler handler=new Handler();
    private List<Song> songList=new ArrayList<>();
    private List<Song> shuffledList=new ArrayList<>();
    private int currentIndex=0;
    private boolean isShuffle=false;
    private boolean isRepeat=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       songList = getIntent().getParcelableArrayListExtra("songList");
       currentIndex=getIntent().getIntExtra("position",0);
       if(songList== null || songList.isEmpty()){
           Toast.makeText(this, "no songs found", Toast.LENGTH_SHORT).show();
            finish();
            return;
       }
       shuffledList=new ArrayList<>(songList);
       binding.waveformSeekBar.setWaveform(createWaveForm(),true);
    }

private void initPlayerWithSong(int index){
        Song song=isShuffle?shuffledList.get(index):songList.get(index);

        if (player!=null) player.release();
        player=new ExoPlayer() {
            @Nullable
            @Override
            public ExoPlaybackException getPlayerError() {
                return null;
            }

            @Nullable
            @Override
            public AudioComponent getAudioComponent() {
                return null;
            }

            @Nullable
            @Override
            public VideoComponent getVideoComponent() {
                return null;
            }

            @Nullable
            @Override
            public TextComponent getTextComponent() {
                return null;
            }

            @Nullable
            @Override
            public DeviceComponent getDeviceComponent() {
                return null;
            }

            @Override
            public void addAudioOffloadListener(AudioOffloadListener listener) {

            }

            @Override
            public void removeAudioOffloadListener(AudioOffloadListener listener) {

            }

            @Override
            public AnalyticsCollector getAnalyticsCollector() {
                return null;
            }

            @Override
            public void addAnalyticsListener(AnalyticsListener listener) {

            }

            @Override
            public void removeAnalyticsListener(AnalyticsListener listener) {

            }

            @Override
            public int getRendererCount() {
                return 0;
            }

            @Override
            public int getRendererType(int index) {
                return 0;
            }

            @Override
            public Renderer getRenderer(int index) {
                return null;
            }

            @Nullable
            @Override
            public TrackSelector getTrackSelector() {
                return null;
            }

            @Override
            public TrackGroupArray getCurrentTrackGroups() {
                return null;
            }

            @Override
            public TrackSelectionArray getCurrentTrackSelections() {
                return null;
            }

            @Override
            public Looper getPlaybackLooper() {
                return null;
            }

            @Override
            public Clock getClock() {
                return null;
            }

            @Override
            public void prepare(MediaSource mediaSource) {

            }

            @Override
            public void prepare(MediaSource mediaSource, boolean resetPosition, boolean resetState) {

            }

            @Override
            public void setMediaSources(List<MediaSource> mediaSources) {

            }

            @Override
            public void setMediaSources(List<MediaSource> mediaSources, boolean resetPosition) {

            }

            @Override
            public void setMediaSources(List<MediaSource> mediaSources, int startMediaItemIndex, long startPositionMs) {

            }

            @Override
            public void setMediaSource(MediaSource mediaSource) {

            }

            @Override
            public void setMediaSource(MediaSource mediaSource, long startPositionMs) {

            }

            @Override
            public void setMediaSource(MediaSource mediaSource, boolean resetPosition) {

            }

            @Override
            public void addMediaSource(MediaSource mediaSource) {

            }

            @Override
            public void addMediaSource(int index, MediaSource mediaSource) {

            }

            @Override
            public void addMediaSources(List<MediaSource> mediaSources) {

            }

            @Override
            public void addMediaSources(int index, List<MediaSource> mediaSources) {

            }

            @Override
            public void setShuffleOrder(ShuffleOrder shuffleOrder) {

            }

            @Override
            public void setAudioAttributes(AudioAttributes audioAttributes, boolean handleAudioFocus) {

            }

            @Override
            public void setAudioSessionId(int audioSessionId) {

            }

            @Override
            public int getAudioSessionId() {
                return 0;
            }

            @Override
            public void setAuxEffectInfo(AuxEffectInfo auxEffectInfo) {

            }

            @Override
            public void clearAuxEffectInfo() {

            }

            @Override
            public void setPreferredAudioDevice(@Nullable AudioDeviceInfo audioDeviceInfo) {

            }

            @Override
            public void setSkipSilenceEnabled(boolean skipSilenceEnabled) {

            }

            @Override
            public boolean getSkipSilenceEnabled() {
                return false;
            }

            @Override
            public void setVideoEffects(List<Effect> videoEffects) {

            }

            @Override
            public void setVideoScalingMode(int videoScalingMode) {

            }

            @Override
            public int getVideoScalingMode() {
                return 0;
            }

            @Override
            public void setVideoChangeFrameRateStrategy(int videoChangeFrameRateStrategy) {

            }

            @Override
            public int getVideoChangeFrameRateStrategy() {
                return 0;
            }

            @Override
            public void setVideoFrameMetadataListener(VideoFrameMetadataListener listener) {

            }

            @Override
            public void clearVideoFrameMetadataListener(VideoFrameMetadataListener listener) {

            }

            @Override
            public void setCameraMotionListener(CameraMotionListener listener) {

            }

            @Override
            public void clearCameraMotionListener(CameraMotionListener listener) {

            }

            @Override
            public PlayerMessage createMessage(PlayerMessage.Target target) {
                return null;
            }

            @Override
            public void setSeekParameters(@Nullable SeekParameters seekParameters) {

            }

            @Override
            public SeekParameters getSeekParameters() {
                return null;
            }

            @Override
            public void setForegroundMode(boolean foregroundMode) {

            }

            @Override
            public void setPauseAtEndOfMediaItems(boolean pauseAtEndOfMediaItems) {

            }

            @Override
            public boolean getPauseAtEndOfMediaItems() {
                return false;
            }

            @Nullable
            @Override
            public Format getAudioFormat() {
                return null;
            }

            @Nullable
            @Override
            public Format getVideoFormat() {
                return null;
            }

            @Nullable
            @Override
            public DecoderCounters getAudioDecoderCounters() {
                return null;
            }

            @Nullable
            @Override
            public DecoderCounters getVideoDecoderCounters() {
                return null;
            }

            @Override
            public void setHandleAudioBecomingNoisy(boolean handleAudioBecomingNoisy) {

            }

            @Override
            public void setWakeMode(int wakeMode) {

            }

            @Override
            public void setPriorityTaskManager(@Nullable PriorityTaskManager priorityTaskManager) {

            }

            @Override
            public void experimentalSetOffloadSchedulingEnabled(boolean offloadSchedulingEnabled) {

            }

            @Override
            public boolean experimentalIsSleepingForOffload() {
                return false;
            }

            @Override
            public boolean isTunnelingEnabled() {
                return false;
            }

            @Override
            public Looper getApplicationLooper() {
                return null;
            }

            @Override
            public void addListener(Listener listener) {

            }

            @Override
            public void removeListener(Listener listener) {

            }

            @Override
            public void setMediaItems(List<MediaItem> mediaItems) {

            }

            @Override
            public void setMediaItems(List<MediaItem> mediaItems, boolean resetPosition) {

            }

            @Override
            public void setMediaItems(List<MediaItem> mediaItems, int startIndex, long startPositionMs) {

            }

            @Override
            public void setMediaItem(MediaItem mediaItem) {

            }

            @Override
            public void setMediaItem(MediaItem mediaItem, long startPositionMs) {

            }

            @Override
            public void setMediaItem(MediaItem mediaItem, boolean resetPosition) {

            }

            @Override
            public void addMediaItem(MediaItem mediaItem) {

            }

            @Override
            public void addMediaItem(int index, MediaItem mediaItem) {

            }

            @Override
            public void addMediaItems(List<MediaItem> mediaItems) {

            }

            @Override
            public void addMediaItems(int index, List<MediaItem> mediaItems) {

            }

            @Override
            public void moveMediaItem(int currentIndex, int newIndex) {

            }

            @Override
            public void moveMediaItems(int fromIndex, int toIndex, int newIndex) {

            }

            @Override
            public void replaceMediaItem(int index, MediaItem mediaItem) {

            }

            @Override
            public void replaceMediaItems(int fromIndex, int toIndex, List<MediaItem> mediaItems) {

            }

            @Override
            public void removeMediaItem(int index) {

            }

            @Override
            public void removeMediaItems(int fromIndex, int toIndex) {

            }

            @Override
            public void clearMediaItems() {

            }

            @Override
            public boolean isCommandAvailable(int command) {
                return false;
            }

            @Override
            public boolean canAdvertiseSession() {
                return false;
            }

            @Override
            public Commands getAvailableCommands() {
                return null;
            }

            @Override
            public void prepare() {

            }

            @Override
            public int getPlaybackState() {
                return 0;
            }

            @Override
            public int getPlaybackSuppressionReason() {
                return 0;
            }

            @Override
            public boolean isPlaying() {
                return false;
            }

            @Override
            public void play() {

            }

            @Override
            public void pause() {

            }

            @Override
            public void setPlayWhenReady(boolean playWhenReady) {

            }

            @Override
            public boolean getPlayWhenReady() {
                return false;
            }

            @Override
            public void setRepeatMode(int repeatMode) {

            }

            @Override
            public int getRepeatMode() {
                return 0;
            }

            @Override
            public void setShuffleModeEnabled(boolean shuffleModeEnabled) {

            }

            @Override
            public boolean getShuffleModeEnabled() {
                return false;
            }

            @Override
            public boolean isLoading() {
                return false;
            }

            @Override
            public void seekToDefaultPosition() {

            }

            @Override
            public void seekToDefaultPosition(int mediaItemIndex) {

            }

            @Override
            public void seekTo(long positionMs) {

            }

            @Override
            public void seekTo(int mediaItemIndex, long positionMs) {

            }

            @Override
            public long getSeekBackIncrement() {
                return 0;
            }

            @Override
            public void seekBack() {

            }

            @Override
            public long getSeekForwardIncrement() {
                return 0;
            }

            @Override
            public void seekForward() {

            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public boolean hasPreviousWindow() {
                return false;
            }

            @Override
            public boolean hasPreviousMediaItem() {
                return false;
            }

            @Override
            public void previous() {

            }

            @Override
            public void seekToPreviousWindow() {

            }

            @Override
            public void seekToPreviousMediaItem() {

            }

            @Override
            public long getMaxSeekToPreviousPosition() {
                return 0;
            }

            @Override
            public void seekToPrevious() {

            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasNextWindow() {
                return false;
            }

            @Override
            public boolean hasNextMediaItem() {
                return false;
            }

            @Override
            public void next() {

            }

            @Override
            public void seekToNextWindow() {

            }

            @Override
            public void seekToNextMediaItem() {

            }

            @Override
            public void seekToNext() {

            }

            @Override
            public void setPlaybackParameters(PlaybackParameters playbackParameters) {

            }

            @Override
            public void setPlaybackSpeed(float speed) {

            }

            @Override
            public PlaybackParameters getPlaybackParameters() {
                return null;
            }

            @Override
            public void stop() {

            }

            @Override
            public void release() {

            }

            @Override
            public Tracks getCurrentTracks() {
                return null;
            }

            @Override
            public TrackSelectionParameters getTrackSelectionParameters() {
                return null;
            }

            @Override
            public void setTrackSelectionParameters(TrackSelectionParameters parameters) {

            }

            @Override
            public MediaMetadata getMediaMetadata() {
                return null;
            }

            @Override
            public MediaMetadata getPlaylistMetadata() {
                return null;
            }

            @Override
            public void setPlaylistMetadata(MediaMetadata mediaMetadata) {

            }

            @Nullable
            @Override
            public Object getCurrentManifest() {
                return null;
            }

            @Override
            public Timeline getCurrentTimeline() {
                return null;
            }

            @Override
            public int getCurrentPeriodIndex() {
                return 0;
            }

            @Override
            public int getCurrentWindowIndex() {
                return 0;
            }

            @Override
            public int getCurrentMediaItemIndex() {
                return 0;
            }

            @Override
            public int getNextWindowIndex() {
                return 0;
            }

            @Override
            public int getNextMediaItemIndex() {
                return 0;
            }

            @Override
            public int getPreviousWindowIndex() {
                return 0;
            }

            @Override
            public int getPreviousMediaItemIndex() {
                return 0;
            }

            @Nullable
            @Override
            public MediaItem getCurrentMediaItem() {
                return null;
            }

            @Override
            public int getMediaItemCount() {
                return 0;
            }

            @Override
            public MediaItem getMediaItemAt(int index) {
                return null;
            }

            @Override
            public long getDuration() {
                return 0;
            }

            @Override
            public long getCurrentPosition() {
                return 0;
            }

            @Override
            public long getBufferedPosition() {
                return 0;
            }

            @Override
            public int getBufferedPercentage() {
                return 0;
            }

            @Override
            public long getTotalBufferedDuration() {
                return 0;
            }

            @Override
            public boolean isCurrentWindowDynamic() {
                return false;
            }

            @Override
            public boolean isCurrentMediaItemDynamic() {
                return false;
            }

            @Override
            public boolean isCurrentWindowLive() {
                return false;
            }

            @Override
            public boolean isCurrentMediaItemLive() {
                return false;
            }

            @Override
            public long getCurrentLiveOffset() {
                return 0;
            }

            @Override
            public boolean isCurrentWindowSeekable() {
                return false;
            }

            @Override
            public boolean isCurrentMediaItemSeekable() {
                return false;
            }

            @Override
            public boolean isPlayingAd() {
                return false;
            }

            @Override
            public int getCurrentAdGroupIndex() {
                return 0;
            }

            @Override
            public int getCurrentAdIndexInAdGroup() {
                return 0;
            }

            @Override
            public long getContentDuration() {
                return 0;
            }

            @Override
            public long getContentPosition() {
                return 0;
            }

            @Override
            public long getContentBufferedPosition() {
                return 0;
            }

            @Override
            public AudioAttributes getAudioAttributes() {
                return null;
            }

            @Override
            public void setVolume(float volume) {

            }

            @Override
            public float getVolume() {
                return 0;
            }

            @Override
            public void clearVideoSurface() {

            }

            @Override
            public void clearVideoSurface(@Nullable Surface surface) {

            }

            @Override
            public void setVideoSurface(@Nullable Surface surface) {

            }

            @Override
            public void setVideoSurfaceHolder(@Nullable SurfaceHolder surfaceHolder) {

            }

            @Override
            public void clearVideoSurfaceHolder(@Nullable SurfaceHolder surfaceHolder) {

            }

            @Override
            public void setVideoSurfaceView(@Nullable SurfaceView surfaceView) {

            }

            @Override
            public void clearVideoSurfaceView(@Nullable SurfaceView surfaceView) {

            }

            @Override
            public void setVideoTextureView(@Nullable TextureView textureView) {

            }

            @Override
            public void clearVideoTextureView(@Nullable TextureView textureView) {

            }

            @Override
            public VideoSize getVideoSize() {
                return null;
            }

            @Override
            public Size getSurfaceSize() {
                return null;
            }

            @Override
            public CueGroup getCurrentCues() {
                return null;
            }

            @Override
            public DeviceInfo getDeviceInfo() {
                return null;
            }

            @Override
            public int getDeviceVolume() {
                return 0;
            }

            @Override
            public boolean isDeviceMuted() {
                return false;
            }

            @Override
            public void setDeviceVolume(int volume) {

            }

            @Override
            public void setDeviceVolume(int volume, int flags) {

            }

            @Override
            public void increaseDeviceVolume() {

            }

            @Override
            public void increaseDeviceVolume(int flags) {

            }

            @Override
            public void decreaseDeviceVolume() {

            }

            @Override
            public void decreaseDeviceVolume(int flags) {

            }

            @Override
            public void setDeviceMuted(boolean muted) {

            }

            @Override
            public void setDeviceMuted(boolean muted, int flags) {

            }
        }
}
    private int[] createWaveForm(){
        Random random=new Random(System.currentTimeMillis());
        int[] values=new int[50];
        for (int i=0;i<values.length;i++){
            values[i]=5+random.nextInt(50);
        }
        return values
    }
}