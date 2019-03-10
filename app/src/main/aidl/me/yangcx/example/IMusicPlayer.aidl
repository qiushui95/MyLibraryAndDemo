// IMusicPlayer.aidl
package me.yangcx.example;

import me.yangcx.example.IPlayerCallback;
// Declare any non-default types here with import statements

interface IMusicPlayer {
void start();
void pause();
void stop();
void registerCallback(IPlayerCallback callback);
void unregisterCallback(IPlayerCallback callback);
}
