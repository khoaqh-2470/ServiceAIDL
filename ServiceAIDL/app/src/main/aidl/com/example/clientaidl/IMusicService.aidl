// IMusicService.aidl
package com.example.clientaidl;

// Declare any non-default types here with import statements

interface IMusicService {
    String getSongName();

    void changeMediaStatus();

    void playSong();

    void play();

    void pause();

    int getCurrentDuration();

    int getTotalDuration();
}